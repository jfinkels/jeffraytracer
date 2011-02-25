/**
 * Palm.java - a model for the palm of a hand
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * A model for the palm of a hand as a sphere scaled in one direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Palm implements Displayable {

  private final double radius;
  private final GLUT glut;

  public Palm(final double radius, final GLUT glut) {
    this.radius = radius;
    this.glut = glut;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(GL gl) {
    gl.glCallList(this.callListHandle);
  }

  private int callListHandle;

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL gl) {
    this.callListHandle = gl.glGenLists(1);

    // create an ellipsoid for the palm by scaling a sphere
    gl.glNewList(this.callListHandle, GL.GL_COMPILE);
    gl.glPushMatrix();
    // position this so that the sphere is drawn above the x-y plane, not at the
    // origin
    gl.glTranslated(0, 0, this.radius);
    gl.glScalef(0.8f, 0.5f, 1);
    this.glut.glutSolidSphere(this.radius, 36, 18);
    gl.glPopMatrix();
    gl.glEndList();
  }

}
