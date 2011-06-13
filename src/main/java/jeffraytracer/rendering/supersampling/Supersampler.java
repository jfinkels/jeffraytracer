/**
 * Supersampler.java - a supersampler of rays for antialiasing
 */
package jeffraytracer.rendering.supersampling;

import jeffraytracer.Ray;

/**
 * Provides a supersampling of virtual rays for use in antialiasing.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Supersampler {
  /**
   * Generates an array of blocks of rays.
   * 
   * @return The array of blocks of rays.
   */
  Ray[][] generateRays();
}