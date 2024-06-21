package model;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.EditorControllerImplementor.PPMImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.ImageObject.ImageObjectBuilder;
import org.junit.Test;

/**
 * This class represents all test cases for the Model ImageObject Class.
 */
public class ImageObjectTest {


  @Test
  public void testHorizontalFlip() throws IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();

    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor referHorizontalImage = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/koala-horizontal.ppm"));
    ImageEditor horizontalFlippedImage = image.horizontalFlip();
    assertTrue(horizontalFlippedImage.equals(referHorizontalImage));
  }

  @Test
  public void testVerticalFlip() throws IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor referenceVerticalImage = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage(
            "assignment4/images/koala-vertical.ppm"));
    ImageEditor verticalFlippedImage = image.verticalFlip();
    assertTrue(referenceVerticalImage.equals(verticalFlippedImage));
  }

  @Test
  public void testVerticalHorizontalAndHorizontalVerticalFlip() throws IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));

    ImageEditor image1 = image.verticalFlip().horizontalFlip();
    ImageEditor image2 = image.horizontalFlip().verticalFlip();
    assertTrue(image1.equals(image2));
  }

  @Test
  public void testImageBrighten() throws IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    int brightenBy = 20;
    ImageEditor brightenedImage = image.brighten(brightenBy);
    int maxPixValue = image.getMaxPixel();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(Math.min(image.getImagePixels().get(i).get(j).getRed() + brightenBy,
                maxPixValue),
            brightenedImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(Math.min(image.getImagePixels().get(i).get(j).getBlue() + brightenBy,
                maxPixValue),
            brightenedImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(Math.min(image.getImagePixels().get(i).get(j).getGreen() + brightenBy,
                maxPixValue),
            brightenedImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testImageDarken() throws IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    int brightenBy = -220;
    ImageEditor brightenedImage = image.brighten(brightenBy);
    int minPixValue = 0;
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(Math.max(image.getImagePixels().get(i).get(j).getRed() + brightenBy,
                minPixValue),
            brightenedImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(Math.max(image.getImagePixels().get(i).get(j).getBlue() + brightenBy,
                minPixValue),
            brightenedImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(Math.max(image.getImagePixels().get(i).get(j).getGreen() + brightenBy,
                minPixValue),
            brightenedImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testImageSplit() throws IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    Map<String, ImageEditor> splitImageMap = image.rgbSplit();
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(splitImageMap.get("red").getImagePixels().get(i).get(j).getRed(),
            image.getImagePixels().get(i).get(j).getRed());
        assertEquals(splitImageMap.get("red").getImagePixels().get(i).get(j).getBlue(),
            image.getImagePixels().get(i).get(j).getRed());
        assertEquals(splitImageMap.get("red").getImagePixels().get(i).get(j).getGreen(),
            image.getImagePixels().get(i).get(j).getRed());
        assertEquals(splitImageMap.get("blue").getImagePixels().get(i).get(j).getRed(),
            image.getImagePixels().get(i).get(j).getBlue());
        assertEquals(splitImageMap.get("blue").getImagePixels().get(i).get(j).getBlue(),
            image.getImagePixels().get(i).get(j).getBlue());
        assertEquals(splitImageMap.get("blue").getImagePixels().get(i).get(j).getGreen(),
            image.getImagePixels().get(i).get(j).getBlue());
        assertEquals(splitImageMap.get("green").getImagePixels().get(i).get(j).getRed(),
            image.getImagePixels().get(i).get(j).getGreen());
        assertEquals(splitImageMap.get("green").getImagePixels().get(i).get(j).getBlue(),
            image.getImagePixels().get(i).get(j).getGreen());
        assertEquals(splitImageMap.get("green").getImagePixels().get(i).get(j).getGreen(),
            image.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testImageCombine() throws FileNotFoundException, IOException, IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    Map<String, ImageEditor> splitImageMap = image.rgbSplit();
    ImageEditor combineImage = splitImageMap.get("red").rgbCombine(splitImageMap.get("green"),
        splitImageMap.get("blue"));
    assertTrue(image.equals(combineImage));
  }

  @Test
  public void testRedGreyScaleImage() throws FileNotFoundException, IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor greyScaleImage = image.greyScale("red-component");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(image.getImagePixels().get(i).get(j).getRed(),
            greyScaleImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(image.getImagePixels().get(i).get(j).getRed(),
            greyScaleImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(image.getImagePixels().get(i).get(j).getRed(),
            greyScaleImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testBlueGreyScaleImage() throws FileNotFoundException, IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor greyScaleImage = image.greyScale("blue-component");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(image.getImagePixels().get(i).get(j).getBlue(),
            greyScaleImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(image.getImagePixels().get(i).get(j).getBlue(),
            greyScaleImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(image.getImagePixels().get(i).get(j).getBlue(),
            greyScaleImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testGreenGreyScaleImage() throws FileNotFoundException, IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor greyScaleImage = image.greyScale("green-component");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(image.getImagePixels().get(i).get(j).getGreen(),
            greyScaleImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(image.getImagePixels().get(i).get(j).getGreen(),
            greyScaleImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(image.getImagePixels().get(i).get(j).getGreen(),
            greyScaleImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testValueGreyScaleImage() throws FileNotFoundException, IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor greyScaleImage = image.greyScale("value-component");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(image.getImagePixels().get(i).get(j).getVal(),
            greyScaleImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(image.getImagePixels().get(i).get(j).getVal(),
            greyScaleImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(image.getImagePixels().get(i).get(j).getVal(),
            greyScaleImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testIntensityGreyScaleImage() throws FileNotFoundException, IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor greyScaleImage = image.greyScale("intensity-component");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(image.getImagePixels().get(i).get(j).getIntensity(),
            greyScaleImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(image.getImagePixels().get(i).get(j).getIntensity(),
            greyScaleImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(image.getImagePixels().get(i).get(j).getIntensity(),
            greyScaleImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }

  @Test
  public void testLumaGreyScaleImage() throws FileNotFoundException, IOException {
    ImageObjectBuilder im = new ImageObjectBuilder();
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor greyScaleImage = image.greyScale("luma-component");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(image.getImagePixels().get(i).get(j).getLuma(),
            greyScaleImage.getImagePixels().get(i).get(j).getRed());
        assertEquals(image.getImagePixels().get(i).get(j).getLuma(),
            greyScaleImage.getImagePixels().get(i).get(j).getBlue());
        assertEquals(image.getImagePixels().get(i).get(j).getLuma(),
            greyScaleImage.getImagePixels().get(i).get(j).getGreen());
      }
    }
  }


  @Test
  public void testBlurModel() throws IOException {
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor imageBlurred = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    imageBlurred = ((ImageObjectV2) imageBlurred).runOperation(new GaussianBlurCommand());
    for (int row = 0; row < image.getHeight(); row++) {
      for (int column = 0; column < image.getWidth(); column++) {
        assertEquals(imageBlurred.getImagePixels().get(row).get(column), BlurOperation.blurPixel(
            row, column, image.getImagePixels(),
            image.getHeight() - 1, image.getWidth() - 1));
      }
    }
  }

  private static class BlurOperation {


    private static ImagePixel blurPixel(int row, int column, List<List<ImagePixel>> imgPixels,
        int maxRowIndex,
        int maxColIndex) {
      double sigmaBlurRedComp = 0;
      double sigmaBlurGreenComp = 0;
      double sigmaBlurBlueComp = 0;
      int blurMatrixRowIndex = 0;
      int blurMatrixColIndex = 0;
      int rowIndex = row - 1;
      int colIndex;
      List<List<Double>> gaussianBlurMatrix = new ArrayList<>();
      gaussianBlurMatrix.clear();
      gaussianBlurMatrix.add(new ArrayList(Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16)));
      gaussianBlurMatrix.add(new ArrayList(Arrays.asList(1.0 / 8, 1.0 / 4, 1.0 / 8)));
      gaussianBlurMatrix.add(new ArrayList(Arrays.asList(1.0 / 16, 1.0 / 8, 1.0 / 16)));
      while (rowIndex <= row + 1) {
        colIndex = column - 1;
        blurMatrixColIndex = 0;
        while (colIndex <= column + 1) {
          if (BlurOperation.validCell(rowIndex, colIndex, maxRowIndex, maxColIndex)) {
            sigmaBlurRedComp = sigmaBlurRedComp
                + (gaussianBlurMatrix.get(blurMatrixRowIndex).get(blurMatrixColIndex))
                * (imgPixels.get(rowIndex).get(colIndex).getRed());
            sigmaBlurGreenComp = sigmaBlurGreenComp
                + (gaussianBlurMatrix.get(blurMatrixRowIndex).get(blurMatrixColIndex))
                * (imgPixels.get(rowIndex).get(colIndex).getGreen());
            sigmaBlurBlueComp = sigmaBlurBlueComp
                + (gaussianBlurMatrix.get(blurMatrixRowIndex).get(blurMatrixColIndex))
                * (imgPixels.get(rowIndex).get(colIndex).getBlue());
          }
          colIndex += 1;
          blurMatrixColIndex += 1;
        }
        rowIndex += 1;
        blurMatrixRowIndex += 1;
      }
      return new Pixel(BlurOperation.getValidRGBVal(sigmaBlurRedComp),
          BlurOperation.getValidRGBVal(sigmaBlurGreenComp),
          BlurOperation.getValidRGBVal(sigmaBlurBlueComp));
    }

    private static int getValidRGBVal(double sigmaVal) {
      if (sigmaVal < 0) {
        return 0;
      } else if (sigmaVal > 255) {
        return 255;
      } else {
        return (int) sigmaVal;
      }
    }

    private static boolean validCell(int rowIndex, int colIndex, int maxRowIndex, int maxColIndex) {
      return rowIndex >= 0 && colIndex >= 0 && rowIndex <= maxRowIndex && colIndex <= maxColIndex;
    }
  }

  @Test
  public void testSharpenModel() throws IOException {
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor imageSharpen = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    imageSharpen = ((ImageObjectV2) imageSharpen).runOperation(new SharpenCommand());
    for (int row = 0; row < image.getHeight(); row++) {
      for (int column = 0; column < image.getWidth(); column++) {
        assertEquals(imageSharpen.getImagePixels().get(row).get(column),
            SharpenOperation.sharpenPixel(row, column, image.getImagePixels(),
                image.getHeight() - 1, image.getWidth() - 1));
      }
    }
  }

  private static class SharpenOperation {

    private static ImagePixel sharpenPixel(int row, int column, List<List<ImagePixel>> imgPixels,
        int maxRowIndex,
        int maxColIndex) {
      double sigmaSharpRedComp = 0.0;
      double sigmaSharpGreenComp = 0.0;
      double sigmaSharpBlueComp = 0.0;
      List<List<Double>> sharpenMatrix = new ArrayList<>();
      sharpenMatrix.clear();
      sharpenMatrix.add(
          new ArrayList(Arrays.asList(-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8)));
      sharpenMatrix.add(
          new ArrayList(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)));
      sharpenMatrix.add(new ArrayList(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8)));
      sharpenMatrix.add(
          new ArrayList(Arrays.asList(-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8)));
      sharpenMatrix.add(
          new ArrayList(Arrays.asList(-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8)));
      int sharpMatrixRowIndex = 0;
      int sharpMatrixColIndex = 0;
      int rowIndex = row - 2;
      int colIndex;
      while (rowIndex <= row + 2) {
        colIndex = column - 2;
        sharpMatrixColIndex = 0;
        while (colIndex <= column + 2) {
          if (validCell(rowIndex, colIndex, maxRowIndex, maxColIndex)) {
            sigmaSharpRedComp = sigmaSharpRedComp
                + (sharpenMatrix.get(sharpMatrixRowIndex).get(sharpMatrixColIndex))
                * ((double) (imgPixels.get(rowIndex).get(colIndex).getRed()));
            sigmaSharpGreenComp = sigmaSharpGreenComp
                + (sharpenMatrix.get(sharpMatrixRowIndex).get(sharpMatrixColIndex))
                * ((double) (imgPixels.get(rowIndex).get(colIndex).getGreen()));
            sigmaSharpBlueComp = sigmaSharpBlueComp
                + (sharpenMatrix.get(sharpMatrixRowIndex).get(sharpMatrixColIndex))
                * ((double) (imgPixels.get(rowIndex).get(colIndex).getBlue()));
          }
          colIndex += 1;
          sharpMatrixColIndex += 1;
        }
        rowIndex += 1;
        sharpMatrixRowIndex += 1;
      }
      return new Pixel(getValidRGBVal(sigmaSharpRedComp),
          getValidRGBVal(sigmaSharpGreenComp),
          getValidRGBVal(sigmaSharpBlueComp));
    }

    private static int getValidRGBVal(double sigmaVal) {
      if (sigmaVal < 0) {
        return 0;
      } else if (sigmaVal > 255) {
        return 255;
      } else {
        return (int) sigmaVal;
      }
    }

    private static boolean validCell(int rowIndex, int colIndex, int maxRowIndex, int maxColIndex) {
      return rowIndex >= 0 && colIndex >= 0 && rowIndex <= maxRowIndex && colIndex <= maxColIndex;
    }
  }

  @Test
  public void testGreyScaleNewTransformModel() throws IOException {
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor imageGreyScaleNew = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    imageGreyScaleNew = ((ImageObjectV2) imageGreyScaleNew).runOperation(
        new ColorTransformCommand("greyscale"));
    for (int row = 0; row < image.getHeight(); row++) {
      for (int column = 0; column < image.getWidth(); column++) {
        assertEquals(imageGreyScaleNew.getImagePixels().get(row).get(column),
            GreyScaleImageOperation.getGreyScaleTransformedPixel(image.getImagePixels()
                .get(row).get(column)));
      }
    }
  }

  @Test
  public void testSepiaTransformModel() throws IOException {
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor imageSepia = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    imageSepia = ((ImageObjectV2) imageSepia).runOperation(
        new ColorTransformCommand("sepia"));
    for (int row = 0; row < image.getHeight(); row++) {
      for (int column = 0; column < image.getWidth(); column++) {
        assertEquals(imageSepia.getImagePixels().get(row).get(column),
            GreyScaleImageOperation.getSepiaTransformedPixel(image.getImagePixels()
                .get(row).get(column)));
      }
    }
  }

  private static class GreyScaleImageOperation {

    private static int multipliedPixel(ImagePixel pix, List<Double> equation) {
      return (int) (pix.getRed() * equation.get(0))
          + (int) (pix.getGreen() * equation.get(1))
          + (int) (pix.getBlue() * equation.get(2));
    }

    private static List<List<Double>> getGreyscaleMatrix() {
      List<List<Double>> mt = new ArrayList<>();
      mt.add(new ArrayList(Arrays.asList(0.2126, 0.7152, 0.0722)));
      mt.add(new ArrayList(Arrays.asList(0.2126, 0.7152, 0.0722)));
      mt.add(new ArrayList(Arrays.asList(0.2126, 0.7152, 0.0722)));
      return mt;
    }

    private static List<List<Double>> getSepiaMatrix() {
      List<List<Double>> mt = new ArrayList<>();

      mt.add(new ArrayList(Arrays.asList(0.393, 0.769, 0.189)));
      mt.add(new ArrayList(Arrays.asList(0.349, 0.686, 0.168)));
      mt.add(new ArrayList(Arrays.asList(0.272, 0.534, 0.131)));
      return mt;
    }

    private static ImagePixel getGreyScaleTransformedPixel(ImagePixel pix) {
      List<List<Double>> transformMatrix = getGreyscaleMatrix();
      return new Pixel(
          multipliedPixel(pix, transformMatrix.get(0)),
          multipliedPixel(pix, transformMatrix.get(1)),
          multipliedPixel(pix, transformMatrix.get(2))
      );
    }

    private static ImagePixel getSepiaTransformedPixel(ImagePixel pix) {
      List<List<Double>> transformMatrix = getSepiaMatrix();
      return new Pixel(
          multipliedPixel(pix, transformMatrix.get(0)),
          multipliedPixel(pix, transformMatrix.get(1)),
          multipliedPixel(pix, transformMatrix.get(2))
      );
    }
  }


  @Test
  public void testDitherImageModel() throws IOException {
    ImageEditor image = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    ImageEditor imageDither = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/Koala.ppm"));
    imageDither = ((ImageObjectV2) imageDither).runOperation(new
        DitherImageCommand());
    image = DitherOperation.getDitheredImage(image);
    for (int row = 0; row < image.getHeight(); row++) {
      for (int column = 0; column < image.getWidth(); column++) {
        assertEquals(imageDither.getImagePixels().get(row).get(column), image.getImagePixels()
            .get(row).get(column));
      }
    }
  }

  private static class DitherOperation {

    public static ImageEditor getDitheredImage(ImageEditor image) {
      List<List<ImagePixel>> pixelMap = new ArrayList<>();
      pixelMap.addAll(image.getImagePixels());
      for (int column = 0; column < image.getHeight(); column++) {
        List<ImagePixel> rowList = new ArrayList<>();
        rowList.addAll(pixelMap.get(column));
        for (int row = 0; row < image.getWidth(); row++) {
          ImagePixel pixel = rowList.get(row);
          double oldColor = pixel.getLuma();
          double newColor = Math.round(oldColor / 255.0) * 255;
          double error = oldColor - newColor;
          pixel = new Pixel((int) newColor, (int) newColor, (int) newColor);
          rowList.set(row, pixel);
          setAdjacentPixelValues(row + 1, column, error * (7.0 / 16), pixelMap);
          setAdjacentPixelValues(row - 1, column + 1, error * (3.0 / 16), pixelMap);
          setAdjacentPixelValues(row, column + 1, error * (5.0 / 16), pixelMap);
          setAdjacentPixelValues(row + 1, column + 1, error * (1.0 / 16), pixelMap);
        }
        pixelMap.set(column, rowList);
      }
      return new ImageObjectV2(pixelMap, image.getWidth(), image.getHeight(), image.getMaxPixel());
    }

    static void setAdjacentPixelValues(int row, int col, double error,
        List<List<ImagePixel>> pixelMap) {
      if (row >= 0 && col >= 0 && row < pixelMap.get(0).size() && col < pixelMap.size()) {
        ImagePixel pixel = pixelMap.get(col).get(row);
        double newColor = pixel.getLuma() + error;
        if (newColor < 0) {
          newColor = 0;
        }
        if (newColor > 255) {
          newColor = 255;
        }
        List<ImagePixel> rowList = new ArrayList<>();
        rowList.addAll(pixelMap.get(col));
        pixel = new Pixel((int) newColor, (int) newColor, (int) newColor);
        rowList.set(row, pixel);
        pixelMap.set(col, rowList);
      }
    }
  }

}