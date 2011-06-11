/**
 * FileFormatException.java - thrown when a data file has an incorrect format
 */
package edu.bu.cs.cs480.io;

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
