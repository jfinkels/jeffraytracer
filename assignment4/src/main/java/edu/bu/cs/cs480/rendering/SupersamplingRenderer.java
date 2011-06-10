/**
 * SupersamplingRenderer.java -
 */
package edu.bu.cs.cs480.rendering;

import edu.bu.cs.cs480.DoubleColor;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SupersamplingRenderer extends BaseRenderer {
  /**
   * @param environment
   */
  public SupersamplingRenderer(final RenderingEnvironment environment) {
    super(environment);
  }

  @Override
  protected Ray[][] generatePrimaryRays() {
    return this.supersampler.generateRays();
  }

  private Supersampler supersampler = null;

  public void setSupersampler(final Supersampler supersampler) {
    this.supersampler = supersampler;
  }

  private Averager averager = null;

  public void setAverager(final Averager averager) {
    this.averager = averager;
  }

  @Override
  protected int[] postProcessing(final Vector3D[] pixels) {
    // average the pixels
    final Vector3D[] colors = this.averager.average(pixels);
    final int[] result = new int[colors.length];
    for (int i = 0; i < pixels.length; ++i) {
      result[i] = DoubleColor.toRGB(colors[i]);
    }
    return result;
  }
}
