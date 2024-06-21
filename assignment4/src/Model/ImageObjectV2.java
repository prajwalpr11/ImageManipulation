package model;

import java.util.List;

/**
 * Implements the ImageEditorWithCommand interface where the operations are applied on to the
 * ImageEditor object. Extends the ImageObject inheriting the class members from base class
 * ImageObject.
 */
public class ImageObjectV2 extends ImageObject implements ImageEditorWithCommand {

  /**
   * Constructs the ImageObjectV2 object consuming list of list pixels, width, height, maxPixel
   * values. Calls the base class constructor.
   *
   * @param pixels       List of pixels lists.
   * @param width        width value of the image.
   * @param height       height value of the image.
   * @param maximumPixel maximum pixel value allowed in the image.
   */
  public ImageObjectV2(List<List<ImagePixel>> pixels, int width, int height, int maximumPixel) {
    super(pixels, width, height, maximumPixel);
  }

  @Override
  public ImageEditor runOperation(ImageModelCommand imgOperation) {
    return imgOperation.operationOnImage(this);
  }
}
