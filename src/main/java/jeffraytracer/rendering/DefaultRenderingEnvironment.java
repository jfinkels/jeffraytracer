/**
 * DefaultRenderingEnvironment.java - a scene which can be rendered
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

import java.util.ArrayList;
import java.util.List;

import jeffraytracer.camera.ViewingSystemSupport;
import jeffraytracer.lights.AmbientLight;
import jeffraytracer.lights.Light;
import jeffraytracer.surfaces.SurfaceObject;

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
   * @see jeffraytracer.rendering.RenderingEnvironment#lights()
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
   * @see jeffraytracer.rendering.RenderingEnvironment#ambientLights()
   */
  @Override
  public List<AmbientLight> ambientLights() {
    return this.ambientLights;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.rendering.RenderingEnvironment#surfaceObjects()
   */
  @Override
  public List<SurfaceObject> surfaceObjects() {
    return this.surfaceObjects;
  }
}
