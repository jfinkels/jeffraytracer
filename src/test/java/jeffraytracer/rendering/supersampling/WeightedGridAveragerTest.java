/**
 * WeightedGridAverager.java - test for the WeightedGridAverager class
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
package jeffraytracer.rendering.supersampling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jeffraytracer.Vector3D;
import jeffraytracer.rendering.averagers.WeightedGridAverager;

import org.junit.Test;

/**
 * Test for the WeightedGridAverager class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class WeightedGridAveragerTest {

  /**
   * Test method for
   * {@link jeffraytracer.rendering.averagers.WeightedGridAverager#average(jeffraytracer.Vector3D[])}
   * .
   */
  @Test
  public void testAverage() {

    Vector3D[] colors = new Vector3D[4];
    colors[0] = new Vector3D(0.2, 0.2, 0.2);
    colors[1] = new Vector3D(0.3, 0.3, 0.3);
    colors[2] = new Vector3D(0.4, 0.4, 0.4);
    colors[3] = new Vector3D(0.5, 0.5, 0.5);

    double[] weights = { 1, 2, 2, 3 };

    final WeightedGridAverager a = new WeightedGridAverager();
    a.setGridSize(2);
    a.setWeights(weights);
    final Vector3D[] averages = a.average(colors);
    assertEquals(1, averages.length);
    assertTrue(averages[0].equalTo(new Vector3D(.3875, .3875, .3875)));

    Vector3D[] colors2 = new Vector3D[8];
    System.arraycopy(colors, 0, colors2, 0, 4);
    System.arraycopy(colors, 0, colors2, 4, 4);
    final Vector3D[] averages2 = a.average(colors2);
    assertEquals(2, averages2.length);
    assertTrue(averages2[0].equalTo(new Vector3D(.3875, .3875, .3875)));
    assertTrue(averages2[1].equalTo(new Vector3D(.3875, .3875, .3875)));

  }

}
