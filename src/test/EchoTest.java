@Test
  public void testAddAppend() {
    // Adds and appends a file
    input = "echo \"file\" > fileName";
    echo.execute(dir, input, null);
    input = "echo \"add\" >> fileName";
    echo.execute(dir, input, null);
    expected = "fileadd";
    output = Cat.getFileInfo(dir, "fileName");
    assertEquals(expected, output);
  }

  @Test
  public void testAddAppendEdit() {
    // Adds and appends a file and rewrites the file
    input = "echo \"file\" > fileName";
    echo.execute(dir, input, null);
    input = "echo \"add\" >> fileName";
    echo.execute(dir, input, null);
    output = Cat.getFileInfo(dir, "fileName");
    expected = "fileadd";
    assertEquals(expected, output);
    input = "echo \"reset\" > fileName";
    echo.execute(dir, input, null);
    expected = "reset";
    output = Cat.getFileInfo(dir, "fileName");
    assertEquals(expected, output);
  }

  @Test
  public void testCheckFileExists() {
    // Checks if file exists
    input = "echo \"file\" > fileName";
    echo.execute(dir, input, null);
    boolean status = FileExists.getFileExists(dir, "fileName");
    assertEquals(true, status);
  }

  @Test
  public void testCheckFileNotExists() {
    // Checks if file doesn't exists
    boolean status = FileExists.getFileExists(dir, "fileName");
    assertEquals(false, status);
  }
}