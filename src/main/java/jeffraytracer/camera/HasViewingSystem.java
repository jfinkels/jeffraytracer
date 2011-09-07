/**
 * HasViewingSystem.java - object which has a camera, viewport, and resolution
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer.camera;


/**
 * An object which has a camera, a resolution, and a viewport.
 * 
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
