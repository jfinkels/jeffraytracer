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
  
  /* (non-Javadoc)
   * @see edu.bu.cs.cs480.UpdatingDisplayable#update(javax.media.opengl.GL)
   */
  @Override
  public void update(final GL gl) {
    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    this.forearm.draw(gl);

    // TODO rotate this object (shoulder)
    super.draw(gl);
    
    gl.glEndList();
  }
  
}
