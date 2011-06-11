/**
 * RenderingEnvironment.java - a scene which can be rendered
 */
package edu.bu.cs.cs480.rendering;

import java.util.List;

import edu.bu.cs.cs480.HasViewingSystem;
import edu.bu.cs.cs480.lights.AmbientLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * Provides all the properties necessary for a scene which can be rendered.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface RenderingEnvironment extends HasViewingSystem {
  /**
   * Adds the specified light to the scene.
   * 
   * @param light
   *          The light to add.
   */
  void addLight(final Light light);

  /**
   * Adds the specified ambient light to the scene.
   * 
   * @param light
   *          The ambient light to add.
   */
  void addAmbientLight(final AmbientLight light);

  /**
   * Adds the specified surface object to the scene.
   * 
   * @param surfaceObject
   *          The surface object to add.
   */
  void addSurfaceObject(final SurfaceObject surfaceObject);

  /**
   * Gets the list of lights in the scene.
   * 
   * @return The list of lights in the scene.
   */
  List<Light> lights();

  /**
   * Gets the list of ambient lights in the scene.
   * 
   * @return The list of ambient lights in the scene.
   */
  List<AmbientLight> ambientLights();

  /**
   * Gets the list of surface objects in the scene.
   * 
   * @return The list of surface objects in the scene.
   */
  List<SurfaceObject> surfaceObjects();

}
