/**
 * SupersamplingRenderer.java - renders a subpixel grid for antialiasing
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer.rendering.renderers;

import jeffraytracer.DoubleColor;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.rendering.RenderingEnvironment;
import jeffraytracer.rendering.supersampling.Averager;
import jeffraytracer.rendering.supersampling.Supersampler;

/**
 * A renderer which supersamples the scene, then averages the traced color
 * values in order to antialias the rendered image.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SupersamplingRenderer extends DefaultRenderer {
  /**
   * Instantiates this class by calling the corresponding constructor of the
   * superclass.
   * 
   * @param environment
   *          The scene to render.
   */
  public SupersamplingRenderer(final RenderingEnvironment environment) {
    super(environment);
  }

  /**
   * Generates primary rays for a subpixel grid on a virtual viewport with
   * higher resolution than the original.
   * 
   * @return A two-dimensional array of primary rays extending through the
   *         virtual subpixel viewport.
   */
  @Override
  protected Ray[][][] generatePrimaryRays() {
    return this.supersampler.generateRays();
  }

  /** The object which provides the supersampling ray generation. */
  private Supersampler supersampler = null;

  /**
   * Sets the object which provides the supersampling ray generation.
   * 
   * @param supersampler
   *          The object which provides the supersampling ray generation.
   */
  public void setSupersampler(final Supersampler supersampler) {
    this.supersampler = supersampler;
  }

  /** The object which averages the traced subpixel color values. */
  private Averager averager = null;

  /**
   * Sets the object which averages the traced subpixel color values.
   * 
   * @param averager
   *          The object which averages the traced subpixel color values.
   */
  public void setAverager(final Averager averager) {
    this.averager = averager;
  }

  /**
   * Averages the colors traced through the virtual subpixel viewport and
   * converts each color to its corresponding integer representation.
   * 
   * @param colors
   *          The color values of each traced and shaded subpixel, according to
   *          the scene.
   * @return A new array containing the corresponding integer representations
   *         of the averages of the colors specified by the input array.
   */
  @Override
  protected int[] postProcessing(final Vector3D[] colors) {
    final Vector3D[] averagedColors = this.averager.average(colors);
    final int[] result = new int[averagedColors.length];
    for (int i = 0; i < averagedColors.length; ++i) {
      result[i] = DoubleColor.toRGB(averagedColors[i]);
    }
    return result;
  }
}
