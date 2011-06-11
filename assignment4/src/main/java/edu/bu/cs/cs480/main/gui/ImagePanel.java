/**
 * ImagePanel.java - panel which draws an image
 */
package edu.bu.cs.cs480.main.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * The panel which contains the image to draw.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ImagePanel extends JPanel {
  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = 6697790610294450550L;
  /** The image to draw in this panel. */
  private BufferedImage image = null;

  /**
   * Sets the image to draw in this panel.
   * 
   * @param image
   *          The image to draw in this panel.
   */
  public void setImage(final BufferedImage image) {
    this.image = image;
    this.setSize(this.image.getWidth(), this.image.getHeight());
    this.repaint();
  }

  public static final int EMPTY_WIDTH = 400;
  public static final int EMPTY_HEIGHT = 300;

  @Override
  public Dimension getPreferredSize() {
    if (this.image == null) {
      return new Dimension(EMPTY_WIDTH, EMPTY_HEIGHT);
    }
    return new Dimension(this.image.getWidth(), this.image.getHeight());
  }

  /**
   * Draws the image.
   * 
   * @param graphics
   *          The graphics object to use to draw the image.
   */
  @Override
  public void paint(final Graphics graphics) {
    graphics.drawImage(this.image, 0, 0, null);
  }
}
