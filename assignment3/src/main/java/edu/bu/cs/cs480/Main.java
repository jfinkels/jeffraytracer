/**
 * Main.java - the driver program for the vivarium simulation
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;

/**
 * The driver class which contains the main method for the vivarium simulation.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Main {
  /** The default width of the created window. */
  public static final int DEFAULT_WINDOW_HEIGHT = 640;
  /** The default height of the created window. */
  public static final int DEFAULT_WINDOW_WIDTH = 800;

  /**
   * Runs the vivarium simulation in a single JFrame.
   * 
   * @param args
   *          This parameter is ignored.
   */
  public static void main(final String[] args) {

    final RotationController rotationController = new RotationController();
    final DrawingController drawingController = new DrawingController();
    final KeyboardController keyboardController = new KeyboardController();

    final GLCapabilities capabilities = new GLCapabilities();
    capabilities.setDoubleBuffered(true);

    final GLCanvas canvas = new GLCanvas(capabilities);
    canvas.addGLEventListener(drawingController);
    canvas.addMouseListener(rotationController);
    canvas.addMouseMotionListener(rotationController);
    canvas.addKeyListener(keyboardController);

    // this is true by default, but we just add this line to be explicit
    canvas.setAutoSwapBufferMode(true);

    // refresh the scene at 60 frames per second
    final FPSAnimator animator = new FPSAnimator(canvas, 45);

    // make the drawing controller aware of the rotation controller, so that it
    // can draw the scene correctly rotated
    drawingController.setRotationController(rotationController);

    // make the keyboard controller aware of the rotation controller, so that
    // keyboard events can update the rotation
    keyboardController.setRotationController(rotationController);
    // make the keyboard controller aware of the drawing controller, so that
    // keyboard events can change the displayed scene
    keyboardController.setDrawingController(drawingController);
    // make the keyboard controller aware of the animator so that it can stop
    // the animation on an exit key event
    keyboardController.setAnimator(animator);

    // create the window
    final JFrame window = new JFrame();
    window.getContentPane().add(canvas);
    window.setTitle("CS480/CS680 : Vivarium simulation");
    window.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // start the animation
    animator.start();

    // display the window
    window.setVisible(true);
  }

}
