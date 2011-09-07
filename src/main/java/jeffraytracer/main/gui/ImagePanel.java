/**
 * ImagePanel.java - panel which draws an image
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer.main.gui;

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

  /** The width of the panel when no image is being drawn. */
  public static final int EMPTY_WIDTH = 400;
  /** The height of the panel when no image is being drawn. */
  public static final int EMPTY_HEIGHT = 300;

  /**
   * Returns the dimensions of the image which is being drawn, or if no image
   * is being drawn, some reasonable default.
   * 
   * @return The dimensions of the image being drawn, or if no image is being
   *         drawn, some reasonable default.
   */
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
