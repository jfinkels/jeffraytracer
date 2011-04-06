/**
 * TracerEnvironment.java -
 */
package edu.bu.cs.cs480.main;

import java.util.ArrayList;
import java.util.List;

import edu.bu.cs.cs480.Light;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TracerEnvironment {
  private Camera camera = null;
  private List<Light> lights = new ArrayList<Light>();
  private Resolution resolution = null;
  private List<SurfaceObject> surfaceObjects = new ArrayList<SurfaceObject>();
  private Viewport viewport = null;

  /**
   * @param lights
   *          the lights to set
   */
  public void addLight(final Light light) {
    this.lights.add(light);
  }

  /**
   * @param surfaceObject
   *          the surfaceObject to set
   */
  public void addSurfaceObject(final SurfaceObject surfaceObject) {
    this.surfaceObjects.add(surfaceObject);
  }

  public void render(final String filename) {
    // not yet implemented
  }

  /**
   * @param camera
   *          the camera to set
   */
  public void setCamera(final Camera camera) {
    this.camera = camera;
  }

  /**
   * @param resolution
   *          the resolution to set
   */
  public void setResolution(final Resolution resolution) {
    this.resolution = resolution;
  }

  /**
   * @param viewport
   *          the viewport to set
   */
  public void setViewport(final Viewport viewport) {
    this.viewport = viewport;
  }
}
