package test;

import static org.junit.Assert.*;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import commands.*;
import helpers.*;

public class LsTest {

  Stack a = new Stack();
  Mkdir mkDir;
  Cd cd;
  Directory dir;


  @Before
  public void setUp(){
    dir = new Directory();
    a = new Stack();
    cd = new Cd();
  }
  @Test
  public void testOnlyRootInTree() {
    // Tests ls when the tree only has a root
    // try using the getChildren function via Directory
    assertEquals("/:\n", Ls.listDir(dir, ""));

  }

  @Test // First cd into a folder then ls it
  public void testCdThenLs() {
    mkDir.execute(dir, "mkdir /File1 /File1/File2 File3 File4", null);
    cd.execute(dir, "cd /File1", null);
    assertEquals("/File1: File2\n", Ls.listDir(dir, ""));
  }

  @Test // Ls multiple relative paths
  public void testMultiplePaths() {
    mkDir.execute(dir, "mkdir /File1 /File1/File2 File3 File4", null);
    assertEquals("/File1/File2:\n", Ls.listDir(dir, "File1/File2"));
  }

  @Test // Ls a file path
  public void testFilePath() {
    dir = new Directory();
    mkDir = new Mkdir();
    cd = new Cd();
    Echo echo = new Echo();
    echo.execute(dir, "echo \"file\" > fileName.txt", null);
    assertEquals("fileName.txt", Ls.listDir(dir, "fileName.txt"));
  }

  @Test // Ls -r without any other arguments
  public void testRecursiveLs() {
    dir = new Directory();
    mkDir = new Mkdir();
    cd = new Cd();
    mkDir.execute(dir, "mkdir /File1 /File1/File2 File3 File4", null);
    String expected =
 "/: File1 File3 File4\n\n/File1: File2\n\n/File1/File2:\n\n/File3:\n\n/File4:"
 + "\n";
    assertEquals(expected, Ls.recursivelyListAllDir(dir));
  }

  @Test // Ls multiple recursive paths
  public void testMultipleRecursivePaths() {
    dir = new Directory();
    mkDir = new Mkdir();
    cd = new Cd();
    mkDir.execute(dir, "mkdir /File1 /File1/File2 File3 File4", null);
    String expected =
 "/: File1 File3 File4\n\n/File1: File2\n\n/File1/File2:\n\n/File3:\n\n/File4:"
 + "\n";
    assertEquals(expected, Ls.recursivelyListAllDir(dir));
    cd.execute(dir, "cd /File1/File2", null);
    String expected2 = "/File1/File2:\n";
    assertEquals(expected2, Ls.recursivelyListAllDir(dir));
    
  }


}