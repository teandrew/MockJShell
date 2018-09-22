package exceptions;


/**
 * Class Name: FileDirectoryExistsException is an exception class when a file or
 * directory is trying to be created when the same directory or file with the
 * same name exists.
 */
public class FileDirectoryExistsException extends Exception {
  
  /** The error. */
  private String error = "";

  /**
   * Instantiates a new file directory exists exception.
   *
   * @param input the input
   */
  public FileDirectoryExistsException(String input) {
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