package commands;

import java.util.Stack;

import helpers.Directory;
import helpers.PathWalker;

/**
 * Class Name: Cd This class navigates through the tree with help of other
 * classes.
 */
public class Cd implements CommandInterface {
  /**
   * Changes the current working directory to the specified. Returns an error if
   * directory does not exist.
   * 
   * @param explorer, holds the series of directories that switchDir should
   *        navigate.
   * @param input, the directory to be switched to, also can be a path of
   *        folders.
   * 
   * @return boolean
   */
  public static boolean switchDir(Directory explorer, String input) {
    String initialDir = Pwd.printDir(explorer);
    if (input.contentEquals("/")) {// switch to root
      explorer.changeCurrentDir("/");
      return true;// switch was made
    } else if (input.indexOf("/") == 0) {// relative path
      explorer.changeCurrentDir("/");
    }
    String path[] = input.trim().split("/");// populate array with paths
    for (int i = 0; i < path.length; i++) {// go through all the paths
      if (path[i].contentEquals("..")// go to parent folder
          && !explorer.workingDir().contentEquals("/")) {
        explorer.changeCurrentDir(explorer.getParentDir());
      } else if (path[i].contentEquals(".")// go to parent folder
          && !explorer.workingDir().contentEquals("/")) {
        return true;
      } else if (!explorer.cdOneDown(path[i]) && !path[i].contentEquals("")) {
        System.out.println("cd: " + path[i] + ": No such directory");
        PathWalker.goToPath(explorer, initialDir);
        return false;// indicate the switch was not made to other methods
      }
    }
    return true;// switch was made if this code is reached
  }

  /**
   * Used by Jshell interface to execute the above methods
   * 
   */
  public void execute(Directory dir, String input, Stack Holder) {
    // cut cd and space out of input string
    if (input.split("\\s+").length == 2) {
      input = input.substring(3).trim();
      Cd.switchDir(dir, input);
    } else {
      System.out.println("cd: Incorrect input try again.");
    }
  }
}
