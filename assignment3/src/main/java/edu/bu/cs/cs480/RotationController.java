/**
 * RotationController.java - controller for rotation based on mouse events
 */
package edu.bu.cs.cs480;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import edu.bu.cs.cs480.model.Quaternion;

/**
 * The object which manages rotation of a scene caused by mouse movements.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class RotationController implements MouseListener, MouseMotionListener {
  /** The x component of the position of the last mouse press. */
  private int lastX = 0;
  /** The y component of the position of the last mouse press. */
  private int lastY = 0;
  /** Whether the world is being rotated. */
  private boolean rotateWorld = false;
  /** The quaternion which controls the rotation of the world. */
  private Quaternion viewingRotation = new Quaternion();

  /**
   * Gets the current view rotation quaternion.
   * 
   * @return The current view rotation quaternion.
   */
  public Quaternion rotation() {
    return this.viewingRotation;
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseClicked(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * Updates the rotation quaternion as the mouse is dragged.
   * 
   * @param mouse
   *          The mouse drag event object.
   */
  public void mouseDragged(final MouseEvent mouse) {
    if (this.rotateWorld) {
      // get the current position of the mouse
      final int x = mouse.getX();
      final int y = mouse.getY();

      // get the change in position from the previous one
      final int dx = x - this.lastX;
      final int dy = y - this.lastY;

      // create a unit vector in the direction of the vector (dy, dx, 0)
      final double magnitude = Math.sqrt(dx * dx + dy * dy);
      final float[] axis = new float[] { (float) (dy / magnitude),
          (float) (dx / magnitude), 0 };

      // calculate appropriate quaternion
      final float viewing_delta = 3.1415927f / 180.0f;
      final float s = (float) Math.sin(0.5f * viewing_delta);
      final float c = (float) Math.cos(0.5f * viewing_delta);
      final Quaternion Q = new Quaternion(c, s * axis[0], s * axis[1], s
          * axis[2]);
      this.viewingRotation = Q.multiply(this.viewingRotation);

      // normalize to counteract acccumulating round-off error
      this.viewingRotation.normalize();

      // save x, y as last x, y
      this.lastX = x;
      this.lastY = y;
    }
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseEntered(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseExited(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseMoved(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * Starts rotating the world if the left mouse button was released.
   * 
   * @param mouse
   *          The mouse press event object.
   */
  public void mousePressed(final MouseEvent mouse) {
    if (mouse.getButton() == MouseEvent.BUTTON1) {
      this.lastX = mouse.getX();
      this.lastY = mouse.getY();
      this.rotateWorld = true;
    }
  }

  /**
   * Stops rotating the world if the left mouse button was released.
   * 
   * @param mouse
   *          The mouse release event object.
   */
  public void mouseReleased(final MouseEvent mouse) {
    if (mouse.getButton() == MouseEvent.BUTTON1) {
      this.rotateWorld = false;
    }
  }

}
