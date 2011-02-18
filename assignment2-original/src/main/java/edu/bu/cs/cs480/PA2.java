// ****************************************************************************
// Example Main Program for CS480 Programming Assignment 2
// ****************************************************************************
// Description: This is a simple example program that allows the
// user to display a hand model and change its parameters.
//
// The following keys control the program:
//
// Q,q, <escape>: quit
// R: reset viewing angle
//
// Left mouse click + drag motion: rotate the hand view
//
// 1 : toggle the first finger (thumb) active in rotation
// 2 : toggle the second finger active in rotation
// 3 : toggle the third finger active in rotation
// 4 : toggle the fourth finger active in rotation
// 5 : toggle the fifth finger active in rotation
//
// X : use the X axis rotation at the active joint(s)
// Y : use the Y axis rotation at the active joint(s)
// Z : use the Z axis rotation at the active joint(s)
//
// P : select joint that connects finger to palm
// M : select middle joint
// D : select last (distal) joint
//
// up-arrow, down-arrow: increase/decrease rotation angle
//
// ****************************************************************************
// History :
// 16 Jan 2008 Created by Tai-Peng Tian (tiantp@gmail.com) based on the C
// code by Stan Sclaroff
//
package edu.bu.cs.cs480;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;

public class PA2 extends JFrame implements GLEventListener, KeyListener,
    MouseListener, MouseMotionListener {
  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = -7060944143920496524L;

  /**
   * Runs the hand simulation in a single JFrame.
   * 
   * @param args
   *          This parameter is ignored.
   */
  public static void main(final String[] args) {
    new PA2().animator.start();
  }

  /** The animator which controls the framerate at which the canvas is animated. */
  private final FPSAnimator animator;
  /** The canvas on which we draw the scene. */
  private final GLCanvas canvas;
  /** The capabilities of the canvas. */
  private final GLCapabilities capabilities = new GLCapabilities();
  /** The default width of the created window. */
  private final int DEFAULT_WINDOW_HEIGHT = 512;
  /** The default height of the created window. */
  private final int DEFAULT_WINDOW_WIDTH = 512;
  /** The GL utility object. */
  private final GLU glu = new GLU();
  /** The hand model which will be changed by keyboard and mouse presses. */
  private final Hand hand = new Hand();

  /** The last x and y coordinates of the mouse press. */
  private int last_x = 0, last_y = 0;
  /** Whether the world is being rotated. */
  private boolean rotate_world = false;

  /** The quaternion which controls the rotation of the world. */
  private Quaternion viewing_quaternion = new Quaternion();

  /**
   * Initializes the necessary OpenGL objects and adds a canvas to this JFrame.
   */
  public PA2() {
    this.capabilities.setDoubleBuffered(true);

    this.canvas = new GLCanvas(this.capabilities);
    this.canvas.addGLEventListener(this);
    this.canvas.addMouseListener(this);
    this.canvas.addMouseMotionListener(this);
    this.canvas.addKeyListener(this);
    // this is true by default, but we just add this line to be explicit
    this.canvas.setAutoSwapBufferMode(true);
    this.getContentPane().add(this.canvas);

    // refresh the scene at 60 frames per second
    this.animator = new FPSAnimator(this.canvas, 60);

    this.setTitle("CS480/CS680 : Hand Simulator");
    this.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

    /**
     * After this line, all OpenGL initialization is complete. Add any
     * initialization code required for your hand class here.
     */
  }

  /**
   * Redisplays the scene containing the hand model.
   * 
   * @param drawable
   *          The OpenGL drawable object with which to create OpenGL models.
   */
  public void display(final GLAutoDrawable drawable) {
    final GL gl = drawable.getGL();

    // clear the display
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

    // from here on affect the model view
    gl.glMatrixMode(GL.GL_MODELVIEW);

    // start with the identity matrix initially
    gl.glLoadIdentity();

    // rotate the world by the appropriate rotation quaternion
    gl.glMultMatrixf(this.viewing_quaternion.to_matrix(), 0);

    // update the position of the hand if it needs to be updated, and draw it
    this.hand.update(gl);
    this.hand.draw(gl);
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
  public void init(final GLAutoDrawable drawable) {
    final GL gl = drawable.getGL();

    // perform any initialization needed by the hand model
    this.hand.init(gl);

    // set up for shaded display of the hand
    final float light0_position[] = { 1, 1, 1, 0 };
    final float light0_ambient_color[] = { 0.25f, 0.25f, 0.25f, 1 };
    final float light0_diffuse_color[] = { 1, 1, 1, 1 };

    gl.glPolygonMode(GL.GL_FRONT, GL.GL_FILL);
    gl.glEnable(GL.GL_COLOR_MATERIAL);
    gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);

    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glShadeModel(GL.GL_SMOOTH);

    // set up the light source
    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light0_position, 0);
    gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light0_ambient_color, 0);
    gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light0_diffuse_color, 0);

    // turn lighting and depth buffering on
    gl.glEnable(GL.GL_LIGHTING);
    gl.glEnable(GL.GL_LIGHT0);
    gl.glEnable(GL.GL_DEPTH_TEST);
    gl.glEnable(GL.GL_NORMALIZE);
  }

  /**
   * Interprets key presses according to the following scheme:
   * 
   * up-arrow, down-arrow: increase/decrease rotation angle
   * 
   * @param key
   *          The key press event object.
   */
  public void keyPressed(final KeyEvent key) {
    switch (key.getKeyCode()) {
    case KeyEvent.VK_KP_UP:
    case KeyEvent.VK_UP:
      this.hand.increment_rotation_angle();
      break;

    case KeyEvent.VK_KP_DOWN:
    case KeyEvent.VK_DOWN:
      hand.decrement_rotation_angle();
      break;

    default:
      break;
    }
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param key
   *          This parameter is ignored.
   */
  public void keyReleased(final KeyEvent key) {
    // intentionally unimplemented
  }

  /**
   * Interprets typed keys according to the following scheme:
   * 
   * 1 : toggle the first finger (thumb) active in rotation
   * 
   * 2 : toggle the second finger active in rotation
   * 
   * 3 : toggle the third finger active in rotation
   * 
   * 4 : toggle the fourth finger active in rotation
   * 
   * 5 : toggle the fifth finger active in rotation
   * 
   * X : use the X axis rotation at the active joint(s)
   * 
   * Y : use the Y axis rotation at the active joint(s)
   * 
   * Z : use the Z axis rotation at the active joint(s)
   * 
   * P : select joint that connects finger to palm
   * 
   * M : select middle joint
   * 
   * D : select last (distal) joint
   * 
   * R : resets the view to the initial rotation
   * 
   * Q, Esc : exits the program
   * 
   */
  public void keyTyped(KeyEvent key) {
    switch (key.getKeyChar()) {
    case 'Q':
    case 'q':
    case KeyEvent.VK_ESCAPE:
      new Thread() {
        public void run() {
          animator.stop();
        }
      }.start();
      System.exit(0);
      break;

    // set the viewing quaternion to 0 rotation
    case 'R':
    case 'r':
      this.viewing_quaternion.reset();
      break;

    // Toggle which finger(s) are affected by the current rotation
    case '1':
      this.hand.toggle_finger(Hand.FingerType.FIRST);
      break;
    case '2':
      this.hand.toggle_finger(Hand.FingerType.SECOND);
      break;
    case '3':
      this.hand.toggle_finger(Hand.FingerType.THIRD);
      break;
    case '4':
      this.hand.toggle_finger(Hand.FingerType.FOURTH);
      break;
    case '5':
      this.hand.toggle_finger(Hand.FingerType.FIFTH);
      break;

    // select joint
    case 'D':
    case 'd':
      this.hand.set_joint(Hand.JointType.DISTAL);
      break;
    case 'P':
    case 'p':
      this.hand.set_joint(Hand.JointType.PALM);
      break;
    case 'M':
    case 'm':
      this.hand.set_joint(Hand.JointType.MIDDLE);
      break;

    // change the axis of rotation at current active joint
    case 'X':
    case 'x':
      this.hand.set_rotation_axis(Hand.AxisType.X);
      break;
    case 'Y':
    case 'y':
      this.hand.set_rotation_axis(Hand.AxisType.Y);
      break;
    case 'Z':
    case 'z':
      this.hand.set_rotation_axis(Hand.AxisType.Z);
      break;

    default:
      break;
    }
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseClicked(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * Updates the rotation quaternion as the mouse is dragged.
   * 
   * @param mouse
   *          The mouse drag event object.
   */
  public void mouseDragged(final MouseEvent mouse) {
    if (this.rotate_world) {
      // get the current position of the mouse
      final int x = mouse.getX();
      final int y = mouse.getY();

      // get the change in position from the previous one
      final int dx = x - this.last_x;
      final int dy = y - this.last_y;

      // create a unit vector in the direction of the vector (dy, dx, 0)
      final double magnitude = Math.sqrt(dx * dx + dy * dy);
      final float[] axis = new float[] { (float) (dy / magnitude),
          (float) (dx / magnitude), 0 };

      // calculate appropriate quaternion
      final float viewing_delta = 3.1415927f / 180.0f;
      final float s = (float) Math.sin(0.5f * viewing_delta);
      final float c = (float) Math.cos(0.5f * viewing_delta);
      final Quaternion Q = new Quaternion(c, s * axis[0], s * axis[1], s
          * axis[2]);
      this.viewing_quaternion = Q.multiply(this.viewing_quaternion);

      // normalize to counteract acccumulating round-off error
      this.viewing_quaternion.normalize();

      // save x, y as last x, y
      this.last_x = x;
      this.last_y = y;
    }
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseEntered(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseExited(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * This method is intentionally unimplemented.
   * 
   * @param mouse
   *          This parameter is ignored.
   */
  public void mouseMoved(MouseEvent mouse) {
    // intentionally unimplemented
  }

  /**
   * Starts rotating the world if the left mouse button was released.
   * 
   * @param mouse
   *          The mouse press event object.
   */
  public void mousePressed(final MouseEvent mouse) {
    if (mouse.getButton() == MouseEvent.BUTTON1) {
      this.last_x = mouse.getX();
      this.last_y = mouse.getY();
      this.rotate_world = true;
    }
  }

  /**
   * Stops rotating the world if the left mouse button was released.
   * 
   * @param mouse
   *          The mouse release event object.
   */
  public void mouseReleased(final MouseEvent mouse) {
    if (mouse.getButton() == MouseEvent.BUTTON1) {
      this.rotate_world = false;
    }
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
  public void reshape(final GLAutoDrawable drawable, final int x, final int y,
      final int width, int height) {
    final GL gl = drawable.getGL();

    // prevent division by zero by ensuring window has height 1 at least
    height = Math.max(1, height);

    // compute the aspect ratio
    final double ratio = (double) width / height;

    // reset the projection coordinate system before modifying it
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();

    // set the viewport to be the entire window
    gl.glViewport(0, 0, width, height);

    // set the clipping volume
    this.glu.gluPerspective(25, ratio, 0.1, 100);

    // camera positioned at (0,0,6), look at point (0,0,0), up vector (0,1,0)
    this.glu.gluLookAt(0, 0, 12, 0, 0, 0, 0, 1, 0);

    // switch back to model coordinate system
    gl.glMatrixMode(GL.GL_MODELVIEW);
  }
}