/**
 * ThreadedTracer.java - a tracer which employs threads to split up the work
 */
package edu.bu.cs.cs480.rendering;

/**
 * A tracer which employs threads to split up the work.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface ThreadedTracer extends Tracer {
  /**
   * This method is called by the worker thread with the specified ID which has
   * finished tracing.
   * 
   * @param threadID
   *          The ID of the thread which has finished tracing.
   */
  void threadFinished(final int threadID);
}
