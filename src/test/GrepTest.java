package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.Cd;
import commands.Echo;
import commands.Grep;
import commands.Mkdir;
import helpers.Directory;

public class GrepTest {
  Directory dir;
  Mkdir mkdir;
  Cd cd;
  Grep grep;
  Echo echo;
  String expected;
  String actual;
  
  @Before
  public void setUp(){
    dir = new Directory();
    mkdir = new Mkdir();
    cd = new Cd();
    grep = new Grep();
    echo = new Echo();
    expected = "";
    actual = "";
  }
  @Test
  public void testCorrectSingleLine () {
    echo.execute(dir,
        "echo " + "\"hello\"" + " > test.txt", null);
    expected = "/test.txt:\nhello";
    actual = grep.findRegex(dir, "hello test.txt");
    assertEquals(expected, actual);
  }
  @Test
  public void testCorrectMultipleLine() {
    echo.execute(dir,
        "echo " + "\"hello\nhello\"" + " > test.txt", null);
    expected = "/test.txt:\nhello\nhello";
    actual = grep.findRegex(dir, "hello test.txt");
    assertEquals(expected, actual);
  }
  @Test
  public void testCorrectMultipleLineMultipleRegex() {
    echo.execute(dir,
        "echo " + "\"hellotanayhello\nhello\"" + " > test.txt", null);
    expected = "/test.txt:\nhellotanayhello\nhellotanayhello\nhello";
    actual = grep.findRegex(dir, "hello test.txt");
    assertEquals(expected, actual);
  }
  
}