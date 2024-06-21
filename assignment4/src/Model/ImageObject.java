package model;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ImageObject class contains the maximumPixel maximum pixel value of the pixel in the image, height
 * of the image, width of the image and list of the pixel lists.
 */
public class ImageObject implements ImageEditor {

  private final int maximumPixel;
  private final int height;
  private final int width;
  private final List<List<ImagePixel>> imagePixels;

  /**
   * Constructor creates the ImageObject by taking the input of width, height, maximumPixel and list
   * of pixel lists.
   *
   * @param width        int value of the width.
   * @param height       int value of the height.
   * @param maximumPixel int value of the maximum pixel value.
   * @param pixels       list of pixel lists.
   */
  public ImageObject(List<List<ImagePixel>> pixels, int width, int height, int maximumPixel) {
    this.width = width;
    this.height = height;
    this.maximumPixel = maximumPixel;
    this.imagePixels = pixels;
  }

  private StringBuilder getHashForImageObject() {
    StringBuilder builder = new StringBuilder();
    builder.append(this.maximumPixel);
    builder.append(this.height);
    builder.append(this.width);
    for (int column = 0; column < this.height; column++) {
      for (int row = 0; row < this.width; row++) {
        ImagePixel pixels = this.imagePixels.get(column).get(row);
        builder.append(pixels.getRed());
        builder.append(pixels.getGreen());
        builder.append(pixels.getBlue());
      }
    }
    return builder;
  }

  /**
   * Compare if two image objects are same or not. They are equal if both have the same height,
   * width, maximumPixel value, same list of pixel lists.
   *
   * @param obj Object to compare with the ImageObject.
   * @return boolean value of the comparison to find if two objects are equal or not.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ImageObject)) {
      return false;
    }
    ImageObject img = (ImageObject) obj;
    return this.height == img.height
        && this.width == img.width
        && this.maximumPixel == img.maximumPixel
        && this.imagePixels.equals(img.imagePixels);
  }

  /**
   * Get the hashCode value of the ImageObject, it is computed by creating the string with
   * concatenating the list of pixel lists, height, width, maximumPixel value.
   *
   * @return int value of the hashCode of ImageObject.
   */
  @Override
  public int hashCode() {
    return this.getHashForImageObject().toString().hashCode();
  }

  /**
   * Get the list of pixel lists, where each object stored in the list is of Pixel object.
   *
   * @return List of pixel lists.
   */
  public List<List<ImagePixel>> getImagePixels() {
    List<List<ImagePixel>> pixelCopy = new ArrayList<>();
    pixelCopy.addAll(this.imagePixels);
    return pixelCopy;
  }

  /**
   * Get the maximum value of the pixel stored in the image, i.e it varies from 0 to 255 for an
   * 8-bit pixel representation.
   *
   * @return int value of the maximum pixel value.
   */
  @Override
  public int getMaxPixel() {
    return Integer.valueOf(this.maximumPixel);
  }


  /**
   * Get the height of the image.
   *
   * @return int value of the height of the image.
   */
  @Override
  public int getHeight() {
    return Integer.valueOf(this.height);
  }

  /**
   * Get the width of the image.
   *
   * @return int value of the width of the image.
   */
  @Override
  public int getWidth() {
    return Integer.valueOf(this.width);
  }


  private int setBrightnessValue(int currentValue, int maximumPixelValue) {
    if (currentValue > maximumPixelValue) {
      currentValue = maximumPixelValue;
    }
    if (currentValue < 0) {
      currentValue = 0;
    }
    return currentValue;
  }


