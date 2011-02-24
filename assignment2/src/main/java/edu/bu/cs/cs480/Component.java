/**
 * 
 */
package edu.bu.cs.cs480;

import java.util.HashSet;
import java.util.Set;

import javax.media.opengl.GL;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 */
public class Component implements Rotatable, UpdatingDisplayable, Colorable {
  /** The handle to the OpenGL call list to use to draw this component. */
  private int callListHandle;
  /**
   * The children of this component, which will be drawn respecting the
   * translation and rotation of this component.
   */
  private Set<Component> children = new HashSet<Component>();
  /** The color of this component. */
  private FloatColor color = FloatColor.ORANGE;
  /** The displayable object which this component draws. */
  private final Displayable displayable;
  /** The position of this component. */
  private final Point3D position;
  /** The current angle at which this joint is rotated around the x axis. */
  private double xAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the x axis. */
  private double xNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the x axis. */
  private double xPositiveExtent = 180;
  /** The current angle at which this joint is rotated around the y axis. */
  private double yAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the y axis. */
  private double yNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the y axis. */
  private double yPositiveExtent = 180;
  /** The current angle at which this joint is rotated around the z axis. */
  private double zAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the z axis. */
  private double zNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the z axis. */
  private double zPositiveExtent = 180;

  /**
   * Instantiates this component with the specified position and the displayable
   * which this component represents.
   * 
   * @param position
   *          The opsition of this component.
   * @param displayable
   *          The object which this component represents.
   */
  public Component(final Point3D position, final Displayable displayable) {
    this.position = position;
    this.displayable = displayable;
  }

  /**
   * Adds the specified child to the set of children of this component.
   * 
   * @param component
   *          The component to add as a child of this component.
   */
  public void addChild(final Component component) {
    this.children.add(component);
  }

  /**
   * Calls the OpenGL call list which contains the commands which draw this
   * component.
   * 
   * @param gl
   *          The OpenGL object with which to perform the drawing.
   * 
   * @see edu.bu.cs.cs480.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * Initializes the call list which this component uses for drawing, then calls
   * the corresponding method on the children of this component.
   * 
   * @param gl
   *          The OpenGL object with which to perform the drawing.
   * 
   * @see edu.bu.cs.cs480.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL gl) {
    // create a new OpenGL call list handle
    this.callListHandle = gl.glGenLists(1);

    // initialize each of the children of this component
    for (final Component child : this.children) {
      child.initialize(gl);
    }
  }

  /**
   * Rotates this joint around the specified axis by the specified angle.
   * 
   * @param axis
   *          The axis of rotation.
   * @param angleDelta
   *          The angle by which to rotate this joint.
   */
  public void rotate(final Axis axis, final double angleDelta) {
    if (axis.equals(Axis.X)) {
      this.xAngle += angleDelta;
      this.xAngle = Math.min(this.xAngle, this.xPositiveExtent);
      this.xAngle = Math.max(this.xAngle, this.xNegativeExtent);
    } else if (axis.equals(Axis.Y)) {
      this.yAngle += angleDelta;
      this.yAngle = Math.min(this.yAngle, this.yPositiveExtent);
      this.yAngle = Math.max(this.yAngle, this.yNegativeExtent);
    } else if (axis.equals(Axis.Z)) {
      this.zAngle += angleDelta;
      this.zAngle = Math.min(this.zAngle, this.zPositiveExtent);
      this.zAngle = Math.max(this.zAngle, this.zNegativeExtent);
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @param color
   *          {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.Colorable#setColor(edu.bu.cs.cs480.FloatColor)
   */
  @Override
  public void setColor(final FloatColor color) {
    this.color = color;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXNegativeExtent
   *          The minimum angle to which this joint can be rotated around the x
   *          axis.
   */
  public void setXNegativeExtent(final double newXNegativeExtent) {
    this.xNegativeExtent = newXNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXPositiveExtent
   *          The maximum angle to which this joint can be rotated around the x
   *          axis.
   */
  public void setXPositiveExtent(final double newXPositiveExtent) {
    this.xPositiveExtent = newXPositiveExtent;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYNegativeExtent
   *          The minimum angle to which this joint can be rotated around the y
   *          axis.
   */
  public void setYNegativeExtent(final double newYNegativeExtent) {
    this.yNegativeExtent = newYNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYPositiveExtent
   *          The maximum angle to which this joint can be rotated around the y
   *          axis.
   */
  public void setYPositiveExtent(final double newYPositiveExtent) {
    this.yPositiveExtent = newYPositiveExtent;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZNegativeExtent
   *          The minimum angle to which this joint can be rotated around the z
   *          axis.
   */
  public void setZNegativeExtent(final double newZNegativeExtent) {
    this.zNegativeExtent = newZNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZPositiveExtent
   *          The maximum angle to which this joint can be rotated around the z
   *          axis.
   */
  public void setZPositiveExtent(final double newZPositiveExtent) {
    this.zPositiveExtent = newZPositiveExtent;
  }

  /**
   * Updates the call list used to when this component is drawn.
   * 
   * This method first calls the corresponding method on the children of this
   * component. Then this component is translated, rotated, and colored
   * appropriately. Next this component is drawn using the {@link Displayable}
   * specified in the constructor of this class. Finally, the children of this
   * component are drawn with respect to the rotation and translation done to
   * this component.
   * 
   * @param gl
   *          The OpenGL object with which to perform the drawing.
   * 
   * @see edu.bu.cs.cs480.UpdatingDisplayable#update(javax.media.opengl.GL)
   */
  @Override
  public void update(final GL gl) {

    // update each of the children of this component
    for (final Component child : this.children) {
      child.update(gl);
    }

    gl.glNewList(this.callListHandle, GL.GL_COMPILE);

    // translate this component to where it will be located in the scene last
    gl.glTranslated(this.position.x(), this.position.y(), this.position.z());

    // first, rotate this component around each of the three axes
    gl.glRotated(this.xAngle, 1, 0, 0);
    gl.glRotated(this.yAngle, 0, 1, 0);
    gl.glRotated(this.zAngle, 0, 0, 1);

    // draw the displayable which this component represents in its color
    gl.glPushAttrib(GL.GL_CURRENT_BIT);
    gl.glColor3f(this.color.red(), this.color.green(), this.color.blue());
    this.displayable.draw(gl);
    gl.glPopAttrib();

    // draw all the children of this component
    for (final Component child : this.children) {
      child.draw(gl);
    }

    gl.glEndList();
  }

  /**
   * Gets the current angle at which this joint is rotated around the x axis.
   * 
   * @return The current angle at which this joint is rotated around the x axis.
   */
  public double xAngle() {
    return this.xAngle;
  }

  /**
   * Gets the current angle at which this joint is rotated around the y axis.
   * 
   * @return The current angle at which this joint is rotated around the y axis.
   */
  public double yAngle() {
    return this.yAngle;
  }

  /**
   * Gets the current angle at which this joint is rotated around the z axis.
   * 
   * @return The current angle at which this joint is rotated around the z axis.
   */
  public double zAngle() {
    return this.zAngle;
  }
}
