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
public class FlippedScaledCone extends Cylindrical implements Displayable {
  private final double xScale;
  private final double yScale;
  private final double zScale;

  /**
   * @param radius
   * @param height
   * @param glut
   */
  public FlippedScaledCone(final double radius, final double height,
      final double xScale, final double yScale, final double zScale,
      final GLUT glut) {
    super(radius, height, glut);
    this.xScale = xScale;
    this.yScale = yScale;
    this.zScale = zScale;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.drawing.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(GL gl) {
    gl.glCallList(this.callListHandle);
  }

  private int callListHandle;

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.drawing.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(GL gl) {
    this.callListHandle = gl.glGenLists(1);
    gl.glNewList(this.callListHandle, GL.GL_COMPILE);
    gl.glPushMatrix();
    gl.glTranslated(0, 0, -this.height());
    gl.glScaled(this.xScale, this.yScale, this.zScale);
    this.glut().glutSolidCone(this.radius(), this.height(),
        Circular.DEFAULT_SLICES, Circular.DEFAULT_STACKS);
    gl.glPopMatrix();
    gl.glEndList();
  }

}
