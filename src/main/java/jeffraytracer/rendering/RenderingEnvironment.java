/**
 * RenderingEnvironment.java - a scene which can be rendered
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
package jeffraytracer.rendering;

import java.util.List;

import jeffraytracer.camera.HasViewingSystem;
import jeffraytracer.lights.AmbientLight;
import jeffraytracer.lights.Light;
import jeffraytracer.surfaces.SurfaceObject;

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
