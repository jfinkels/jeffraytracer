/**
 * SupersamplingTracerEnvironment.java -
 */
package edu.bu.cs.cs480.rendering;

import edu.bu.cs.cs480.DoubleColor;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SupersamplingTracerEnvironment extends BaseTracerEnvironment {
  /**
   * The default size of the grid used in supersampling of rays for
   * antialiasing.
   */
  // private static final int DEFAULT_SUPERSAMPLING_GRID_SIZE = 3;
  // /** The size of the grid used in supersampling of rays for antialiasing.
  // */
  // private int supersamplingGridSize = DEFAULT_SUPERSAMPLING_GRID_SIZE;
  /**
   * Sets the size of the grid (in number of pixels) of virtual subpixels to
   * use in place of each pixel for supersampling in order to antialias the
   * rendered image.
   * 
   * @param supersamplingGridSize
   *          The size of the grid of subpixels (that is, the number of pixels
   *          on one side of the square grid).
   */
  // public void setSupersamplingGridSize(final int supersamplingGridSize) {
  // this.supersamplingGridSize = supersamplingGridSize;
  // }
  @Override
  protected Ray[][] generatePrimaryRays() {
    // final int width = this.viewport().width();
    // final int height = this.viewport().height();
    //
    // // create the resolution for the virtual superpixel camera
    // final Resolution superpixelResolution = new Resolution();
    // superpixelResolution.setxResolution(this.resolution().xResolution()
    // / this.supersamplingGridSize);
    // superpixelResolution.setyResolution(this.resolution().yResolution()
    // / this.supersamplingGridSize);
    //
    // // create the viewport for the virtual superpixel camera
    // final int superWidth = width * this.supersamplingGridSize;
    // final int superHeight = height * this.supersamplingGridSize;
    // final Viewport superpixelViewport = new Viewport();
    // superpixelViewport.setWidth(superWidth);
    // superpixelViewport.setHeight(superHeight);
    //
    // // create the object which generates primary rays extending from the
    // camera
    // // through the superpixel viewport
    // final RayGenerator rayGenerator = new RayGenerator();
    // rayGenerator.setCamera(this.camera());
    // rayGenerator.setResolution(superpixelResolution);
    // rayGenerator.setViewport(superpixelViewport);
    //
    // // generate the virtual primary rays for each superpixel
    // this.supersampler.setRayGenerator(rayGenerator);
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
