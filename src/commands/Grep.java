package commands;

import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import helpers.Directory;
import helpers.File;
import helpers.Pathchecker;
import helpers.SplitPaths;
import helpers.Tree;

// TODO: Auto-generated Javadoc
/**
 * The Class Grep.
 */
public class Grep implements CommandInterface {

  /** The counter. */
  private static int counter = 0;

  /**
   * Find regex.Finds the strings that contain the given regular expression
   *
   * @param directory the directory
   * @param input the input
   * @return the string
   */
  public static String findRegex(Directory directory, String input) {
    String finalLines = "";
    String[] arguments = input.trim().split("\\s+");
    String regex = arguments[0];
    // String path = arguments[1];
    if (!Pathchecker.pathCheck(directory, arguments[1]))
      return "Invalid path, please try again"; // If file path is incorrect
    String[] output = SplitPaths.pathSplit(arguments[1]);
    if (directory.peekType(output[1]).equals("File")) {
      if (!Pwd.printDir(directory).endsWith("/")) {
        finalLines = Pwd.printDir(directory) + "/" + output[1].trim() + ":";
      } else {
        finalLines = Pwd.printDir(directory) + output[1].trim() + ":";
      }
      File file = (File) directory.getChild(output[1]);
      String[] array = file.getContent().trim().split("\n");
      for (String currentLine : array) {
        if (Grep.isRegex(currentLine, regex)) {
          while (counter > 0) {
            finalLines += "\n" + currentLine;
            counter = counter - 1;
          }
        }
      }
    }
    return finalLines;
  }


  /**
   * Recursive regex.
   *
   * @param dir the dir
   * @param input the input
   */
  private static void recursiveRegex(Directory dir, String input) {
    String[] arguments = input.trim().split("\\s+");
    String regex = arguments[0];
    String[] children = dir.getContents().trim().split("\\s+");
    if (dir.getContents().trim() == "") {// Base Case(directory has no children)
      // Stop recursion
    } else {
      for (String child : children) {
        if (dir.peekType(child).equals("Folder")) {// recursive only if folder**
          String dirHolder = dir.workingDir();
          Cd.switchDir(dir, child);
          recursiveRegex(dir, input);
          dir.changeCurrentDir(dirHolder);
        } else {
          if (Pwd.printDir(dir).equals("/")) {
            System.out.println(
                findRegex(dir, regex + " " + Pwd.printDir(dir) + child));
          } else {
            System.out.println(
                findRegex(dir, regex + " " + Pwd.printDir(dir) + "/" + child));
          }
        }
      }
    }
  }

  /**
   * Checks if is regex.
   *
   * @param input the input
   * @param regex the regex
   * @return true, if is regex
   */
  private static boolean isRegex(String input, String regex) {
    Pattern pattern = Pattern.compile(regex);
    java.util.regex.Matcher matcher = pattern.matcher(input);
    while (matcher.find()) {
      counter += 1;
    }
    if (counter >= 1) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Used by Jshell interface to execute the above methods
   * 
   */
  @Override
  public void execute(Directory dir, String input, Stack holder) {
    input = input.substring(4);
    String path[] = input.trim().split("\\s+");
    for (int currentPath = 1; currentPath < path.length; currentPath++) {
      if (path.length < 2) {
        System.out.println("Incorrect input, Missing path or regex");
      } else if (!path[0].contains("-R") && !path[0].contains("-r")) {
        input = path[0] + " " + path[currentPath];
        System.out.println(findRegex(dir, input));
      } else {
        if (path[currentPath].equals("-R") || path[currentPath].equals("-r")) {
        } else if (path[currentPath].endsWith(".txt")
            || path[currentPath].endsWith(".html")) {
          System.out.println("Please specify directory, not file path");
        } else if (currentPath == 1) {
        } else {
          input = input.trim().substring(2) + path[currentPath];
          String dirHolder = dir.workingDir();
          Cd.switchDir(dir, path[currentPath]);
          recursiveRegex(dir, input);
          dir.changeCurrentDir(dirHolder);
        }
      }
    }
  }
}