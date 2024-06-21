package model;

/**
 * ImageEditorWithCommand enable the images to execute the operation on the ImageEditor object and
 * returns the transformed ImageEditor object.
 */
public interface ImageEditorWithCommand extends ImageEditor {

  /**
   * This method runs the operation provided on to the ImageEditorWithCommand object and operation
   * is provided as the argument ImageModelCommand object.
   *
   * @param imgOperation ImageModelCommand object which is performed on the ImageEditorWithCommand.
   * @return ImageEditor object which is transformed.
   */
  ImageEditor runOperation(ImageModelCommand imgOperation);
}
