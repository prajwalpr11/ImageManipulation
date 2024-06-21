package model;

import java.awt.image.BufferedImage;

/**
 * ReadOnly object of the model which is used for the viewer as the reference to the current image
 * which is updated by the model and instructed by the controller. Provides the non-updatable
 * operations on the model.
 */
public interface ImageReadOnlyObject {


  /**
   * Get BufferedImage of the image name with reference name.
   *
   * @param imageRefName String name of the image.
   * @return BufferedImage Object returned.
   */
  public BufferedImage getBufferedImageOfObject(String imageRefName);

}
