/**
 * DefaultThreadedTracer - implementation of a tracer which employs threads
 */
package edu.bu.cs.cs480.rendering;

import java.util.concurrent.CountDownLatch;

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
public class DefaultThreadedTracer extends DefaultTracer {

  /** The default number of threads to use when rendering. */
  public static final int DEFAULT_NUM_THREADS = 2;
  /** The logger for this class. */
  private static transient final Logger LOG = Logger
      .getLogger(DefaultThreadedTracer.class);
  /** The number of threads to use when tracing an array of rays. */
  private int numThreads = DEFAULT_NUM_THREADS;

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
   * Sets the number of threads to use when tracing an array of rays.
   * 
   * @param numThreads
   *          The number of threads to use when tracing an array of rays.
   */
  public void setNumThreads(final int numThreads) {
    this.numThreads = numThreads;
  }

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
    final Vector3D[] colors = new Vector3D[rays.length];

    // Set the start and end pixels for the tracer threads. In case
    // this.numThreads is not a divisor of the number of rays, we manually
    // force the last thread to take up the remainder.
    final int[] slices = new int[this.numThreads + 1];
    final int delta = rays.length / this.numThreads;
    for (int i = 0; i < this.numThreads; ++i) {
      slices[i] = i * delta;
    }
    slices[this.numThreads] = rays.length;

    // the latch on which to wait until each tracer thread has completed
    final CountDownLatch latch = new CountDownLatch(this.numThreads);

    // create a tracer thread for each slice
    for (int i = 0; i < slices.length - 1; ++i) {
      final int start = slices[i];
      final int end = slices[i + 1];
      LOG.debug("Tracing rays from pixel " + start + " to pixel " + end
          + "...");
      new Thread() {
        /**
         * Traces rays from the {@code rays} array starting at index
         * {@code start} and ending at index {@code end}.
         * 
         * Assigns the results to the corresponding location of the
         * {@code colors} array.
         */
        @Override
        public void run() {
          for (int i = start; i < end; ++i) {
            colors[i] = DefaultThreadedTracer.this.trace(rays[i]);
          }
          // once this thread has traced its rays, count down the latch
          latch.countDown();
        }
      }.start();
    }

    // wait until all the threads have notified us that they are finished
    try {
      latch.await();
    } catch (final InterruptedException exception) {
      LOG.error(
          "Tracing thread was interrupted; pixels may not have been completely shaded.",
          exception);
    }

    return colors;
  }
}
