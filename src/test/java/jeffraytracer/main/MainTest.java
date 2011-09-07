/**
 * MainTest.java - test for the Main class
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
package jeffraytracer.main;

import org.junit.Test;

/**
 * Test for the Main class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class MainTest {

  /** The directory containing the model files for testing. */
  public static final String TESTDIR = "src/test/resources/jeffraytracer/";
  /** The file containing a test model. */
  public static final String TESTFILE = TESTDIR + "model1.dat";

  /**
   * Test method for {@link jeffraytracer.main.Main#main(java.lang.String[])} .
   */
  //@Test
  public void testMain() {
    Main.main(new String[] { TESTFILE });
  }

}
