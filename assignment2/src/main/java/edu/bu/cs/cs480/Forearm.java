/**
 * 
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * @author Jeffrey Finkelstein
 * 
 */
public class Forearm extends Joint implements UpdatingDisplayable {

  private final Hand hand;
  
  /**
   * @param x
   * @param y
   * @param z
   * @param radius
   * @param height
   */
  public Forearm(final double x, final double y, final double z,
      final double radius, final double height) {
    super(x, y, z, radius, height);
    this.hand = new Hand(x, y, z + height);
  }
  
  @Override
  public void setGlut(final GLUT glut) {
    super.setGlut(glut);
    this.hand.setGlut(glut);
  }

  private int displayListHandle;
  
  @Override
  public void initialize(final GL gl) {
    super.initialize(gl);
    this.hand.initialize(gl);
    this.displayListHandle = gl.glGenLists(1);
  }
  
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.displayListHandle);
  }
  
  /* (non-Javadoc)
   * @see edu.bu.cs.cs480.UpdatingDisplayable#update(javax.media.opengl.GL)
   */
  @Override
  public void update(final GL gl) {
    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    gl.glPushMatrix();

    // rotate this so that it's pointing in the right direction
    //gl.glRotated(90, 0, 0, 0);
    
    // push the current color
    gl.glPushAttrib(GL.GL_CURRENT_BIT);
    gl.glColor3f(this.color().red(), this.color().green(), this.color().blue());

    // translate out
    gl.glTranslated(this.x(), this.y(), this.z());
    // rotate around x axis
    gl.glRotated(this.xAngle(), 1, 0, 0);
    // rotate around y axis
    gl.glRotated(this.yAngle(), 0, 1, 0);
    // rotate around z axis
    gl.glRotated(this.zAngle(), 0, 0, 1);
    // translate in
    gl.glTranslated(-this.x(), -this.y(), -this.z());

    // draw the upper arm
    super.draw(gl);

    // pop the current color
    gl.glPopAttrib();

    // move the hand to the right place, and draw it
    gl.glTranslated(this.hand.x(), this.hand.y(), this.hand.z());
    this.hand.draw(gl);

    gl.glPopMatrix();

    gl.glEndList();
  }

  /**
   * @return
   */
  Hand hand() {
    return this.hand;
  }
  
}
