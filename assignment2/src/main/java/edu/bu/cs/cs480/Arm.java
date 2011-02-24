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
public class Arm extends Joint implements UpdatingDisplayable {

  private final Forearm forearm;
Forearm forearm() {
  return this.forearm;
}
  /**
   * @param x
   * @param y
   * @param z
   * @param radius
   * @param height
   */
  public Arm(final double x, final double y, final double z,
      final double radius, final double height) {
    super(x, y, z, radius, height);
    this.forearm = new Forearm(x, y, z + height, radius, height);
  }

  @Override
  public void setGlut(final GLUT glut) {
    super.setGlut(glut);
    this.forearm.setGlut(glut);
  }

  private int displayListHandle;

  @Override
  public void initialize(final GL gl) {
    super.initialize(gl);
    this.forearm.initialize(gl);
    this.displayListHandle = gl.glGenLists(1);
  }

  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.displayListHandle);
  }

  Hand hand() {
    return this.forearm.hand();
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.UpdatingDisplayable#update(javax.media.opengl.GL)
   */
  @Override
  public void update(final GL gl) {
    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    gl.glPushMatrix();

    // initial rotation so we can see the whole arm
    gl.glRotated(90, 0, 0, 1);
    
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

    this.forearm.draw(gl);

    gl.glPopMatrix();

    gl.glEndList();
  }

}
