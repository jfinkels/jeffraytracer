/**
 * PolygonTest.java
 * 
 * Jeffrey Finkelstein BU CS 680 Programming assignment 1
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for the Polygon class.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class PolygonTest {

  /** An empty polygon to use for testing. */
  private Polygon polygon = null;
  /** A triangle. */
  private Polygon triangle = null;
  /** A square. */
  private Polygon square = null;
  /** A concave polygon. */
  private Polygon concavePolygon = null;

  /** Creates a Polygon for testing. */
  @Before
  public void setUp() {
    // this polygon is initially blank for each test
    this.polygon = new Polygon();

    // create a triangle
    this.triangle = new Polygon();
    this.triangle.addVert(0, 0);
    this.triangle.addVert(3, 0);
    this.triangle.addVert(0, 3);

    // create a square
    this.square = new Polygon();
    this.square.addVert(0, 0);
    this.square.addVert(2, 0);
    this.square.addVert(2, 2);
    this.square.addVert(0, 2);

    // create a concave polygon
    this.concavePolygon = new Polygon();
    this.concavePolygon.addVert(0, 0);
    this.concavePolygon.addVert(1, 1);
    this.concavePolygon.addVert(2, 0);
    this.concavePolygon.addVert(4, 4);
    this.concavePolygon.addVert(0, 4);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#Polygon()}.
   */
  @Test
  public void testPolygon() {
    new Polygon();
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#addVert(int, int)}.
   */
  @Test
  public void testAddVert() {
    this.polygon.addVert(0, 0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#concavePoly()}.
   */
  @Test
  public void testConcavePoly() {
    assertFalse(this.polygon.concavePoly());
    assertFalse(this.triangle.concavePoly());
    assertFalse(this.square.concavePoly());
    assertTrue(this.concavePolygon.concavePoly());
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Polygon#removeHorizontalEdgesFrom(java.util.List)}.
   */
  @Test
  public void testRemoveHorizontalEdges() {
    final LineSegment nonHorizontal1 = new LineSegment(new Vector2D(0, 0),
        new Vector2D(1, 1));
    final LineSegment nonHorizontal2 = new LineSegment(new Vector2D(0, 1),
        new Vector2D(1, 0));
    final LineSegment horizontal1 = new LineSegment(new Vector2D(0, 1),
        new Vector2D(1, 1));
    final LineSegment horizontal2 = new LineSegment(new Vector2D(1, 0),
        new Vector2D(0, 0));

    final List<LineSegment> edges = new ArrayList<LineSegment>();
    
    edges.addAll(Arrays.asList(nonHorizontal1, nonHorizontal2, horizontal1,
        horizontal2));
    System.out.println(edges);

    Polygon.removeHorizontalEdgesFrom(edges);
    
    System.out.println(edges);
    
    assertTrue(edges.contains(nonHorizontal1));
    assertTrue(edges.contains(nonHorizontal2));
    assertFalse(edges.contains(horizontal1));
    assertFalse(edges.contains(horizontal2));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#planarSweep()}.
   */
  @Test
  public void testPlanarSweep() {
    assertArrayEquals(new Polygon[] { this.square }, this.square.planarSweep()
        .toArray());
    assertArrayEquals(new Polygon[] { this.triangle }, this.triangle
        .planarSweep().toArray());

    assertEquals(3, this.concavePolygon.planarSweep().size());
    for (final Polygon subdivision : this.concavePolygon.planarSweep()) {
      assertFalse(subdivision.concavePoly());
    }
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Polygon#drawConcavePoly(javax.media.opengl.GLAutoDrawable)}
   * .
   */
  @Test
  public void testDrawConcavePoly() {
    // nothing to test
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Polygon#drawConvexPoly(javax.media.opengl.GLAutoDrawable)}
   * .
   */
  @Test
  public void testDrawConvexPoly() {
    // nothing to test
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#insidePoly(int, int)}.
   */
  @Test
  public void testInsidePoly() {
    assertTrue(this.square.insidePoly(1, 1));
    assertFalse(this.square.insidePoly(10, 0));
    assertTrue(this.triangle.insidePoly(1, 1));
    assertFalse(this.triangle.insidePoly(10, 0));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#moveVert(int, int)}.
   */
  @Test
  public void testMoveVert() {
    this.square.selectVert(2, 2);
    this.square.moveVert(1, 1);
    assertFalse(this.square.insidePoly(2, 2));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#print()}.
   */
  @Test
  public void testPrint() {
    // nothing to test
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#reset()}.
   */
  @Test
  public void testReset() {
    // nothing to test
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#selectVert(int, int)}.
   */
  @Test
  public void testSelectVert() {
    // nothing to test
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#toString()}.
   */
  @Test
  public void testToString() {
    assertTrue(this.triangle.toString().contains("(0.0, 0.0)"));
    assertTrue(this.triangle.toString().contains("(3.0, 0.0)"));
    assertTrue(this.triangle.toString().contains("(0.0, 3.0)"));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#translate(int, int)}.
   */
  @Test
  public void testTranslate() {
    // nothing to test
  }

}
