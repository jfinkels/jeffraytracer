// ****************************************************************************
// Example Main Program for CS480 Programming Assignment 3
// ****************************************************************************
// Description: This is a simple example program that demonstrates a teapot
// spinning in a tank.
//
// The following keys control the program:
//
// Q,q, <escape>: quit
// R: reset viewing angle
//
// Left mouse click + drag motion: rotate the tank
//
//
// ****************************************************************************
// History :
// 3 Mar 2008 Created by Tai-Peng Tian based on the C code by Stan Sclaroff
//
package edu.bu.cs.cs480;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;

public class Main {
  /** The default width of the created window. */
  public static final int DEFAULT_WINDOW_HEIGHT = 512;
  /** The default height of the created window. */
  public static final int DEFAULT_WINDOW_WIDTH = 512;
  /**
   * Runs the hand simulation in a single JFrame.
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
    final FPSAnimator animator = new FPSAnimator(canvas, 60);

    // make the drawing controller aware of the rotation controller, so that it
    // can draw the scene correctly rotated
    drawingController.setRotationController(rotationController);

    // make the keyboard controller aware of the rotation controller, so that
    // keyboard events can update the rotation
    keyboardController.setRotationController(rotationController);
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
