package model;

import java.util.HashMap;
import java.util.Map;

/**
 * ImageStoreImplementor implements the ImageStore as a hashmap of images, with key value as the
 * image name and value as the ImageEditor Object.
 */
public class ImageStoreImplementor implements ImageStore {

  private final Map<String, ImageEditor> imageObjectHashMap = new HashMap<>();

  @Override
  public ImageEditor getImageModel(String imageRef) {
    return this.imageObjectHashMap.get(imageRef);
  }

  @Override
  public void setImageModel(String imageRef, ImageEditor imgObject) {
    this.imageObjectHashMap.put(imageRef, imgObject);
  }
}
