/**
 * RenderingEnvironment.java - a scene which can be rendered
 */
package edu.bu.cs.cs480.rendering;

import java.util.List;

import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.lights.AmbientLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * Provides all the properties necessary for a scene which can be rendered.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface RenderingEnvironment {
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
   * Sets the virtual camera through which the scene is viewed.
   * 
   * @param camera
   *          The virtual camera through which the scene is viewed.
   */
  void setCamera(final Camera camera);

  /**
   * Sets the resolution of the scene when displayed in the viewport.
   * 
   * @param resolution
   *          The resolution of the scene when displayed in the viewport.
   */
  void setResolution(final Resolution resolution);

  /**
   * Sets the dimensions of the viewport in which the scene is displayed.
   * 
   * @param viewport
   *          The dimensions of the viewport in which the scene is displayed.
   */
  void setViewport(final Viewport viewport);

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

  /**
   * Gets the virtual camera through which the scene is viewed.
   * 
   * @return The virtual camera through which the scene is viewed.
   */
  Camera camera();

  /**
   * Gets the resolution of the scene when displayed in the viewport.
   * 
   * @return The resolution of the scene when displayed in the viewport.
   */
  Resolution resolution();

  /**
   * Gets the dimensions of the viewport in which the scene is displayed.
   * 
   * @return The dimensions of the viewport in which the scene is displayed.
   */
  Viewport viewport();
}
