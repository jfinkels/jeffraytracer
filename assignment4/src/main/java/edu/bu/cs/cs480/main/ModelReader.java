/**
 * ModelReader.java -
 */
package edu.bu.cs.cs480.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ModelReader {
  public static TracerEnvironment fromFile(final String filename)
      throws FileNotFoundException {
    final TracerEnvironment result = new TracerEnvironment();
    final Scanner input = new Scanner(new File(filename));

    while (input.hasNextLine()) {
      final String token = input.next();
      if (token.equals("camera")) {

      } else if (token.equals("resolution")) {

      } else if (token.equals("viewport")) {

      } else if (token.equals("light")) {
        
      } else if (token.equals("mat")) {
      
      } else if (token.equals("obj")) {

      }
    }
    return result;
  }
}
