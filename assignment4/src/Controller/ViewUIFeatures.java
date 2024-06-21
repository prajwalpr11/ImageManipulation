package controller;

import java.io.IOException;

/**
 * This interface defines the methods for handling UI events triggered from the view.
 */
public interface ViewUIFeatures {

  /**
   * Exits the program.
   */
  void exitProgram();

  /**
   * Loads a file from the specified file path selected from the user interface.
   *
   * @param filePath the file path to load the file from
   * @throws IOException if there is an error while loading the file
   */
  void loadFileTriggeredFromView(String filePath) throws IOException;

  /**
   * Brightens the image by the specified amount in the user interface.
   *
   * @param quantity is the quantity of amount to brighten the image by
   */
  void brightenFromView(int quantity);

  /**
   * Flips the image horizontally from the UI.
   */
  void horizontalFlipFromView();

  /**
   * Flips the image vertically from the UI.
   */
  void verticalFlipFromView();


  /**
   * Converts the specified component of the image to grayscale.
   *
   * @param component the component to convert to grayscale
   */
  void greyScaleCompFromGui(String component);


  /**
   * Saves the current image to the specified file path from the UI.
   *
   * @param filePath the file path to save the image to
   * @throws IOException if there is an error while saving the image
   */
  void saveImageFromView(String filePath) throws IOException;

  /**
   * Applies a grayscale filter to the image loaded in UI.
   */
  void greyScaleFilterFromGui();

  /**
   * Applies to sharpen filter to the image loaded in the UI.
   */
  void sharpenFromGui();

  /**
   * Applies a blur filter to the image loaded in the UI.
   */
  void blurFromGui();

  /**
   * Applies a dither filter to the image loaded in the UI.
   */
  void ditherFromGui();

  /**
   * Applies a sepia filter to the image loaded in the UI.
   */
  void sepiaFilterFromGui();

  /**
   * Combines three images into a single image with red, green, and blue components.
   *
   * @param redImageFilePath   the file path to the red component image
   * @param greenImageFilePath the file path to the green component image
   * @param blueImageFilePath  the file path to the blue component image
   */
  void combineImageFromGui(String redImageFilePath, String greenImageFilePath,
      String blueImageFilePath);

  /**
   * Splits the image into three images for the red, green, and blue components.
   *
   * @param redImageFilePath   the file path to save the red component image to
   * @param greenImageFilePath the file path to save the green component image to
   * @param blueImageFilePath  the file path to save the blue component image to
   */
  void splitImageFromGui(String redImageFilePath, String greenImageFilePath,
      String blueImageFilePath);
}
