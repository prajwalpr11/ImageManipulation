package model;

import java.awt.image.BufferedImage;

/**
 * ImageReadOnlyObjectImpl implements the ImageReadOnlyObject interface to give the view the
 * bufferedImage of the image model. For reading purpose.
 */
public class ImageReadOnlyObjectImpl implements ImageReadOnlyObject {

  private final ImageStore imageStoreObject;

  /**
   * Constructor to store the image store which is present in the controller connected as the
   * composition. Where imageStore is a part in the ImageReadOnlyObjectImpl class.
   *
   * @param imgStore ImageStore object present in the model.
   */
  public ImageReadOnlyObjectImpl(ImageStore imgStore) {
    this.imageStoreObject = imgStore;
  }

  @Override
  public BufferedImage getBufferedImageOfObject(String imgRef) {
    return this.imageStoreObject.getImageModel(imgRef).getBufferedImageObject();
  }

}
