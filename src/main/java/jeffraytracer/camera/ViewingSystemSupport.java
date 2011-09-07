/**
 * ViewingSystemSupport.java - base implementation of HasViewingSystem
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
 * An implementation of an object with a camera, resolution, and viewport.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ViewingSystemSupport implements HasViewingSystem {

  /** The virtual camera through which the scene is viewed. */
  private Camera camera = null;
  /** The resolution of the scene when displayed in the viewport. */
  private Resolution resolution = null;
  /** The dimensions of the viewport in which the scene is displayed. */
  private Viewport viewport = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public Viewport viewport() {
    return this.viewport;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public Camera camera() {
    return this.camera;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public Resolution resolution() {
    return this.resolution;
  }

  /**
   * {@inheritDoc}
   * 
   * @param camera
   *          {@inheritDoc}
   */
  @Override
  public void setCamera(final Camera camera) {
    this.camera = camera;
  }

  /**
   * {@inheritDoc}
   * 
   * @param resolution
   *          {@inheritDoc}
   */
  @Override
  public void setResolution(final Resolution resolution) {
    this.resolution = resolution;
  }

  /**
   * {@inheritDoc}
   * 
   * @param viewport
   *          {@inheritDoc}
   */
  @Override
  public void setViewport(final Viewport viewport) {
    this.viewport = viewport;
  }
}
