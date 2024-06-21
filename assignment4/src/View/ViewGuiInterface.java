package view;

import controller.ViewUIFeatures;

/**
 * ViewGuiInterface extends the ViewInterface to provide the functionalities of logging output to
 * the user and also with GUI for the user to interact this connects to the controller
 * EditorControllerGuiImpl.
 */
public interface ViewGuiInterface extends ViewInterface {

  /**
   * View feature event callbacks implemented in the controller, called from view and adding the
   * features.
   *
   * @param features Features of the controller which are available to the view for the event
   *                 callbacks.
   */
  void addFeatures(ViewUIFeatures features);

  /**
   * To update the image of the view post performing the operation on the source image.
   */
  void updateViewImage();

  /**
   * Display info message to the user.
   */
  void infoMessage(String str);

}
