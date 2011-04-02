/**
 * Component.java - an object with a position, rotation, and children
 */
package edu.bu.cs.cs480.model;

import java.util.HashSet;
import java.util.Set;

import javax.media.opengl.GL;

import edu.bu.cs.cs480.drawing.Displayable;
import edu.bu.cs.cs480.drawing.UpdatingDisplayable;

/**
 * An object which can draw itself.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Component extends BaseRotatable implements Colorable, Nameable,
    UpdatingDisplayable {
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
  /** The human-readable name of this component. */
  private final String name;
  /** The position of this component. */
  private Point3D position;

  /**
   * Instantiates this component with the specified position and the
   * displayable which this component represents.
   * 
   * If the specified displayable object is {@code null}, this component will
   * only provide a positioning and rotation.
   * 
   * @param position
   *          The position of this component.
   * @param displayable
   *          The object which this component represents.
   * @param name
   *          The human-readable name of this component.
   */
  public Component(final Point3D position, final Displayable displayable,
      final String name) {
    this.position = position;
    this.displayable = displayable;
    this.name = name;
  }

  /**
   * Instantiates this component with the specified position, but with nothing
   * to display.
   * 
   * @param position
   *          The position of this component.
   */
  public Component(final Point3D position, final String name) {
    this(position, null, name);
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
   * Convenience method which simply calls the {@link #addChild(Component)}
   * method for each of the components specified in the parameter list of this
   * method.
   * 
   * @param components
   *          The components to add as children of this component.
   */
  public void addChildren(final Component... components) {
    for (final Component component : components) {
      this.addChild(component);
    }
  }

  /**
   * Calls the OpenGL call list which contains the commands which draw this
   * component.
   * 
   * @param gl
   *          The OpenGL object with which to perform the drawing.
   * 
   * @see edu.bu.cs.cs480.drawing.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * Initializes the call list which this component uses for drawing, then
   * calls the corresponding method on the children of this component.
   * 
   * @param gl
   *          The OpenGL object with which to perform the drawing.
   * 
   * @see edu.bu.cs.cs480.drawing.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL gl) {
    // create a new OpenGL call list handle
    this.callListHandle = gl.glGenLists(1);

    // initialize the displayable object which this component represents
    if (this.displayable != null) {
      this.displayable.initialize(gl);
    }

    // initialize each of the children of this component
    for (final Component child : this.children) {
      child.initialize(gl);
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.model.Nameable#name()
   */
  @Override
  public String name() {
    return this.name;
  }

  /**
   * Gets the position of this component.
   * 
   * @return The position of this component.
   */
  public Point3D position() {
    return this.position;
  }

  /**
   * Removes the specified child component from the set of children of this
   * component (if it exists).
   * 
   * @param child
   *          The component to remove.
   * @return {@code true} if and only if the set of children contained the
   *         specified component.
   */
  public boolean removeChild(final Component child) {
    return this.children.remove(child);
  }

  /**
   * {@inheritDoc}
   * 
   * @param color
   *          {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.model.Colorable#setColor(edu.bu.cs.cs480.model.FloatColor)
   */
  @Override
  public void setColor(final FloatColor color) {
    this.color = color;
  }

  /**
   * Sets the position of this component.
   * 
   * @param position
   *          The position of this component.
   */
  protected void setPosition(final Point3D position) {
    this.position = position;
  }

  /**
   * Returns the String representation of this component.
   * 
   * @return The String representation of this component.
   */
  @Override
  public String toString() {
    return this.name;
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
   * @see edu.bu.cs.cs480.drawing.UpdatingDisplayable#update(javax.media.opengl.GL)
   */
  @Override
  public void update(final GL gl) {
    // update each of the children of this component
    for (final Component child : this.children) {
      child.update(gl);
    }

    gl.glNewList(this.callListHandle, GL.GL_COMPILE);
    gl.glPushMatrix();

    // translate this component to where it will be located in the scene
    gl.glTranslated(this.position.x(), this.position.y(), this.position.z());

    // first, rotate this component around each of the three axes
    // gl.glRotated(this.xAngle(), 1, 0, 0);
    // gl.glRotated(this.yAngle(), 0, 1, 0);
    // gl.glRotated(this.zAngle(), 0, 0, 1);
    gl.glMultMatrixf(this.rotation().toMatrix(), 0);

    // draw the displayable which this component represents in its color
    if (this.displayable != null) {
      gl.glPushAttrib(GL.GL_CURRENT_BIT);
      gl.glColor3f(this.color.red(), this.color.green(), this.color.blue());
      this.displayable.draw(gl);
      gl.glPopAttrib();
    }

    // draw all the children of this component
    for (final Component child : this.children) {
      child.draw(gl);
    }

    gl.glPopMatrix();
    gl.glEndList();
  }
}
