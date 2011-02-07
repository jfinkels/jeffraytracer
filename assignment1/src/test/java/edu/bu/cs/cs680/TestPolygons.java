/**
 * TestPolygons.java
 */
package edu.bu.cs.cs680;

import java.util.ArrayList;
import java.util.List;

/**
 * The actual polygon test cases used for testing.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class TestPolygons {
  private final List<Polygon> polygons = new ArrayList<Polygon>();

  public TestPolygons() {
    Polygon poly = new Polygon();
    poly.addVert(50, 50);
    poly.addVert(50, 100);
    poly.addVert(100, 100);
    poly.addVert(100, 50);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(50, 50);
    poly.addVert(250, 50);
    poly.addVert(250, 200);
    poly.addVert(150, 125);
    poly.addVert(50, 200);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(50, 200);
    poly.addVert(50, 50);
    poly.addVert(200, 50);
    poly.addVert(200, 100);
    poly.addVert(100, 100);
    poly.addVert(100, 200);

    poly = new Polygon();
    poly.addVert(50, 50);
    poly.addVert(200, 50);
    poly.addVert(200, 200);
    poly.addVert(150, 200);
    poly.addVert(150, 100);
    poly.addVert(50, 100);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(50, 100);
    poly.addVert(100, 100);
    poly.addVert(100, 50);
    poly.addVert(150, 50);
    poly.addVert(150, 100);
    poly.addVert(200, 100);
    poly.addVert(200, 150);
    poly.addVert(150, 150);
    poly.addVert(150, 200);
    poly.addVert(100, 200);
    poly.addVert(100, 150);
    poly.addVert(50, 150);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(50, 250);
    poly.addVert(50, 150);
    poly.addVert(100, 150);
    poly.addVert(150, 50);
    poly.addVert(200, 200);
    poly.addVert(250, 50);
    poly.addVert(275, 150);
    poly.addVert(275, 250);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(50, 50);
    poly.addVert(50, 200);
    poly.addVert(75, 200);
    poly.addVert(75, 100);
    poly.addVert(100, 100);
    poly.addVert(100, 200);
    poly.addVert(125, 200);
    poly.addVert(125, 100);
    poly.addVert(150, 100);
    poly.addVert(150, 200);
    poly.addVert(175, 200);
    poly.addVert(175, 100);
    poly.addVert(275, 100);
    poly.addVert(275, 5);
    poly.addVert(250, 5);
    poly.addVert(250, 50);
    poly.addVert(225, 50);
    poly.addVert(225, 5);
    poly.addVert(200, 5);
    poly.addVert(200, 50);
    poly.addVert(175, 50);
    poly.addVert(175, 5);
    poly.addVert(150, 5);
    poly.addVert(150, 50);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(37, 223);
    poly.addVert(37, 143);
    poly.addVert(91, 143);
    poly.addVert(113, 84);
    poly.addVert(156, 192);
    poly.addVert(191, 84);
    poly.addVert(244, 143);
    poly.addVert(244, 44);
    poly.addVert(17, 44);
    poly.addVert(17, 10);
    poly.addVert(268, 10);
    poly.addVert(268, 223);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(50, 100);
    poly.addVert(250, 100);
    poly.addVert(200, 50);
    poly.addVert(150, 150);
    poly.addVert(100, 50);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(131, 54);
    poly.addVert(52, 207);
    poly.addVert(134, 146);
    poly.addVert(226, 207);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(74, 112);
    poly.addVert(212, 141);
    poly.addVert(96, 150);
    poly.addVert(142, 221);
    poly.addVert(135, 60);
    this.polygons.add(poly);

    poly = new Polygon();
    poly.addVert(9, 260);
    poly.addVert(96, 114);
    poly.addVert(168, 178);
    poly.addVert(242, 99);
    poly.addVert(16, 99);
    poly.addVert(13, 20);
    poly.addVert(279, 24);
    poly.addVert(279, 261);
    this.polygons.add(poly);
  }

  public Polygon get(int index) {
    return this.polygons.get(index);
  }

  public int numPolygons() {
    return this.polygons.size();
  }
}
