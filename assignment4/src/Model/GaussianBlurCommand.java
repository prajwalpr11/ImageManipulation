package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Blur operations alters the pixel values of the image by referencing the gaussian blur matrix and
 * applying the kernels on individual pixel by placing the pixel at the center of the kernel.
 */
public class GaussianBlurCommand implements ImageModelCommand {

  List<List<Double>> gaussianBlurMatrix = new ArrayList<>();

  /**
   * Constructs the gaussian blur matrix.
   */
  public GaussianBlurCommand() {
    this.constructBlurMatrix();
  }

  private void constructBlurMatrix() {
    gaussianBlurMatrix.clear();
    gaussianBlurMatrix.add(new ArrayList(Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16)));
    gaussianBlurMatrix.add(new ArrayList(Arrays.asList(1.0 / 8, 1.0 / 4, 1.0 / 8)));
    gaussianBlurMatrix.add(new ArrayList(Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16)));
  }

  @Override
  public ImageEditor operationOnImage(ImageEditor imageObject) {
    // Transform image here.
    List<List<ImagePixel>> imgPixels = new ArrayList<>();
    List<List<ImagePixel>> blurredPixels = new ArrayList<>();
    imgPixels.addAll(imageObject.getImagePixels());
    int maxRows = imageObject.getHeight();
    int maxCols = imageObject.getWidth();
    for (int row = 0; row < maxRows; row++) {
      List rowBlurredPix = new ArrayList<ImagePixel>();
      for (int column = 0; column < maxCols; column++) {
        ImagePixel pix = imgPixels.get(row).get(column);
        rowBlurredPix.add(column, this.blurPixel(row, column, imgPixels, maxRows - 1,
            maxCols - 1));
      }
      blurredPixels.add(row, rowBlurredPix);
    }
    return new ImageObjectV2(blurredPixels, maxCols,
        maxRows,
        imageObject.getMaxPixel());
  }

  private ImagePixel blurPixel(int row, int column, List<List<ImagePixel>> imgPixels,
      int maxRowIndex,
      int maxColIndex) {
    double sigmaBlurRedComp = 0;
    double sigmaBlurGreenComp = 0;
    double sigmaBlurBlueComp = 0;
    int blurMatrixRowIndex = 0;
    int blurMatrixColIndex = 0;
    int rowIndex = row - 1;
    int colIndex;
    while (rowIndex <= row + 1) {
      colIndex = column - 1;
      blurMatrixColIndex = 0;
      while (colIndex <= column + 1) {
        if (this.validCell(rowIndex, colIndex, maxRowIndex, maxColIndex)) {
          sigmaBlurRedComp = sigmaBlurRedComp
              + (this.gaussianBlurMatrix.get(blurMatrixRowIndex).get(blurMatrixColIndex))
              * (imgPixels.get(rowIndex).get(colIndex).getRed());
          sigmaBlurGreenComp = sigmaBlurGreenComp
              + (this.gaussianBlurMatrix.get(blurMatrixRowIndex).get(blurMatrixColIndex))
              * (imgPixels.get(rowIndex).get(colIndex).getGreen());
          sigmaBlurBlueComp = sigmaBlurBlueComp
              + (this.gaussianBlurMatrix.get(blurMatrixRowIndex).get(blurMatrixColIndex))
              * (imgPixels.get(rowIndex).get(colIndex).getBlue());
        }
        colIndex += 1;
        blurMatrixColIndex += 1;
      }
      rowIndex += 1;
      blurMatrixRowIndex += 1;
    }
    return new Pixel(this.getValidRGBVal(sigmaBlurRedComp), this.getValidRGBVal(sigmaBlurGreenComp),
        this.getValidRGBVal(sigmaBlurBlueComp));
  }

  private int getValidRGBVal(double sigmaVal) {
    if (sigmaVal < 0) {
      return 0;
    } else if (sigmaVal > 255) {
      return 255;
    } else {
      return (int) sigmaVal;
    }
  }

  private boolean validCell(int rowIndex, int colIndex, int maxRowIndex, int maxColIndex) {
    return rowIndex >= 0 && colIndex >= 0 && rowIndex <= maxRowIndex && colIndex <= maxColIndex;
  }
}
