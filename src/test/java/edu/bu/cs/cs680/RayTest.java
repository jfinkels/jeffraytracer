/**
 * 
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * 
 */
public class RayTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Ray#Ray(edu.bu.cs.cs680.Vector2D, edu.bu.cs.cs680.Vector2D)}
   * .
   */
  @Test
  public void testRay() {
    new Ray(new Vector2D(0, 0), new Vector2D(0, 1));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Ray#pointIsBehind(Vector2D)}.
   */
  @Test
  public void testPointIsBehind() {
    final Ray ray = new Ray(new Vector2D(0, 0), new Vector2D(1, 1));
    Vector2D point = new Vector2D(2, 2);
    assertFalse(ray.pointIsBehind(point));
    point = new Vector2D(1, 1);
    assertFalse(ray.pointIsBehind(point));
    point = new Vector2D(0, 0);
    assertFalse(ray.pointIsBehind(point));
    point = new Vector2D(-1, -1);
    assertTrue(ray.pointIsBehind(point));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Ray#intersects(edu.bu.cs.cs680.LineSegment)}.
   */
  @Test
  public void testIntersects() {
    Ray ray = new Ray(new Vector2D(0, 0), new Vector2D(1, 1));

    // edge which intersects the ray
    LineSegment edge = new LineSegment(new Vector2D(0, 1), new Vector2D(1, 0));
    assertTrue(ray.intersects(edge));
    edge = new LineSegment(new Vector2D(1, 0), new Vector2D(0, 1));
    assertTrue(ray.intersects(edge));

    // edge which would intersect the ray if the edge were extended
    edge = new LineSegment(new Vector2D(3, -1), new Vector2D(2, 0));
    assertFalse(ray.intersects(edge));
    edge = new LineSegment(new Vector2D(2, 0), new Vector2D(3, -1));
    assertFalse(ray.intersects(edge));

    // edge which would intersect the ray if the ray were in the opposite
    // direction
    edge = new LineSegment(new Vector2D(-1, 0), new Vector2D(0, -1));
    assertFalse(ray.intersects(edge));
    edge = new LineSegment(new Vector2D(0, -1), new Vector2D(-1, 0));
    assertFalse(ray.intersects(edge));

    // real world problem cases
    ray = new Ray(new Vector2D(2.0, 2.0), new Vector2D(0.0, 1.1144363));
    edge = new LineSegment(new Vector2D(1.0, 1.0), new Vector2D(0.0, 2.0));
    assertTrue(ray.intersects(edge));
    edge = new LineSegment(new Vector2D(0.0, 2.0), new Vector2D(0.0, 0.0));
    assertTrue(ray.intersects(edge));
  }

}
