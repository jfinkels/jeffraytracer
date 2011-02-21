/**
 * PA2.java - driver for the hand model simulation
 * 
 * History:
 * 
 * 19 February 2011
 * 
 * - added documentation
 * 
 * (Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>)
 * 
 * 16 January 2008
 * 
 * - translated from C code by Stan Sclaroff
 * 
 * (Tai-Peng Tian <tiantp@gmail.com>)
 * 
 */
package edu.bu.cs.cs480;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;

/**
 * The main class which drives the hand model simulation.
 * 
 * @author Tai-Peng Tian <tiantp@gmail.com>
 * @since Spring 2008
 */
public class PA2 extends JFrame implements GLEventListener, KeyListener,
    MouseListener, MouseMotionListener {
  /** The default width of the created window. */
  public static final int DEFAULT_WINDOW_HEIGHT = 512;

  /** The default height of the created window. */
  public static final int DEFAULT_WINDOW_WIDTH = 512;

  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = -7060944143920496524L;

  /**
   * If the set contains the specified element, then remove it; if the set does
   * not contain the specified element, then add it.
   * 
   * @param <E>
   *          The type of element to add or remove.
   * @param set
   *          The set from which to add or remove the element.
   * @param element
   *          The element to add or remove.
   */
  private static <E> void addOrRemove(final Set<E> set, final E element) {
    if (set.contains(element)) {
      set.remove(element);
    } else {
      set.add(element);
    }
  }

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
  final FPSAnimator animator;
  /** The canvas on which we draw the scene. */
  private final GLCanvas canvas;
  /** The capabilities of the canvas. */
  private final GLCapabilities capabilities = new GLCapabilities();
  /** The OpenGL utility object. */
  private final GLU glu = new GLU();
  /** The OpenGL utility toolkit object. */
  private final GLUT glut = new GLUT();

  /** The hand model which will be changed by keyboard and mouse presses. */
  private final Hand hand = new Hand(this.glut);
  /** The last x and y coordinates of the mouse press. */
  private int last_x = 0, last_y = 0;

  /** Whether the world is being rotated. */
  private boolean rotate_world = false;

  /** The axis around which to rotate the selected joints. */
  private Axis selectedAxis = Axis.X;

  /** The currently selected fingers, on which to select joints to rotate. */
  private final Set<Finger> selectedFingers = new HashSet<Finger>();

  /** The currently selected joints to rotate. */
  private final Set<Joint> selectedJoints = new HashSet<Joint>();

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
    gl.glMultMatrixf(this.viewing_quaternion.toMatrix(), 0);

    // update the position of the hand if it needs to be updated
    if (this.stateChanged) {
      this.hand.update(gl);
      this.stateChanged = false;
    }

    // draw the hand
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

  public static final double ROTATION_ANGLE = 2.0;

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
      for (final Joint joint : this.selectedJoints) {
        joint.rotate(this.selectedAxis, ROTATION_ANGLE);
      }
      this.stateChanged = true;
      break;
    case KeyEvent.VK_KP_DOWN:
    case KeyEvent.VK_DOWN:
      for (final Joint joint : this.selectedJoints) {
        joint.rotate(this.selectedAxis, -ROTATION_ANGLE);
      }
      this.stateChanged = true;
      break;
    default:
      break;
    }
  }

  private boolean stateChanged = true;

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
  public void keyTyped(final KeyEvent key) {
    switch (key.getKeyChar()) {
    case 'Q':
    case 'q':
    case KeyEvent.VK_ESCAPE:
      new Thread() {
        @Override
        public void run() {
          PA2.this.animator.stop();
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
      if (this.selectedFingers.contains(this.hand.thumb())) {
        this.selectedFingers.remove(this.hand.thumb());
        for (final Joint joint : this.hand.thumb().joints()) {
          this.selectedJoints.remove(joint);
          joint.setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        }
      } else {
        this.selectedFingers.add(this.hand.thumb());
      }
      this.stateChanged = true;
      break;
    case '2':
      if (this.selectedFingers.contains(this.hand.indexFinger())) {
        this.selectedFingers.remove(this.hand.indexFinger());
        for (final Joint joint : this.hand.indexFinger().joints()) {
          this.selectedJoints.remove(joint);
          joint.setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        }
      } else {
        this.selectedFingers.add(this.hand.indexFinger());
      }
      this.stateChanged = true;
      break;
    case '3':
      if (this.selectedFingers.contains(this.hand.middleFinger())) {
        this.selectedFingers.remove(this.hand.middleFinger());
        for (final Joint joint : this.hand.middleFinger().joints()) {
          this.selectedJoints.remove(joint);
          joint.setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        }
      } else {
        this.selectedFingers.add(this.hand.middleFinger());
      }
      this.stateChanged = true;
      break;
    case '4':
      if (this.selectedFingers.contains(this.hand.ringFinger())) {
        this.selectedFingers.remove(this.hand.ringFinger());
        for (final Joint joint : this.hand.ringFinger().joints()) {
          this.selectedJoints.remove(joint);
          joint.setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        }
      } else {
        this.selectedFingers.add(this.hand.ringFinger());
      }
      this.stateChanged = true;
      break;
    case '5':
      if (this.selectedFingers.contains(this.hand.pinkyFinger())) {
        this.selectedFingers.remove(this.hand.pinkyFinger());
        for (final Joint joint : this.hand.pinkyFinger().joints()) {
          this.selectedJoints.remove(joint);
          joint.setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        }
      } else {
        this.selectedFingers.add(this.hand.pinkyFinger());
      }
      this.stateChanged = true;
      break;

    // select joint
    case 'D':
    case 'd':
      for (final Finger finger : this.selectedFingers) {
        if (this.selectedJoints.contains(finger.distal())) {
          this.selectedJoints.remove(finger.distal());
          finger.distal().setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        } else {
          this.selectedJoints.add(finger.distal());
          finger.distal().setColor(new FloatColor(1, 0, 0));
        }
        this.stateChanged = true;
      }
      break;
    case 'P':
    case 'p':
      for (final Finger finger : this.selectedFingers) {
        if (this.selectedJoints.contains(finger.palm())) {
          this.selectedJoints.remove(finger.palm());
          finger.palm().setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        } else {
          this.selectedJoints.add(finger.palm());
          finger.palm().setColor(new FloatColor(1, 0, 0));
        }
        this.stateChanged = true;
      }
      break;
    case 'M':
    case 'm':
      for (final Finger finger : this.selectedFingers) {
        if (this.selectedJoints.contains(finger.middle())) {
          this.selectedJoints.remove(finger.middle());
          finger.middle().setColor(new FloatColor(0.8f, 0.5f, 0.2f));
        } else {
          this.selectedJoints.add(finger.middle());
          finger.middle().setColor(new FloatColor(1, 0, 0));
        }
        this.stateChanged = true;
      }
      break;

    // change the axis of rotation at current active joint
    case 'X':
    case 'x':
      this.selectedAxis = Axis.X;
      break;
    case 'Y':
    case 'y':
      this.selectedAxis = Axis.Y;
      break;
    case 'Z':
    case 'z':
      this.selectedAxis = Axis.Z;
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
}