  /**
   * Brighten or Darken the image by a provided quantity and return the new object created by
   * modifying.
   *
   * @param quantity int value by which the brightness is changed. It can be positive, negative.
   * @return ImageObject created by modifying the provided source object.
   */
  @Override
  public ImageEditor brighten(int quantity) {
    List<List<ImagePixel>> pixelMap = this.getImagePixels();
    List<List<ImagePixel>> modifiedPixels = new ArrayList<>();
    for (int column = 0; column < this.height; column++) {
      List<ImagePixel> pixelsRowList;
      pixelsRowList = pixelMap.get(column);
      for (int row = 0; row < this.width; row++) {
        ImagePixel pixels = pixelsRowList.get(row);
        int red = this.setBrightnessValue(pixels.getRed() + quantity, this.maximumPixel);
        int blue = this.setBrightnessValue(pixels.getBlue() + quantity, this.maximumPixel);
        int green = this.setBrightnessValue(pixels.getGreen() + quantity, this.maximumPixel);
        pixels = new Pixel(red, green, blue);
        pixelsRowList.set(row, pixels);
      }
      pixelMap.set(column, pixelsRowList);
    }
    return this;
  }


  /**
   * Flip the image horizontally.
   *
   * @return ImageObject created by flipping the ImageObject.
   */
  @Override
  public ImageEditor horizontalFlip() {
    List<List<ImagePixel>> pixelMap = this.imagePixels;
    for (int column = 0; column < this.height; column++) {
      List<ImagePixel> rowList = new ArrayList<>();
      rowList.addAll(pixelMap.get(column));
      Collections.reverse(rowList);
      pixelMap.set(column, rowList);
    }
    return this;
  }

  /**
   * Flip the image vertically.
   *
   * @return ImageObject created by flipping the ImageObject.
   */
  @Override
  public ImageEditor verticalFlip() {
    List<List<ImagePixel>> pixelMap = this.imagePixels;
    Collections.reverse(pixelMap);
    return this;
  }


  private int getGreyScaleValueByComponent(ImagePixel pixelValue, String component) {
    switch (component) {
      case "red-component": {
        return pixelValue.getRed();
      }
      case "green-component": {
        return pixelValue.getGreen();
      }
      case "blue-component": {
        return pixelValue.getBlue();
      }
      case "value-component": {
        return pixelValue.getVal();
      }
      case "intensity-component": {
        return pixelValue.getIntensity();
      }
      case "luma-component": {
        return pixelValue.getLuma();
      }
      default: {
        return 0;
      }
    }
  }


  /**
   * Get the grey scale image component.
   *
   * @param valueComponent Value component of the image it could be red, blue, green, value, luma,
   *                       intensity.
   * @return ImageObject created by flipping the ImageObject.
   */
  @Override
  public ImageEditor greyScale(String valueComponent) {
    List<List<ImagePixel>> greyScalePixels = new ArrayList<>();
    greyScalePixels.addAll(this.getImagePixels());
    for (int column = 0; column < this.getHeight(); column++) {
      List<ImagePixel> pixelRowList = new ArrayList<>();
      pixelRowList.addAll(greyScalePixels.get(column));
      for (int row = 0; row < this.getWidth(); row++) {
        ImagePixel pixels = pixelRowList.get(row);
        int pixVal = this.getGreyScaleValueByComponent(pixels, valueComponent);
        pixels = new Pixel(pixVal, pixVal, pixVal);
        pixelRowList.set(row, pixels);
      }
      greyScalePixels.set(column, pixelRowList);
    }

    return new ImageObject.ImageObjectBuilder().buildImageObject(greyScalePixels, this.getWidth(),
        this.getHeight(),
        this.getMaxPixel());
  }


  /**
   * Get the rgb split of the provided image, splits the image into various components that is red,
   * green, blue grey scale images.
   *
   * @return Map containing the key as value components and value as the ImageObject.
   */
  @Override
  public Map<String, ImageEditor> rgbSplit() {
    Map<String, ImageEditor> imageObj = new HashMap<>();
    imageObj.put("red", this.greyScale("red-component"));
    imageObj.put("blue", this.greyScale("blue-component"));
    imageObj.put("green", this.greyScale("green-component"));
    return imageObj;
  }


  private Map<String, Integer> combineThreePixels(ImagePixel red, ImagePixel green,
      ImagePixel blue) {
    Map<String, Integer> combinedPixels = new HashMap<>();
    combinedPixels.put("red", red.getRed());
    combinedPixels.put("green", green.getGreen());
    combinedPixels.put("blue", blue.getBlue());
    return combinedPixels;
  }


