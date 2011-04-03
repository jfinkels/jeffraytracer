/**
 * CreatureTest.java - test for the Creature class
 */
package edu.bu.cs.cs480.model.creatures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.model.Quaternion;

/**
 * Test for the Creature class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class CreatureTest {
  /**
   * A simple creature for use in this test class only.
   * 
   * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
   * @since Spring 2011
   */
  private class TestCreature extends Creature {

    /**
     * Creates this creature at the specified position and belonging to the
     * specified flock.
     * 
     * @param position
     *          The initial position of this creature.
     * @param flock
     *          The flock to which this creature belongs.
     */
    public TestCreature(Point3D position, List<Creature> flock) {
      super(position, null, null, flock, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     * @see edu.bu.cs.cs480.model.creatures.Creature#boundingRadius()
     */
    @Override
    public double boundingRadius() {
      return RADIUS;
    }

  }

  /** The radius of the bounding sphere of the test creature. */
  public static final double RADIUS = 1;
  /** Test creature. */
  private Creature creature;
  /** Another test creature. */
  private Creature creature2;
  /** The flock to which the test creature belongs. */
  private List<Creature> flock;

  /** Creates the test creatures and flock. */
  @Before
  public void setUp() {
    this.flock = new ArrayList<Creature>();
    this.creature = new TestCreature(Point3D.ORIGIN, this.flock);
    this.flock.add(this.creature);
    this.flock.add(new TestCreature(new Point3D(2, 0, 0), this.flock));
    this.flock.add(new TestCreature(new Point3D(-2, 0, 0), this.flock));

    this.creature2 = new TestCreature(new Point3D(1, 0, 0), null);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#boundingRadius()}.
   */
  @Test
  public void testBoundingRadius() {
    for (final Creature creature : this.flock) {
      assertEquals(RADIUS, creature.boundingRadius(), 0);
    }
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#Creature(edu.bu.cs.cs480.model.Point3D, edu.bu.cs.cs480.drawing.Displayable, java.lang.String, java.util.List)}
   * .
   */
  @Test
  public void testCreature() {
    assertEquals(0, this.creature.position().x(), 0);
    assertEquals(0, this.creature.position().y(), 0);
    assertEquals(0, this.creature.position().z(), 0);
    assertNull(this.creature.name());
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#isTouching(edu.bu.cs.cs480.model.creatures.Creature)}
   * .
   */
  @Test
  public void testIsTouching() {
    for (final Creature creature : this.flock) {
      if (!this.creature.equals(creature)) {
        assertFalse(this.creature.isTouching(creature));
      }
    }

    assertTrue(this.creature.isTouching(this.creature2));
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.model.creatures.Creature#move()}.
   */
  @Test
  public void testMove() {
    this.creature.move();
    assertEquals(0.01, this.creature.position().x(), 0);
    assertEquals(0, this.creature.position().y(), 0);
    assertEquals(0, this.creature.position().z(), 0);
    this.creature.move();
    assertEquals(0.02, this.creature.position().x(), 0);
    assertEquals(0, this.creature.position().y(), 0);
    assertEquals(0, this.creature.position().z(), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#setVelocity(edu.bu.cs.cs480.model.Point3D)}
   * .
   */
  @Test
  public void testSetVelocity() {
    this.creature.setVelocity(new Point3D(0.2, 0, 0));
    assertTrue(this.creature.rotation().equals(new Quaternion(1, 0, 0, 0)));
    this.creature.move();
    assertEquals(0.2, this.creature.position().x(), 0);
    assertEquals(0, this.creature.position().y(), 0);
    assertEquals(0, this.creature.position().z(), 0);
    this.creature.move();
    assertEquals(0.4, this.creature.position().x(), 0);
    assertEquals(0, this.creature.position().y(), 0);
    assertEquals(0, this.creature.position().z(), 0);
    this.creature.setVelocity(new Point3D(0, 0, .1));
    this.creature.move();
    assertEquals(0.4, this.creature.position().x(), 0);
    assertEquals(0, this.creature.position().y(), 0);
    assertEquals(0.1, this.creature.position().z(), 0);
    this.creature.move();
    assertEquals(0.4, this.creature.position().x(), 0);
    assertEquals(0, this.creature.position().y(), 0);
    assertEquals(0.2, this.creature.position().z(), 0);

  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#update(javax.media.opengl.GL)}
   * .
   */
  @Test
  public void testUpdate() {
    fail("Not yet implemented");
  }

}
