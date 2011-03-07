/**
 * RoundedCylinder.java - a solid cylinder with a rounded top
 */
package edu.bu.cs.cs480.shapes;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.drawing.Displayable;

/**
 * A solid cylinder with a rounded top.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class RoundedCylinder extends Circular implements Displayable {
  /**
   * The default number of slices to use when drawing the cylinder and the
   * sphere.
   */
  public static final int DEFAULT_SLICES = 36;
  /**
   * The default number of stacks to use when drawing the cylinder and the
   * sphere.
   */
  public static final int DEFAULT_STACKS = 28;

  /**
   * The OpenGL handle to the display list which contains all the components
   * which comprise this cylinder.
   */
  private int callListHandle;
  /** The height of this cylinder. */
  private final double height;

  /**
   * Instantiates this object with the specified radius and height of the
   * cylinder, and the GLUT object to use for drawing the cylinder and the
   * sphere at the top.
   * 
   * @param radius
   *          The radius of this cylinder.
   * @param height
   *          The height of this cylinder.
   * @param glut
   *          The OpenGL utility toolkit object to use to draw the cylinder and
   *          the sphere at the top.
   */
  public RoundedCylinder(final double radius, final double height,
      final GLUT glut) {
    super(radius, glut);
    this.height = height;
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.drawing.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.drawing.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL gl) {
    this.callListHandle = gl.glGenLists(1);

    gl.glNewList(this.callListHandle, GL.GL_COMPILE);

    this.glut().glutSolidCylinder(this.radius(), this.height, DEFAULT_SLICES,
        DEFAULT_STACKS);

    gl.glPushMatrix();
    gl.glTranslated(0, 0, this.height);
    this.glut().glutSolidSphere(this.radius(), DEFAULT_SLICES, DEFAULT_STACKS);
    gl.glPopMatrix();

    gl.glEndList();
  }
}
