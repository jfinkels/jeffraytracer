/**
 * SolidCylinder.java - a cylinder which can draw itself using a triangle mesh
 * approximation in OpenGL
 * 
 * History:
 * 
 * 18 February 2011
 * 
 * - added documentation
 * 
 * (Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>)
 * 
 * 30 January 2008
 * 
 * - translated from C code by Stan Sclaroff
 * 
 * (Tai-Peng Tian <tiantp@gmail.com>)
 */
package edu.bu.cs.cs480;

import java.util.ArrayList;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * A solid cylinder with a rounded top which provides methods for drawing using
 * OpenGL.
 * 
 * @author Tai-Peng Tian <tiantp@gmail.com>
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2008
 */
public class SolidCylinder implements Displayable {
  /**
   * The OpenGL handle to the display list which contains all the components
   * which comprise this cylinder.
   */
  private int callListHandle;
  /** The points along the edges of the approximated circle. */
  private final ArrayList<Point3D> circle = new ArrayList<Point3D>();
  /** The points which are normal to the edges of the approximated circle. */
  private final ArrayList<Point3D> circleNormal = new ArrayList<Point3D>();
  /** The OpenGL utility toolkit object to use for drawing a sphere. */
  private final GLUT glut;
  /** The height of this cylinder. */
  private final double height;
  /** The radius of this cylinder. */
  private final double radius;
  /**
   * The default number of steps for approximating the circumference of this
   * cylinder.
   */
  public static final int DEFAULT_APPROXIMATION_STEPS = 50;

  /**
   * Instantiates this cylinder with center of bottom base at (0, 0, 0) with the
   * specified radius and height.
   * 
   * @param radius
   *          The radius of the cylinder.
   * @param height
   *          The height of the cylinder.
   * @param glut
   *          The OpenGL utility toolkit to use for drawing the sphere at the
   *          top of the cylinder.
   */
  public SolidCylinder(final double radius, final double height, final GLUT glut) {
    this(radius, height, glut, DEFAULT_APPROXIMATION_STEPS);
  }

  /**
   * Instantiates this cylinder with center of bottom base at (0, 0, 0) with the
   * specified radius and height.
   * 
   * The approximationSteps parameter specifies the number of subdivisions to
   * use when approximating the sides of the cylinder. A higher number means a
   * smoother cylinder.
   * 
   * @param radius
   *          The radius of the cylinder.
   * @param height
   *          The height of the cylinder.
   * @param glut
   *          The OpenGL utility toolkit to use for drawing the sphere at the
   *          top of the cylinder.
   * @param approximationSteps
   *          The number of subdivisions to use when approximating the sides of
   *          the cylinder.
   */
  public SolidCylinder(final double radius, final double height,
      final GLUT glut, final int approximationSteps) {
    this.radius = radius;
    this.height = height;
    this.glut = glut;

    // compute the points which approximate the circle
    final double twoPi = 2 * Math.PI;
    final double increment = twoPi / approximationSteps;
    for (double theta = 0; theta < twoPi; theta += increment) {
      final Point3D normal = new Point3D(radius * Math.cos(theta), radius
          * Math.sin(theta), 0);
      this.circleNormal.add(normal);
      this.circle.add(new Point3D(normal.x(), normal.y(), 1));
    }
  }

  /**
   * Draws the cylinder by drawing the appropriate GL call list which contains
   * its component parts.
   * 
   * @param gl
   *          The OpenGL object with which to draw the cylinder.
   */
  public void draw(final GL gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * Initializes the GL call lists which make up the components of this cylinder
   * using the specified OpenGL object.
   * 
   * Pre-condition: the {@link #glut} member is not {@code null}
   * 
   * @param gl
   *          The OpenGL object on which to create the call lists which comprise
   *          this cylinder.
   */
  public void initialize(final GL gl) {
    this.callListHandle = gl.glGenLists(1);

    // temporary variables for iterating over points in circle and circleNormal
    Point3D p = null;
    Point3D n = null;

    gl.glNewList(this.callListHandle, GL.GL_COMPILE);
    gl.glPushMatrix();
    
    // begin a triangle strip for the sides of the cylinder
    gl.glBegin(GL.GL_TRIANGLE_STRIP);
    for (int i = 0; i < this.circle.size(); i++) {
      p = this.circle.get(i);
      n = this.circleNormal.get(i);

      gl.glNormal3d(n.x(), n.y(), 0);
      gl.glVertex3d(p.x(), p.y(), 0);
      gl.glNormal3d(n.x(), n.y(), 0);
      gl.glVertex3d(p.x(), p.y(), this.height);
    }

    n = this.circleNormal.get(0);
    p = this.circle.get(0);
    gl.glNormal3d(n.x(), n.y(), 0);
    gl.glVertex3d(p.x(), p.y(), 0);
    gl.glNormal3d(n.x(), n.y(), 0);
    gl.glVertex3d(p.x(), p.y(), this.height);

    gl.glEnd(); // end the sides of the cylinder

    // begin a polygon which approximates the top of the cylinder
    gl.glBegin(GL.GL_POLYGON);
    for (final Point3D point : this.circle) {
      gl.glVertex3d(point.x(), point.y(), this.height);
      gl.glNormal3d(0, 0, 1);
    }
    p = this.circle.get(0);
    gl.glVertex3d(p.x(), p.y(), this.height);
    gl.glNormal3d(0, 0, 1);

    gl.glEnd(); // end the top of the cylinder

    // begin a polygon which approximates the bottom of the cylinder
    gl.glBegin(GL.GL_POLYGON);
    for (final Point3D point : this.circle) {
      gl.glVertex3d(point.x(), point.y(), 0);
      gl.glNormal3d(0, 0, 1);
    }
    p = this.circle.get(0);
    gl.glVertex3d(p.x(), p.y(), 0);
    gl.glNormal3d(0, 0, 1);

    gl.glEnd(); // end the bottom of the cylinder

    // draw the rounded top
    gl.glPushMatrix();
    gl.glTranslated(0, 0, this.height);
    this.glut.glutSolidSphere(this.radius, 36, 18);
    gl.glPopMatrix();

    gl.glPopMatrix();
    gl.glEndList();
  }

  /**
   * Set the GLUT object for drawing a sphere.
   * 
   * @param glut
   *          The OpenGL utility toolkit object.
   */
  // public void setGlut(final GLUT glut) {
  // this.glut = glut;
  // }

}
