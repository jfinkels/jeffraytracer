/**
 * Vector4DTest.java - test for the Vector4D class
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
package jeffraytracer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for the Vector4D class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Vector4DTest {

  /**
   * Test method for
   * {@link jeffraytracer.Vector4D#dotProduct(jeffraytracer.Vector4D)}.
   */
  @Test
  public void testDotProduct() {
    final Vector4D v1 = new Vector4D(0, 0, 1, 0);
    final Vector4D v2 = new Vector4D(0, 0, -10, -25);
    assertEquals(-10, v1.dotProduct(v2), 0);
  }

}
