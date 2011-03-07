/**
 * 
 */
package edu.bu.cs.cs480;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.opengl.util.Animator;

/**
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class KeyboardController implements KeyListener {

  /**
   * This method is intentionally unimplemented.
   * 
   * @param key
   *          This parameter is ignored.
   */
  public void keyPressed(final KeyEvent key) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param key
   *          This parameter is ignored.
   */
  public void keyReleased(final KeyEvent key) {
    // intentionally unimplemented
  }

  /**
   * Interprets typed keys according to the following scheme:
   * 
   * R : resets the view to the initial rotation
   * 
   * Q, Esc : exits the program
   * 
   * @param key
   *          {@inheritDoc}
   */
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

    default:
      break;
    }
  }

  private RotationController rotationController = null;

  public void setRotationController(final RotationController rotationController) {
    this.rotationController = rotationController;
  }

  private Animator animator = null;

  public void setAnimator(final Animator animator) {
    this.animator = animator;
  }
}
