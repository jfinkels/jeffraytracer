/**
 * Renderer.java - an object which can render a scene to an image
 */
package edu.bu.cs.cs480.rendering.renderers;

import java.awt.Image;

/**
 * An object which can render a scene to an image.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Renderer {
  /**
   * Renders a scene to an image.
   * 
   * @return The rendered image representing the scene.
   */
  Image render();
}
