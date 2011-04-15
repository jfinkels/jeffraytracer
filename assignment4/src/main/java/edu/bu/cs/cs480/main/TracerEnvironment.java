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

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Light;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * An environment which aggregates all the objects necessary to trace a scene.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TracerEnvironment {
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
    System.out.println("Generating primary rays...");
    final Ray[] rays = new Ray[width * height];
    for (int y = 0; y < height; ++y) {
      for (int x = 0; x < width; ++x) {
        rays[y * width + x] = generateRay(x, y);
      }
    }

    // compile all the surface objects so that we only compute their quadratic
    // form matrices one time
    for (final SurfaceObject surfaceObject : this.surfaceObjects) {
      surfaceObject.compile();
    }

    // compute the min intercept for each ray
    System.out.println("Computing min intercepts for each ray...");
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
    System.out.println("Drawing from primary rays...");
    final BufferedImage result = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; ++y) {
      for (int x = 0; x < width; ++x) {
        final Ray ray = rays[y * width + x];
        // System.out.println("(x, y): " + x + ", " + y);
        // System.out.println("ray: " + ray);
        // System.out.println("intercepts: " + intercepts.get(ray));
        if (intercepts.get(ray) == null) {
          result.setRGB(x, y, BACKGROUND_COLOR);
        } else {
          result.setRGB(x, y, 0x00FFFF);
        }
      }
    }

    return result;
  }

  /** The color of the background for rendered images. */
  public static final int BACKGROUND_COLOR = 0xFF0000;

  /**
   * Generates the ray which would start at pixel location (i, j) in the
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
   * @return The ray which would start at pixel location (i, j) in the
   *         viewport.
   */
  protected Ray generateRay(final int i, final int j) {
    // compute location of pixel on view plane
    final double du = (i - (this.viewport.width() / 2) + 1)
        * this.resolution.xResolution();
    final double dv = (-j + (this.viewport.height() / 2) - 1)
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
}
