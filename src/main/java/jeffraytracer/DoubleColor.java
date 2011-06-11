/**
 * DoubleColor.java - a color with double precision component values
 */
package jeffraytracer;

/**
 * A color with double precision red, green, and blue component values between
 * 0 and 1.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
@Immutable
public class DoubleColor {
  /**
   * Returns the ARGB integer representation of the specified color.
   * 
   * The alpha value is always 0xFF (that is, always completely opaque).
   * 
   * @param color
   *          The color to convert to integer representation.
   * @return The ARGB integer representation of the specified color.
   */
  public static int toRGB(final Vector3D color) {
    final int red = (int) (color.x() * 0xFF);
    final int green = (int) (color.y() * 0xFF);
    final int blue = (int) (color.z() * 0xFF);
    final int alpha = 0xFF;
    return alpha << 24 | red << 16 | green << 8 | blue;
  }

  /** The blue component value, between 0 and 1. */
  private final double blue;
  /** The green component value, between 0 and 1. */
  private final double green;

  /** The red component value, between 0 and 1. */
  private final double red;

  /**
   * Instantiates this color with the specified red, green, and blue component
   * values between 0 and 1.
   * 
   * @param red
   *          The red component value.
   * @param green
   *          The green component value.
   * @param blue
   *          The blue component value.
   */
  public DoubleColor(final double red, final double green, final double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Returns a new vector representing the components of this color scaled by
   * the specified amount.
   * 
   * This method does not check if the scaling brings the value of a component
   * outside of the interval [0, 1].
   * 
   * @param scale
   *          The amount by which to scale the components of this color.
   * @return A new vector representing the components of this color scaled by
   *         the specified amount.
   */
  public Vector3D scaledBy(final double scale) {
    return new Vector3D(this.red * scale, this.green * scale, this.blue
        * scale);
  }

  /**
   * Returns a new vector representing the components of this color scaled by
   * the corresponding components of the specified vector.
   * 
   * This method does not check if the scaling brings the value of a component
   * outside of the interval [0, 1].
   * 
   * @param scale
   *          The amounts by which to scale each of the components of this
   *          color.
   * @return A new vector representing the components of this color scaled by
   *         the corresponding components of the specified vector.
   */
  public Vector3D scaledByComponentwise(final Vector3D scale) {
    return new Vector3D(this.red * scale.x(), this.green * scale.y(),
        this.blue * scale.z());
  }

  /**
   * Returns a new vector with the same components as this color.
   * 
   * @return A new vector with the same components as this color.
   */
  public Vector3D toVector() {
    return new Vector3D(this.red, this.green, this.blue);
  }
}
