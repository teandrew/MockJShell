package test;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import commands.*;
import helpers.*;
import static org.junit.Assert.*;

public class CdTest {
  Directory dir;
  Mkdir mkDir;
  Cd cd;

  @Before
  public void setUp() {
    dir = new Directory();
    mkDir = new Mkdir();
    cd = new Cd();
  }

  @Test
  public void testChanginDirectoryFromRoot() {
    // Test changing the directory from the root directory
    mkDir.execute(dir, "mkdir /Folder1", null);
    cd.execute(dir, "cd /Folder1", null);
    assertEquals("Folder1", dir.workingDir());
  }

  @Test
  public void testInputWithoutSlash() {
    // Test if the method accepts with the "/"
    mkDir.execute(dir, "mkdir /Folder1", null);
    cd.execute(dir, "cd Folder1", null);
    assertEquals("Folder1", dir.workingDir());
  }

  @Test
  public void testInputWithSlash() {
    // Test if accepts with the "/"
    mkDir.execute(dir, "mkdir /Folder1", null);
    cd.execute(dir, "cd /Folder1", null);
    assertEquals("Folder1", dir.workingDir());
  }

  @Test
  public void testChangeDirToPath() {
    // Test if cd accepts a full path
    mkDir.execute(dir,
        "mkdir /Folder1 /Folder1/Folder2 /Folder1/Folder" + "2/Folder3", null);
    cd.execute(dir, "cd Folder1/Folder2/Folder3", null);
    assertEquals("Folder3", dir.workingDir());
  }

  @Test
  public void testGoingUpAndDownATree() {
    // Test if cd can go up and down a directory
    mkDir.execute(dir,
        "mkdir /Folder1 /Folder1/Folder2 /Folder1/Folder2/Folder3", null);
    cd.execute(dir, "cd Folder1/Folder2/Folder3", null);
    cd.execute(dir, "cd ..", null);
    assertEquals("Folder2", dir.workingDir());
  }

  @Test
  public void testGoingToRoot() {
    // Test going to root (cd /) as input
    mkDir.execute(dir, "mkdir /Folder1", null);
    cd.execute(dir, "cd /", null);
    assertEquals("/", dir.workingDir());
  }

  @Test
  public void testGoingUpWhileInRoot() {
    // Test trying to go up from root
    mkDir.execute(dir, "mkdir /Folder1", null);
    cd.execute(dir, "cd /..", null);
    assertEquals("/", dir.workingDir());
  }

  @Test
  public void testChangeDirToNonExistentDir() {
    // Test trying to go up from root
    mkDir.execute(dir, "mkdir /Folder1", null);
    cd.execute(dir, "cd Folder2", null);
    assertEquals("/", dir.workingDir());
  }

  @Test
  public void testUsingDotDotWithFilePath() {
    // Test if cd can go up and down a directory using a single path
    mkDir.execute(dir,
        "mkdir /Folder1 /Folder1/Folder2 /Folder1/Folder" + "2/Folder3", null);
    cd.execute(dir, "cd Folder1/Folder2/Folder3/..", null);
    assertEquals("Folder2", dir.workingDir());
  }

  @Test
  public void testWithIncorrectFilePath() {
    // Test full path where some folder does not exist
   