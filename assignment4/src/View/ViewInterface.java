package view;

import java.util.List;

/**
 * ViewInterface provides method to logOutput and getLoggedMessages for the user to view the list of
 * exceptions occurred during the application execution flow and provide the following commands
 * accordingly.
 */
public interface ViewInterface {


  /**
   * Log the message provided to the view.
   *
   * @param str String value of the message.
   */
  public void logOutput(String str);


  /**
   * Return the list of messages logged by view.
   *
   * @return List of string values of messages.
   */
  public List<String> getLoggedMessages();


}
