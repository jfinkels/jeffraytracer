/**
 * DefaultThreadedTracer - implementation of a tracer which employs threads
 */
package edu.bu.cs.cs480.rendering;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * A tracer which, when tracing multiple rays, splits the work into some number
 * of threads.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class DefaultThreadedTracer extends DefaultTracer implements
    ThreadedTracer {

  /**
   * Instantiates this object by calling the corresponding constructor of the
   * superclass.
   * 
   * @param environment
   *          The scene to trace.
   */
  public DefaultThreadedTracer(final RenderingEnvironment environment) {
    super(environment);
  }

  /**
   * Resets the value of each element of the specified array to {@code false}.
   * 
   * @param array
   *          The array to reset to false.
   */
  private static void resetBooleans(final boolean[] array) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = false;
    }
  }

  /** The logger for this class. */
  private static transient final Logger LOG = Logger
      .getLogger(DefaultThreadedTracer.class);
  /** Stores whether each of the rendering threads has finished. */
  private boolean[] renderersFinished = null;

  /**
   * Marks the rendering thread with the specified ID completed.
   * 
   * Pre-condition: threadID is between 0 and the number of threads.
   * 
   * @param threadID
   *          The ID of the thread to be marked completed.
   */
  @Override
  public void threadFinished(final int threadID) {
    this.renderersFinished[threadID] = true;
  }

  /** The default number of threads to use when rendering. */
  public static final int DEFAULT_NUM_THREADS = 2;

  /**
   * Sets the number of threads to use when tracing an array of rays.
   * 
   * @param numThreads
   *          The number of threads to use when tracing an array of rays.
   */
  public void setNumThreads(final int numThreads) {
    this.numThreads = numThreads;
  }

  /** The number of threads to use when tracing an array of rays. */
  private int numThreads = DEFAULT_NUM_THREADS;

  /**
   * Traces each of the rays in the specified array by splitting up the work
   * among some number of threads, set by {@link #setNumThreads(int)} (or using
   * the default number of threads {@link #DEFAULT_NUM_THREADS}).
   * 
   * Post-condition: the length of the returned array equals the length of the
   * input array.
   * 
   * @param rays
   *          The rays to trace.
   * @return An array of three-dimensional vectors representing the colors when
   *         each ray was traced according to the scene specified in the
   *         constructor of this class.
   */
  @Override
  public Vector3D[] traceAll(final Ray[] rays) {
    final int numPixels = rays.length;
    final Vector3D[] pixels = new Vector3D[numPixels];
    final int delta = numPixels / this.numThreads;
    this.renderersFinished = new boolean[this.numThreads];
    resetBooleans(this.renderersFinished);
    final TracerThread[] renderers = new TracerThread[this.numThreads];
    // ugly: in case NUM_THREADS is not a divisor of the number of rays, we
    // manually force the last thread to take up the remainder, that's why
    // the upper bound is NUM_THREADS - 1 and the last renderer is created
    // after the loop
    for (int i = 0; i < this.numThreads - 1; ++i) {
      // this creates a renderer which renders "delta" pixels, starting at
      // offset "i * delta", and with ID number "i"
      renderers[i] = new TracerThread(rays, i * delta, (i + 1) * delta, this,
          i, pixels);
    }
    renderers[this.numThreads - 1] = new TracerThread(rays,
        (this.numThreads - 1) * delta, numPixels, this, this.numThreads - 1,
        pixels);

    // run the threads which will fill in the colors in the pixels array
    for (final TracerThread renderer : renderers) {
      new Thread(renderer).start();
    }

    // wait until all the threads have notified us that they are finished
    this.waitForThreads();

    return pixels;
  }

  /** Waits for the rendering threads to complete. */
  private synchronized void waitForThreads() {
    while (!all(this.renderersFinished)) {
      try {
        this.wait();
      } catch (final InterruptedException exception) {
        LOG.error(exception);
      }
    }
  }

  /**
   * Returns {@code true} if and only if all of the elements of the specified
   * array are {@code true}.
   * 
   * @param array
   *          The array to check.
   * @return {@code true} if and only if all of the elements of the specified
   *         array are {@code true}.
   */
  private static boolean all(final boolean[] array) {
    for (int i = 0; i < array.length; ++i) {
      if (!array[i]) {
        return false;
      }
    }
    return true;
  }

}
