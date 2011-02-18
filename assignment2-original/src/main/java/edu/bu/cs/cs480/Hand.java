// ****************************************************************************
// Hand class.
// ****************************************************************************
// Comments :
// Support Routines for a Simple Hand Model. To complete the assignment,
// add in the necessary data structure and code to implement functionalities
// for manipulating and drawing the hand model. As a place holder, we have
// draw a simple ellipse for demonstration purposes.
//
//
// History :
// 16 Jan 08 Created by Tai-Peng Tian (tiantp@gmail.com) based on C code by
// Stan Sclaroff (from CS480 '06 Hand.c )
//
//
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * A model of a hand containing an ellipsoid for the palm, four fingers and a
 * thumb.
 * 
 * This class provides methods for rotating individual joints on individual
 * fingers as well.
 */
public class Hand {

  /** The x-, y-, or z-axis. */
  enum AxisType {
    X, Y, Z
  };

  /** The type of finger to toggle. */
  enum FingerType {
    FIFTH, FIRST, FOURTH, SECOND, THIRD
  };

  /** The type of joint at which to enable rotation. */
  enum JointType {
    DISTAL, MIDDLE, PALM
  };

  /**
   * The change in rotation angle (in degrees) to apply on each rotation update.
   */
  private static final double DELTA_ANGLE = 1.0;
  /** The currently active fingers. */
  private final boolean active_fingers[] = new boolean[] { false, false, false,
      false, false };
  /** The currently active joint type. */
  private JointType active_joint = JointType.DISTAL;
  /** The currently active axis of rotation. */
  private AxisType active_rotation_axis = AxisType.X;
  /** The OpenGL handle (integer) for the OpenGL call list object. */
  private int hand_object;
  /** Whether the state of the hand model has changed since the last update. */
  private boolean state_has_changed = false;

  /**
   * Decreases the rotation of the currently selected joint on the currently
   * selected fingers by {@value #DELTA_ANGLE} degrees.
   */
  public void decrement_rotation_angle() {
    state_has_changed = true; // flag to recreate the display list

    // you will need to rewrite this function
    System.out.println("Hand::decrement_rotation_angle()");
  }

  /**
   * Draws the hand on the specified OpenGL object based on the current state.
   * 
   * @param gl
   *          The GL object with which to draw the hand.
   */
  public void draw(final GL gl) {
    gl.glCallList(this.hand_object);
  }

  /**
   * Increases the rotation of the currently selected joint on the currently
   * selected fingers by {@value #DELTA_ANGLE} degrees.
   */
  public void increment_rotation_angle() {
    state_has_changed = true; // flag to recreate the display list

    // you will need to rewrite this function
    System.out.println("Hand::increment_rotation_angle()");
  }

  /**
   * Uses the specified OpenGL object to create a new OpenGL call list on which
   * to draw the hand and fingers.
   * 
   * @param gl
   *          The OpenGL object from which to get a handle (which is just a
   *          unique integer) for a call list.
   */
  public void init(final GL gl) {
    this.hand_object = gl.glGenLists(1);
    this.update(gl);
  }

  /**
   * Activates the specified type of joint for rotation.
   * 
   * @param joint
   *          The type of joint to rotate.
   */
  public void set_joint(final JointType joint) {
    switch (joint) {
    case DISTAL:
      System.out.println(" Distal Chosen ");
      break;
    case PALM:
      System.out.println(" Palm Chosen ");
      break;
    case MIDDLE:
      System.out.println(" Middle Chosen ");
      break;
    }
    active_joint = joint;
  }

  /**
   * Sets the axis around which to rotate.
   * 
   * @param axis
   *          The axis around which to rotate.
   */
  public void set_rotation_axis(AxisType axis) {
    switch (axis) {
    case X:
      System.out.println("X-axis chosen");
      break;
    case Y:
      System.out.println("Y-axis chosen");
      break;
    case Z:
      System.out.println("Z-axis chosen");
      break;
    }
    active_rotation_axis = axis;
  }

  /**
   * Toggles the specified finger to be affected by rotations.
   * 
   * @param finger
   *          The finger to toggle.
   */
  public void toggle_finger(FingerType finger) {
    int loc = 0;

    switch (finger) {
    case FIRST:
      loc = 0;
      System.out.println(" Toggling First Finger");
      break;
    case SECOND:
      loc = 1;
      System.out.println(" Toggling Second Finger");
      break;
    case THIRD:
      loc = 2;
      System.out.println(" Toggling Third Finger");
      break;
    case FOURTH:
      loc = 3;
      System.out.println(" Toggling Fourth Finger");
      break;
    case FIFTH:
      loc = 4;
      System.out.println(" Toggling Fifth Finger");
      break;
    }
    active_fingers[loc] = !active_fingers[loc];
  }

  public void update(GL gl) {
    if (state_has_changed) {
      gl.glNewList(hand_object, GL.GL_COMPILE);

      // You need to rewrite the code for constructing the display
      // list

      // Create an ellipsoid by scaling a sphere
      gl.glColor3f(0.8f, 0.5f, 0.2f);
      GLUT glut = new GLUT();
      gl.glScalef(1.0f, 1.0f, 0.5f);
      glut.glutSolidSphere(1, 36, 18);

      gl.glEndList();

      state_has_changed = false;
    }
  }
}
