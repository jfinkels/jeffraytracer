/**
 * 
 */
package edu.bu.cs.cs480.shapes;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.drawing.Displayable;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Ellipsoid extends Circular implements Displayable {

  private final double xScale = 1;
  private final double yScale = 0.8;
  private final double zScale = 0.8;

  /**
   * @param radius
   * @param glut
   */
  public Ellipsoid(final double radius, final GLUT glut) {
    super(radius, glut);

  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.drawing.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.callListHandle);
  }

  private int callListHandle;

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.drawing.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL gl) {
    this.callListHandle = gl.glGenLists(1);

    gl.glNewList(this.callListHandle, GL.GL_COMPILE);
    gl.glPushMatrix();
    gl.glScaled(this.xScale, this.yScale, this.zScale);
    this.glut().glutSolidSphere(this.radius(), Circular.DEFAULT_SLICES,
        Circular.DEFAULT_STACKS);
    gl.glPopMatrix();
    gl.glEndList();
  }

}
