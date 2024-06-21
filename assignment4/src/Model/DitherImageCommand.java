package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Diether operation transforms the ImageEditor object i.e individual channels with values of 0 or
 * 255. It is used to create black and white images. Used for printing applications.
 */
public class DitherImageCommand implements ImageModelCommand {

  @Override
  public ImageEditor operationOnImage(ImageEditor image) {
    List<List<ImagePixel>> pixelMap = new ArrayList<>();
    pixelMap.addAll(image.getImagePixels());
    for (int column = 0; column < image.getHeight(); column++) {
      for (int row = 0; row < image.getWidth(); row++) {
        ImagePixel pixel = pixelMap.get(column).get(row);
        double oldColor = pixel.getLuma();
        double newColor = oldColor < 128 ? 0 : image.getMaxPixel();
        double error = oldColor - newColor;
        if (row + 1 < image.getWidth()) {
          pixelMap.get(column).set(row + 1,
              setAdjacentPixelValues(row + 1, column, error * (0.4375), pixelMap));
        }
        if (row > 0 && column + 1 < image.getHeight()) {
          pixelMap.get(column + 1).set(row - 1,
              setAdjacentPixelValues(row - 1, column + 1, error * (0.1875), pixelMap));
        }
        if (column + 1 < image.getHeight()) {
          pixelMap.get(column + 1).set(row,
              setAdjacentPixelValues(row, column + 1, error * (0.3125), pixelMap));
        }
        if (row + 1 < image.getWidth() && column + 1 < image.getHeight()) {
          pixelMap.get(column + 1).set(row + 1,
              setAdjacentPixelValues(row + 1, column + 1, error * (0.0625), pixelMap));
        }
        pixel = new Pixel((int) newColor, (int) newColor, (int) newColor);
        pixelMap.get(column).set(row, pixel);
      }
    }
    return new ImageObjectV2(pixelMap, image.getWidth(), image.getHeight(), image.getMaxPixel());
  }


  private ImagePixel setAdjacentPixelValues(int row, int col, double error,
      List<List<ImagePixel>> pixelMap) {
    ImagePixel pixel = pixelMap.get(col).get(row);
    double newColor = pixel.getLuma() + error;
    if (newColor < 0) {
      newColor = 0;
    } else {
      newColor = Math.min(newColor, 255);
    }
    List<ImagePixel> rowList = new ArrayList<>();
    rowList.addAll(pixelMap.get(col));
    pixel = new Pixel((int) newColor, (int) newColor, (int) newColor);
    return pixel;
  }


}