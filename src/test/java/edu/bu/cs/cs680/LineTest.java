/**
 * LineTest.java
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the Line class.
 * 
 * @author Jeffrey Finkelstein
 */
public class LineTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Line#Line(edu.bu.cs.cs680.Vector2D, edu.bu.cs.cs680.Vector2D)}
   * .
   */
  @Test
  public void testLine() {
    new LineSegment(new Vector2D(0.0, 0.0), new Vector2D(1.0, 1.0));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Line#parallelTo(edu.bu.cs.cs680.Line)}.
   */
  @Test
  public void testParallelTo() {
    final Line line1 = new LineSegment(new Vector2D(0.0, 0.0), new Vector2D(
        1.0, 1.0));
    assertTrue(line1.parallelTo(line1));

    Line line2 = new LineSegment(new Vector2D(1.0, 0.0),
        new Vector2D(2.0, 1.0));
    assertTrue(line1.parallelTo(line2));
    assertTrue(line2.parallelTo(line1));

    line2 = new LineSegment(new Vector2D(0.0, 0.0), new Vector2D(-2.0, -2.0));
    assertTrue(line1.parallelTo(line2));
    assertTrue(line2.parallelTo(line1));

    line2 = new LineSegment(new Vector2D(0.5, 0.5), new Vector2D(1.5, 1.5));
    assertTrue(line1.parallelTo(line2));
    assertTrue(line2.parallelTo(line1));

    line2 = new LineSegment(new Vector2D(0.0, 0.0), new Vector2D(0.0, 1.0));
    assertFalse(line1.parallelTo(line2));
    assertFalse(line2.parallelTo(line1));
    
    final Line line3 = new LineSegment(new Vector2D(0, 1), new Vector2D(0, 0));
    assertTrue(line2.parallelTo(line3));
    assertTrue(line3.parallelTo(line2));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Line#distanceTo(edu.bu.cs.cs680.Vector3D)}.
   */
  @Test
  public void testDistanceTo() {
    final Line line = new LineSegment(new Vector2D(0.0, 0.0), new Vector2D(
        2.0, 0.0));
    
    assertEquals(1.0, line.distanceTo(new Vector2D(1.0, 1.0)), 0.0);
    assertEquals(1.0, line.distanceTo(new Vector2D(0.0, 1.0)), 0.0);
    assertEquals(1.0, line.distanceTo(new Vector2D(0.0, -1.0)), 0.0);
  
    assertEquals(0.0, line.distanceTo(new Vector2D(0.0, 0.0)), 0.0);
    assertEquals(0.0, line.distanceTo(new Vector2D(1.0, 0.0)), 0.0);
    assertEquals(0.0, line.distanceTo(new Vector2D(2.0, 0.0)), 0.0);
    assertEquals(0.0, line.distanceTo(new Vector2D(3.0, 0.0)), 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Line#toVector()}.
   */
  @Test
  public void testToVector() {
    Line line = new LineSegment(new Vector2D(2.0, 2.0), new Vector2D(3.0, 4.0));
    assertEquals(1.0, line.toVector().x, 0.0);
    assertEquals(2.0, line.toVector().y, 0.0);
    assertEquals(0.0, line.toVector().z, 0.0);

    line = new LineSegment(new Vector2D(-2.0, -2.0), new Vector2D(-3.0, -4.0));
    assertEquals(-1.0, line.toVector().x, 0.0);
    assertEquals(-2.0, line.toVector().y, 0.0);
    assertEquals(0.0, line.toVector().z, 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Line#first()}.
   */
  @Test
  public void testFirst() {
    final Line line = new LineSegment(new Vector2D(1.0, 2.0), new Vector2D(
        3.0, 4.0));
    assertEquals(1.0, line.first().x, 0.0);
    assertEquals(2.0, line.first().y, 0.0);
    assertEquals(0.0, line.first().z, 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Line#second()}.
   */
  @Test
  public void testSecond() {
    final Line line = new LineSegment(new Vector2D(1.0, 2.0), new Vector2D(
        3.0, 4.0));
    assertEquals(3.0, line.second().x, 0.0);
    assertEquals(4.0, line.second().y, 0.0);
    assertEquals(0.0, line.second().z, 0.0);
  }

}
