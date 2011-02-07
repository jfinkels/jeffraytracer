// ****************************************************************************
// Polygon class.
// ****************************************************************************
// Comments :
// Subroutines to manage and draw polygons
//
// History :
// 7 February 2011 - updated code to be more Java-y
// 9 Jan 2008 Created by Tai-Peng Tian (tiantp@gmail.com) based on code by
// Stan Sclaroff (from CS480 '06 poly.c)
package edu.bu.cs.cs480;

import java.util.ArrayList;
import java.util.List;

/**
 * A polygon with additional drawing capabilities.
 * 
 * @author Stan Sclaroff <sclaroff>
 * @author Tai-Peng Tian <tiantp@gmail.com>
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Polygon {

  /** The currently selected vertex, which will be moved. */
  private Point selectedVertex = null;
  /** The list of vertices which make up this polygon. */
  private final ArrayList<Point> vertices = new ArrayList<Point>();

  /**
   * Add a vertex to this polygon at the point specified by the given x and y
   * values.
   * 
   * @param x
   *          The x value of the vertex to add.
   * @param y
   *          The y value of the vertex to add.
   */
  public void addVert(final int x, final int y) {
    this.vertices.add(new Point(x, y));
  }

  /**
   * Returns {@code true} if and only if this polygon is concave.
   * 
   * @return {@code true} if and only if this polygon is concave.
   */
  public boolean isConcave() {
    /**
     * For now this always returns false. You should implement this method.
     */
    return false;
  }

  /**
   * Returns {@code true} if and only if the specified point is inside this
   * polygon.
   * 
   * @param x
   *          The x value of the point to test.
   * @param y
   *          The y value of the point to test.
   * @return {@code true} if and only if the specified point is inside this
   *         polygon.
   */
  public boolean isInside(final int x, final int y) {
    /**
     * For now, this method always returns false. Remove this line when you
     * implement your own algorithm.
     */
    return false;

    /** Your code goes here. */
  }

  /**
   * Moves the currently selected vertex to the specified location.
   * 
   * @param x
   *          The x value of the point to which to move the currently selected
   *          vertex.
   * @param y
   *          The y value of the point to which to move the currently selected
   *          vertex.
   */
  public void moveVert(final int x, final int y) {
    if (this.selectedVertex != null) {
      this.selectedVertex.x = x;
      this.selectedVertex.y = y;
    }
  }

  /**
   * Resets this polygon so that its list of vertices is empty and the currently
   * selected vertex is {@code null}.
   */
  public void reset() {
    this.selectedVertex = null;
    this.vertices.clear();
  }

  /**
   * Selects the vertex closest to the specified point.
   * 
   * @param x
   *          The x value of the point used to select the nearest vertex.
   * @param y
   *          The y value of the point used to select the nearest vertex.
   */
  public void selectVert(final int x, final int y) {
    if (this.vertices.isEmpty())
      return;
    final Point c = this.vertices.get(0);
    float dx = x - c.x;
    float dy = y - c.y;
    float winning_dist_squared = dx * dx + dy * dy;
    Point winner = null;
    for (final Point vertex : this.vertices) {
      dx = x - vertex.x;
      dy = y - vertex.y;
      float dist_squared = dx * dx + dy * dy;
      if (dist_squared < winning_dist_squared) {
        winner = vertex;
        winning_dist_squared = dist_squared;
      }
    }
    this.selectedVertex = winner;
  }

  /**
   * Returns the String representation of this polygon.
   * 
   * @return The String representation of this polygon.
   */
  @Override
  public String toString() {
    String result = "Polygon[";
    for (final Point vertex : this.vertices) {
      result += vertex + ",";
    }
    result = result.substring(0, result.length() - 1);
    result += "]";
    return result;
  }

  /**
   * Returns the list of vertices which comprise this polygon.
   * 
   * @return The list of vertices which comprise this polygon.
   */
  List<Point> vertices() {
    return this.vertices;
  }
}
