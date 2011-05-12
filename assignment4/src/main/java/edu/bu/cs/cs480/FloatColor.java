/**
 * FloatColor.java - a Color which easily allows getting components as floats
 */
package edu.bu.cs.cs480;

import java.awt.Color;


/**
 * A Color object which allows getting the component values as floats.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class FloatColor extends Color {

  /** The color orange. */
  public static final FloatColor ORANGE = new FloatColor(0.8f, 0.5f, 0.2f);
  /** The color red. */
  public static final FloatColor RED = new FloatColor(1, 0, 0);
  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = 6281360072954213961L;

  /**
   * Returns the integer representation of the specified color.
   * 
   * @param color
   *          The color to convert to integer form.
   * @return The integer representation of the specified color.
   */
  public static int toRGB(final Vector3D color) {
    return new FloatColor(color).getRGB();
  }

  /** The value of the blue component of this color as a float between 0 and 1. */
  private final float blue;
  /**
   * The value of the green component of this color as a float between 0 and 1.
   */
  private final float green;
  /** The value of the red component of this color as a float between 0 and 1. */
  private final float red;

  /**
   * Creates a FloatColor object with the same component values of the
   * specified Color object.
   * 
   * @param color
   *          The color from which to get component values.
   */
  private FloatColor(final Color color) {
    super(color.getRGB());
    final float[] components = new float[3];
    super.getColorComponents(components);
    this.red = components[0];
    this.green = components[1];
    this.blue = components[2];
  }

  /**
   * Instantiates this color with the specified red, green, and blue values as
   * {@code float}s between 0 and 1.
   * 
   * Warning: double values will be cast to floats, so loss of precision is
   * probable.
   * 
   * @param red
   *          The value of the red component of this color as a double between
   *          0 and 1.
   * @param green
   *          The value of the green component of this color as a double
   *          between 0 and 1.
   * @param blue
   *          The value of the blue component of this color as a double between
   *          0 and 1.
   */
  public FloatColor(final double red, final double green, final double blue) {
    this((float) red, (float) green, (float) blue);
  }

  /**
   * Instantiates this color with the specified red, green, and blue values as
   * {@code float}s between 0 and 1.
   * 
   * @param red
   *          The value of the red component of this color as a float between 0
   *          and 1.
   * @param green
   *          The value of the green component of this color as a float between
   *          0 and 1.
   * @param blue
   *          The value of the blue component of this color as a float between
   *          0 and 1.
   */
  public FloatColor(final float red, final float green, final float blue) {
    this(new Color(red, green, blue, 1.0f));
  }

  /**
   * Instantiates this color with red, green, and blue components corresponding
   * to the x, y, and z components of the specified vector, respectively.
   * 
   * @param components
   *          The red, green, and blue components of the color.
   */
  public FloatColor(final Vector3D components) {
    this(components.x(), components.y(), components.z());
  }

  /**
   * Gets the value of the blue component of this color as a float between 0
   * and 1.
   * 
   * @return The value of the blue component of this color as a float between 0
   *         and 1.
   */
  public float blue() {
    return this.blue;
  }

  /**
   * Returns {@code true} if and only if the specified other color is an
   * instance of FloatColor and its components match the components of this
   * color.
   * 
   * Pre-condition: the specified other object is not {@code null}.
   * 
   * @param that
   *          The other color with which to compare.
   * @return {@code true} if and only if the colors match.
   */
  @Override
  public boolean equals(final Object that) {
    if (!(that instanceof FloatColor)) {
      return false;
    }
    final FloatColor other = (FloatColor) that;
    return this.red == other.red && this.green == other.green
        && this.blue == other.blue;
  }

  /**
   * Gets the value of the green component of this color as a float between 0
   * and 1.
   * 
   * @return The value of the green component of this color as a float between
   *         0 and 1.
   */
  public float green() {
    return this.green;
  }

  /**
   * Not implemented: always returns -1.
   * 
   * @return -1.
   */
  @Override
  public int hashCode() {
    assert false : "hashCode() not implemented";
    return -1;
  }

  /**
   * Gets the value of the red component of this color as a float between 0 and
   * 1.
   * 
   * @return The value of the red component of this color as a float between 0
   *         and 1.
   */
  public float red() {
    return this.red;
  }

  /**
   * Returns a new vector whose components are the component values of this
   * color each scaled by the specified value.
   * 
   * @param scale
   *          The amount by which to scale each component.
   * @return A new vector whose components are the component values of this
   *         color each scaled by the specified value.
   */
  public Vector3D scaledBy(final double scale) {
    return this.scaledByComponentwise(scale, scale, scale);
  }

  /**
   * Returns a new vector whose components are the component values of this
   * color scaled by the specified values.
   * 
   * For example, if this color is [10, 20, 30] and the specified scale values
   * are [2, 1, 0.5], then this method would return [20, 20, 15].
   * 
   * @param redScale
   *          The factor by which to scale the red component of this color.
   * @param greenScale
   *          The factor by which to scale the green component of this color.
   * @param blueScale
   *          The factor by which to scale the blue component of this color.
   * @return A new vector whose components are the component values of this
   *         color scaled by the corresponding components of the specified
   *         vector.
   */
  public Vector3D scaledByComponentwise(final double redScale,
      final double greenScale, final double blueScale) {
    return new Vector3D(this.red * redScale, this.green * greenScale,
        this.blue * blueScale);
  }

  /**
   * Returns a new vector whose components are the component values of this
   * color scaled by the corresponding components of the specified vector.
   * 
   * For example, if this color is [10, 20, 30] and the specified scale vector
   * is [2, 1, 0.5], then this method would return [20, 20, 15].
   * 
   * @param scale
   *          The factors by which to scale the components of this color.
   * @return A new vector whose components are the component values of this
   *         color scaled by the corresponding components of the specified
   *         vector.
   */
  public Vector3D scaledByComponentwise(final Vector3D scale) {
    return this.scaledByComponentwise(scale.x(), scale.y(), scale.z());
  }

  /**
   * Returns a new color which is the componentwise sum of this and the
   * specified other color.
   * 
   * @param that
   *          The other color with which to sum.
   * @return A new color which is the componentwise sum of this and the
   *         specified other color.
   */
  public FloatColor sumWith(final FloatColor that) {
    return new FloatColor(this.red + that.red, this.green + that.green,
        this.blue + that.blue);
  }

  /**
   * Returns a vector whose components are the red, green, and blue values of
   * this color, in that order.
   * 
   * @return A vector whose components are the red, green, and blue values of
   *         this color, in that order.
   */
  public Vector3D toVector() {
    return new Vector3D(this.red, this.green, this.blue);
  }
}
