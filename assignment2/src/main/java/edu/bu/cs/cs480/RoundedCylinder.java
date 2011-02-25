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
public class RoundedCylinder implements Displayable {
  private final double radius;
  private final double height;
  private final GLUT glut;

  public RoundedCylinder(final double radius, final double height,
      final GLUT glut) {
    this.radius = radius;
    this.height = height;
    this.glut = glut;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * The OpenGL handle to the display list which contains all the components
   * which comprise this cylinder.
   */
  private int callListHandle;

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL gl) {
    this.callListHandle = gl.glGenLists(1);

    gl.glNewList(this.callListHandle, GL.GL_COMPILE);

    this.glut.glutSolidCylinder(this.radius, this.height, DEFAULT_SLICES,
        DEFAULT_STACKS);

    gl.glPushMatrix();
    gl.glTranslated(0, 0, this.height);
    this.glut.glutSolidSphere(this.radius, DEFAULT_SLICES, DEFAULT_STACKS);
    gl.glPopMatrix();
    
    gl.glEndList();
  }

  public static final int DEFAULT_SLICES = 36;
  public static final int DEFAULT_STACKS = 28;
}
