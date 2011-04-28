/**
 * TracerEnvironment.java - the environment which can trace a scene
 */
package edu.bu.cs.cs480.main;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  public static final int BACKGROUND_COLOR = 0xFF101010;
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
  public static final double TOLERANCE2 = 1e-13;

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
   * @param material
   *          The material on which the ambient light energy is computed.
   * @return The ambient light energy on the specified material.
   */
  private Vector3D ambientReflection(final Material material) {
    Vector3D reflection = new Vector3D(0, 0, 0);
    for (final AmbientLight light : this.ambientLights) {
      reflection = reflection.sumWith(light.color().toVector());
    }
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
   * Returns {@code true} if and only if the specified ray intersects any
   * surface object.
   * 
   * @param ray
   *          The ray to check for intercepts with surface objects.
   * @return {@code true} if and only if the specified ray intersects any
   *         surface object.
   */
  private boolean isShadowed(final Ray ray) {
    for (final SurfaceObject surfaceObject : this.surfaceObjects) {
      // TODO don't we need to check if the object in between the point of
      // intersection and the light is not transmissive?
      final Intercept i = surfaceObject.interceptWith(ray);
      if (i != null && i.time() > TOLERANCE2) {
        return true;
      }
    }
    return false;
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
    for (final SurfaceObject surfaceObject : this.surfaceObjects) {
      surfaceObject.compile();
    }

    // compute the min intercept for each ray
    LOG.debug("Computing min intercepts for each ray...");
    final Map<Ray, Intercept> intercepts = new HashMap<Ray, Intercept>();
    for (final Ray ray : rays) {
      // compute all intersections with surface objects
      final List<Intercept> candidates = new ArrayList<Intercept>();
      for (final SurfaceObject surfaceObject : this.surfaceObjects) {
        final Intercept intercept = surfaceObject.interceptWith(ray);
        if (intercept != null) {
          candidates.add(intercept);
        }
      }

      if (candidates.isEmpty()) {
        intercepts.put(ray, null);
      } else {
        intercepts.put(ray, Collections.min(candidates));
      }
    }

    // draw the intercept on an image
    LOG.debug("Casting primary rays...");
    final BufferedImage result = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; ++y) {
      for (int x = 0; x < width; ++x) {
        final Ray ray = rays[y * width + x];
        final int color = this.trace(intercepts.get(ray), 1);
        result.setRGB(x, y, color);
      }
    }

    return result;
  }

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
    // get the material to shade
    final Material material = intercept.surfaceObject().material();

    // get the color of the material to shade
    final FloatColor materialColor = material.color();

    // always apply at least the ambient lighting due to all the ambient lights
    Vector3D reflection = this.ambientReflection(material);

    // get the point of intersection which will be colored
    final Vector3D point = intercept.pointOfIntersection();

    // get the normal to the surface at the point of intersection
    final Vector3D normal = intercept.normal();

    // get the vector from the point of intersection to the origin of the ray
    // which caused the intercept
    final Vector3D viewPlaneVector = intercept.ray().direction().scaledBy(-1);

    // create a ray from the point of intersection to the light
    final Ray shadowRay = new Ray();
    shadowRay.setPosition(point);

    for (final Light light : this.lights) {
      // get the vector from the point to the light
      final Vector3D pointToLight = light.vectorFrom(point);

      // set the direction of the ray as a unit vector
      final Vector3D direction = pointToLight.normalized();
      shadowRay.setDirection(direction);

      // compute the cosine of the angle between the normal and the ray
      final double dotProduct = normal.dotProduct(direction);

      // if the light hits the point at an angle between -90 and 90 and this is
      // not in shadow
      if (dotProduct > TOLERANCE) {
        
        // determine if the point is not in shadow
        if (!light.castsShadow() || !this.isShadowed(shadowRay)) {

          // radial attenuation scale factor
          final double radialAttenuation = light
              .radialAttenuation(pointToLight.norm());

          // angular attenuation scale factor
          final double cosineAngle = shadowRay.direction().scaledBy(-1)
              .dotProduct(light.direction());
          final double angularAttenuation = light
              .angularAttenuation(cosineAngle);

          // get the color of the light
          final Vector3D lightColor = light.color().toVector();

          // energy from diffuse reflection
          // NOTE: dot product is guaranteed to be > 0 by conditional execution
          final double diffuseScale = material.diffuseReflection()
              * dotProduct;
          final Vector3D diffuseReflection = lightColor.scaledBy(diffuseScale);

          // determine the energy from specular reflection
          Vector3D specularReflection = Vector3D.ORIGIN;

          // determine the cosine of the angle between the light's ray
          // reflected
          // through the normal and the vector from the intercept to the
          // intersecting ray's origin
          final Vector3D reflected = reflected(direction, normal);
          final double dotProduct2 = reflected.dotProduct(viewPlaneVector);

          // if the light hits at an angle between -90 and 90
          if (dotProduct2 > TOLERANCE) {
            // energy from specular reflection
            // NOTE: dot product 2 is guaranteed to be > 0 by conditional
            // execution
            final double specularScale = material.specularReflection()
                * Math.pow(dotProduct2, material.specularExponent());
            specularReflection = lightColor.scaledBy(specularScale);
          }

          // add the total energy due to this light source to the color of this
          // point on the surface object
          reflection = reflection.sumWith(diffuseReflection.sumWith(
              specularReflection).scaledBy(
              radialAttenuation * angularAttenuation));
        }
      }
    }

    // do reflection and transmission
    
    
    return boundedColor(materialColor.scaledByComponentwise(reflection));
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
  private int trace(final Intercept intercept, final int depth) {
    if (intercept == null) {
      return BACKGROUND_COLOR;
    }
    return FloatColor.toRGB(this.shade(intercept, depth));
  }
}
