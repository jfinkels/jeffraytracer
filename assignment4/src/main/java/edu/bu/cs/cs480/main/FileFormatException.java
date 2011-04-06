/**
 * FileFormatException.java - 
 */
package edu.bu.cs.cs480.main;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class FileFormatException extends Exception {

  /** Generated serial version UID. */
  private static final long serialVersionUID = 4964321011106855251L;

  /**
   * 
   */
  public FileFormatException() {
    super();
  }

  /**
   * @param message
   */
  public FileFormatException(final String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public FileFormatException(final Throwable cause) {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public FileFormatException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
