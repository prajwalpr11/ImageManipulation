package controller;

import java.io.IOException;
import view.ViewGuiInterface;

/**
 * EditorControllerGuiImpl implements the ViewUIFeatures. It acts as an adapter between
 * ViewGuiInterface and EditorController. This class defines methods to perform various image
 * editing operations such as loading an image, applying filters, splitting and combining channels,
 * saving an image, exiting the program. Total UI controller is done in this class.
 */
public class EditorControllerGuiImpl implements ViewUIFeatures {

  private final EditorController editorController;
  private final ViewGuiInterface viewGuiImpl;

  /**
   * Constructs EditorControllerGuiImpl object.
   *
   * @param ctrl        initialises an EditorController object that provides image editing
   *                    functionality.
   * @param viewGuiImpl initialises a ViewGuiInterface object that provides graphical user interface
   *                    to the user.
   */

  public EditorControllerGuiImpl(EditorController ctrl, ViewGuiInterface viewGuiImpl) {
    this.editorController = ctrl;
    this.viewGuiImpl = viewGuiImpl;
    this.viewGuiImpl.addFeatures(this);
  }

  private void updateImage() {
    try {
      this.viewGuiImpl.updateViewImage();
    } catch (Exception e) {
      this.viewGuiImpl.logOutput(e.getMessage());
    }
  }

  @Override
  public void exitProgram() {
    System.exit(1);
  }

  @Override
  public void loadFileTriggeredFromView(String filePath) throws IOException {
    this.editorController.readAndExecuteCommand(
        this.getIOCommandString(this.getDefaultImageRefName(), filePath, "input"));
  }

  @Override
  public void brightenFromView(int quantity) {
    String bgtCmd = "brighten " + quantity;
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), bgtCmd));
    this.updateImage();
  }

  @Override
  public void horizontalFlipFromView() {
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), "hFlip"));
    this.viewGuiImpl.updateViewImage();
  }

  @Override
  public void verticalFlipFromView() {
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), "vFlip"));
    this.viewGuiImpl.updateViewImage();
  }

  @Override
  public void greyScaleCompFromGui(String comp) {
    if (this.isValidComp(comp)) {
      String bgtCmd = "greyscale " + comp;
      this.editorController.readAndExecuteCommand(
          this.getUnaryCommandString(this.getDefaultImageRefName(), bgtCmd));
      this.viewGuiImpl.updateViewImage();
    } else {
      this.viewGuiImpl.logOutput("wrong input comp");
    }
  }

  @Override
  public void saveImageFromView(String filePath) throws IOException {
    this.editorController.readAndExecuteCommand(
        this.getIOCommandString(this.getDefaultImageRefName(), filePath, "output"));
    this.viewGuiImpl.infoMessage("Image Saved Successfully.");
  }

  @Override
  public void greyScaleFilterFromGui() {
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), "greyscale"));
    this.viewGuiImpl.updateViewImage();
  }

  @Override
  public void sharpenFromGui() {
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), "sharp"));
    this.viewGuiImpl.updateViewImage();
  }

  @Override
  public void blurFromGui() {
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), "blur"));
    this.viewGuiImpl.updateViewImage();
  }

  @Override
  public void ditherFromGui() {
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), "dither"));
    this.viewGuiImpl.updateViewImage();
  }

  @Override
  public void sepiaFilterFromGui() {
    this.editorController.readAndExecuteCommand(
        this.getUnaryCommandString(this.getDefaultImageRefName(), "sepia"));
    this.updateImage();
  }

  @Override
  public void combineImageFromGui(String redImageFilePath, String greenImageFilePath,
      String blueImageFilePath) {
    System.out.println(redImageFilePath + "" + greenImageFilePath + "" + blueImageFilePath);
    this.editorController.readAndExecuteCommand("load "
        + redImageFilePath + " red-combine");
    this.editorController.readAndExecuteCommand("load "
        + greenImageFilePath + " green-combine");
    this.editorController.readAndExecuteCommand("load "
        + blueImageFilePath + " blue-combine");
    this.editorController.readAndExecuteCommand("rgb-combine "
        + this.getDefaultImageRefName() + " red-combine green-combine blue-combine");
    this.updateImage();

  }

  @Override
  public void splitImageFromGui(String redImageFilePath, String greenImageFilePath,
      String blueImageFilePath) {
    System.out.println(redImageFilePath + "" + greenImageFilePath + "" + blueImageFilePath);
    this.editorController.readAndExecuteCommand("rgb-split "
        + this.getDefaultImageRefName() + " red-split green-split blue-split");
    this.editorController.readAndExecuteCommand("save "
        + redImageFilePath + " red-split");
    this.editorController.readAndExecuteCommand("save "
        + greenImageFilePath + " green-split");
    this.editorController.readAndExecuteCommand("save "
        + blueImageFilePath + " blue-split");
    this.updateImage();
    this.viewGuiImpl.infoMessage("Image split and saved done successfully.");
  }

  private String getDefaultImageRefName() {
    return "editing-image";
  }

  private String getIOCommandString(String imgRef, String filePath, String ioType) {
    if (ioType.equals("input")) {
      return "load " + filePath + " " + imgRef;
    } else {
      return "save " + filePath + " " + imgRef;
    }

  }

  private String getUnaryCommandString(String imgRef, String command) {
    StringBuilder cmdString = new StringBuilder("");
    switch (command) {
      case "greyscale": {
        cmdString.append("greyscale ").append(imgRef).append(" ").append(imgRef);
        break;
      }
      case "hFlip": {
        cmdString.append("horizontal-flip ").append(imgRef).append(" ").append(imgRef);
        break;
      }
      case "vFlip": {
        cmdString.append("vertical-flip ").append(imgRef).append(" ").append(imgRef);
        break;
      }
      case "blur": {
        cmdString.append("blur ").append(imgRef).append(" ").append(imgRef);
        break;
      }
      case "sharp": {
        cmdString.append("sharpen ").append(imgRef).append(" ").append(imgRef);
        break;
      }
      case "sepia": {
        cmdString.append("sepia ").append(imgRef).append(" ").append(imgRef);
        break;
      }
      case "dither": {
        cmdString.append("dither ").append(imgRef).append(" ").append(imgRef);
        break;
      }
      default: {
        cmdString.append(command + " ").append(imgRef).append(" ").append(imgRef);
        break;
      }
    }
    return cmdString.toString();
  }

  private boolean isValidComp(String comp) {
    return ((comp.equals("red-component"))
        || (comp.equals("green-component"))
        || (comp.equals("blue-component"))
        || (comp.equals("value-component"))
        || (comp.equals("intensity-component"))
        || (comp.equals("luma-component")));
  }
}
