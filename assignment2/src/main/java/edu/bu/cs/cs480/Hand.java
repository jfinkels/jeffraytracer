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
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2008
 */
public class Hand extends RotatableComponent implements UpdatingDisplayable,
    Colorable {

  /** The OpenGL handle (integer) for the OpenGL call list object. */
  private int displayListHandle;
  /** The fingers of this hand, indexed from thumb to pinky. */
  private final Finger[] fingers = new Finger[] { new Finger(-.5, 0, .7),
      new Finger(-.2, 0, .7), new Finger(0.1, 0, .7), new Finger(0.4, 0, 0.7),
      new Thumb(0.5, 0, 0) };
  /** The OpenGL utility toolkit object. */
  private GLUT glut = null;

  private FloatColor color = FloatColor.ORANGE;

  public void setColor(final FloatColor color) {
    this.color = color;
  }

  private final double x;
  private final double y;
  private final double z;

  /**
   * Instantiates this hand.
   */
  public Hand(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    
    // set limits on how far the wrist can bend
    this.setXPositiveExtent(70);
    this.setXNegativeExtent(-90);
    this.setYPositiveExtent(0);
    this.setYNegativeExtent(0);
    this.setZPositiveExtent(15);
    this.setZNegativeExtent(-15);
  }
  
  double x() { return this.x; }
  double y() {return this.y;}
  double z() {return this.z;}

  public void setGlut(final GLUT glut) {
    this.glut = glut;
    for (final Finger finger : this.fingers) {
      finger.setGlut(glut);
    }
  }

  /**
   * Draws the hand on the specified OpenGL object based on the current state.
   * 
   * The {@link #initialize(GL)} method must be called before this method.
   * 
   * @param gl
   *          The GL object with which to draw the hand.
   */
  public void draw(final GL gl) {
    gl.glCallList(this.displayListHandle);
  }

  /**
   * Gets the index finger.
   * 
   * @return The index finger.
   */
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
  public void initialize(final GL gl) {
    this.displayListHandle = gl.glGenLists(1);

    // initialize each finger
    for (final Finger finger : this.fingers) {
      finger.initialize(gl);
    }
  }

  /**
   * Gets the middle finger.
   * 
   * @return The middle finger.
   */
  public Finger middleFinger() {
    return this.fingers[2];
  }

  /**
   * Gets the pinky finger.
   * 
   * @return The pinky finger.
   */
  public Finger pinkyFinger() {
    return this.fingers[4];
  }

  /**
   * Gets the ring finger.
   * 
   * @return The ring finger.
   */
  public Finger ringFinger() {
    return this.fingers[3];
  }

  /**
   * Gets the thumb.
   * 
   * @return The thumb.
   */
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

    // update the state of each of the fingers
    for (final Finger finger : this.fingers) {
      finger.update(gl);
    }

    if (this.glut == null) {
      throw new RuntimeException(
          "The GLUT object must be set on this joint before updating the joint model.");
    }

    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    gl.glPushMatrix();

    // push the current color
    gl.glPushAttrib(GL.GL_CURRENT_BIT);
    gl.glColor3f(this.color.red(), this.color.green(), this.color.blue());

    // rotate around x axis
    gl.glRotated(this.xAngle(), 1, 0, 0);
    // rotate around y axis
    gl.glRotated(this.yAngle(), 0, 1, 0);
    // rotate around z axis
    gl.glRotated(this.zAngle(), 0, 0, 1);

    // create an ellipsoid for the palm by scaling a sphere
    gl.glPushMatrix();
    gl.glScalef(1.0f, 1.0f, 0.5f);
    this.glut.glutSolidSphere(1, 36, 18);
    gl.glPopMatrix();

    // draw the fingers
    for (final Finger finger : this.fingers) {
      finger.draw(gl);
    }

    // pop the current color
    gl.glPopAttrib();

    gl.glPopMatrix();

    gl.glEndList();
  }
}
