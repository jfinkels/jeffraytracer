/**
 * KeyboardController.java - the controller for keyboard interactions
 */
package edu.bu.cs.cs480;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.opengl.util.Animator;

/**
 * The controller for keyboard interactions.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class KeyboardController implements KeyListener {

  /** The object which animates the scene. */
  Animator animator = null;
  /** The controller for drawing to the screen. */
  private DrawingController drawingController = null;
  /** The controller for view rotations. */
  private RotationController rotationController = null;

  /**
   * This method is intentionally unimplemented.
   * 
   * @param key
   *          This parameter is ignored.
   */
  @Override
  public void keyPressed(final KeyEvent key) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param key
   *          This parameter is ignored.
   */
  @Override
  public void keyReleased(final KeyEvent key) {
    // intentionally unimplemented
  }

  /**
   * Interprets typed keys according to the following scheme:
   * 
   * R : resets the view to the initial rotation
   * 
   * F : adds a single piece of food to the tank
   * 
   * Q, Esc : exits the program
   * 
   * @param key
   *          {@inheritDoc}
   */
  @Override
  public void keyTyped(final KeyEvent key) {
    switch (key.getKeyChar()) {

    // quit the program
    case 'Q':
    case 'q':
    case KeyEvent.VK_ESCAPE:
      new Thread() {
        @Override
        public void run() {
          KeyboardController.this.animator.stop();
        }
      }.start();
      System.exit(0);
      break;

    // set the viewing quaternion to 0 rotation
    case 'R':
    case 'r':
      this.rotationController.rotation().reset();
      break;

    // add food to the tank
    case 'F':
    case 'f':
      this.drawingController.generateFood();
      break;

    default:
      break;
    }
  }

  /**
   * Sets the object which animates the scene.
   * 
   * @param animator
   *          The object which animates the scene.
   */
  public void setAnimator(final Animator animator) {
    this.animator = animator;
  }

  /**
   * Sets the drawing controller, so that the display can be updated when food
   * is added.
   * 
   * @param drawingController
   *          The controller for drawing to the screen.
   */
  public void setDrawingController(final DrawingController drawingController) {
    this.drawingController = drawingController;
  }

  /**
   * Sets the controller for view rotations, so that the display can be updated
   * based on the rotation requested by the user.
   * 
   * @param rotationController
   *          The controller for view rotations.
   */
  public void setRotationController(final RotationController rotationController) {
    this.rotationController = rotationController;
  }
}
