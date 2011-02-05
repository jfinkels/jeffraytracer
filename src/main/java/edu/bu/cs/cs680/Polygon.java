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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

/**
 * A polygon with additional drawing methods.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Polygon {

  /**
   * Helper method which creates a new Polygon object from the specified four
   * vertices.
   * 
   * If the bottom two vertices are the same, only one is added. If the top two
   * vertices are the same, only one is added.
   * 
   * Pre-condition: either the bottom two vertices are not equal or the top two
   * vertices are not equal (or both). Otherwise the created polygon is just a
   * line segment.
   * 
   * @param bottomLeft
   *          The bottom left vertex of the polygon. May be the same as the
   *          bottom right vertex.
   * @param bottomRight
   *          The bottom right vertex of the polygon. May be the same as the
   *          bottom left vertex.
   * @param topRight
   *          The top right vertex of the polygon. May be the same as the top
   *          left vertex.
   * @param topLeft
   *          The top left vertex of the polygon. May be the same as the top
   *          right vertex.
   * @return The polygon created from the specified vertices; a quadrilateral if
   *         all four vertices are different, or a triangle if either the bottom
   *         two or the top two vertices are the same.
   */
  protected static Polygon createPolygon(final Vector2D bottomLeft,
      final Vector2D bottomRight, final Vector2D topRight,
      final Vector2D topLeft) {
    final Polygon polygon = new Polygon();

    polygon.vertices.add(bottomLeft);
    if (!bottomLeft.equalTo(bottomRight)) {
      polygon.vertices.add(bottomRight);
    }

    polygon.vertices.add(topRight);
    if (!topRight.equalTo(topLeft)) {
      polygon.vertices.add(topLeft);
    }

    return polygon;
  }

  /**
   * Returns true if and only if the specified lists of vertices are the same
   * vertices, either in the same order or in reverse order.
   * 
   * @param vertices1
   *          A list of vertices.
   * @param vertices2
   *          Another list of vertices.
   * @return {@code true} if and only if the specified lists of vertices are the
   *         same vertices, either in the same order or in reverse order.
   */
  private static boolean equalVertices(final List<Vector2D> vertices1,
      final List<Vector2D> vertices2) {
    // if they are not the same size, return false immediately
    if (vertices1.size() != vertices2.size()) {
      return false;
    }

    // first check if they are in the same order
    boolean sameOrder = true;
    for (int i = 0; i < vertices1.size(); ++i) {
      if (!vertices1.get(i).equalTo(vertices2.get(i))) {
        sameOrder = false;
      }
    }
    if (sameOrder) {
      return true;
    }

    // then check if they are in reverse order
    for (int i = 0; i < vertices1.size(); ++i) {
      if (!vertices1.get(i).equalTo(vertices2.get(vertices2.size() - 1 - i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Returns true if and only if the specified vertex is the top endpoint of
   * both of the two edges to which it is incident, with edges drawn from the
   * specified collection of line segments.
   * 
   * @param vertex
   *          The endpoint to test.
   * @param edges
   *          The collection of edges for which the specified vertex may be an
   *          endpoint.
   * @return {@code true} if and only if the specified vertex is the top
   *         endpoint of both of the two edges to which it is incident, with
   *         edges drawn from the specified collection of line segments.
   */
  protected static boolean isDownwardV(final Vector2D vertex,
      final Collection<LineSegment> edges) {
    int numDownwardEdges = 0;
    for (final LineSegment edge : edges) {
      final Vector2D oppositeEndpoint = edge.otherEndpoint(vertex);
      if (oppositeEndpoint != null) {
        if (oppositeEndpoint.y < vertex.y) {
          numDownwardEdges += 1;
        }
      }
    }

    return numDownwardEdges == 2;
  }

  /**
   * Returns true if and only if the specified vertex is the bottom endpoint of
   * both of the two edges to which it is incident, with edges drawn from the
   * specified collection of line segments.
   * 
   * @param vertex
   *          The endpoint to test.
   * @param edges
   *          The collection of edges for which the specified vertex may be an
   *          endpoint.
   * @return {@code true} if and only if the specified vertex is the bottom
   *         endpoint of both of the two edges to which it is incident, with
   *         edges drawn from the specified collection of line segments.
   */
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
  public void addVert(final int x, final int y) {
    this.vertices.add(new Vector2D(x, y));
  }

  /**
   * Returns true if and only if this polygon is concave.
   * 
   * Returns false if this polygon has fewer than three vertices.
   * 
   * Algorithm: iterate over each pair of adjacent edges. If the sign of the z
   * component of the cross product of any pair is different from that of the
   * adjacent pair, then this polygon is concave. Otherwise this polygon is
   * convex.
   * 
   * @return Whether this polygon is concave.
   */
  public boolean concavePoly() {
    // if this polygon does not have three vertices, just return false
    if (this.vertices.size() < 3) {
      return false;
    }
    // determine whether we will be looking for all positive or all negative
    // cross product z values
    final int signToMatch = (int) Math.signum(this.edges().get(0).toVector()
        .crossProduct(this.edges().get(1).toVector()).z);
    int numEdges = this.edges().size();
    // start at 1 because we already checked the first pair above
    for (int i = 1; i < numEdges; ++i) {
      final Vector2D vector1 = this.edges().get(i).toVector();
      final Vector2D vector2 = this.edges().get((i + 1) % numEdges).toVector();
      if ((int) Math.signum(vector1.crossProduct(vector2).z) != signToMatch) {
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
  public void drawConcavePoly(final GLAutoDrawable drawable) {
    for (final Polygon polygon : this.planarSweep()) {
      polygon.drawConvexPoly(drawable);
    }
  }

  // Generate necessary OpenGL commands to draw a convex polygon
  public void drawConvexPoly(final GLAutoDrawable drawable) {
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
  protected List<LineSegment> edges() {
    final List<LineSegment> result = new ArrayList<LineSegment>();
    final int numVertices = this.vertices.size();
    for (int i = 0; i < numVertices; ++i) {
      result.add(new LineSegment(this.vertices.get(i), this.vertices
          .get((i + 1) % numVertices)));
    }
    return result;
  }

  /**
   * Returns true if and only if this polygon has the same sequence of vertices
   * as the specified other polygon, or if this polygon has the reverse sequence
   * of vertices as the specified other polygon.
   * 
   * @param that
   *          The polygon to test for equality.
   * @return {@code true} if and only if this polygon has the same sequence of
   *         vertices as the specified other polygon, or if this polygon has the
   *         reverse sequence of vertices as the specified other polygon.
   */
  public boolean equalOrOppositeTo(final Polygon that) {
    return equalVertices(this.vertices, that.vertices);
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
    // if this point is on the border, then we count it as inside
    final Vector2D initialPoint = new Vector2D(x, y);
    if (this.pointIsOnBorder(initialPoint)) {
      return true;
    }

    // the integer i counts the number of edges we have checked. we may not
    // check all the edges at first because we need to choose a new random ray
    // if the ray intersects a line segment at its vertex
    int i = 0;
    int counter = 0;
    final int numEdges = this.edges().size();
    while (i < numEdges) {
      // reset the counter for number of intersections
      counter = 0;

      // create a random ray by choosing a point around the specified x and y
      final double randomX = x + (Math.random() * 2 - 1);
      final double randomY = y + (Math.random() * 2 - 1);
      final Vector2D passesThrough = new Vector2D(randomX, randomY);
      final Ray ray = new Ray(initialPoint, passesThrough);

      // loop over edges to see how many intersections we get
      for (i = 0; i < numEdges; ++i) {
        final LineSegment edge = this.edges().get(i);
        if (ray.intersects(edge)) {
          final Vector2D intersectionPoint = ray.intersectionPointWith(edge);
          if (edge.isEndpoint(intersectionPoint)) {
            break;
          }
          counter += 1;
        }
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
    // maintain a queue of edges which have not yet been active, excluding
    // horizontal edges
    final AbstractQueue<LineSegment> remainingEdges = new PriorityQueue<LineSegment>(
        11, EdgeComparator.INSTANCE);
    for (final LineSegment edge : this.edges()) {
      if (!edge.isHorizontal()) {
        remainingEdges.add(edge);
      }
    }

    // order vertices from bottom to top, then left to right
    final QueueSet<Vector2D> remainingVertices = new QueueSet<Vector2D>(
        BottomToTopComparator.INSTANCE);
    for (final LineSegment edge : this.edges()) {
      remainingVertices.add(edge.lowerEndpoint());
      remainingVertices.add(edge.upperEndpoint());
    }
    final Vector2D bottomVertex = remainingVertices.pop();

    // move edges which start at the y value of the current vertex from the
    // remainingEdges queue to the active edges queue
    final List<LineSegment> activeEdges = new ArrayList<LineSegment>();
    while (!remainingEdges.isEmpty()
        && remainingEdges.peek().lowerEndpoint().y == bottomVertex.y) {
      activeEdges.add(remainingEdges.remove());
    }
    Collections.sort(activeEdges, EdgeComparator.INSTANCE);

    // add all the lower endpoints of the active edges to the list of bottom
    // intersections. Note: if there are two edges which come to a vertex in a
    // "V" shape, the lower endpoint there will be added twice, but this is
    // expected behavior
    final List<Vector2D> topIntersections = new ArrayList<Vector2D>();
    final List<Vector2D> bottomIntersections = new ArrayList<Vector2D>();
    for (final LineSegment edge : activeEdges) {
      bottomIntersections.add(edge.lowerEndpoint());
    }
    Collections.sort(bottomIntersections, LeftToRightComparator.INSTANCE);

    final Set<Polygon> result = new HashSet<Polygon>();
    while (!remainingVertices.isEmpty()) {
      // get the next lowest vertex from the remaining queue
      final Vector2D upperVertex = remainingVertices.pop();

      // get the intersection with each active edge with the horizontal at the
      // current y value. Note: here also, the same vertex may be added to the
      // list twice if it is incident to two active edges which meet in an
      // upside down "V" shape. Again this is expected behavior
      for (final LineSegment edge : activeEdges) {
        topIntersections.add(edge.intersectionWithHorizontalAt(upperVertex.y));
      }
      Collections.sort(topIntersections, LeftToRightComparator.INSTANCE);

      // create new polygons for each 4-tuple of two top intersections and two
      // bottom intersections
      for (int i = 0; i < topIntersections.size() - 1; i += 2) {
        final Vector2D bottomLeft = bottomIntersections.get(i);
        final Vector2D bottomRight = bottomIntersections.get(i + 1);
        final Vector2D topLeft = topIntersections.get(i);
        final Vector2D topRight = topIntersections.get(i + 1);

        // create the polygon, accounting for the possibility that either the
        // top two or the bottom two are the same point, and add it to the set
        // which we will output
        result.add(createPolygon(bottomLeft, bottomRight, topRight, topLeft));
      }

      // clear the bottom intersections, because we no longer need them after
      // creating the polygon
      bottomIntersections.clear();

      // move all top intersection points to the bottom list, unless they are
      // the top of two active edges (like an upside down "V" shape)
      for (final Vector2D point : topIntersections) {
        if (!isDownwardV(point, activeEdges)) {
          bottomIntersections.add(point);
        }
      }
      topIntersections.clear();

      // deactivate edges which end at the same y value as this vertex
      for (int i = activeEdges.size() - 1; i >= 0; --i) {
        if (activeEdges.get(i).upperEndpoint().y == upperVertex.y) {
          final LineSegment edge = activeEdges.remove(i);
          // and remove their upper endpoints from the list of bottom
          // intersections
          bottomIntersections.remove(edge.upperEndpoint());
        }
      }

      // activate edges which start at the same y value as this vertex
      while (!remainingEdges.isEmpty()
          && (remainingEdges.peek().lowerEndpoint().y == upperVertex.y)) {
        final LineSegment edge = remainingEdges.remove();
        activeEdges.add(edge);
        // and add their lower endpoints to the list of bottom intersections
        bottomIntersections.add(edge.lowerEndpoint());
      }
      Collections.sort(activeEdges, EdgeComparator.INSTANCE);

      // add to bottom intersections the bottom vertex of an upward v
      // for (final LineSegment edge : activeEdges) {
      // if (isUpwardV(edge.lowerEndpoint(), activeEdges)) {
      // bottomIntersections.add(edge.lowerEndpoint());
      // }
      // }
      Collections.sort(bottomIntersections, LeftToRightComparator.INSTANCE);
    }

    // at this point, all active edges should end at the same upper endpoint
    for (final LineSegment edge : activeEdges) {
      topIntersections.add(edge.upperEndpoint());
    }
    Collections.sort(topIntersections, LeftToRightComparator.INSTANCE);

    // add any remaining polygons
    // create new polygons for each 4-tuple of two top intersections and two
    // bottom intersections
    for (int i = 0; i < topIntersections.size() - 1; i += 2) {
      final Vector2D bottomLeft = bottomIntersections.get(i);
      final Vector2D bottomRight = bottomIntersections.get(i + 1);
      final Vector2D topLeft = topIntersections.get(i);
      final Vector2D topRight = topIntersections.get(i + 1);

      // create the polygon, accounting for the possibility that either the
      // top two or the bottom two are the same point, and add it to the set
      // which we will output
      result.add(createPolygon(bottomLeft, bottomRight, topRight, topLeft));
    }

    return result;
  }

  protected boolean pointIsOnBorder(final Vector2D point) {
    for (final LineSegment edge : this.edges()) {
      if (edge.contains(point)) {
        return true;
      }
    }
    return false;
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
    for (final Vector2D vertex : this.vertices
        .subList(1, this.vertices.size())) {
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
