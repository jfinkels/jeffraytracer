/**
 * LinearTracer.java - a tracer which just traces each ray in linear order
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
