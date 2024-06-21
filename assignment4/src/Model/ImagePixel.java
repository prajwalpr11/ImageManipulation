package model;

/**
 * ImagePixel interface gives the red, green, blue components of the pixel. It also provides the
 * value, intensity and luma components of the pixel.
 */
public interface ImagePixel {

  /**
   * Gets the red component of the pixel.
   *
   * @return int value of the red component of the pixel.
   */
  public int getRed();

  /**
   * Gets the green component of the pixel.
   *
   * @return int value of the green component of the pixel.
   */
  public int getGreen();


  /**
   * Gets the blue component of the pixel.
   *
   * @return int value of the blue component of the pixel.
   */
  public int getBlue();


  /**
   * Gets the value component of the pixel.
   *
   * @return int value of the value component of the pixel.
   */
  public int getVal();


  /**
   * Gets the intensity component of the pixel.
   *
   * @return int value of the intensity component of the pixel.
   */
  public int getIntensity();

  /**
   * Gets the luma component of the pixel.
   *
   * @return int value of the luma component of the pixel.
   */
  public int getLuma();
}
