/**
 * Main.java - the frame on which rendered images will be drawn
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jeffraytracer.io.FileFormatException;
import jeffraytracer.main.ImageCreator;

import org.apache.log4j.Logger;

/**
 * The frame on which rendered images will be drawn.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Main extends JFrame implements ActionListener {
  /** The logger for this class. */
  private static final transient Logger LOG = Logger.getLogger(Main.class);
  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = 7151092822403566484L;

  /**
   * Creates and displays the JFrame.
   * 
   * @param args
   *          This parameter is ignored.
   */
  public static void main(final String[] args) {
    new Main();
  }

  /** The panel which draws the rendered image. */
  private final ImagePanel imagePanel;
  /** The menu item for loading a model file. */
  private final JMenuItem loadModel;

  /**
   * Instantiates this frame with some initial settings.
   */
  public Main() {
    this.loadModel = new JMenuItem("Load model...", KeyEvent.VK_L);
    this.loadModel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
        ActionEvent.CTRL_MASK));
    this.loadModel.getAccessibleContext().setAccessibleDescription(
        "Choose a model file to render.");
    this.loadModel.addActionListener(this);

    final JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    fileMenu.getAccessibleContext().setAccessibleDescription("File menu.");
    fileMenu.add(this.loadModel);

    final JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileMenu);

    this.imagePanel = new ImagePanel();

    this.setJMenuBar(menuBar);
    this.getContentPane().add(this.imagePanel);

    this.setTitle("Jeffraytracer - Rendered Model Viewer");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(ImagePanel.EMPTY_WIDTH, ImagePanel.EMPTY_HEIGHT);

    this.setVisible(true);
  }

  /**
   * Responds to selection of the "Load model..." menu item by presenting a
   * file chooser dialog, then rendering and displaying the image.
   * 
   * @param event
   *          The event which triggered this method call.
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(final ActionEvent event) {
    if (event.getSource().equals(this.loadModel)) {
      final JFileChooser fileChooser = new JFileChooser();
      final int result = fileChooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        final File file = fileChooser.getSelectedFile();
        try {
          this.imagePanel.setImage(ImageCreator.fromFile(file));
          // resize this window so that it displays the entire image
          this.pack();
        } catch (final FileNotFoundException exception) {
          LOG.warn("File could not be found: " + file);
        } catch (final FileFormatException exception) {
          LOG.warn("File was in incorrect format: " + file);
        }
      }
    }
  }
}
