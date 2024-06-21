package model;

/**
 * go method that enables to write new operation on the model which needs to alter the state of the
 * model object. It accepts model and command string which performs the operation on the given
 * object.
 */
public interface ImageModelCommand {

  /**
   * Method to perform different operations on the ImageEditor, command is the user entered string.
   *
   * @param imageObject ImageModel object provided.
   */
  ImageEditor operationOnImage(ImageEditor imageObject);
}
