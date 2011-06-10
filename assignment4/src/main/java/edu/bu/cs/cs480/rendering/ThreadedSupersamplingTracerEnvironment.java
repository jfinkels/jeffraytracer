/**
 * ThreadedSupersamplingTracerEnvironment.java -
 */
package edu.bu.cs.cs480.rendering;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ThreadedSupersamplingTracerEnvironment extends
    SupersamplingTracerEnvironment implements ThreadedTracerEnvironment {

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
      .getLogger(ThreadedSupersamplingTracerEnvironment.class);
  /** Stores whether each of the rendering threads has finished. */
  private boolean[] renderersFinished = new boolean[NUM_THREADS];

  /**
   * Marks the rendering thread with the specified ID completed.
   * 
   * Pre-condition: threadID is between 0 and the number of threads.
   * 
   * @param threadID
   *          The ID of the thread to be marked completed.
   */
  @Override
  public void rendererFinished(final int threadID) {
    this.renderersFinished[threadID] = true;
  }

  @Override
  protected Vector3D[] traceAllRays(final Ray[] rays) {
    final int numPixels = rays.length;
    final Vector3D[] pixels = new Vector3D[numPixels];
    final int delta = numPixels / NUM_THREADS;
    resetBooleans(this.renderersFinished);
    final Renderer[] renderers = new Renderer[NUM_THREADS];
    // ugly: in case NUM_THREADS is not a divisor of the number of rays, we
    // manually force the last thread to take up the remainder, that's why
    // the upper bound is NUM_THREADS - 1 and the last renderer is created
    // after the loop
    for (int i = 0; i < NUM_THREADS - 1; ++i) {
      // this creates a renderer which renders "delta" pixels, starting at
      // offset "i * delta", and with ID number "i"
      renderers[i] = new Renderer(rays, i * delta, (i + 1) * delta, this, i,
          pixels);
    }
    renderers[NUM_THREADS - 1] = new Renderer(rays, (NUM_THREADS - 1) * delta,
        numPixels, this, NUM_THREADS - 1, pixels);

    // run the threads which will fill in the colors in the pixels array
    for (final Renderer renderer : renderers) {
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
