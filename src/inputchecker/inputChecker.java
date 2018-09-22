package inputchecker;

import java.util.List;
import java.util.ArrayList;

public class inputChecker {

  private ArrayList<String> commandHistory = new ArrayList<String>();
  private String[][] commandsAndArguments = {{"mkdir", "2"}, 
                                              {"cd", "1"}, 
                                             {"ls", "1"}, 
                                             {"pwd", "0"}, 
                                             {"pushd", "1"}, 
                                             {"popd", "0"}, 
                                             {"history", "1"}, 
                                             {"cat", "1"}, 
                                             {"echo"}, 
                                             {"man", "1"}};
      
  public inputChecker(String command) {
    String[] commands = command.split("\\s+");
    
    int i = 0;
    boolean validCommand = false; 
    int numberOfMaxArguments = 0;
    while (i < commandsAndArguments.length && !validCommand) {
      // Checks if first typed text is a valid command
      if (commands[0] == commandsAndArguments[i][0]) 
      {
        validCommand = true;
        numberOfMaxArguments = Integer.parseInt(commandsAndArguments[i][1]);
      } 
      else { i++; }
    }
   
  }
  
  
  
  public void keepCommandHistory(String input){
  
    commandHistory.add(input);
  }
}
