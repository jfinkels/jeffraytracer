/**
 * Joint.java - a single joint of a finger which can be rotated
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * A joint which can be rotated.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Joint extends SolidCylinder {

  /**
   * The number of steps to use to approximate the circumference of the cylinder
   * (a higher number means more polygons and better appearance, but slower
   * rendering).
   */
  public static final int NUM_STEPS = 100;
  /** The color of this joint. */
  private FloatColor color = FloatColor.ORANGE;
  /** The OpenGL handle (integer) for the OpenGL call list object. */
  private int displayListHandle;
  /**
   * The GLUT object with which to draw the sphere at the top of this joint.
   * 
   * This member must be set to a non-null object before this joint can be
   * drawn.
   */
  private GLUT glut = null;
  /** The current angle at which this joint is rotated around the x axis. */
  private double xAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the x axis. */
  private double xNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the x axis. */
  private double xPositiveExtent = 180;
  /** The current angle at which this joint is rotated around the y axis. */
  private double yAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the y axis. */
  private double yNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the y axis. */
  private double yPositiveExtent = 180;
  /** The current angle at which this joint is rotated around the z axis. */
  private double zAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the z axis. */
  private double zNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the z axis. */
  private double zPositiveExtent = 180;

  /**
   * Instantiates this joint at the specified (x, y, z) position and the
   * specified radius and height.
   * 
   * @param x
   *          The x component of the position of this joint.
   * @param y
   *          The y component of the position of this joint.
   * @param z
   *          The z component of the position of this joint.
   * @param radius
   *          The radius of this joint.
   * @param height
   *          The height of this joint.
   */
  public Joint(final double x, final double y, final double z,
      final double radius, final double height) {
    super(x, y, z, radius, height, NUM_STEPS);
  }

  /**
   * Gets the color of this joint.
   * 
   * @return The color of this joint.
   */
  public FloatColor color() {
    return this.color;
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.displayListHandle);
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  @Override
  public void initialize(final GL gl) {
    if (this.glut == null) {
      throw new RuntimeException(
          "The GLUT object must be set on this joint before updating the joint model.");
    }

    super.initialize(gl);
    this.displayListHandle = gl.glGenLists(1);
    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    gl.glPushMatrix();
    gl.glTranslated(this.x(), this.y(), this.zTop());
    this.glut.glutSolidSphere(this.radius(), 36, 18);
    gl.glPopMatrix();

    super.draw(gl);
    gl.glEndList();
  }

  /**
   * Rotates this joint around the specified axis by the specified angle.
   * 
   * @param axis
   *          The axis of rotation.
   * @param angleDelta
   *          The angle by which to rotate this joint.
   */
  public void rotate(final Axis axis, final double angleDelta) {
    if (axis.equals(Axis.X)) {
      this.xAngle += angleDelta;
      this.xAngle = Math.min(this.xAngle, this.xPositiveExtent);
      this.xAngle = Math.max(this.xAngle, this.xNegativeExtent);
    } else if (axis.equals(Axis.Y)) {
      this.yAngle += angleDelta;
      this.yAngle = Math.min(this.yAngle, this.yPositiveExtent);
      this.yAngle = Math.max(this.yAngle, this.yNegativeExtent);
    } else if (axis.equals(Axis.Z)) {
      this.zAngle += angleDelta;
      this.zAngle = Math.min(this.zAngle, this.zPositiveExtent);
      this.zAngle = Math.max(this.zAngle, this.zNegativeExtent);
    }
  }

  /**
   * Sets the color of this joint.
   * 
   * @param color
   *          The color of this joint.
   */
  public void setColor(final FloatColor color) {
    this.color = color;
  }

  /**
   * Sets the GLUT object for drawing spheres.
   * 
   * @param glut
   *          The GLUT object.
   */
  public void setGlut(final GLUT glut) {
    this.glut = glut;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXNegativeExtent
   *          The minimum angle to which this joint can be rotated around the x
   *          axis.
   */
  public void setXNegativeExtent(final double newXNegativeExtent) {
    this.xNegativeExtent = newXNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXPositiveExtent
   *          The maximum angle to which this joint can be rotated around the x
   *          axis.
   */
  public void setXPositiveExtent(final double newXPositiveExtent) {
    this.xPositiveExtent = newXPositiveExtent;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYNegativeExtent
   *          The minimum angle to which this joint can be rotated around the y
   *          axis.
   */
  public void setYNegativeExtent(final double newYNegativeExtent) {
    this.yNegativeExtent = newYNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYPositiveExtent
   *          The maximum angle to which this joint can be rotated around the y
   *          axis.
   */
  public void setYPositiveExtent(final double newYPositiveExtent) {
    this.yPositiveExtent = newYPositiveExtent;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZNegativeExtent
   *          The minimum angle to which this joint can be rotated around the z
   *          axis.
   */
  public void setZNegativeExtent(final double newZNegativeExtent) {
    this.zNegativeExtent = newZNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZPositiveExtent
   *          The maximum angle to which this joint can be rotated around the z
   *          axis.
   */
  public void setZPositiveExtent(final double newZPositiveExtent) {
    this.zPositiveExtent = newZPositiveExtent;
  }

  /**
   * Gets the current angle at which this joint is rotated around the x axis.
   * 
   * @return The current angle at which this joint is rotated around the x axis.
   */
  public double xAngle() {
    return this.xAngle;
  }

  /**
   * Gets the current angle at which this joint is rotated around the y axis.
   * 
   * @return The current angle at which this joint is rotated around the y axis.
   */
  public double yAngle() {
    return this.yAngle;
  }

  /**
   * Gets the current angle at which this joint is rotated around the z axis.
   * 
   * @return The current angle at which this joint is rotated around the z axis.
   */
  public double zAngle() {
    return this.zAngle;
  }
}
