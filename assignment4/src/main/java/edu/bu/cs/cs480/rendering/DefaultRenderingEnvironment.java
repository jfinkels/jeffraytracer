/**
 * DefaultRenderingEnvironment.java - 
 */
package edu.bu.cs.cs480.rendering;

import java.util.ArrayList;
import java.util.List;

import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.lights.AmbientLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class DefaultRenderingEnvironment implements RenderingEnvironment {
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
  @Override
  public void addAmbientLight(final AmbientLight light) {
    this.ambientLights.add(light);
  }

  /**
   * Adds the specified light to the scene.
   * 
   * @param light
   *          The light to add.
   */
  @Override
  public void addLight(final Light light) {
    this.lights.add(light);
  }

  /**
   * Adds the specified surface object to the scene.
   * 
   * @param surfaceObject
   *          The surface object to add.
   */
  @Override
  public void addSurfaceObject(final SurfaceObject surfaceObject) {
    this.surfaceObjects.add(surfaceObject);
  }

  @Override
  public Viewport viewport() {
   return this.viewport;
 }

 @Override
public Camera camera() {
   return this.camera;
 }

 @Override
public Resolution resolution() {
   return this.resolution;
 }

 
  /**
   * Sets the virtual camera through which the scene is viewed.
   * 
   * @param camera
   *          The virtual camera through which the scene is viewed.
   */
  @Override
  public void setCamera(final Camera camera) {
    this.camera = camera;
  }

  /**
   * Sets the resolution of the scene when displayed in the viewport.
   * 
   * @param resolution
   *          The resolution of the scene when displayed in the viewport.
   */
  @Override
  public void setResolution(final Resolution resolution) {
    this.resolution = resolution;
  }

  /**
   * Sets the dimensions of the viewport in which the scene is displayed.
   * 
   * @param viewport
   *          The dimensions of the viewport in which the scene is displayed.
   */
  @Override
  public void setViewport(final Viewport viewport) {
    this.viewport = viewport;
  }


  /* (non-Javadoc)
   * @see edu.bu.cs.cs480.rendering.RenderingEnvironment#lights()
   */
  @Override
  public List<Light> lights() {
    return this.lights;
  }

  /* (non-Javadoc)
   * @see edu.bu.cs.cs480.rendering.RenderingEnvironment#ambientLights()
   */
  @Override
  public List<AmbientLight> ambientLights() {
    return this.ambientLights;
  }

  /* (non-Javadoc)
   * @see edu.bu.cs.cs480.rendering.RenderingEnvironment#surfaceObjects()
   */
  @Override
  public List<SurfaceObject> surfaceObjects() {
    return this.surfaceObjects;
  }
}
