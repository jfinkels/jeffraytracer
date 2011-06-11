/**
 * DefaultRenderingEnvironment.java - a scene which can be rendered
 */
package edu.bu.cs.cs480.rendering;

import java.util.ArrayList;
import java.util.List;

import edu.bu.cs.cs480.ViewingSystemSupport;
import edu.bu.cs.cs480.lights.AmbientLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * Provides all the properties necessary for a scene which can be rendered.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class DefaultRenderingEnvironment extends ViewingSystemSupport
    implements RenderingEnvironment {
  /** The list of all lights in the scene which are ambient lights. */
  private List<AmbientLight> ambientLights = new ArrayList<AmbientLight>();
  /** The list of light sources for the scene. */
  private List<Light> lights = new ArrayList<Light>();
  /** The list of surface objects to be rendered. */
  private List<SurfaceObject> surfaceObjects = new ArrayList<SurfaceObject>();

  /**
   * {@inheritDoc}
   * 
   * @param light
   *          {@inheritDoc}
   */
  @Override
  public void addAmbientLight(final AmbientLight light) {
    this.ambientLights.add(light);
  }

  /**
   * {@inheritDoc}
   * 
   * @param light
   *          {@inheritDoc}
   */
  @Override
  public void addLight(final Light light) {
    this.lights.add(light);
  }

  /**
   * {@inheritDoc}
   * 
   * @param surfaceObject
   *          {@inheritDoc}
   */
  @Override
  public void addSurfaceObject(final SurfaceObject surfaceObject) {
    this.surfaceObjects.add(surfaceObject);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.rendering.RenderingEnvironment#lights()
   */
  @Override
  public List<Light> lights() {
    return this.lights;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.rendering.RenderingEnvironment#ambientLights()
   */
  @Override
  public List<AmbientLight> ambientLights() {
    return this.ambientLights;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.rendering.RenderingEnvironment#surfaceObjects()
   */
  @Override
  public List<SurfaceObject> surfaceObjects() {
    return this.surfaceObjects;
  }
}
