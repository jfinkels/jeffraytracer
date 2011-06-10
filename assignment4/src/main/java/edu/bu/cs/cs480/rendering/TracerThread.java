/**
 * TracerThread.java - Runnable object which traces some number of rays
 */
package edu.bu.cs.cs480.rendering;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * A runnable which traces some number of rays specified by the parent
 * ThreadedTracer object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
class TracerThread implements Runnable {
  /** The logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(TracerThread.class);
  /** The pixel in the {@link #rays} array at which to stop rendering. */
  private final int end;
  /**
   * The tracer to use to trace the scene, and to inform when this object has
   * completed its portion of the tracing.
   */
  private final ThreadedTracer parentTracer;
  /** The primary rays to use to trace the image. */
  private final Ray[] rays;
  /** The array of colors to which to write the traced colors. */
  private final Vector3D[] colors;
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
   * This object will trace rays from the specified array and assign the result
   * to the corresponding location in the specified colors array.
   * 
   * Pre-condition: the length of the {@code colors} array must be at least as
   * great as the length of the {@code rays} array.
   * 
   * @param rays
   *          An array containing (as a sublist) the primary rays to trace.
   * @param start
   *          The pixel at which to start tracing.
   * @param end
   *          The pixel at which to end tracing.
   * @param parentTracer
   *          The threaded tracer to use to trace the scene, and to notify when
   *          this object has completed tracing.
   * @param colors
   *          The array to which to write the colors traced by this object.
   * @param threadID
   *          The ID of this rendering thread.
   */
  public TracerThread(final Ray[] rays, final int start, final int end,
      final ThreadedTracer parentTracer, final int threadID,
      final Vector3D[] colors) {
    // WARNING: since we are passing in a reference to an array of rays, this
    // class could potentially modify the contents of the array!
    this.rays = rays;
    this.start = start;
    this.end = end;
    this.parentTracer = parentTracer;
    this.colors = colors;
    this.threadID = threadID;
  }

  /**
   * Traces rays from the {@link #rays} array starting at position (0,
   * {@link #startRow}) and ending at (this.width - 1, {@link #endRow} - 1).
   * 
   * Assigns the results to the corresponding location of the {@link #colors}
   * array.
   */
  @Override
  public void run() {
    LOG.debug("Tracing rays from pixel " + this.start + " to pixel "
        + this.end + "...");
    for (int i = this.start; i < this.end; ++i) {
      this.colors[i] = this.parentTracer.trace(this.rays[i]);
    }

    LOG.debug("Completed tracing rays from pixel " + this.start + " to pixel "
        + this.end + ".");
    synchronized (this.parentTracer) {
      this.parentTracer.threadFinished(this.threadID);
      this.parentTracer.notifyAll();
    }
  }
}
