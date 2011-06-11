/**
 * ViewingSystemSupport.java -
 */
package edu.bu.cs.cs480;

import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;

/**
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
