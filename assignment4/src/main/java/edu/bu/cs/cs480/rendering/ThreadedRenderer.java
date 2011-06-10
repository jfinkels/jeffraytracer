/**
 * ThreadedRenderer.java - 
 */
package edu.bu.cs.cs480.rendering;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface ThreadedRenderer  extends Renderer {
  void rendererFinished(final int threadID);
}
