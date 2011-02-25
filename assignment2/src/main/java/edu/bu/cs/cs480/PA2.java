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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2008
 */
public class PA2 extends JFrame implements GLEventListener, KeyListener,
    MouseListener, MouseMotionListener {
  /** The default width of the created window. */
  public static final int DEFAULT_WINDOW_HEIGHT = 512;
  /** The default height of the created window. */
  public static final int DEFAULT_WINDOW_WIDTH = 512;
  /** The angle by which to rotate the joint on user request to rotate. */
  public static final double ROTATION_ANGLE = 2.0;
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
  // private final Hand hand = new Hand();
  /** The arm model which will be changed by keyboard and mouse presses. */
  // private final Arm arm = new Arm(0, 0, 0, 0.5, 5);
  /** The last x and y coordinates of the mouse press. */
  private int last_x = 0, last_y = 0;
  /** Whether the world is being rotated. */
  private boolean rotate_world = false;
  /** The axis around which to rotate the selected joints. */
  private Axis selectedAxis = Axis.X;
  /** The currently selected fingers, on which to select joints to rotate. */
  // private final Set<Finger> selectedFingers = new HashSet<Finger>();
  /** The currently selected joints to rotate. */
  // private final Set<RotatableComponent> selectedJoints = new
  // HashSet<RotatableComponent>();
  private final Set<Component> selectedComponents = new HashSet<Component>(8);
  /** Whether the state of the model has been changed. */
  private boolean stateChanged = true;
  /** The quaternion which controls the rotation of the world. */
  private Quaternion viewing_quaternion = new Quaternion();

  public static final double PALM_JOINT_HEIGHT = 0.25;
  public static final double MIDDLE_JOINT_HEIGHT = 0.25;
  public static final double DISTAL_JOINT_HEIGHT = 0.2;

  public static final double FINGER_RADIUS = 0.09;
  private final Component hand;
  private final Component forearm;
  private final Component upperArm;

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

    // all the distal joints
    final Component distal1 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut));
    final Component distal2 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut));
    final Component distal3 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut));
    final Component distal4 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut));
    final Component distal5 = new Component(new Point3D(0, 0,
        MIDDLE_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        DISTAL_JOINT_HEIGHT, this.glut));

    // all the middle joints
    final Component middle1 = new Component(new Point3D(0, 0,
        PALM_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut));
    final Component middle2 = new Component(new Point3D(0, 0,
        PALM_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut));
    final Component middle3 = new Component(new Point3D(0, 0,
        PALM_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut));
    final Component middle4 = new Component(new Point3D(0, 0,
        PALM_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut));
    final Component middle5 = new Component(new Point3D(0, 0,
        PALM_JOINT_HEIGHT), new RoundedCylinder(FINGER_RADIUS,
        MIDDLE_JOINT_HEIGHT, this.glut));

    // all the palm joints, displaced by various amounts from the palm
    final Component palm1 = new Component(new Point3D(-0.5, 0, 0.7),
        new RoundedCylinder(FINGER_RADIUS, PALM_JOINT_HEIGHT, this.glut));
    final Component palm2 = new Component(new Point3D(-.2, 0, 0.7),
        new RoundedCylinder(FINGER_RADIUS, PALM_JOINT_HEIGHT, this.glut));
    final Component palm3 = new Component(new Point3D(0.1, 0, 0.7),
        new RoundedCylinder(FINGER_RADIUS, PALM_JOINT_HEIGHT, this.glut));
    final Component palm4 = new Component(new Point3D(0.4, 0, 0.7),
        new RoundedCylinder(FINGER_RADIUS, PALM_JOINT_HEIGHT, this.glut));
    final Component palm5 = new Component(new Point3D(0.5, 0, 0),
        new RoundedCylinder(FINGER_RADIUS, PALM_JOINT_HEIGHT, this.glut));

    // put together the fingers for easier selection by keyboard input later on
    this.fingers = new Finger[] { new Finger(palm1, middle1, distal1),
        new Finger(palm2, middle2, distal2),
        new Finger(palm3, middle3, distal3),
        new Finger(palm4, middle4, distal4),
        new Finger(palm5, middle5, distal5), };

    // the hand, which models the wrist joint
    this.hand = new Component(new Point3D(0, 0, FOREARM_HEIGHT), new Palm(
        HAND_RADIUS, this.glut));

    // the forearm, which models the elbow joint
    this.forearm = new Component(new Point3D(0, 0, UPPER_ARM_HEIGHT),
        new RoundedCylinder(ARM_RADIUS, FOREARM_HEIGHT, this.glut));

    // the upper arm which models the shoulder joint
    this.upperArm = new Component(new Point3D(0, 0, 0), new RoundedCylinder(
        ARM_RADIUS, UPPER_ARM_HEIGHT, this.glut));

    // the top level component which provides an initial position and rotation
    // to the scene (but does not cause anything to be drawn)
    this.topLevelComponent = new Component(INITIAL_POSITION);

    this.topLevelComponent.addChild(this.upperArm);
    // the funny bone's connected to the...forearm
    this.upperArm.addChild(this.forearm);
    // the forearm's connected to the...wrist bone
    this.forearm.addChild(this.hand);
    // the wrist bone's connected to the...fingers
    this.hand.addChildren(palm1, palm2, palm3, palm4, palm5);
    palm1.addChild(middle1);
    palm2.addChild(middle2);
    palm3.addChild(middle3);
    palm4.addChild(middle4);
    palm5.addChild(middle5);
    middle1.addChild(distal1);
    middle2.addChild(distal2);
    middle3.addChild(distal3);
    middle4.addChild(distal4);
    middle5.addChild(distal5);

    // turn the whole arm to be at an arm-like angle
    this.topLevelComponent.rotate(Axis.Y, 225);
    this.topLevelComponent.rotate(Axis.X, -90);

    // rotate the elbow to be half bent
    this.forearm.rotate(Axis.Y, 90);

    // set rotation limits for the shoulder
    this.upperArm.setXPositiveExtent(45);
    this.upperArm.setXNegativeExtent(-45);
    this.upperArm.setYPositiveExtent(135);
    this.upperArm.setYNegativeExtent(-135);
    this.upperArm.setZPositiveExtent(45);
    this.upperArm.setZNegativeExtent(0);

    // set rotation limits for the elbow
    this.forearm.setXPositiveExtent(180);
    this.forearm.setXNegativeExtent(0);
    this.forearm.setYPositiveExtent(0);
    this.forearm.setYNegativeExtent(0);
    this.forearm.setZPositiveExtent(0);
    this.forearm.setZNegativeExtent(0);

    // set rotation limits for the wrist
    this.hand.setXPositiveExtent(90);
    this.hand.setXNegativeExtent(-90);
    this.hand.setYPositiveExtent(12);
    this.hand.setYNegativeExtent(-12);
    this.hand.setZPositiveExtent(0);
    this.hand.setZNegativeExtent(0);

    // set rotation limits for the palm joints of the finger
    for (final Component palmJoint : Arrays.asList(palm1, palm2, palm3, palm4,
        palm5)) {
      palmJoint.setXPositiveExtent(90);
      palmJoint.setXNegativeExtent(-15);
      palmJoint.setYPositiveExtent(12);
      palmJoint.setYNegativeExtent(-12);
      palmJoint.setZPositiveExtent(0);
      palmJoint.setZNegativeExtent(0);
    }

    // set rotation limits for the middle joints of the finger
    for (final Component middleJoint : Arrays.asList(middle1, middle2,
        middle3, middle4, middle5)) {
      middleJoint.setXPositiveExtent(110);
      middleJoint.setXNegativeExtent(0);
      middleJoint.setYPositiveExtent(0);
      middleJoint.setYNegativeExtent(0);
      middleJoint.setZPositiveExtent(0);
      middleJoint.setZNegativeExtent(0);
    }

    // set rotation limits for the distal joints of the finger
    for (final Component distalJoint : Arrays.asList(distal1, distal2,
        distal3, distal4, distal5)) {
      distalJoint.setXPositiveExtent(80);
      distalJoint.setXNegativeExtent(-5);
      distalJoint.setYPositiveExtent(0);
      distalJoint.setYNegativeExtent(0);
      distalJoint.setZPositiveExtent(0);
      distalJoint.setZNegativeExtent(0);
    }
  }

  public static final double FOREARM_HEIGHT = 1.5;
  public static final double UPPER_ARM_HEIGHT = 1.5;
  public static final double HAND_RADIUS = 0.5;
  public static final double ARM_RADIUS = 0.25;

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

    // update the position of the components which need to be updated
    // TODO only need to update the selected and JUST deselected components
    if (this.stateChanged) {
      this.topLevelComponent.update(gl);
      this.stateChanged = false;
    }

    // redraw the components
    this.topLevelComponent.draw(gl);
  }

  /** The initial position of the top level component in the scene. */
  public static final Point3D INITIAL_POSITION = new Point3D(2, 0, 2);
  /** The top level component in the scene. */
  private final Component topLevelComponent;

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
    this.topLevelComponent.initialize(gl);

    // initially draw the scene
    this.topLevelComponent.update(gl);

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
      for (final Component component : this.selectedComponents) {
        component.rotate(this.selectedAxis, ROTATION_ANGLE);
      }
      this.stateChanged = true;
      break;
    case KeyEvent.VK_KP_DOWN:
    case KeyEvent.VK_DOWN:
      for (final Component component : this.selectedComponents) {
        component.rotate(this.selectedAxis, -ROTATION_ANGLE);
      }
      this.stateChanged = true;
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

  // private final Hand hand = this.arm.hand();

  /**
   * Prints the angles of each joint in each finger of the hand for debugging
   * purposes.
   */
  // private void printHandAngles() {
  // System.out.print("pinky finger - palm joint - x angle: ");
  // System.out.println(this.hand.pinkyFinger().palm().xAngle());
  // System.out.print("pinky finger - palm joint - y angle: ");
  // System.out.println(this.hand.pinkyFinger().palm().yAngle());
  // System.out.print("pinky finger - palm joint - z angle: ");
  // System.out.println(this.hand.pinkyFinger().palm().zAngle());
  // System.out.print("pinky finger - middle joint - x angle: ");
  // System.out.println(this.hand.pinkyFinger().middle().xAngle());
  // System.out.print("pinky finger - middle joint - y angle: ");
  // System.out.println(this.hand.pinkyFinger().middle().yAngle());
  // System.out.print("pinky finger - middle joint - z angle: ");
  // System.out.println(this.hand.pinkyFinger().middle().zAngle());
  // System.out.print("pinky finger - distal joint - x angle: ");
  // System.out.println(this.hand.pinkyFinger().distal().xAngle());
  // System.out.print("pinky finger - distal joint - y angle: ");
  // System.out.println(this.hand.pinkyFinger().distal().yAngle());
  // System.out.print("pinky finger - distal joint - z angle: ");
  // System.out.println(this.hand.pinkyFinger().distal().zAngle());
  //
  // System.out.print("ring finger - palm joint - x angle: ");
  // System.out.println(this.hand.ringFinger().palm().xAngle());
  // System.out.print("ring finger - palm joint - y angle: ");
  // System.out.println(this.hand.ringFinger().palm().yAngle());
  // System.out.print("ring finger - palm joint - z angle: ");
  // System.out.println(this.hand.ringFinger().palm().zAngle());
  // System.out.print("ring finger - middle joint - x angle: ");
  // System.out.println(this.hand.ringFinger().middle().xAngle());
  // System.out.print("ring finger - middle joint - y angle: ");
  // System.out.println(this.hand.ringFinger().middle().yAngle());
  // System.out.print("ring finger - middle joint - z angle: ");
  // System.out.println(this.hand.ringFinger().middle().zAngle());
  // System.out.print("ring finger - distal joint - x angle: ");
  // System.out.println(this.hand.ringFinger().distal().xAngle());
  // System.out.print("ring finger - distal joint - y angle: ");
  // System.out.println(this.hand.ringFinger().distal().yAngle());
  // System.out.print("ring finger - distal joint - z angle: ");
  // System.out.println(this.hand.ringFinger().distal().zAngle());
  //
  // System.out.print("middle finger - palm joint - x angle: ");
  // System.out.println(this.hand.middleFinger().palm().xAngle());
  // System.out.print("middle finger - palm joint - y angle: ");
  // System.out.println(this.hand.middleFinger().palm().yAngle());
  // System.out.print("middle finger - palm joint - z angle: ");
  // System.out.println(this.hand.middleFinger().palm().zAngle());
  // System.out.print("middle finger - middle joint - x angle: ");
  // System.out.println(this.hand.middleFinger().middle().xAngle());
  // System.out.print("middle finger - middle joint - y angle: ");
  // System.out.println(this.hand.middleFinger().middle().yAngle());
  // System.out.print("middle finger - middle joint - z angle: ");
  // System.out.println(this.hand.middleFinger().middle().zAngle());
  // System.out.print("middle finger - distal joint - x angle: ");
  // System.out.println(this.hand.middleFinger().distal().xAngle());
  // System.out.print("middle finger - distal joint - y angle: ");
  // System.out.println(this.hand.middleFinger().distal().yAngle());
  // System.out.print("middle finger - distal joint - z angle: ");
  // System.out.println(this.hand.middleFinger().distal().zAngle());
  //
  // System.out.print("index finger - palm joint - x angle: ");
  // System.out.println(this.hand.indexFinger().palm().xAngle());
  // System.out.print("index finger - palm joint - y angle: ");
  // System.out.println(this.hand.indexFinger().palm().yAngle());
  // System.out.print("index finger - palm joint - z angle: ");
  // System.out.println(this.hand.indexFinger().palm().zAngle());
  // System.out.print("index finger - middle joint - x angle: ");
  // System.out.println(this.hand.indexFinger().middle().xAngle());
  // System.out.print("index finger - middle joint - y angle: ");
  // System.out.println(this.hand.indexFinger().middle().yAngle());
  // System.out.print("index finger - middle joint - z angle: ");
  // System.out.println(this.hand.indexFinger().middle().zAngle());
  // System.out.print("index finger - distal joint - x angle: ");
  // System.out.println(this.hand.indexFinger().distal().xAngle());
  // System.out.print("index finger - distal joint - y angle: ");
  // System.out.println(this.hand.indexFinger().distal().yAngle());
  // System.out.print("index finger - distal joint - z angle: ");
  // System.out.println(this.hand.indexFinger().distal().zAngle());
  //
  // System.out.print("thumb - palm joint - x angle: ");
  // System.out.println(this.hand.thumb().palm().xAngle());
  // System.out.print("thumb - palm joint - y angle: ");
  // System.out.println(this.hand.thumb().palm().yAngle());
  // System.out.print("thumb - palm joint - z angle: ");
  // System.out.println(this.hand.thumb().palm().zAngle());
  // System.out.print("thumb - middle joint - x angle: ");
  // System.out.println(this.hand.thumb().middle().xAngle());
  // System.out.print("thumb - middle joint - y angle: ");
  // System.out.println(this.hand.thumb().middle().yAngle());
  // System.out.print("thumb - middle joint - z angle: ");
  // System.out.println(this.hand.thumb().middle().zAngle());
  // System.out.print("thumb - distal joint - x angle: ");
  // System.out.println(this.hand.thumb().distal().xAngle());
  // System.out.print("thumb - distal joint - y angle: ");
  // System.out.println(this.hand.thumb().distal().yAngle());
  // System.out.print("thumb - distal joint - z angle: ");
  // System.out.println(this.hand.thumb().distal().zAngle());
  // }

  // private final boolean[] selectedFingers = {false, false, false, false,
  // false};

  private final Set<Finger> selectedFingers = new HashSet<Finger>(5);
  private final Finger[] fingers;

  private class Finger {
    private final Component palmJoint;
    private final Component middleJoint;
    private final Component distalJoint;

    private final List<Component> joints;

    public Finger(final Component palmJoint, final Component middleJoint,
        final Component distalJoint) {
      this.palmJoint = palmJoint;
      this.middleJoint = middleJoint;
      this.distalJoint = distalJoint;

      this.joints = Collections.unmodifiableList(Arrays.asList(this.palmJoint,
          this.middleJoint, this.distalJoint));
    }

    public Component palmJoint() {
      return this.palmJoint;
    }

    public Component middleJoint() {
      return this.middleJoint;
    }

    public Component distalJoint() {
      return this.distalJoint;
    }

    public List<Component> joints() {
      return this.joints;
    }
  }

  private void toggleSelection(final Finger finger) {
    if (this.selectedFingers.contains(finger)) {
      this.selectedFingers.remove(finger);
      this.selectedComponents.removeAll(finger.joints());
      for (final Component joint : finger.joints()) {
        joint.setColor(INACTIVE_COLOR);
      }
    } else {
      this.selectedFingers.add(finger);
    }
    this.stateChanged = true;
  }

  private void toggleSelection(final Component component) {
    if (this.selectedComponents.contains(component)) {
      this.selectedComponents.remove(component);
      component.setColor(INACTIVE_COLOR);
    } else {
      this.selectedComponents.add(component);
      component.setColor(ACTIVE_COLOR);
    }
    this.stateChanged = true;
  }

  public static final FloatColor INACTIVE_COLOR = FloatColor.ORANGE;
  public static final FloatColor ACTIVE_COLOR = FloatColor.RED;

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
   * 6 : toggle the hand for rotation
   * 
   * 7 : toggle the forearm for rotation
   * 
   * 8 : toggle the upper arm for rotation
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
   * K : prints the angles of the five fingers for debugging purposes
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

    // print the angles of the fingers
    case 'K':
    case 'k':
      // printHandAngles();
      break;

    // set the state of the hand to the next test case
    case 'T':
    case 't':
      
      break;

    // set the viewing quaternion to 0 rotation
    case 'R':
    case 'r':
      this.viewing_quaternion.reset();
      break;

    // Toggle which finger(s) are affected by the current rotation
    case '1':
      toggleSelection(this.fingers[0]);
      break;
    case '2':
      toggleSelection(this.fingers[1]);
      break;
    case '3':
      toggleSelection(this.fingers[2]);
      break;
    case '4':
      toggleSelection(this.fingers[3]);
      break;
    case '5':
      toggleSelection(this.fingers[4]);
      break;

    // toggle which joints are affected by the current rotation
    case 'D':
    case 'd':
      for (final Finger finger : this.selectedFingers) {
        toggleSelection(finger.distalJoint());
      }
      break;
    case 'M':
    case 'm':
      for (final Finger finger : this.selectedFingers) {
        toggleSelection(finger.middleJoint());
      }
      break;
    case 'P':
    case 'p':
      for (final Finger finger : this.selectedFingers) {
        toggleSelection(finger.palmJoint());
      }
      break;

    case '6':
      toggleSelection(this.hand);
      break;
    case '7':
      toggleSelection(this.forearm);
      break;
    case '8':
      toggleSelection(this.upperArm);
      break;

    /*
     * case '1': if (this.selectedFingers.contains(this.hand.thumb())) {
     * this.selectedFingers.remove(this.hand.thumb()); for (final Joint joint :
     * this.hand.thumb().joints()) { this.selectedJoints.remove(joint);
     * joint.setColor(FloatColor.ORANGE); } } else {
     * this.selectedFingers.add(this.hand.thumb()); } this.stateChanged = true;
     * break; case '2': if
     * (this.selectedFingers.contains(this.hand.indexFinger())) {
     * this.selectedFingers.remove(this.hand.indexFinger()); for (final Joint
     * joint : this.hand.indexFinger().joints()) {
     * this.selectedJoints.remove(joint); joint.setColor(FloatColor.ORANGE); } }
     * else { this.selectedFingers.add(this.hand.indexFinger()); }
     * this.stateChanged = true; break; case '3': if
     * (this.selectedFingers.contains(this.hand.middleFinger())) {
     * this.selectedFingers.remove(this.hand.middleFinger()); for (final Joint
     * joint : this.hand.middleFinger().joints()) {
     * this.selectedJoints.remove(joint); joint.setColor(FloatColor.ORANGE); } }
     * else { this.selectedFingers.add(this.hand.middleFinger()); }
     * this.stateChanged = true; break; case '4': if
     * (this.selectedFingers.contains(this.hand.ringFinger())) {
     * this.selectedFingers.remove(this.hand.ringFinger()); for (final Joint
     * joint : this.hand.ringFinger().joints()) {
     * this.selectedJoints.remove(joint); joint.setColor(FloatColor.ORANGE); } }
     * else { this.selectedFingers.add(this.hand.ringFinger()); }
     * this.stateChanged = true; break; case '5': if
     * (this.selectedFingers.contains(this.hand.pinkyFinger())) {
     * this.selectedFingers.remove(this.hand.pinkyFinger()); for (final Joint
     * joint : this.hand.pinkyFinger().joints()) {
     * this.selectedJoints.remove(joint); joint.setColor(FloatColor.ORANGE); } }
     * else { this.selectedFingers.add(this.hand.pinkyFinger()); }
     * this.stateChanged = true; break;
     * 
     * // toggle the hand for rotation case '6': if
     * (this.selectedJoints.contains(this.hand)) {
     * this.selectedJoints.remove(this.hand);
     * this.hand.setColor(FloatColor.ORANGE); } else {
     * this.selectedJoints.add(this.hand); this.hand.setColor(FloatColor.RED); }
     * this.stateChanged = true; break;
     * 
     * // toggle the forearm for rotation case '7': if
     * (this.selectedJoints.contains(this.arm.forearm())) {
     * this.selectedJoints.remove(this.arm.forearm());
     * this.arm.forearm().setColor(FloatColor.ORANGE); } else {
     * this.selectedJoints.add(this.arm.forearm());
     * this.arm.forearm().setColor(FloatColor.RED); } this.stateChanged = true;
     * break;
     * 
     * // toggle the upper arm for rotation case '8': if
     * (this.selectedJoints.contains(this.arm)) {
     * this.selectedJoints.remove(this.arm);
     * this.arm.setColor(FloatColor.ORANGE); } else {
     * this.selectedJoints.add(this.arm); this.arm.setColor(FloatColor.RED); }
     * this.stateChanged = true; break;
     */

    // select joint
    /*
     * case 'D': case 'd': for (final Finger finger : this.selectedFingers) { if
     * (this.selectedJoints.contains(finger.distal())) {
     * this.selectedJoints.remove(finger.distal());
     * finger.distal().setColor(FloatColor.ORANGE); } else {
     * this.selectedJoints.add(finger.distal());
     * finger.distal().setColor(FloatColor.RED); } this.stateChanged = true; }
     * break; case 'P': case 'p': for (final Finger finger :
     * this.selectedFingers) { if (this.selectedJoints.contains(finger.palm()))
     * { this.selectedJoints.remove(finger.palm());
     * finger.palm().setColor(FloatColor.ORANGE); } else {
     * this.selectedJoints.add(finger.palm());
     * finger.palm().setColor(FloatColor.RED); } this.stateChanged = true; }
     * break; case 'M': case 'm': for (final Finger finger :
     * this.selectedFingers) { if
     * (this.selectedJoints.contains(finger.middle())) {
     * this.selectedJoints.remove(finger.middle());
     * finger.middle().setColor(FloatColor.ORANGE); } else {
     * this.selectedJoints.add(finger.middle());
     * finger.middle().setColor(FloatColor.RED); } this.stateChanged = true; }
     * break;
     */

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
