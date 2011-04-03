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
     * @param food
     *          The food which is visible to this creature.
     */
    public TestCreature(final Point3D position, final List<Creature> flock,
        final List<Food> food) {
      super(position, null, null, flock, food);
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
  /** The food to use for testing. */
  private List<Food> food;

  /** Creates the test creatures and flock. */
  @Before
  public void setUp() {
    this.flock = new ArrayList<Creature>();

    final Food f = new Food(new Point3D(1, 0, 0), null, "food");
    this.food = new ArrayList<Food>();
    this.food.add(f);

    this.creature = new TestCreature(Point3D.ORIGIN, this.flock, food);
    this.flock.add(new TestCreature(new Point3D(2, 0, 0), this.flock, null));
    this.flock.add(new TestCreature(new Point3D(-2, 0, 0), this.flock, null));
    this.flock.add(this.creature);

    this.creature2 = new TestCreature(new Point3D(1, 0, 0), null, null);
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

    // test for limitVelocity()
    this.creature.setVelocity(new Point3D(Creature.MAX_SPEED + 1, 0, 0));
    assertEquals(Creature.MAX_SPEED, this.creature.velocity().x(), 0);
    assertEquals(0, this.creature.velocity().y(), 0);
    assertEquals(0, this.creature.velocity().z(), 0);

    this.creature.setVelocity(new Point3D(Creature.MAX_SPEED,
        Creature.MAX_SPEED, 0));
    assertEquals(Creature.MAX_SPEED / Math.sqrt(2), this.creature.velocity()
        .x(), 0);
    assertEquals(Creature.MAX_SPEED / Math.sqrt(2), this.creature.velocity()
        .y(), 0);
    assertEquals(0, this.creature.velocity().z(), 0);
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

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#checkBounds()}.
   */
  @Test
  public void testCheckBounds() {
    this.creature = new TestCreature(Creature.MAX_POSITION.sumWith(new Point3D(
        1, 1, 1)), null, null);
    this.creature.checkBounds();
    assertEquals(Creature.MAX_POSITION.x(), this.creature.position().x(), 0);
    assertEquals(Creature.MAX_POSITION.y(), this.creature.position().y(), 0);
    assertEquals(Creature.MAX_POSITION.z(), this.creature.position().z(), 0);
    this.creature = new TestCreature(Creature.MIN_POSITION.sumWith(new Point3D(
        -1, -1, -1)), null, null);
    this.creature.checkBounds();
    assertEquals(Creature.MIN_POSITION.x(), this.creature.position().x(), 0);
    assertEquals(Creature.MIN_POSITION.y(), this.creature.position().y(), 0);
    assertEquals(Creature.MIN_POSITION.z(), this.creature.position().z(), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#flockVelocityUpdate()}.
   */
  @Test
  public void testFlockVelocityUpdate() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#foodVelocityUpdate()}.
   */
  @Test
  public void testFoodVelocityUpdate() {
    this.creature.foodVelocityUpdate();
    assertEquals(Creature.FOOD_WEIGHT, this.creature.velocity().x(), 0);
    assertEquals(0, this.creature.velocity().y(), 0);
    assertEquals(0, this.creature.velocity().z(), 0);

    this.food.clear();
    this.food.add(new Food(new Point3D(0, 0, .5), null, "food"));
    this.creature.setVelocity(new Point3D(0, 0, 0));
    this.creature.foodVelocityUpdate();
    assertEquals(0, this.creature.velocity().x(), 0);
    assertEquals(0, this.creature.velocity().y(), 0);
    assertEquals(Creature.FOOD_WEIGHT / 2, this.creature.velocity().z(), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#perceivedFlockVelocity()}.
   */
  @Test
  public void testPerceivedFlockVelocity() {
    Point3D v = this.creature.perceivedFlockVelocity();
    assertEquals(0, v.x(), 0);
    assertEquals(0, v.y(), 0);
    assertEquals(0, v.z(), 0);

    this.flock.get(0).setVelocity(new Point3D(-0.02, 0, 0));
    this.flock.get(1).setVelocity(new Point3D(-0.02, 0, 0));
    v = this.creature.perceivedFlockVelocity();
    assertEquals(Creature.VELOCITY_WEIGHT * -.03, v.x(), 0);
    assertEquals(0, v.y(), 0);
    assertEquals(0, v.z(), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#repulsionVelocity()}.
   */
  @Test
  public void testRepulsionVelocity() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.creatures.Creature#velocityTowardsCenter()}.
   */
  @Test
  public void testVelocityTowardsCenter() {
    fail("Not yet implemented");
  }

}
