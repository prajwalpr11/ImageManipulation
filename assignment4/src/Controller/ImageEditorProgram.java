package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import model.ImageReadOnlyObject;
import model.ImageReadOnlyObjectImpl;
import model.ImageStore;
import model.ImageStoreImplementor;
import view.View;
import view.ViewGuiImpl;
import view.ViewGuiInterface;
import view.ViewInterface;

/**
 * ImageEditorProgram runs the image editor application it accepts command line arguments which is
 * the file name of the script file to run and exits. If the command line argument is not provided
 * then the application interacts with the user interactively.
 */
public class ImageEditorProgram {

  /**
   * Main program to start the application which accepts the file from command line and exits the
   * application. If the file is not provided then the program expects user to interact.
   *
   * @param args command line arguments provided.
   * @throws IOException File exception thrown if the file is not able to be read.
   */
  public static void main(String[] args) throws IOException {

    ImageStore model = new ImageStoreImplementor();
    ViewInterface view = new View(new ByteArrayOutputStream());
    if (args.length == 2 && args[0].equals("-file")) {
      Readable rd = new InputStreamReader(new ByteArrayInputStream(("run " + args[1]
          + "\nexit").getBytes()));
      EditorController ctrl = new EditorControllerImplementor(model, rd, view);
      ctrl.startApplication();
    } else if (args.length == 1 && args[0].equals("-text")) {
      Readable rd = new InputStreamReader(System.in);
      EditorController ctrl = new EditorControllerImplementor(model, rd, view);
      ctrl.startApplication();
    } else if (args.length == 0) {
      ImageReadOnlyObject roModel = new ImageReadOnlyObjectImpl(model);
      ViewGuiInterface viewUI = new ViewGuiImpl("Image Editor", view, roModel);
      Readable rd = new InputStreamReader(System.in);
      EditorController ctrl = new EditorControllerImplementor(model, rd, viewUI);
      new EditorControllerGuiImpl(ctrl, viewUI);
    } else {
      view.logOutput("Error running application, quiting.");
    }
  }
}
