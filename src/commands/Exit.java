package commands;

import java.util.Stack;

import helpers.Directory;

/**
 * Class Name: Exit, Exits out of the Jshell
 */
public class Exit implements CommandInterface {

  /**
   * Checks if the current input is equal to exit
   * 
   * @param input
   * @return boolean
   */
  public static boolean checkString(String input) {
    return input.trim().equals("exit");
  }

  /**
   * Used by Jshell interface to execute the above methods
   * 
   */
  public void execute(Directory dir, String input, Stack Holder) {
    Exit.checkString(input);
  }
}