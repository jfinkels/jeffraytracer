/**
 * FileFormatException.java - thrown when a data file has an incorrect format
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
package jeffraytracer.io;

/**
 * Thrown when a data file has an incorrect format.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class FileFormatException extends Exception {

  /** Generated serial version UID. */
  private static final long serialVersionUID = 4964321011106855251L;

  /**
   * Instantiates this exception by calling the corresponding constructor of
   * the superclass.
   */
  public FileFormatException() {
    super();
  }

  /**
   * Instantiates this exception by calling the corresponding constructor of
   * the superclass.
   * 
   * @param message
   *          A human-readable message which describes this exception.
   */
  public FileFormatException(final String message) {
    super(message);
  }

  /**
   * Instantiates this exception by calling the corresponding constructor of
   * the superclass.
   * 
   * @param message
   *          A human-readable message which describes this exception.
   * @param cause
   *          The throwable which caused this exception to be thrown.
   */
  public FileFormatException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates this exception by calling the corresponding constructor of
   * the superclass.
   * 
   * @param cause
   *          The throwable which caused this exception to be thrown.
   */
  public FileFormatException(final Throwable cause) {
    super(cause);
  }

}
