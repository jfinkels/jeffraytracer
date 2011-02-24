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
public class Joint extends RotatableComponent implements Colorable, Displayable {

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
    this.cylinder = new SolidCylinder(x, y, z, radius, height, NUM_STEPS);
  }
  
  protected double x() {
    return this.cylinder.x();
  }
  
  protected double y() {
    return this.cylinder.y();
  }
  
  protected double z() {
    return this.cylinder.z();
  }

  private final SolidCylinder cylinder;

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

    this.cylinder.initialize(gl);
    this.displayListHandle = gl.glGenLists(1);
    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    gl.glPushMatrix();
    gl
        .glTranslated(this.cylinder.x(), this.cylinder.y(), this.cylinder
            .zTop());
    this.glut.glutSolidSphere(this.cylinder.radius(), 36, 18);
    gl.glPopMatrix();

    this.cylinder.draw(gl);
    gl.glEndList();
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

}
