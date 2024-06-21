package model;

/**
 * Operations store to list the set of possible operations which can be performed on the image
 * object.
 */
public interface ImageOperationsStore {

  /**
   * ImageEditor blur implemented in the new version model. Shows list of operations available and
   * implemented on the new model object.
   *
   * @param img ImageEditor Object.
   */
  ImageEditor blur(ImageEditor img);

  /**
   * ImageEditor sharpen implemented in the new version model. Shows list of operations available
   * and implemented on the new model object.
   *
   * @param img ImageEditor Object.
   */
  ImageEditor sharpen(ImageEditor img);

  /**
   * ImageEditor sepia tone filter implemented in the new version model. Shows list of operations
   * available and implemented on the new model object.
   *
   * @param img ImageEditor Object.
   */
  ImageEditor sepiaFilter(ImageEditor img);

  /**
   * ImageEditor greyscale implemented in the new version model. Shows list of operations available
   * and implemented on the new model object.
   *
   * @param img ImageEditor Object.
   */
  ImageEditor greyScaleFilter(ImageEditor img);

  /**
   * ImageEditor dither implemented in the new version model. Shows list of operations available and
   * implemented on the new model object.
   *
   * @param img ImageEditor Object.
   */
  ImageEditor ditherOperation(ImageEditor img);

}
