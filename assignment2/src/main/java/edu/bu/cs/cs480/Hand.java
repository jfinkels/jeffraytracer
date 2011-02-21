/**
 * Hand.java - hand model which can draw itself in OpenGL
 * 
 * History:
 * 
 * 18 February 2011
 * 
 * - added documentation
 * 
 * (Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>)
 * 
 * 16 January 2008
 * 
 * - translated from C code by Stan Sclaroff from CS480 2006 assignment 2,
 * "hand.c"
 * 
 * (Tai-Peng Tian <tiantp@gmail.com>)
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * A model of a hand containing an ellipsoid for the palm, four fingers and a
 * thumb.
 * 
 * This class provides methods for rotating individual joints on individual
 * fingers as well.
 * 
 * @author Tai-Peng Tian <tiantp@gmail.com>
 * @since Spring 2008
 */
public class Hand implements Displayable {

  /** The OpenGL handle (integer) for the OpenGL call list object. */
  private int displayListHandle;
  /** The fingers of this hand, indexed from thumb to pinky. */
  private final Finger[] fingers = new Finger[] { new Finger(-.5, 0, .7),
      new Finger(-.2, 0, .7), new Finger(0.1, 0, .7), new Finger(0.4, 0, 0.7),
      new Finger(0.7, 0, 0.7) };
  /** The OpenGL utility toolkit object. */
  private final GLUT glut;

  /**
   * Instantiates this hand with access to the specified OpenGL utility toolkit
   * object.
   * 
   * @param glut
   *          The OpenGL utility toolkit object.
   */
  public Hand(final GLUT glut) {
    this.glut = glut;
  }

  /**
   * Draws the hand on the specified OpenGL object based on the current state.
   * 
   * The {@link #init(GL)} method must be called before this method.
   * 
   * @param gl
   *          The GL object with which to draw the hand.
   */
  public void draw(final GL gl) {
    gl.glCallList(this.displayListHandle);
    for (final Finger finger : this.fingers) {
      finger.draw(gl);
    }
  }

  public Finger indexFinger() {
    return this.fingers[1];
  }

  /**
   * Uses the specified OpenGL object to create a new OpenGL call list on which
   * to draw the hand and fingers.
   * 
   * This method must be called before the {@link #draw(GL)} method.
   * 
   * @param gl
   *          The OpenGL object from which to get a handle (which is just a
   *          unique integer) for a call list.
   */
  public void init(final GL gl) {
    this.displayListHandle = gl.glGenLists(1);

    // create an ellipsoid by scaling a sphere
    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);
    gl.glPushMatrix();
    gl.glColor3f(0.8f, 0.5f, 0.2f);
    gl.glScalef(1.0f, 1.0f, 0.5f);
    this.glut.glutSolidSphere(1, 36, 18);
    gl.glPopMatrix();
    gl.glEndList();

    // initialize each finger
    for (final Finger finger : this.fingers) {
      finger.init(gl);
    }

    this.update(gl);
  }

  public Finger middleFinger() {
    return this.fingers[2];
  }

  public Finger pinkyFinger() {
    return this.fingers[4];
  }

  public Finger ringFinger() {
    return this.fingers[3];
  }

  public Finger thumb() {
    return this.fingers[0];
  }

  /**
   * Updates the current model of the hand and fingers.
   * 
   * @param gl
   *          The OpenGL object with which to draw the hand and fingers.
   */
  public void update(final GL gl) {
    for (final Finger finger : this.fingers) {
      finger.update(gl);
    }
  }
}
