/**
 * Renderer.java - object which renders some number of rows of the image
 */
package edu.bu.cs.cs480.rendering;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * A runnable which renders some number of rows of the image specified by the
 * tracer environment.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
class Renderer implements Runnable {
  /** The logger for this class. */
  private static final transient Logger LOG = Logger.getLogger(Renderer.class);
  /** The pixel in the {@link #rays} array at which to stop rendering. */
  private final int end;
  /** The environment to use to trace the scene. */
  private final ThreadedTracerEnvironment environment;
  /** The primary rays to use to trace the image. */
  private final Ray[] rays;
  /** The array of pixels to which to write the traced colors. */
  private final Vector3D[] pixels;
  /** The pixel in the {@link #rays} array at which to start rendering. */
  private final int start;
  /**
   * The ID number of this thread, used to identify which thread has finished
   * in the tracer environment.
   */
  private final int threadID;

  /**
   * Instantiates this renderer with access to all the necessary information
   * from the tracer environment.
   * 
   * @param rays
   *          An array containing (as a sublist) the primary rays to trace.
   * @param start
   *          The pixel at which to start tracing.
   * @param end
   *          The pixel at which to end tracing.
   * @param environment
   *          The tracer environment to use to trace the scene.
   * @param pixels
   *          The array of pixels to which to write the colors traced scene.
   * @param threadID
   *          The ID of this rendering thread.
   */
  public Renderer(final Ray[] rays, final int start, final int end,
      final ThreadedTracerEnvironment environment, final int threadID,
      final Vector3D[] pixels) {
    // WARNING: since we are passing in a reference to an array of rays, this
    // class could potentially modify the contents of the array!
    this.rays = rays;
    this.start = start;
    this.end = end;
    this.environment = environment;
    this.pixels = pixels;
    this.threadID = threadID;
  }

  /**
   * Traces rays from the {@link #rays} array starting at position (0,
   * {@link #startRow}) and ending at (this.width - 1, {@link #endRow} - 1).
   */
  @Override
  public void run() {
    LOG.debug("Tracing rays from pixel " + this.start + " to pixel "
        + this.end + "...");
    for (int i = this.start; i < this.end; ++i) {
      final Ray ray = this.rays[i];
      // TODO for some reason, my scenes are all reflected in the x direction
      // this.result.setRGB(x, y, color);
      this.pixels[i] = this.environment.trace(ray, 1);
    }

    LOG.debug("Completed tracing rays from pixel " + this.start + " to pixel "
        + this.end + ".");
    synchronized (this.environment) {
      this.environment.rendererFinished(this.threadID);
      this.environment.notifyAll();
    }
  }
}
