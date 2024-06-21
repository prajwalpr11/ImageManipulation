package model;

/**
 * ImageStore stores the list of images which are loaded and transformed by the user interaction.
 */
public interface ImageStore {

  /**
   * Gets the ImageEditor object from the store.
   *
   * @param imageRef string name of the image.
   * @return ImageEditor object in the store.
   */
  ImageEditor getImageModel(String imageRef);

  /**
   * Stores the ImageEditor object to the store.
   *
   * @param imageRef string name of the image.
   * @param imgObj   ImageEditor object to store.
   */
  void setImageModel(String imageRef, ImageEditor imgObj);
}
