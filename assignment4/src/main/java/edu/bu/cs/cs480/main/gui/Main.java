/**
 * Main.java - displays a GUI which draws images as they are rendered
 */
package edu.bu.cs.cs480.main.gui;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.io.FileFormatException;
import edu.bu.cs.cs480.main.ImageCreator;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Main {
  /** The logger for this class. */
  public static final transient Logger LOG = Logger.getLogger(Main.class);

  /**
   * Renders and displays the images whose model files are specified in the
   * arguments array.
   * 
   * @param args
   *          Each element of this array is a path to a model file which will
   *          be rendered.
   */
  public static void main(String[] args) {
    // TODO use an options parsing library
    if (args.length < 1) {
      LOG.error("Must provide at least one model file to render.");
      return;
    }

    final MainFrame frame = new MainFrame();
    frame.setVisible(true);

    for (final String filename : args) {
      try {
        LOG.debug("Rendering image from model " + filename);
        frame.setImage(ImageCreator.fromFile(filename));
      } catch (final FileNotFoundException exception) {
        LOG.error(exception);
      } catch (final FileFormatException exception) {
        LOG.error(exception);
      }
    }
  }
}
