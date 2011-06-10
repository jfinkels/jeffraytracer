/**
 * TracerEnvironment.java - 
 */
package edu.bu.cs.cs480.rendering;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface TracerEnvironment {
  Vector3D trace(Ray primaryRay, int level);
}
