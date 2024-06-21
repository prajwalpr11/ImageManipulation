package model;

/**
 * Pixel class is the implementation of the ImagePixel interface where each pixel class contains
 * various components of a pixel such as red, green, blue, val, intensity and luma.
 */
public class Pixel implements ImagePixel {

  private final int red;
  private final int green;
  private final int blue;
  private final int val;
  private final int intensity;
  private final int luma;

  /**
   * Constructor to create the pixel object by taking the red, green and blue components of the
   * pixel. The value, intensity and luma components are calculated and stored in the object.
   *
   * @param r int value of the red component.
   * @param g int value of the green component.
   * @param b int value of the blue component.
   */
  public Pixel(int r, int g, int b) {
    this.red = r;
    this.green = g;
    this.blue = b;
    this.val = Math.max(r, Math.max(g, b));
    this.intensity = (int) Math.round((r + g + b) / 3.0);
    this.luma = (int) Math.round((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
  }

  /**
   * Gets the red component of the pixel.
   *
   * @return int value of the red component.
   */
  public int getRed() {
    return this.red;
  }


  /**
   * Gets the green component of the pixel.
   *
   * @return int value of the green component.
   */
  public int getGreen() {
    return this.green;
  }


  /**
   * Gets the blue component of the pixel.
   *
   * @return int value of the blue component.
   */
  public int getBlue() {
    return this.blue;
  }


  /**
   * Gets the value component of the pixel.
   *
   * @return int value of the value component.
   */
  public int getVal() {
    return this.val;
  }


  /**
   * Gets the intensity component of the pixel.
   *
   * @return int value of the intensity component.
   */
  public int getIntensity() {
    return this.intensity;
  }


  /**
   * Gets the luma component of the pixel.
   *
   * @return int value of the luma component.
   */
  public int getLuma() {
    return this.luma;
  }

  /**
   * Equals method is overwritten to compare different pixel objects basing upon their red, blue and
   * green components. If these three values are same for two pixel objects then the two objects are
   * same.
   *
   * @param obj Object passed to compare with the Pixel object.
   * @return boolean value if two objects are same.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Pixel)) {
      return false;
    }
    ImagePixel p = (Pixel) obj;
    return this.red == p.getRed() && this.blue == p.getBlue() && this.green == p.getGreen();
  }

  /**
   * Gets the hashcode value of the pixel object. It is found by concatenating the red, green and
   * blue components of the pixel.
   *
   * @return int value of the hashCode of the pixel object.
   */
  @Override
  public int hashCode() {
    String s = "" + this.red + this.green + this.blue;
    return s.hashCode();
  }
}
