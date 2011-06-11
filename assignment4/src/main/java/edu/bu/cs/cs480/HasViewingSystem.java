/**
 * HasViewingSystem.java -
 */
package edu.bu.cs.cs480;

import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface HasViewingSystem {

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
