/**
 * TracerEnvironment.java - the environment which can trace a scene
 */
package edu.bu.cs.cs480.main;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.FloatColor;
import edu.bu.cs.cs480.Material;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.lights.AmbientLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.surfaces.Intercept;
import edu.bu.cs.cs480.surfaces.SurfaceObject;
import edu.bu.cs.cs480.vectors.Ray;
import edu.bu.cs.cs480.vectors.Vector3D;

/**
 * An environment which aggregates all the objects necessary to trace a scene.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TracerEnvironment {
  /** The color of the background for rendered images. */
  public static final Vector3D BACKGROUND_COLOR = new Vector3D(0x10 / 255.0,
      0x10 / 255.0, 0x10 / 255.0);
  /** The logger for this class. */
  private static final Logger LOG = Logger.getLogger(TracerEnvironment.class);
  /** The maximum value by which to bound colors in the rendered scene. */
  public static final Vector3D MAX_COLOR = new Vector3D(1, 1, 1);
  /** The maximum depth in the ray tree. */
  public static final int MAX_DEPTH = 3;
  /** The tolerance for floating point value comparison to zero. */
  public static final double TOLERANCE = Double.MIN_VALUE;
  /**
   * HACK: The tolerance for comparing floating point values to zero when
   * computing the shadows.
   */
  public static final double SHADOW_TOLERANCE = 1e-13;

  /**
   * Return the component-wise minimum of the specified color and the maximum
   * color as specified in {@link #MAX_COLOR}.
   * 
   * @param color
   *          The color to bound.
   * @return The component-wise minimum of the specified color and the maximum
   *         color as specified in {@link #MAX_COLOR}.
   */
  private static Vector3D boundedColor(final Vector3D color) {
    return new Vector3D(Math.min(MAX_COLOR.x(), color.x()), Math.min(
        MAX_COLOR.y(), color.y()), Math.min(MAX_COLOR.z(), color.z()));
  }

  /**
   * Returns the reflection of the specified ray through the specified normal.
   * 
   * @param ray
   *          The ray to reflect.
   * @param normal
   *          A unit vector originating at the same point as the ray.
   * @return The reflection of the specified ray through the specified normal.
   */
  private static Vector3D reflected(final Vector3D ray, final Vector3D normal) {
    return normal.scaledBy(normal.dotProduct(ray) * 2).difference(ray);
  }

  /** The list of all lights in the scene which are ambient lights. */
  private List<AmbientLight> ambientLights = new ArrayList<AmbientLight>();
  /** The virtual camera through which the scene is viewed. */
  private Camera camera = null;
  /** The list of light sources for the scene. */
  private List<Light> lights = new ArrayList<Light>();
  /** The resolution of the scene when displayed in the viewport. */
  private Resolution resolution = null;
  /** The list of surface objects to be rendered. */
  private List<SurfaceObject> surfaceObjects = new ArrayList<SurfaceObject>();
  /** The dimensions of the viewport in which the scene is displayed. */
  private Viewport viewport = null;

  /**
   * Adds the specified ambient light to the scene.
   * 
   * The light must also be added to the list of all lights, by calling the
   * {@link #addLight(Light)} method.
   * 
   * @param light
   *          The ambient light to add.
   */
  public void addAmbientLight(final AmbientLight light) {
    this.ambientLights.add(light);
  }

  /**
   * Adds the specified light to the scene.
   * 
   * @param light
   *          The light to add.
   */
  public void addLight(final Light light) {
    this.lights.add(light);
  }

  /**
   * Adds the specified surface object to the scene.
   * 
   * @param surfaceObject
   *          The surface object to add.
   */
  public void addSurfaceObject(final SurfaceObject surfaceObject) {
    this.surfaceObjects.add(surfaceObject);
  }

  /**
   * Returns the energy of the ambient lights in the scene scaled by the
   * coefficient of ambient reflection due to the specified material.
   * 
   * @param intercept
   *          The intercept at which to compute the reflection due to ambient
   *          lights.
   * @return The ambient light energy on the specified material.
   */
  private Vector3D ambientColor(final Intercept intercept) {
    Vector3D reflection = Vector3D.ORIGIN;
    for (final AmbientLight light : this.ambientLights) {
      reflection = reflection.sumWith(light.color().toVector());
    }
    final Material material = intercept.surfaceObject().material();
    return reflection.scaledBy(material.ambientReflection());
  }

  /**
   * Generates the ray which would start at pixel location (row, column) in the
   * viewport based on the resolution, the viewport size, and the camera's
   * measurements.
   * 
   * Algorithm adapted from source code of Zheng Wu.
   * 
   * @param i
   *          The horizontal pixel location in the viewport at which the ray
   *          originates.
   * @param j
   *          The vertical pixel location in the viewport at which the ray
   *          originates.
   * @return The ray which would start at pixel location (row, column) in the
   *         viewport.
   */
  protected Ray generateRay(final int row, final int column) {
    // compute location of pixel on view plane
    final double du = (column - (this.viewport.width() / 2.0) + 1)
        * this.resolution.xResolution();
    final double dv = -(row - (this.viewport.height() / 2.0) + 1)
        * this.resolution.yResolution();

    // get the vectors which define the camera's basis
    final Vector3D c = this.camera.position();
    final Vector3D n = this.camera.direction();
    final Vector3D v = this.camera.up();
    final Vector3D u = v.crossProduct(n);

    // convert (du, dv) to location in scene coordinates using the camera's
    // basis vectors
    final Vector3D temp1 = n.scaledBy(this.camera.focalLength());
    final Vector3D temp2 = u.scaledBy(du);
    final Vector3D temp3 = v.scaledBy(dv);
    final Vector3D origin = c.sumWith(temp1).sumWith(temp2).sumWith(temp3);

    // compute the direction of the ray with respect to the camera and position
    final Vector3D direction = this.camera.rayDirection(origin);

    final Ray result = new Ray();
    result.setPosition(origin);
    result.setDirection(direction);

    return result;
  }

  /**
   * Returns a number between 0 and 1 inclusive representing how much the
   * origin of the specified ray is in shadow (0 means not at all in shadow and
   * 1 means entirely in shadow).
   * 
   * @param ray
   *          The ray to check for intercepts with surface objects.
   * @param light
   * @return A number between 0 and 1 inclusive representing how much the
   *         origin of the specified ray is in shadow (0 means not at all in
   *         shadow and 1 means entirely in shadow).
   */
  // TODO for now, this only returns 0 or 1, no intermediate values
  private double shadowAmount(final Ray ray, final Light light) {
    if (!light.castsShadow()) {
      return 0;
    }
    for (final SurfaceObject surfaceObject : this.surfaceObjects) {
      final Intercept i = surfaceObject.interceptWith(ray);
      if (i != null && i.time() > SHADOW_TOLERANCE) {
        // TODO should be doing result += this.shadowAmount(ray, surfaceObject)
        return 1;
      }
    }
    return 0;
  }

  /**
   * Renders the scene and returns the resulting image.
   * 
   * @return The image which is the result of rendering the scene.
   */
  public RenderedImage render() {
    // get the width and height of the viewport
    final int width = this.viewport.width();
    final int height = this.viewport.height();
    final int halfHeight = height / 2;

    // first create the rays and initialize them with the appropriate computed
    // origin and direction based on the camera type and measurements
    LOG.debug("Generating primary rays...");
    final Ray[] rays = new Ray[width * height];
    for (int y = 0; y < height; ++y) {
      for (int x = 0; x < width; ++x) {
        rays[y * width + x] = generateRay(y, x);
      }
    }

    // compile all the surface objects so that we only compute their quadratic
    // form matrices one time
    LOG.debug("Compiling quadric form matrices...");
    for (final SurfaceObject surfaceObject : this.surfaceObjects) {
      surfaceObject.compile();
    }

    // draw the intercept on an image
    LOG.debug("Casting primary rays and shading...");
    final BufferedImage result = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    // for (int y = 0; y < height; ++y) {
    // for (int x = 0; x < width; ++x) {
    // final Ray ray = rays[y * width + x];
    // final int color = TracerEnvironment.this.trace(intercepts.get(ray), 1);
    // result.setRGB(x, y, color);
    // }
    // }

    // mark the threads as not done
    this.done1 = false;
    this.done2 = false;

    // create the two runnables which will render the top half and the bottom
    // half of the result image separately
    final Renderer r1 = new Renderer(rays, 0, halfHeight, width, this, result,
        1);
    final Renderer r2 = new Renderer(rays, halfHeight, height, width, this,
        result, 2);

    // run the two threads
    new Thread(r1).start();
    new Thread(r2).start();

    this.waitForThreads();

    return result;
  }

  /** Waits for the rendering threads to complete. */
  private synchronized void waitForThreads() {
    while (!this.renderersFinished()) {
      try {
        this.wait();
      } catch (final InterruptedException exception) {
        LOG.error(exception);
      }
    }
  }

  /**
   * Marks the rendering thread with the specified ID completed.
   * 
   * @param threadID
   *          The ID of the thread to be marked completed.
   */
  void rendererFinished(final int threadID) {
    if (threadID == 1) {
      this.done1 = true;
    } else if (threadID == 2) {
      this.done2 = true;
    } else {
      LOG.error("Did not understand thread ID " + threadID);
    }
  }

  /**
   * Returns {@code true} if and only if both threads have finished rendering
   * their respective halves of the image.
   * 
   * @return {@code true} if and only if both threads have rendered their
   *         halves of the image.
   */
  private boolean renderersFinished() {
    return this.done1 && this.done2;
  }

  /** Whether the first thread is done. */
  private boolean done1 = false;
  /** Whether the second thread is done. */
  private boolean done2 = false;

  /**
   * Sets the virtual camera through which the scene is viewed.
   * 
   * @param camera
   *          The virtual camera through which the scene is viewed.
   */
  public void setCamera(final Camera camera) {
    this.camera = camera;
  }

  /**
   * Sets the resolution of the scene when displayed in the viewport.
   * 
   * @param resolution
   *          The resolution of the scene when displayed in the viewport.
   */
  public void setResolution(final Resolution resolution) {
    this.resolution = resolution;
  }

  /**
   * Sets the dimensions of the viewport in which the scene is displayed.
   * 
   * @param viewport
   *          The dimensions of the viewport in which the scene is displayed.
   */
  public void setViewport(final Viewport viewport) {
    this.viewport = viewport;
  }

  private Vector3D shade(final Intercept intercept, final int depth) {
    Vector3D result = Vector3D.ORIGIN;

    // get the material of the object at this intercept
    final Material material = intercept.surfaceObject().material();

    // get the ambient color at the point of intersection
    final Vector3D ambientColor = this.ambientColor(intercept);

    // iterate over each light in the scene to determine its contribution to
    // the total illumination at the point of intersection
    for (final Light light : this.lights) {
      // get the vector from the point of intersection to the light source
      final Vector3D pointOfIntersection = intercept.pointOfIntersection();
      final Vector3D pointToLight = light.position().difference(
          pointOfIntersection);
      final Vector3D unitPointToLight = pointToLight.normalized();

      // create the ray from the point of intersection to the current light
      final Ray shadowRay = new Ray();
      shadowRay.setPosition(pointOfIntersection);
      shadowRay.setDirection(unitPointToLight);

      // get the illumination due to diffuse reflection and specular reflection
      // at the point with respect to the current light
      final Vector3D diffuseColor = this.diffuseColor(intercept, light);
      final Vector3D specularColor = this.specularColor(intercept, light);

      // compute the angular and radial attenuation for this light
      final double cosineAngle = unitPointToLight.scaledBy(-1).dotProduct(
          light.direction());
      final double angularAttenuation = light.angularAttenuation(cosineAngle);
      final double distance = pointToLight.norm();
      final double radialAttenuation = light.radialAttenuation(distance);
      final double attenuationScale = angularAttenuation * radialAttenuation;

      // compute the total contribution due to this light
      Vector3D totalColor = Vector3D.ORIGIN;
      totalColor = totalColor.sumWith(diffuseColor);
      totalColor = totalColor.sumWith(specularColor);
      totalColor = totalColor.scaledBy(attenuationScale);

      // scale the total color down due to shadow at this point
      final double shadowScale = 1 - this.shadowAmount(shadowRay, light);
      totalColor = totalColor.scaledBy(shadowScale);

      // add the contribution due to this light to the overall color of this
      // point
      result = result.sumWith(totalColor);
    }

    // add the contribution due to ambient illumination to the overall color
    result = result.sumWith(ambientColor);

    // if the recursion has not reached its maximum depth
    if (depth < MAX_DEPTH) {
      // get the colors due to reflection and transmission
      final Vector3D reflectionColor = this.reflectionColor(intercept, depth);
      final Vector3D transColor = this.transmissionColor(intercept, depth);

      // add the illumination due to reflection and transmission to the overall
      // illumination at this point
      result = result.sumWith(reflectionColor);
      result = result.sumWith(transColor);
    }

    // apply the illumination to the color of the material at the intercept
    final FloatColor color = material.color();
    final Vector3D illuminatedColor = color.scaledByComponentwise(result);

    // the color should not be greater than some maximum
    return boundedColor(illuminatedColor);
  }

  private Vector3D reflectionColor(final Intercept intercept, final int depth) {
    // get the reflectivity of the material at the point of intersection
    final Material material = intercept.surfaceObject().material();
    final double reflection = material.reflection();

    // if there is no reflection, the color is (0, 0, 0)
    if (reflection <= 0) {
      return Vector3D.ORIGIN;
    }

    // get the normal to the surface at the point of intersection
    final Vector3D normal = intercept.normal();

    // get the inverse of the direction of the primary ray
    final Vector3D direction = intercept.ray().direction();
    final Vector3D inverseDirection = direction.scaledBy(-1);

    // create the direction of the reflected ray
    final Vector3D reflectedDir = reflected(inverseDirection, normal);

    // create the reflected ray
    final Ray reflectionRay = new Ray();
    reflectionRay.setPosition(intercept.pointOfIntersection());
    reflectionRay.setDirection(reflectedDir);

    // make the recursive call with the reflected ray as the primary ray
    Vector3D reflectionColor = this.trace(reflectionRay, depth + 1);

    // scale the reflected color by the coefficient of reflection of the
    // material at the point of intersection
    return reflectionColor.scaledBy(reflection);
  }

  private Vector3D transmissionColor(final Intercept intercept, final int depth) {
    // get the transmission of the material at the point of intersection
    final Material material = intercept.surfaceObject().material();
    final double transmission = material.transmission();

    // if there is no transmission, the color is (0, 0, 0)
    if (transmission <= 0) {
      return Vector3D.ORIGIN;
    }

    // get the primary ray which caused this intercept
    final Ray ray = intercept.ray();
    final Vector3D direction = ray.direction();

    // get the midpoint between the point of intersection and the origin of
    // the ray, to determine whether it is inside the object (since we are
    // only dealing with concave surface objects)
    final Vector3D midPoint = halfwayBetween(ray, intercept.time());

    // get the surface object of this intercept
    final SurfaceObject surfaceObject = intercept.surfaceObject();

    // determine the ratio of the indices of refraction; if we are inside,
    // we need to flip the index of refractions
    final double power = surfaceObject.outside(midPoint) ? 1 : -1;
    final double ratio = Math.pow(material.refraction(), power);

    // get the normal to the surface at the point of intersection
    final Vector3D normal = intercept.normal();

    // get the cosine of the angle between the normal and the vector from
    // the point of intersection to the origin of the primary ray
    final double cosAngle1 = normal.dotProduct(direction.scaledBy(-1));

    // get the cosine of the angle between the inverse normal and the
    // refracted ray
    final double cosAngle2 = Math.sqrt(1 - Math.pow(ratio, 2)
        * (1 - Math.pow(cosAngle1, 2)));

    // whether to add or multiply a term in the refracted vector definition
    final double factor = cosAngle1 < 0 ? 1 : -1;

    final Vector3D transmittedDir = direction.scaledBy(ratio)
        .sumWith(normal.scaledBy(ratio * cosAngle1 + factor * cosAngle2))
        .normalized();

    // create the ray of transmission
    final Ray transmissionRay = new Ray();
    transmissionRay.setPosition(intercept.pointOfIntersection());
    transmissionRay.setDirection(transmittedDir);

    // make the recursive call with the reflected ray as the primary ray
    final Vector3D transmissionColor = this.trace(transmissionRay, depth + 1);

    // scale the transmitted color by the coefficient of transmission of
    // the material at the point of intersection
    return transmissionColor.scaledBy(transmission);
  }

  private static Vector3D halfwayBetween(final Ray ray, final double time) {
    final Vector3D origin = ray.position();
    final Vector3D direction = ray.direction();
    return origin.sumWith(direction.scaledBy(time / 2.0));
  }

  private Vector3D diffuseColor(final Intercept intercept, final Light light) {
    // get the vector from the point of intersection to the light source (L)
    final Vector3D point = intercept.pointOfIntersection();
    final Vector3D lightPoint = light.position();
    final Vector3D pointToLight = lightPoint.difference(point).normalized();

    // get the normal to the surface (N)
    final Vector3D normal = intercept.normal();

    // get the dot product between the normal and the light ray
    final double dotProduct = pointToLight.dotProduct(normal);

    // get the coefficient of diffuse reflection due to the material (k_d)
    final Material material = intercept.surfaceObject().material();
    final double diffuseCoefficient = material.diffuseReflection();

    // get the total scaling factor due to diffuse reflection
    final double diffuseScale = diffuseCoefficient * Math.max(dotProduct, 0);

    // return the color due to the specified light scaled by the diffuse scale
    return light.color().scaledBy(diffuseScale);
  }

  private Vector3D specularColor(final Intercept intercept, final Light light) {
    // reflect the light vector through the normal (R)
    final Vector3D reflectedLight = reflected(light.direction(),
        intercept.normal());

    // get the view plane vector (V)
    final Vector3D viewPlaneVector = intercept.ray().direction();

    // get the dot product between the view plane vector and the reflected vec
    final double dotProduct = reflectedLight.dotProduct(viewPlaneVector);

    // get the coefficient and exponent of specular reflection
    final Material material = intercept.surfaceObject().material();
    final double specularCoefficient = material.specularReflection();
    final double specularExponent = material.specularExponent();

    // get the total scaling factor due to specular reflection
    final double specularScale = specularCoefficient
        * Math.pow(Math.max(0, dotProduct), specularExponent);

    // return color due to the specified light scaled by the specular scale
    return light.color().scaledBy(specularScale);
  }

  /**
   * Computes the color at this intercept.
   * 
   * Note: in the provided pseudocode this was called RT_trace.
   * 
   * @param intercept
   *          The intercept for which to compute the color.
   * @param depth
   *          The current depth of recursion in the ray tree.
   * @return The color at this intercept.
   */
  // TODO combine trace() and shade()
  Vector3D trace(final Ray ray, final int depth) {
    final Intercept minIntercept = this.minimumIntercept(ray);
    return minIntercept == null ? BACKGROUND_COLOR : this.shade(minIntercept,
        depth);
  }

  /**
   * Returns the intercept of the specified ray with the surface objects in the
   * scene (between the near and far clip planes) at the minimum time.
   * 
   * @param ray
   *          The ray to intercept with the surface objects in the scene.
   * @return The intercept at the minimum time within the clipping planes.
   */
  private Intercept minimumIntercept(final Ray ray) {
    final List<Intercept> candidates = new ArrayList<Intercept>();
    for (final SurfaceObject surfaceObject : this.surfaceObjects) {
      final Intercept intercept = surfaceObject.interceptWith(ray);
      if (intercept != null
          && (intercept.time() <= this.camera.far() && intercept.time() >= this.camera
              .near())) {
        candidates.add(intercept);
      }
    }

    return candidates.isEmpty() ? null : Collections.min(candidates);
  }
}
