/**
 * 
 * DrawingController.java - controller for drawing on an OpenGL canvas
 */
package edu.bu.cs.cs480;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.model.Component;
import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.model.SizedComponent;
import edu.bu.cs.cs480.model.creatures.Bird;
import edu.bu.cs.cs480.model.creatures.Creature;
import edu.bu.cs.cs480.model.creatures.Fish;
import edu.bu.cs.cs480.model.creatures.Food;
import edu.bu.cs.cs480.shapes.Tank;

/**
 * The controller for drawing on the OpenGL canvas.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class DrawingController implements GLEventListener {
  /** The initial position of the top level component in the scene. */
  public static final Point3D INITIAL_POSITION = new Point3D(0, 0, 0);
  /** The total number of birds to place in the scene. */
  public static final int NUM_BIRDS = 10;
  /** The total number of fish to place in the scene. */
  public static final int NUM_FISH = 10;
  /** A random number generator. */
  private static final Random RANDOM = new Random();
  /**
   * The length of one side of the cube-shaped tank in which the creatures live.
   */
  public static final int TANK_SIZE = 6;

  /**
   * Returns a random velocity.
   * 
   * @return
   */
  private static Point3D randomVelocity() {
    final double x = RANDOM.nextDouble() - .5;
    final double y = RANDOM.nextDouble() - .5;
    final double z = RANDOM.nextDouble() - .5;
    return new Point3D(x, y, z);
  }

  /**
   * Returns a random point within the bounds of the tank, as specified by
   * {@value #TANK_SIZE}.
   * 
   * @return A random point within the bounds of the tank.
   */
  private static Point3D randomPoint() {
    final double x = (RANDOM.nextDouble() * TANK_SIZE) - (TANK_SIZE / 2);
    final double y = (RANDOM.nextDouble() * TANK_SIZE) - (TANK_SIZE / 2);
    final double z = (RANDOM.nextDouble() * TANK_SIZE) - (TANK_SIZE / 2);
    return new Point3D(x, y, z);
  }

  /** The list of food currently in the tank. */
  private final List<SizedComponent> food = new ArrayList<SizedComponent>();
  /** The number of times {@link #generateFood()} has been called. */
  private int foodCounter = 0;
  /**
   * The piece of food to add on the next call to
   * {@link #display(GLAutoDrawable)}.
   */
  private Food foodToAdd = null;
  /** The OpenGL utility object. */
  private final GLU glu = new GLU();

  /** The OpenGL utility toolkit object. */
  // TODO should this be a static member?
  private final GLUT glut = new GLUT();
  /** The creatures which are predators. */
  private final List<Creature> predators = new ArrayList<Creature>();
  /** The creatures which are prey. */
  private final List<Creature> prey = new ArrayList<Creature>();

  /** The controller for view rotations. */
  private RotationController rotationController = null;

  /**
   * The top level component in the scene which controls the positioning and
   * rotation of everything in the scene.
   */
  private final Component topLevelComponent = new Component(INITIAL_POSITION,
      "top level");

  /**
   * Instantiates this object by adding all the necessary components to the
   * scene.
   */
  public DrawingController() {
    final Component tank = new Component(Point3D.ORIGIN, new Tank(TANK_SIZE,
        TANK_SIZE, TANK_SIZE), "tank");
    this.topLevelComponent.addChild(tank);

    for (int i = 0; i < NUM_FISH; i++) {
      final Creature fish = new Fish(randomPoint(), this.glut, "fish " + i,
          this.prey, this.food, this.predators);
      fish.setVelocity(randomVelocity());
      this.prey.add(fish);
      this.topLevelComponent.addChild(fish);
    }

    final List<SizedComponent> eat = new ArrayList<SizedComponent>(this.prey);
    for (int i = 0; i < NUM_BIRDS; i++) {
      final Creature bird = new Bird(randomPoint(), this.glut, "bird " + i,
          this.predators, eat);
      bird.setVelocity(randomVelocity());
      this.predators.add(bird);
      this.topLevelComponent.addChild(bird);
    }
  }

  /**
   * Redisplays the scene containing the model.
   * 
   * @param drawable
   *          The OpenGL drawable object with which to create OpenGL models.
   */
  @Override
  public void display(final GLAutoDrawable drawable) {
    final GL gl = drawable.getGL();

    // clear the display
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

    // from here on affect the model view
    gl.glMatrixMode(GL.GL_MODELVIEW);

    // start with the identity matrix initially
    gl.glLoadIdentity();

    // rotate the world by the appropriate rotation quaternion
    gl.glMultMatrixf(this.rotationController.rotation().toMatrix(), 0);

    // check if any predator is touching a prey creature
    // pre-condition: the set of predators and the set of prey are disjoint
    final List<Creature> toRemove = new ArrayList<Creature>();
    for (final Creature predator : this.predators) {
      for (final Creature prey : this.prey) {
        if (predator.isTouching(prey)) {
          // we add the creature to remove to a list for later removal so that
          // we do not modify the list while iterating through it
          toRemove.add(prey);
        }
      }
    }

    // check if any prey is touching any food
    final List<Component> foodToRemove = new ArrayList<Component>();
    for (final Creature prey : this.prey) {
      for (final SizedComponent food : this.food) {
        if (prey.isTouching(food)) {
          // we add the creature to remove to a list for later removal so that
          // we do not modify the list while iterating through it
          foodToRemove.add(food);
        }
      }
    }

    // iterate over the list of creatures to remove
    for (final Creature creature : toRemove) {
      this.prey.remove(creature);
      this.topLevelComponent.removeChild(creature);
    }

    // iterate over the list of food to remove
    for (final Component food : foodToRemove) {
      this.food.remove(food);
      this.topLevelComponent.removeChild(food);
    }

    // add food if food has been added by the user
    if (this.foodToAdd != null) {
      this.foodToAdd.initialize(gl);
      this.food.add(this.foodToAdd);
      this.topLevelComponent.addChild(this.foodToAdd);
      this.foodToAdd = null;
    }

    // update the position of the components which need to be updated
    this.topLevelComponent.update(gl);

    // redraw the components
    this.topLevelComponent.draw(gl);
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param drawable
   *          This parameter is ignored.
   * @param modeChanged
   *          This parameter is ignored.
   * @param deviceChanged
   *          This parameter is ignored.
   */
  @Override
  public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
      boolean deviceChanged) {
    // intentionally unimplemented
  }

  /**
   * Queues a single piece of food to be added to the tank at a random position.
   * 
   * Even if this method is called multiple times before
   * {@link #display(GLAutoDrawable)} is called, only one new piece of food will
   * be added.
   */
  public void generateFood() {
    this.foodToAdd = new Food(randomPoint(), this.glut, "food "
        + this.foodCounter);
    this.foodCounter++;
  }

  /**
   * Initializes the scene and model.
   * 
   * @param drawable
   *          {@inheritDoc}
   */
  @Override
  public void init(final GLAutoDrawable drawable) {
    final GL gl = drawable.getGL();

    // perform any initialization needed by the scene model
    this.topLevelComponent.initialize(gl);

    // initially draw the scene
    this.topLevelComponent.update(gl);

    // set up the lighting for shaded display of the model
    final float position[] = { 1, 1, 1, 0 };
    final float ambientColor[] = { 0.25f, 0.25f, 0.25f, 1 };
    final float diffuseColor[] = { 1, 1, 1, 1 };

    gl.glPolygonMode(GL.GL_FRONT, GL.GL_FILL);
    gl.glEnable(GL.GL_COLOR_MATERIAL);
    gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);

    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glShadeModel(GL.GL_SMOOTH);

    // set up the light source
    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);
    gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientColor, 0);
    gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseColor, 0);

    // turn lighting and depth buffering on
    gl.glEnable(GL.GL_LIGHTING);
    gl.glEnable(GL.GL_LIGHT0);
    gl.glEnable(GL.GL_DEPTH_TEST);
    gl.glEnable(GL.GL_NORMALIZE);
  }

  /**
   * {@inheritDoc}
   * 
   * @param drawable
   *          {@inheritDoc}
   * @param x
   *          {@inheritDoc}
   * @param y
   *          {@inheritDoc}
   * @param width
   *          {@inheritDoc}
   * @param height
   *          {@inheritDoc}
   */
  @Override
  public void reshape(final GLAutoDrawable drawable, final int x, final int y,
      final int width, final int height) {
    final GL gl = drawable.getGL();

    // prevent division by zero by ensuring window has height 1 at least
    final int newHeight = Math.max(1, height);

    // compute the aspect ratio
    final double ratio = (double) width / newHeight;

    // reset the projection coordinate system before modifying it
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();

    // set the viewport to be the entire window
    gl.glViewport(0, 0, width, newHeight);

    // set the clipping volume
    this.glu.gluPerspective(25, ratio, 0.1, 100);

    // camera positioned at (0,0,6), look at point (0,0,0), up vector (0,1,0)
    this.glu.gluLookAt(0, 0, 18, 0, 0, 0, 0, 1, 0);

    // switch back to model coordinate system
    gl.glMatrixMode(GL.GL_MODELVIEW);
  }

  /**
   * Sets the controller for view rotations, so that the display can be updated
   * based on the rotation requested by the user.
   * 
   * @param rotationController
   *          The controller for view rotations.
   */
  public void setRotationController(final RotationController rotationController) {
    this.rotationController = rotationController;
  }
}
