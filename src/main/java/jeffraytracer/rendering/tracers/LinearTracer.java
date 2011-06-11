/**
 * LinearTracer.java - a tracer which just traces each ray in linear order
 */
package jeffraytracer.rendering.tracers;

import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.rendering.RenderingEnvironment;

/**
 * Traces each ray in a given array from start to finish, one at a time.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class LinearTracer extends BaseTracer {

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
   * @see jeffraytracer.rendering.tracers.Tracer#traceAll(jeffraytracer.Ray[])
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
