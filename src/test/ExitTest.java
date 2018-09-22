package test;

import static org.junit.Assert.*;

import java.util.Stack;
import org.junit.Test;
import commands.*;
import helpers.*;

public class ExitTest {
  Exit exit;
  Stack a;
  Directory dir;



  @Test
  public void testIfExitWorks() {
    exit = new Exit();
    dir = new Directory();
    assertTrue(exit.checkString("exit"));

  }

  @Test
  public void testIfExitWorksWithWrongInput() {
    exit = new Exit();
    dir = new Directory();
    assertFalse(exit.checkString("wrong input"));
  }


}