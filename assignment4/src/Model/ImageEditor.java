package model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 * ImageProcessing interface contains the methods to load, brighten, horizontalFlip, verticalFlip,
 * get greyscale image, rgb split the image, rgb combine the image.
 */
public interface ImageEditor {

  /**
   * Brighten or Darken the image by a provided quantity.
   *
   * @param quantity int value by which the brightness is changed. It can be positive, negative.
   * @return ImageObject created by modifying the provided source object.
   */
  public ImageEditor brighten(int quantity);

  /**
   * Flip the image horizontally.
   *
   * @return ImageObject created by flipping the ImageObject.
   */
  public ImageEditor horizontalFlip();

  /**
   * Flip the image vertically.
   *
   * @return ImageObject created by flipping the ImageObject.
   */
  public ImageEditor verticalFlip();

  /**
   * Get the grey scale image component.
   *
   * @param component Value component of the image it could be red, blue, green, value, luma,
   *                  intensity.
   * @return ImageObject created by flipping the ImageObject.
   */
  public ImageEditor greyScale(String component);


  /**
   * Get the rgb split of the provided image, splits the image into various components i.e red,
   * green, blue grey scale images.
   *
   * @return Map containing the key as value components and value as the ImageObject.
   */
  public Map<String, ImageEditor> rgbSplit();

  /**
   * Combines the three value components of the image and returns a new ImageObject formed by
   * combining the red, blue and green ImageObjects.
   *
   * @param greenComp ImageObject of the green component.
   * @param blueComp  ImageObject of the blue component.
   * @return ImageObject created by combining the three ImageObjects.
   */
  public ImageEditor rgbCombine(ImageEditor greenComp, ImageEditor blueComp);


  /**
   * Returns the string builder needed to save the file by transforming the ImageEditor object.
   *
   * @return StringBuilder by unwrapping the ImageEditorObject.
   */
  StringBuilder getImageEditorContent();

  /**
   * Get the list of the pixel list. It's a two-dimensional list of pixels.
   *
   * @return List of pixel lists.
   */
  public List<List<ImagePixel>> getImagePixels();

  /**
   * Get the maximum value of the pixel stored in the image, i.e it varies from 0 to 255 for an
   * 8-bit pixel representation.
   *
   * @return int value of the maximum pixel value.
   */
  public int getMaxPixel();

  /**
   * Get the height of the image.
   *
   * @return int value of the height of the image.
   */
  public int getHeight();


  /**
   * Get the width of the image.
   *
   * @return int value of the width of the image.
   */
  public int getWidth();

  /**
   * Get BufferedImage of the image.
   */
  public BufferedImage getBufferedImageObject();

}
