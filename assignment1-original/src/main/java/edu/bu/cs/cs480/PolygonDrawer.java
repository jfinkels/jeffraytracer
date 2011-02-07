/**
 * PolygonDrawer.java
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

/**
 * A class which provides a single public method which draws a polygon.
 * 
 * This method contains separate internal methods for drawing a convex polygon
 * and for drawing a concave polygon.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 * @since Spring 2011
 */
public class PolygonDrawer {

  /**
   * Draws the specified polygon (concave or convex) on the specified OpenGL
   * drawable object.
   * 
   * @param drawable
   *          The OpenGL object on which to draw this polygon.
   * @param polygon
   *          The polygon to draw.
   */
  public static void draw(final GLAutoDrawable drawable, final Polygon polygon) {
    if (polygon.isConcave()) {
      drawConcave(drawable, polygon);
    } else {
      drawConvex(drawable, polygon);
    }
  }

  /**
   * Draws the specified concave polygon with the specified OpenGL drawable
   * object.
   * 
   * Pre-condition: the specified polygon is concave.
   * 
   * @param drawable
   *          The OpenGL object on which to draw this polygon.
   * @param polygon
   *          The polygon to draw.
   */
  private static void drawConcave(final GLAutoDrawable drawable,
      final Polygon polygon) {
    final GL gl = drawable.getGL();

    /**
     * This line is only here until you implement your own algorithm for drawing
     * a concave polygon. Comment out this line once you have implemented it!
     */
    drawConvex(drawable, polygon);

    /**
     * Your code for subdividing and drawing a concave polygon should go here.
     */
  }

  /**
   * Draws the specified convex polygon with the specified OpenGL drawable
   * object.
   * 
   * Pre-condition: the specified polygon is convex.
   * 
   * @param drawable
   *          The OpenGL object on which to draw this polygon.
   * @param polygon
   *          The polygon to draw.
   */
  public static void drawConvex(final GLAutoDrawable drawable,
      final Polygon polygon) {
    if (polygon.vertices().isEmpty())
      return;

    final GL gl = drawable.getGL();

    // push the current color
    gl.glPushAttrib(GL.GL_CURRENT_BIT);

    // set boundary color
    gl.glColor3f(1, 1, 1);

    // if only 1 vertex, draw a point
    if (polygon.vertices().size() == 1)
      gl.glBegin(GL.GL_POINTS);

    // if only 2 vertices, draw a line
    else if (polygon.vertices().size() == 2)
      gl.glBegin(GL.GL_LINES);

    // otherwise draw a polygon
    else
      gl.glBegin(GL.GL_POLYGON);

    for (final Point vertex : polygon.vertices())
      gl.glVertex2f(vertex.x, vertex.y);

    gl.glEnd();

    // pop current color
    gl.glPopAttrib();
  }
}
