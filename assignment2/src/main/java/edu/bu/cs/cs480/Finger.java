/**
 * Finger.java - a finger with three joints and with OpenGL drawing methods
 */
package edu.bu.cs.cs480;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * A finger which has three joints, and which can draw itself using OpenGL.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Finger implements UpdatingDisplayable {
  /** The OpenGL handle (integer) for the OpenGL call list object. */
  private int displayListHandle;
  /** The distal joint of this finger. */
  private final Joint distal = new Joint(0, 0, 1.0, .1, 0.3);
  /** The middle joint of this finger. */
  private final Joint middle = new Joint(0, 0, 0.5, .1, 0.5);
  /** The palm joint of this finger. */
  private final Joint palm = new Joint(0, 0, 0, .1, 0.5);
  /**
   * An immutable view of the joints of this finger.
   * 
   * This declaration must come after the three joints have been initialized.
   */
  private final List<Joint> joints = Collections.unmodifiableList(Arrays
      .asList(this.palm, this.middle, this.distal));

  /** The x component of the position of this finger. */
  private final double x;
  /** The y component of the position of this finger. */
  private final double y;
  /** The z component of the position of this finger. */
  private final double z;

  /**
   * Instantiates this finger at the specified (x, y, z) position.
   * 
   * @param x
   *          The x component of the position of this finger.
   * @param y
   *          The y component of the position of this finger.
   * @param z
   *          The z component of the position of this finger.
   */
  public Finger(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;

    // set limits on how far the joints can bend
    this.distal.setXPositiveExtent(80);
    this.distal.setXNegativeExtent(-10);
    this.distal.setYPositiveExtent(0);
    this.distal.setYNegativeExtent(0);
    this.distal.setZPositiveExtent(0);
    this.distal.setZNegativeExtent(0);

    this.middle.setXPositiveExtent(160);
    this.middle.setXNegativeExtent(0);
    this.middle.setYPositiveExtent(0);
    this.middle.setYNegativeExtent(0);
    this.middle.setZPositiveExtent(0);
    this.middle.setZNegativeExtent(0);

    this.palm.setXPositiveExtent(90);
    this.palm.setXNegativeExtent(-10);
    this.palm.setYPositiveExtent(15);
    this.palm.setYNegativeExtent(-15);
    this.palm.setZPositiveExtent(0);
    this.palm.setZNegativeExtent(0);
  }

  /**
   * Gets the distal joint of this finger.
   * 
   * @return The distal joint of this finger.
   */
  public Joint distal() {
    return this.distal;
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  public void draw(final GL gl) {
    gl.glCallList(this.displayListHandle);
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  public void initialize(final GL gl) {
    this.displayListHandle = gl.glGenLists(1);
    for (final Joint joint : this.joints) {
      joint.initialize(gl);
    }
  }

  /**
   * Gets an immutable view on the joints of this finger.
   * 
   * @return An immutable view on the joints of this finger.
   */
  public List<Joint> joints() {
    return this.joints;
  }

  /**
   * Gets the middle joint of this finger.
   * 
   * @return The middle joint of this finger.
   */
  public Joint middle() {
    return this.middle;
  }

  /**
   * Gets the palm joint of this finger.
   * 
   * @return The palm joint of this finger.
   */
  public Joint palm() {
    return this.palm;
  }

  /**
   * Calls the {@link Joint#setGlut(GLUT)} method on the joints of this finger
   * providing the specified GLUT object as the parameter.
   * 
   * @param glut
   *          The GLUT object to use to draw spheres on the ends of the joints.
   */
  public void setGlut(final GLUT glut) {
    for (final Joint joint : this.joints) {
      joint.setGlut(glut);
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  public void update(final GL gl) {
    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    gl.glPushMatrix();

    // rotate the finger so that it is pointing up
    gl.glRotated(-90, 1, 0, 0);

    // translate the finger to the right place
    gl.glTranslated(this.x, this.y, this.z);

    // rotate the palm joint
    gl.glPushMatrix();

    // push the current color
    gl.glPushAttrib(GL.GL_CURRENT_BIT);

    gl.glColor3f(this.palm().color().red(), this.palm().color().green(), this
        .palm().color().blue());

    // translate out
    gl.glTranslated(0, 0, 0);
    // rotate around x axis
    gl.glRotated(this.palm.xAngle(), 1, 0, 0);
    // rotate around y axis
    gl.glRotated(this.palm.yAngle(), 0, 1, 0);
    // rotate around z axis
    gl.glRotated(this.palm.zAngle(), 0, 0, 1);
    // translate in
    gl.glTranslated(0, 0, 0);

    // draw the distal joint
    this.palm.draw(gl);

    // pop the current color
    gl.glPopAttrib();

    // rotate the middle joint
    gl.glPushMatrix();

    // push the current color
    gl.glPushAttrib(GL.GL_CURRENT_BIT);

    gl.glColor3f(this.middle().color().red(), this.middle().color().green(),
        this.middle().color().blue());

    // translate out
    gl.glTranslated(0, 0, 0.5);
    // rotate around x axis
    gl.glRotated(this.middle.xAngle(), 1, 0, 0);
    // rotate around y axis
    gl.glRotated(this.middle.yAngle(), 0, 1, 0);
    // rotate around z axis
    gl.glRotated(this.middle.zAngle(), 0, 0, 1);
    // translate in
    gl.glTranslated(0, 0, -.5);

    // draw the distal joint
    this.middle.draw(gl);

    // pop color
    gl.glPopAttrib();

    // rotate the distal joint
    gl.glPushMatrix();

    // push the current color
    gl.glPushAttrib(GL.GL_CURRENT_BIT);

    gl.glColor3f(this.distal().color().red(), this.distal().color().green(),
        this.distal().color().blue());

    // translate out
    gl.glTranslated(0, 0, 1);
    // rotate around x axis
    gl.glRotated(this.distal.xAngle(), 1, 0, 0);
    // rotate around y axis
    gl.glRotated(this.distal.yAngle(), 0, 1, 0);
    // rotate around z axis
    gl.glRotated(this.distal.zAngle(), 0, 0, 1);
    // translate in
    gl.glTranslated(0, 0, -1);

    // draw the distal joint
    this.distal.draw(gl);

    // pop the current color
    gl.glPopAttrib();

    gl.glPopMatrix(); // distal joint rotation

    gl.glPopMatrix(); // middle joint rotation

    gl.glPopMatrix(); // palm joint rotation

    gl.glPopMatrix(); // putting finger in the right orientation

    gl.glEndList();
  }

  /**
   * Gets the x component of the position of this finger.
   * 
   * @return The x component of the position of this finger.
   */
  public double x() {
    return this.x;
  }

  /**
   * Gets the y component of the position of this finger.
   * 
   * @return The y component of the position of this finger.
   */
  public double y() {
    return this.y;
  }

  /**
   * Gets the z component of the position of this finger.
   * 
   * @return The z component of the position of this finger.
   */
  public double z() {
    return this.z;
  }
}
