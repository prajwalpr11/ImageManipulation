package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Sharpen operation alters the pixel values of the image by referencing to sharpen matrix and
 * applying the kernels on individual pixel by placing the pixel at the center of the kernel.
 */
public class SharpenCommand implements ImageModelCommand {

  List<List<Double>> sharpenMatrix = new ArrayList<>();


  /**
   * Constructs sharpen matrix.
   */
  public SharpenCommand() {
    this.constructSharpenMatrix();
  }


  private void constructSharpenMatrix() {
    sharpenMatrix.clear();
    sharpenMatrix.add(
        new ArrayList(Arrays.asList(-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8)));
    sharpenMatrix.add(new ArrayList(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)));
    sharpenMatrix.add(new ArrayList(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8)));
    sharpenMatrix.add(new ArrayList(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)));
    sharpenMatrix.add(
        new ArrayList(Arrays.asList(-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8)));

  }

  @Override

  public ImageEditor operationOnImage(ImageEditor imageObject) {
    // Transform image here.
    List<List<ImagePixel>> imagePixels = new ArrayList<>();
    List<List<ImagePixel>> shapenedPixels = new ArrayList<>();
    imagePixels.addAll(imageObject.getImagePixels());
    int maxRows = imageObject.getHeight();
    int maxCols = imageObject.getWidth();
    for (int row = 0; row < maxRows; row++) {
      List rowSharpedPix = new ArrayList<ImagePixel>();
      for (int column = 0; column < maxCols; column++) {
        ImagePixel pix = imagePixels.get(row).get(column);
        rowSharpedPix.add(column, this.sharpenPixel(row, column, imagePixels, maxRows - 1,
            maxCols - 1));
      }
      shapenedPixels.add(row, rowSharpedPix);
    }

    return new ImageObjectV2(shapenedPixels, maxCols,
        maxRows,
        imageObject.getMaxPixel());
  }

  private ImagePixel sharpenPixel(int row, int column, List<List<ImagePixel>> imgPixels,
      int maxRowIndex,
      int maxColIndex) {
    double sigmaSharpRedComp = 0.0;
    double sigmaSharpGreenComp = 0.0;
    double sigmaSharpBlueComp = 0.0;
    int sharpMatrixRowIndex = 0;
    int sharpMatrixColIndex = 0;
    int rowIndex = row - 2;
    int colIndex;
    while (rowIndex <= row + 2) {
      colIndex = column - 2;
      sharpMatrixColIndex = 0;
      while (colIndex <= column + 2) {
        if (this.validCell(rowIndex, colIndex, maxRowIndex, maxColIndex)) {
          sigmaSharpRedComp = sigmaSharpRedComp
              + (this.sharpenMatrix.get(sharpMatrixRowIndex).get(sharpMatrixColIndex))
              * ((double) (imgPixels.get(rowIndex).get(colIndex).getRed()));
          sigmaSharpGreenComp = sigmaSharpGreenComp
              + (this.sharpenMatrix.get(sharpMatrixRowIndex).get(sharpMatrixColIndex))
              * ((double) (imgPixels.get(rowIndex).get(colIndex).getGreen()));
          sigmaSharpBlueComp = sigmaSharpBlueComp
              + (this.sharpenMatrix.get(sharpMatrixRowIndex).get(sharpMatrixColIndex))
              * ((double) (imgPixels.get(rowIndex).get(colIndex).getBlue()));
        }
        colIndex += 1;
        sharpMatrixColIndex += 1;
      }
      rowIndex += 1;
      sharpMatrixRowIndex += 1;
    }
    return new Pixel(this.getValidRGBVal(sigmaSharpRedComp),
        this.getValidRGBVal(sigmaSharpGreenComp),
        this.getValidRGBVal(sigmaSharpBlueComp));
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
