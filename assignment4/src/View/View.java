package view;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * View implementation of ViewInterface, the messages are logged on to the provided OutputStream.
 * Gets the list of logged messages by the application. Also the list of performed operations by the
 * user.
 */
public class View implements ViewInterface {

  private final OutputStream outStream;
  private final List<String> loggedMessages = new ArrayList<>();

  /**
   * View constructor to initialise the output stream and log the list of message.
   *
   * @param out OutputStream to initialise the view.
   */
  public View(
      OutputStream out) {
    this.outStream = out;
  }


  /**
   * Log the message provided to the view to the corresponding OutputStream initialised.
   *
   * @param message String value of the message to log.
   */
  public void logOutput(String message) {
    PrintStream out = new PrintStream(this.outStream);
    out.printf(message);
    this.loggedMessages.add(message);
    System.out.println(message);
  }

  /**
   * Return the list of messages logged by the viewer.
   *
   * @return List of string values of messages.
   */
  public List<String> getLoggedMessages() {
    return new ArrayList<>(this.loggedMessages);
  }


}
