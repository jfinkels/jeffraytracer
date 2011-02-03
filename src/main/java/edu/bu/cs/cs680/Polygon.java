/**
 * Polygon.java - a polygon class which provides additional drawing functions
 * 
 * Jeffrey Finkelstein
 * 
 * BU CS 680 programming assignment 1
 * 
 * 7 February 2010
 * 
 * This class is adapted from the skeleton Polygon class provided in the
 * assignment. The most important methods are the concavePoly() method, which
 * returns true if and only if this polygon is concave, the insidePoly() method,
 * which returns true if and only if the specified point is inside this polygon,
 * and the planarSweep() method, which returns a set of convex polygons which
 * form a partition of this polygon.
 */
package edu.bu.cs.cs680;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class Polygon {

  /** The currently selected vertex. */
  private Vector2D selected_vert = null;
  /** The list of all vertices in counter-clockwise order. */
  private final ArrayList<Vector2D> vertices = new ArrayList<Vector2D>();

  /**
   * Adds a vertex to this polygon at the specified point.
   * 
   * @param x
   *          The x component of the point to add.
   * @param y
   *          The y component of the point to add.
   */
  public void addVert(int x, int y) {
    this.vertices.add(new Vector2D(x, y));
  }

  /**
   * Returns a rectangle which contains this polygon.
   * 
   * @return A rectangle which contains this polygon.
   */
  // TODO test for this method
  // private Polygon boundingBox() {
  // float minX = Float.MAX_VALUE;
  // float minY = Float.MAX_VALUE;
  // float maxX = 0;
  // float maxY = 0;
  //
  // for (final Vector2D vertex : this.vertices) {
  // if (vertex.x < minX) {
  // minX = vertex.x;
  // }
  //
  // if (vertex.x > maxX) {
  // maxX = vertex.x;
  // }
  //
  // if (vertex.y < minY) {
  // minY = vertex.y;
  // }
  //
  // if (vertex.y > maxY) {
  // maxY = vertex.y;
  // }
  // }
  //
  // final Polygon result = new Polygon();
  // result.addVert((int) minX, (int) minY);
  // result.addVert((int) maxX, (int) minY);
  // result.addVert((int) maxX, (int) maxY);
  // result.addVert((int) minX, (int) maxY);
  //
  // return result;
  // }

  /**
   * Returns true if and only if this polygon is concave.
   * 
   * Algorithm: iterate over each pair of adjacent edges in counter clockwise
   * order. If the sign of the z component of the cross product of any pair is
   * negative, then this polygon is concave. Otherwise this polygon is convex.
   * 
   * Precondition: this polygon has at least three vertices and the vertices of
   * this polygon are stored in counter-clockwise order.
   * 
   * @return Whether this polygon is concave.
   */
  public boolean concavePoly() {
    int numEdges = this.edges().size();
    for (int i = 0; i < numEdges; ++i) {
      final Line edge1 = this.edges().get(i);
      final Line edge2 = this.edges().get((i + 1) % numEdges);
      if (edge1.toVector().crossProduct(edge2.toVector()).z < 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Draws a concave polygon by dividing the current concave polygon into a set
   * of discrete, convex polygons which together form a partition of the current
   * polygon.
   * 
   * @param drawable
   *          The OpenGL object which is used to draw the polygons.
   */
  public void drawConcavePoly(GLAutoDrawable drawable) {
    /* this is only here until you write your concave handler */
    /* comment it out once you do! */
    drawConvexPoly(drawable);

    /* your code for subdividing/drawing a concave polygon here */
    for (final Polygon polygon : this.planarSweep()) {
      polygon.drawConvexPoly(drawable);
    }
  }

  // Generate necessary OpenGL commands to draw a convex polygon
  public void drawConvexPoly(GLAutoDrawable drawable) {
    final GL gl = drawable.getGL();
    if (this.vertices.isEmpty()) {
      return;
    }

    gl.glPushAttrib(GL.GL_CURRENT_BIT);
    gl.glColor3f(1.0f, 1.0f, 1.0f);

    if (this.vertices.size() == 1) {
      gl.glBegin(GL.GL_POINTS);
    } else if (this.vertices.size() == 2) {
      gl.glBegin(GL.GL_LINES);
    } else {
      gl.glBegin(GL.GL_POLYGON);
    }

    for (final Vector2D vertex : this.vertices) {
      gl.glVertex2f(vertex.x, vertex.y);
    }

    gl.glEnd();
    gl.glPopAttrib();
  }

  /**
   * Gets the edges of this polygon as a List of LineSegment objects.
   * 
   * This is <em>not</em> a view on the edges of this Polygon; every call
   * returns a list of new LineSegment objects.
   * 
   * @return A List of LineSegment objects representing the edges of this
   *         polygon.
   */
  private List<LineSegment> edges() {
    final List<LineSegment> result = new ArrayList<LineSegment>();
    final int numVertices = this.vertices.size();
    for (int i = 0; i < numVertices; ++i) {
      result.add(new LineSegment(this.vertices.get(i), this.vertices
          .get((i + 1) % numVertices)));
    }
    return result;
  }

  /**
   * Determines if a specified point is inside this polygon.
   * 
   * Algorithm: given the specified initial point, draw a random ray starting
   * there. The point is outside the polygon if and only if the number of edges
   * of the polygon which this ray intersects is even.
   * 
   * Note: a point on the border of the polygon is considered inside.
   * 
   * @param x
   *          The x component of the point.
   * @param y
   *          The y component of the point.
   * @return Whether the specified point is inside this polygon.
   */
  public boolean insidePoly(int x, int y) {
    // choose a random second point around the initial point
    final double randomX = x + (Math.random() * 2 - 1);
    final double randomY = y + (Math.random() * 2 - 1);
    final Vector2D passesThrough = new Vector2D(randomX, randomY);
    final Vector2D initialPoint = new Vector2D(x, y);
    final Ray ray = new Ray(initialPoint, passesThrough);
    int counter = 0;
    for (final LineSegment edge : this.edges()) {
      if (edge.contains(initialPoint)) {
        System.out.println("point " + initialPoint + " is on edge " + edge);
        return true;
      }
      if (ray.intersects(edge)) {
        counter += 1;
      }
    }
    return counter % 2 == 1;
  }

  /**
   * Moves the currently selected vertex to the specified point.
   * 
   * @param x
   *          The horizontal component of the new point.
   * @param y
   *          The vertical component of the new point.
   */
  public void moveVert(int x, int y) {
    if (this.selected_vert != null) {
      this.selected_vert.moveTo(x, y);
    }
  }

  /**
   * Returns a list of convex polygons which partition the current polygon using
   * the planar sweep algorithm.
   * 
   * Each of the polygons in the resulting list is guaranteed to be convex.
   * 
   * @return A list of convex polygons which partition the current polygon.
   */
  public Set<Polygon> planarSweep() {
    // get all the edges, then remove all horizontal ones
    final List<LineSegment> edges = this.edges();
    removeHorizontalEdgesFrom(edges);

    // place the remaining edges in a priority queue based on vertically lowest
    // endpoint y value
    // Note: Java doesn't provide a constructor which allows specifying just a
    // comparator. 11 is the default initial capacity for the queue.
    final AbstractQueue<LineSegment> queue = new PriorityQueue<LineSegment>(11,
        EdgeComparator.INSTANCE);
    queue.addAll(edges);

    final Set<Polygon> result = new HashSet<Polygon>();
    return result;
  }

  /**
   * Removes all horizontal line segments from the specified list of line
   * segments.
   * 
   * @param edges
   *          The list from which to remove horizontal line segments.
   */
  protected static void removeHorizontalEdgesFrom(final List<LineSegment> edges) {
    for (int i = edges.size() - 1; i >= 0; i--) {
      if (edges.get(i).isHorizontal()) {
        edges.remove(i);
      }
    }
  }

  
  // TODO test for this method
  protected static boolean isUpwardV(final Vector2D vertex,
      final Collection<LineSegment> edges) {
    int numUpwardEdges = 0;
    for (final LineSegment edge : edges) {
      final Vector2D oppositeEndpoint = edge.otherEndpoint(vertex);
      if (oppositeEndpoint != null) {
        if (oppositeEndpoint.y > vertex.y) {
          numUpwardEdges += 1;
        }
      }
    }
    
    return numUpwardEdges == 2;
  }

  /**
   * Prints a human-readable representation of this polygon to stdout.
   */
  public void print() {
    System.out.println(this.toString());
  }

  /**
   * Resets the state of this polygon to its initial state by unreferencing any
   * currently selected vertex and clearing all vertices from the list of
   * vertices.
   */
  public void reset() {
    // is_concave = false;
    this.selected_vert = null;
    this.vertices.clear();
  }

  // select a vertex based on a mouse click position (x,y)
  public void selectVert(int x, int y) {
    if (this.vertices.isEmpty()) {
      return;
    }
    final Vector2D click = new Vector2D(x, y);
    double bestDistance = click.distanceTo(this.vertices.get(0));
    Vector2D winner = this.vertices.get(0);
    for (final Vector2D vertex : this.vertices.subList(1, this.vertices.size())) {
      final double currentDistance = click.distanceTo(vertex);
      if (currentDistance < bestDistance) {
        winner = vertex;
        bestDistance = currentDistance;
      }
    }
    this.selected_vert = winner;
  }

  /**
   * Returns a String representation of this polygon.
   */
  @Override
  public String toString() {
    String result = "";
    for (final Vector2D vertex : this.vertices) {
      result += vertex + "\n";
    }
    return result;
  }

  /**
   * Translates this polygon by the specified x and y values.
   * 
   * @param deltaX
   *          The amount to translate this polygon horizontally.
   * @param deltaY
   *          The amount to translate this polygon vertically.
   */
  public void translate(int deltaX, int deltaY) {
    for (Vector2D vertex : this.vertices) {
      vertex.translate(deltaX, deltaY);
    }
  }
}
