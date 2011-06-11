/**
 * MainFrame.java - the frame on which rendered images will be drawn
 */
package edu.bu.cs.cs480.main.gui;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * The frame on which rendered images will be drawn.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class MainFrame extends JFrame {
  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = 7151092822403566484L;
  /** The initial width of this frame. */
  public static final int INITIAL_WIDTH = 400;
  /** The initial height of this frame. */
  public static final int INITIAL_HEIGHT = 300;

  /**
   * Instantiates this frame with some initial settings.
   */
  public MainFrame() {
    this.setTitle("Ray Tracer Previewer");
    this.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Sets the image to display in this frame.
   * 
   * @param image
   *          The image to display in this frame.
   */
  public void setImage(final BufferedImage image) {
    this.setSize(image.getWidth(), image.getHeight());
    this.getContentPane().getGraphics().drawImage(image, 0, 0, null);
  }
}
