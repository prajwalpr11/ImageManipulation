package controller;

import java.io.IOException;

/**
 * Controller controls the flow of the application, it acts as a delegator of tasks between model
 * and view according to the given user command. Go starts the application with the set of available
 * options.
 */
public interface EditorController {

  /**
   * Execute starts the application and waits for the user commands progressively.
   */
  void startApplication() throws IOException;

  /**
   * Executes the given command from the user.
   *
   * @param cmdString command string given to run.
   * @throws IllegalArgumentException throws exception if the provided command is invalid.
   */
  void readAndExecuteCommand(String cmdString) throws IllegalArgumentException;

}
