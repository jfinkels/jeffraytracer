/**
 * LinearTracer.java - a tracer which just traces each ray in linear order
 */
package edu.bu.cs.cs480.rendering;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * Traces each ray in a given array from start to finish, one at a time.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class LinearTracer extends DefaultTracer {

  /**
   * Instantiates this object by calling the corresponding constructor of the
   * superclass.
   * 
   * @param environment
   *          The scene to trace.
   */
  public LinearTracer(final RenderingEnvironment environment) {
    super(environment);
  }

  /**
   * Traces each of the specified rays one at a time.
   * 
   * @param rays
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.rendering.Tracer#traceAll(edu.bu.cs.cs480.Ray[])
   */
  @Override
  public Vector3D[] traceAll(final Ray[] rays) {
    final Vector3D[] result = new Vector3D[rays.length];
    for (int i = 0; i < rays.length; ++i) {
      result[i] = this.trace(rays[i]);
    }
    return result;
  }

}