  /**
   * Combines the three value components of the image and returns a new ImageObject formed by
   * combining the red, blue and green ImageObjects.
   *
   * @param greenComponent ImageObject of the green component.
   * @param blueComponent  ImageObject of the blue component.
   * @return ImageObject created by combining the three ImageObjects.
   */
  @Override
  public ImageEditor rgbCombine(ImageEditor greenComponent,
      ImageEditor blueComponent) {
    List<List<ImagePixel>> combinedPixels = new ArrayList<>();
    for (int column = 0; column < this.height; column++) {
      List<ImagePixel> rowList = new ArrayList<>();
      for (int row = 0; row < this.width; row++) {
        Map<String, Integer> channelVal = this.combineThreePixels(
            this.imagePixels.get(column).get(row),
            greenComponent.getImagePixels().get(column).get(row),
            blueComponent.getImagePixels().get(column).get(row));
        ImagePixel pixels = new Pixel(channelVal.get("red"), channelVal.get("green"),
            channelVal.get("blue"));
        rowList.add(pixels);
      }
      combinedPixels.add(rowList);
    }
    return new ImageObject.ImageObjectBuilder().buildImageObject(combinedPixels, this.getWidth(),
        this.getHeight(),
        this.getMaxPixel());
  }

  /**
   * Returns the string builder needed to save the file by transforming the ImageEditor object.
   *
   * @return StringBuilder by unwrapping the ImageEditorObject.
   */
  public StringBuilder getImageEditorContent() {
    StringBuilder builder = new StringBuilder();
    builder.append(this.width).append(System.lineSeparator());
    builder.append(this.height).append(System.lineSeparator());
    builder.append(this.maximumPixel).append(System.lineSeparator());
    for (int col = 0; col < this.height; col++) {
      List<ImagePixel> rowList = this.imagePixels.get(col);
      for (int row = 0; row < this.width; row++) {
        ImagePixel pixel = rowList.get(row);
        builder.append(pixel.getRed()).append(System.lineSeparator());
        builder.append(pixel.getGreen()).append(System.lineSeparator());
        builder.append(pixel.getBlue()).append(System.lineSeparator());
      }
    }
    return builder;
  }

  @Override
  public BufferedImage getBufferedImageObject() {
    StringBuilder builder = this.getImageEditorContent();
    Scanner sc = new Scanner(builder.toString());
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxVal = sc.nextInt();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        int col = (r << 16) | (g << 8) | b;
        bufferedImage.setRGB(j, i, col);
      }
    }
    return bufferedImage;
  }

  /**
   * Builder to create the ImageObject, this builds the object and ImageEditor operations are
   * performed on it. Once the ImageObject is built it cannot be modified.
   */
  public static class ImageObjectBuilder {

    /**
     * Builds the PPM ImageObject with the provided file. Parses individual line and creates the
     * list of ImagePixels and initialises the member variables.
     *
     * @param filename String value of the file path.
     * @return ImageEditor type object.
     * @throws IllegalArgumentException If the provided file is not a valid PPM file.
     * @throws FileNotFoundException    If the provided file path is not accessible.
     */
    public ImageEditor buildImageWithFile(StringBuilder filename)
        throws IllegalArgumentException, IOException {
      Scanner sc = new Scanner(filename.toString());
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxVal = sc.nextInt();
      List<List<ImagePixel>> pixels = new ArrayList<>();
      for (int i = 0; i < height; i++) {
        List<ImagePixel> rowList = new ArrayList<>();
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          rowList.add(new Pixel(r, g, b));
        }
        pixels.add(rowList);
      }
      return buildImageObject(pixels, width, height, maxVal);
    }


    /**
     * Builds the image object by consuming the list of image pixels, width, height, maximum pixel
     * value.
     *
     * @param imgPix    list of image pixel list.
     * @param widthVal  width of the image.
     * @param heightVal height of the image.
     * @param maxPix    maximum pixel value of the image.
     * @return
     */
    public ImageEditor buildImageObject(List<List<ImagePixel>> imgPix, int widthVal, int heightVal,
        int maxPix) {
      return new ImageObjectV2(imgPix, widthVal, heightVal,
          maxPix
      );
    }

  }


}
