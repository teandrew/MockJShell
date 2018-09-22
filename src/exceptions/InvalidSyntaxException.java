package exceptions;


/**
 * Class Name: InvalidSyntaxException is an exception class when invalid syntax
 * is used as input.
 */
public class InvalidSyntaxException extends Exception {
  
  /** The error. */
  private String error = "";

  /**
   * Instantiates a new invalid syntax exception.
   *
   * @param input the input
   */
  public InvalidSyntaxException(String input) {
    this.error = input;
  }

  /**
   * Gets the error.
   *
   * @return the error
   */
  public String getError() {
    return this.error;
  }
}