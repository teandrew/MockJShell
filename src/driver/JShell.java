// **********************************************************
// Assignment2:
// Student1: Filip Fabiszak
// UTOR user_name: fabiszak
// UT Student #: 1001641137
// Author: Filip Fabiszak
//
// Student2: Crendall Castro
// UTOR user_name: castroc5
// UT Student #: 1001585670
// Author: Crendall Castro
//
// Student3:Asmir Islamovic
// UTOR user_name:islamovi
// UT Student #:1001564853
// Author:Asmir Islamovic
//
// Student4: Andrew Te
// UTOR user_name: teandrew
// UT Student #: 1001617823
// Author: Andrew Te
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package driver;

import java.util.Scanner;

public class JShell {

  public static void main(String[] args) {
    String input = "start";
    Scanner in = new Scanner(System.in);
    while (!input.replaceAll("\\s", "").equals("exit")) {
      System.out.print("/# ");
      input = in.nextLine();
    }
  }
}
