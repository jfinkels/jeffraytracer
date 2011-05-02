/**
 * Renderer.java - object which renders some number of rows of the image
 */
package edu.bu.cs.cs480.main;

import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.FloatColor;
import edu.bu.cs.cs480.vectors.Ray;

/**
 * A runnable which renders some number of rows of the image specified by the
 * tracer environment.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
class Renderer implements Runnable {
  /** The primary rays to use to trace the image. */
  private final Ray[] rays;
  /** The row in the {@link #rays} array row at which to start rendering. */
  private final int startRow;
  /** The row in the {@link #rays} array row at which to stop rendering. */
  private final int endRow;
  /**
   * The width of the output image (and therefore the dimension of a row in the
   * {@link #rays} array).
   */
  private final int width;
  /** The environment to use to trace the scene. */
  private final TracerEnvironment environment;
  /** The image to which to write the traced pixels. */
  private final BufferedImage result;
  /**
   * The ID number of this thread, used to identify which thread has finished in
   * the tracer environment.
   */
  private final int threadID;
  /** The logger for this class. */
  private static final transient Logger LOG = Logger.getLogger(Renderer.class);

  /**
   * Instantiates this renderer with access to all the necessary information
   * from the tracer environment.
   * 
   * @param rays
   * @param startRow
   * @param endRow
   * @param width
   * @param environment
   * @param result
   * @param threadID
   */
  public Renderer(final Ray[] rays, final int startRow, final int endRow,
      final int width, final TracerEnvironment environment,
      final BufferedImage result, final int threadID) {
    this.rays = rays;
    this.startRow = startRow;
    this.endRow = endRow;
    this.width = width;
    this.environment = environment;
    this.result = result;
    this.threadID = threadID;
  }

  /**
   * Traces rays from the {@link #rays} array starting at position (0,
   * {@link #startRow}) and ending at (this.width - 1, {@link #endRow}).
   */
  @Override
  public void run() {
    LOG.debug("Tracing rays from row " + this.startRow + " to row "
        + this.endRow + "...");
    for (int y = this.startRow; y < this.endRow; ++y) {
      for (int x = 0; x < this.width; ++x) {
        final Ray ray = this.rays[y * this.width + x];
        final int color = FloatColor.toRGB(this.environment.trace(ray, 1));
        // HACK for some reason, my scenes are all reflected in the x direction
        this.result.setRGB(this.width - 1 - x, y, color);
      }
    }
    LOG.debug("Completed tracing rays from row " + this.startRow + " to row "
        + this.endRow + ".");
    synchronized (this.environment) {
      this.environment.rendererFinished(this.threadID);
      this.environment.notifyAll();
    }
  }
}
