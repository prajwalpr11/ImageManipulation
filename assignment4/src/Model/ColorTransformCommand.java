package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ColorTransform operation transforms the ImageEditor object by transforming individual pixel in to
 * another type of pixel depending up on the transformation matrix. It provides sepia transform,
 * greyscale transformations.
 */
public class ColorTransformCommand implements ImageModelCommand {

  List<List<Double>> transformMatrix = new ArrayList<>();

  /**
   * Initialises the transformation matrix by checking the type of transformation required.
   *
   * @param transformType String value of the transformation.
   */
  public ColorTransformCommand(String transformType) {
    switch (transformType) {
      case "sepia": {
        this.transformMatrix = this.getSepiaMatrix();
        break;
      }
      case "greyscale": {
        this.transformMatrix = this.getGreyscaleMatrix();
        break;
      }
      default: {
        break;
      }
    }
  }

  private List<List<Double>> getSepiaMatrix() {
    List<List<Double>> mt = new ArrayList<>();
    mt.add(new ArrayList(Arrays.asList(0.393, 0.769, 0.189)));
    mt.add(new ArrayList(Arrays.asList(0.349, 0.686, 0.168)));
    mt.add(new ArrayList(Arrays.asList(0.272, 0.534, 0.131)));
    return mt;
  }

  private List<List<Double>> getGreyscaleMatrix() {
    List<List<Double>> mt = new ArrayList<>();
    mt.add(new ArrayList(Arrays.asList(0.2126, 0.7152, 0.0722)));
    mt.add(new ArrayList(Arrays.asList(0.2126, 0.7152, 0.0722)));
    mt.add(new ArrayList(Arrays.asList(0.2126, 0.7152, 0.0722)));
    return mt;
  }

  @Override
  public ImageEditor operationOnImage(ImageEditor imageObject) {
    List<List<ImagePixel>> imagePixels = new ArrayList<>();
    imagePixels.addAll(imageObject.getImagePixels());
    int maxRows = imageObject.getHeight();
    int maxCols = imageObject.getWidth();
    for (int row = 0; row < maxRows; row++) {
      List rowSharpedPix = new ArrayList<ImagePixel>();
      for (int column = 0; column < maxCols; column++) {
        rowSharpedPix.add(this.getTransformedPixel(imagePixels.get(row).get(column)));
      }
      imagePixels.set(row, rowSharpedPix);
    }

    return new ImageObjectV2(imagePixels, maxCols,
        maxRows,
        imageObject.getMaxPixel());
  }

  private int multipliedPixel(ImagePixel pix, List<Double> equation) {
    int res = (int) ((pix.getRed() * equation.get(0))
        + (pix.getGreen() * equation.get(1))
        + (pix.getBlue() * equation.get(2)));
    if (res < 0) {
      return 0;
    } else {
      return Math.min(res, 255);
    }
  }

  private ImagePixel getTransformedPixel(ImagePixel pix) {
    return new Pixel(
        this.multipliedPixel(pix, this.transformMatrix.get(0)),
        this.multipliedPixel(pix, this.transformMatrix.get(1)),
        this.multipliedPixel(pix, this.transformMatrix.get(2))
    );
  }
}
