/**
 * DrawingController.java - controller for drawing on an OpenGL canvas
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import edu.bu.cs.cs480.model.Component;
import edu.bu.cs.cs480.model.FloatColor;
import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.shapes.Tank;

/**
 * The controller for drawing on the OpenGL canvas.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class DrawingController implements GLEventListener {
  /** The color for components which are selected for rotation. */
  public static final FloatColor ACTIVE_COLOR = FloatColor.RED;
  /** The color for components which are not selected for rotation. */
  public static final FloatColor INACTIVE_COLOR = FloatColor.ORANGE;
  /** The initial position of the top level component in the scene. */
  public static final Point3D INITIAL_POSITION = new Point3D(0, 0, 0);

  /** The OpenGL utility object. */
  private final GLU glu = new GLU();
  /** The OpenGL utility toolkit object. */
  // private final GLUT glut = new GLUT();
  /** The controller for view rotations. */
  private RotationController rotationController = null;
  /** Whether the state of the model has been changed. */
  private boolean stateChanged = true;
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
    final Component tank = new Component(Point3D.ORIGIN, new Tank(4, 4, 4),
        "tank");
    this.topLevelComponent.addChild(tank);
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

    // update the position of the components which need to be updated
    if (this.stateChanged) {
      this.topLevelComponent.update(gl);
      this.stateChanged = false;
    }

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
    this.glu.gluLookAt(0, 0, 12, 0, 0, 0, 0, 1, 0);

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
