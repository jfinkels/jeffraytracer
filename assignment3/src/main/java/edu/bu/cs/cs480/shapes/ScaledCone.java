/**
 * ScaledCone.java - a cone scaled in at most three directions
 */
package edu.bu.cs.cs480.shapes;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.drawing.Displayable;

/**
 * A cone scaled in at most three directions.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ScaledCone extends Cylindrical implements Displayable {
  /** The handle to the OpenGL call list to use to draw this cone. */
  private int callListHandle;
  /** The amount between 0 and 1 to scale this cone in the x direction. */
  private final double xScale;
  /** The amount between 0 and 1 to scale this cone in the y direction. */
  private final double yScale;
  /** The amount between 0 and 1 to scale this cone in the z direction. */
  private final double zScale;

  /**
   * Instantiates this object with the specified height and radius.
   * 
   * @param radius
   *          The radius of this cylinder.
   * @param height
   *          The height of this cylinder.
   * @param xScale
   *          The amount between 0 and 1 to scale this cone in the x direction.
   * @param yScale
   *          The amount between 0 and 1 to scale this cone in the y direction.
   * @param zScale
   *          The amount between 0 and 1 to scale this cone in the z direction.
   * @param glut
   *          The OpenGL utility toolkit object used to draw this circular
   *          object.
   */
  public ScaledCone(final double radius, final double height,
      final double xScale, final double yScale, final double zScale,
      final GLUT glut) {
    super(radius, height, glut);
    this.xScale = xScale;
    this.yScale = yScale;
    this.zScale = zScale;
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.drawing.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(GL gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * Generates and defines the call list used to draw this cone.
   * 
   * @param gl
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.drawing.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(GL gl) {
    this.callListHandle = gl.glGenLists(1);
    gl.glNewList(this.callListHandle, GL.GL_COMPILE);
    gl.glPushMatrix();
    gl.glScaled(this.xScale, this.yScale, this.zScale);
    this.glut().glutSolidCone(this.radius(), this.height(),
        Circular.DEFAULT_SLICES, Circular.DEFAULT_STACKS);
    gl.glPopMatrix();
    gl.glEndList();
  }

}
