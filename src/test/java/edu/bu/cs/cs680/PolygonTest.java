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

  /** A concave polygon. */
  private Polygon concavePolygon = null;
  /** An empty polygon to use for testing. */
  private Polygon polygon = null;
  /** A square. */
  private Polygon square = null;
  /** A triangle. */
  private Polygon triangle = null;

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
    //assertFalse(this.triangle.concavePoly());
    //assertFalse(this.square.concavePoly());
    //assertTrue(this.concavePolygon.concavePoly());

    // real test case
    final TestPolygons testCases = new TestPolygons();
    assertFalse(testCases.get(0).concavePoly());
    for (int i = 1; i < testCases.numPolygons(); i++) {
      assertTrue(testCases.get(i).concavePoly());
    }
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Polygon#createPolygon(Vector2D, Vector2D, Vector2D, Vector2D)}
   * .
   */
  @Test
  public void testCreatePolygon() {
    Vector2D bottomLeft = new Vector2D(0, 0);
    Vector2D bottomRight = new Vector2D(1, 0);
    Vector2D topRight = new Vector2D(2, 2);
    Vector2D topLeft = new Vector2D(0, 1);

    Polygon polygon = Polygon.createPolygon(bottomLeft, bottomRight, topRight,
        topLeft);

    assertEquals(4, polygon.edges().size());
    assertEquals(0, polygon.edges().get(0).first().x, 0);
    assertEquals(0, polygon.edges().get(0).first().y, 0);
    assertEquals(1, polygon.edges().get(1).first().x, 0);
    assertEquals(0, polygon.edges().get(1).first().y, 0);
    assertEquals(2, polygon.edges().get(2).first().x, 0);
    assertEquals(2, polygon.edges().get(2).first().y, 0);
    assertEquals(0, polygon.edges().get(3).first().x, 0);
    assertEquals(1, polygon.edges().get(3).first().y, 0);
    assertEquals(0, polygon.edges().get(3).second().x, 0);
    assertEquals(0, polygon.edges().get(3).second().y, 0);

    bottomLeft = new Vector2D(1, 0);
    polygon = Polygon
        .createPolygon(bottomLeft, bottomRight, topRight, topLeft);

    assertEquals(3, polygon.edges().size());
    assertEquals(1, polygon.edges().get(0).first().x, 0);
    assertEquals(0, polygon.edges().get(0).first().y, 0);
    assertEquals(2, polygon.edges().get(1).first().x, 0);
    assertEquals(2, polygon.edges().get(1).first().y, 0);
    assertEquals(0, polygon.edges().get(2).first().x, 0);
    assertEquals(1, polygon.edges().get(2).first().y, 0);
    assertEquals(1, polygon.edges().get(2).second().x, 0);
    assertEquals(0, polygon.edges().get(2).second().y, 0);
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
   * Test method for {@link edu.bu.cs.cs680.Polygon#edges()}.
   */
  @Test
  public void testEdges() {
    final List<LineSegment> edges = this.triangle.edges();
    assertEquals(0, edges.get(0).first().x, 0);
    assertEquals(0, edges.get(0).first().y, 0);
    assertEquals(3, edges.get(1).first().x, 0);
    assertEquals(0, edges.get(1).first().y, 0);
    assertEquals(0, edges.get(2).first().x, 0);
    assertEquals(3, edges.get(2).first().y, 0);

    assertTrue(edges.get(0).second().equalTo(edges.get(1).first()));
    assertTrue(edges.get(1).second().equalTo(edges.get(2).first()));
    assertTrue(edges.get(2).second().equalTo(edges.get(0).first()));
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
   * Test method for
   * {@link edu.bu.cs.cs680.Polygon#isDownwardV(Vector2D, java.util.Collection)}
   * .
   */
  @Test
  public void testIsDownwardV() {
    this.polygon.addVert(1, 0);
    this.polygon.addVert(2, 3);
    this.polygon.addVert(1, 2);
    this.polygon.addVert(0, 3);

    final List<LineSegment> edges = this.polygon.edges();
    assertTrue(Polygon.isDownwardV(edges.get(1).first(), edges));
    assertTrue(Polygon.isDownwardV(edges.get(3).first(), edges));
    assertFalse(Polygon.isDownwardV(edges.get(0).first(), edges));
    assertFalse(Polygon.isDownwardV(edges.get(2).first(), edges));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Polygon#isUpwardV(Vector2D, java.util.Collection)}.
   */
  @Test
  public void testIsUpwardV() {
    this.polygon.addVert(1, 0);
    this.polygon.addVert(2, 3);
    this.polygon.addVert(1, 2);
    this.polygon.addVert(0, 3);

    final List<LineSegment> edges = this.polygon.edges();
    assertTrue(Polygon.isUpwardV(edges.get(0).first(), edges));
    assertTrue(Polygon.isUpwardV(edges.get(2).first(), edges));
    assertFalse(Polygon.isUpwardV(edges.get(1).first(), edges));
    assertFalse(Polygon.isUpwardV(edges.get(3).first(), edges));
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
   * Test method for {@link edu.bu.cs.cs680.Polygon#Polygon()}.
   */
  @Test
  public void testPolygon() {
    new Polygon();
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Polygon#print()}.
   */
  @Test
  public void testPrint() {
    // nothing to test
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

    Polygon.removeHorizontalEdgesFrom(edges);

    assertTrue(edges.contains(nonHorizontal1));
    assertTrue(edges.contains(nonHorizontal2));
    assertFalse(edges.contains(horizontal1));
    assertFalse(edges.contains(horizontal2));
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
