package controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import model.ImageStore;
import model.ImageStoreImplementor;
import org.junit.Test;
import view.View;
import view.ViewInterface;

/**
 * This class represents all test cases.
 */
public class EditorControllerImplementorTest {


  /**
   * Test case to check if the file has been loaded successfully or not.
   *
   * @throws FileNotFoundException If the file is null or the extension is not PPM.
   */
  @Test
  public void brightenImageTest1() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();
    String testCommand = "";
    EditorController controller = new EditorControllerImplementor(model, (Readable) System.in,
        view);
    System.out.println(System.getProperty("user.dir"));
    controller.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
    controller.readAndExecuteCommand(
        "brighten 50 koala koala-brighten");
    controller.readAndExecuteCommand(
        "save assignment4/output-images/koala-brighten.ppm koala-brighten");

    assertArrayEquals(
        Files.readAllBytes(Path.of("assignment4/output-images/koala-brighten.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-brighter-by-50.ppm")));
  }

  @Test
  public void loadCommandFaulty() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController controller = new EditorControllerImplementor(model, (Readable) System.in,
        view);
    controller.readAndExecuteCommand(
        "load assignment4 koala");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Illegal image file is trying to read.\n");
    controller.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
  }

  @Test
  public void loadInvalidFileFaulty() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand(
        "load assignment4.ppm koala");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "assignment4.ppm (No such file or directory)");
  }

  @Test
  public void loadInvalidFileFaulty1() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand(
          "load assignment4.ppm koala");
      assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
          "Illegal image file is trying to read.\n");
    });
  }


  @Test
  public void testGreyScaleRedComponent() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("greyscale red-component koala koala-red");
    ctrl.readAndExecuteCommand("save assignment4/output-images/koala-red.ppm koala-red");
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/koala-red.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-red-greyscale.ppm")));
  }


  @Test
  public void testGreyScaleGreenComponent() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("greyscale green-component koala koala-green");
    ctrl.readAndExecuteCommand("save assignment4/output-images/koala-green.ppm koala-green");
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/koala-green.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-green-greyscale.ppm")));
  }


  @Test
  public void testGreyScaleBlueComponent() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("greyscale blue-component koala koala-blue");
    ctrl.readAndExecuteCommand("save assignment4/output-images/koala-blue.ppm koala-blue");
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/koala-blue.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-blue-greyscale.ppm")));
  }


  @Test
  public void testGreyScaleValueComponent() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("greyscale value-component koala koala-value");
    ctrl.readAndExecuteCommand("save assignment4/output-images/koala-value.ppm koala-value");
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/koala-value.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-value-greyscale.ppm")));
  }

  @Test
  public void testGreyScaleLumaComponent() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("greyscale luma-component koala koala-luma");
    ctrl.readAndExecuteCommand("save assignment4/output-images/koala-luma.ppm koala-luma");
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/koala-luma.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-luma-greyscale.ppm")));
  }


  @Test
  public void testGreyScaleIntensityComponent() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("greyscale intensity-component koala koala-intensity");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-intensity.ppm koala-intensity");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-intensity.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-intensity-greyscale.ppm")));
  }


  @Test
  public void testingLargeDimensionImageFile() {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Extremely large file height of image.");
  }


  @Test
  public void testVerticalFlip() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("vertical-flip koala koala-vertical");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-vertical-sample.ppm koala-vertical");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-vertical-sample.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-vertical.ppm")));
  }


  @Test
  public void testVerticalDoubleFlip() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("vertical-flip koala koala-vertical");
    ctrl.readAndExecuteCommand("vertical-flip koala-vertical koala-vertical");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-vertical-double-flip.ppm koala-vertical");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-vertical-double-flip.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
  }


  @Test
  public void testHorizontalFlip() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("horizontal-flip koala koala-horizontal");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-horizontal-sample.ppm koala-horizontal");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-horizontal-sample.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-horizontal.ppm")));
  }


  @Test
  public void testHorizontalDoubleFlip() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("horizontal-flip koala koala-horizontal");
    ctrl.readAndExecuteCommand("horizontal-flip koala-horizontal koala-horizontal");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-horizontal-double-flip.ppm koala-horizontal"
            + "");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-horizontal-double-flip.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
  }


  @Test
  public void testHorizontalVerticalDoubleFlip() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("horizontal-flip koala koala-horizontal-1");
    ctrl.readAndExecuteCommand("vertical-flip koala-horizontal-1 koala-hv-1");
    ctrl.readAndExecuteCommand("vertical-flip koala koala-horizontal-2");
    ctrl.readAndExecuteCommand("horizontal-flip koala-horizontal-2 koala-hv-2");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-hv-1.ppm koala-hv-1"
            + "");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-hv-2.ppm koala-hv-2"
            + "");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-hv-1.ppm")),
        Files.readAllBytes(Path.of("assignment4/output-images/koala-hv-2.ppm")));
  }


  @Test
  public void testBrighten() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("brighten 50 koala koala-brighter");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-brighter-50-sample.ppm koala-brighter");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-brighter-50-sample.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-brighter-by-50.ppm")));
  }


  @Test
  public void testBrightenByZero() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("brighten 0 koala koala-brighter");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-brighter-0-sample.ppm koala-brighter");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-brighter-0-sample.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
  }


  @Test
  public void testDarken() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("brighten -30 koala koala-dim");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-dim-30-sample.ppm koala-dim");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-dim-30-sample.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-brighter-30-test.ppm")));
  }

  @Test
  public void testBrightenNDarken() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("brighten -30 koala koala-dim1");
    ctrl.readAndExecuteCommand("brighten 30 koala-dim1 koala-dim");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-dim-bright-sample.ppm koala-dim");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-dim-bright-sample.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
  }


  @Test
  public void testBrightenByMaxValue() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("brighten 600 koala koala-max");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-brighter-max.ppm koala-max");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-brighter-max.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/white-color.ppm")));
  }


  @Test
  public void testDarkenByMaxValue() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("brighten -600 koala koala-min");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-dim-max.ppm koala-min");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-dim-max.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/black-color.ppm")));
  }


  @Test
  public void splitImageTest() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("rgb-split koala koala-red koala-green koala-blue");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-red-split-test.ppm koala-red");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-blue-split-test.ppm koala-blue");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-green-split-test.ppm koala-green");
    assertArrayEquals(Files.readAllBytes(Path.of(
            "assignment4/output-images/koala-red-split-test.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-red-greyscale.ppm")));
    assertArrayEquals(Files.readAllBytes(Path.of(
            "assignment4/output-images/koala-blue-split-test.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-blue-greyscale.ppm")));
    assertArrayEquals(Files.readAllBytes(Path.of(
            "assignment4/output-images/koala-green-split-test.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/koala-green-greyscale.ppm")));
  }

  @Test
  public void splitOneOfGreyScaleImageTest() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/koala-red-greyscale.ppm koala");
    ctrl.readAndExecuteCommand("rgb-split koala koala-red koala-green koala-blue");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-red-split-test.ppm koala-red");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-blue-split-test.ppm koala-blue");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-green-split-test.ppm koala-green");
    byte[] greyImage = Files.readAllBytes(
        Path.of("assignment4/images/koala-red-greyscale.ppm"));
    assertArrayEquals(Files.readAllBytes(Path.of(
        "assignment4/output-images/koala-red-split-test.ppm")), greyImage);
    assertArrayEquals(Files.readAllBytes(Path.of(
        "assignment4/output-images/koala-blue-split-test.ppm")), greyImage);
    assertArrayEquals(Files.readAllBytes(Path.of(
        "assignment4/output-images/koala-green-split-test.ppm")), greyImage);
  }


  @Test
  public void rgbCombine() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand(
        "load assignment4/images/koala-red-greyscale.ppm koala-red");
    ctrl.readAndExecuteCommand(
        "load assignment4/images/koala-green-greyscale.ppm koala-green");
    ctrl.readAndExecuteCommand(
        "load assignment4/images/koala-blue-greyscale.ppm koala-blue");
    ctrl.readAndExecuteCommand(
        "rgb-combine koala-combine-img koala-red koala-green koala-blue");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-combine-sample.ppm koala-combine-img");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-combine-sample.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
  }


  @Test
  public void multipleEditsToImage() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");

    ctrl.readAndExecuteCommand("vertical-flip koala koala-v");
    ctrl.readAndExecuteCommand("horizontal-flip koala-v koala-v-h");
    ctrl.readAndExecuteCommand("vertical-flip koala-v-h "
        + "koala-v-h-v");
    ctrl.readAndExecuteCommand("horizontal-flip koala-v-h-v "
        + "koala-v-h-v-h");
    ctrl.readAndExecuteCommand("greyscale red-component koala-v-h-v-h koala-red");
    ctrl.readAndExecuteCommand("greyscale green-component koala-red koala-green");
    ctrl.readAndExecuteCommand("greyscale blue-component koala-green koala-blue");
    ctrl.readAndExecuteCommand("greyscale luma-component koala-blue koala-luma");
    ctrl.readAndExecuteCommand("greyscale value-component koala-luma koala-value");
    ctrl.readAndExecuteCommand("greyscale intensity-component koala-value koala-intensity");
    ctrl.readAndExecuteCommand("rgb-split koala koala-r koala-g koala-b");
    ctrl.readAndExecuteCommand("rgb-combine koala-combine koala-r koala-g koala-b");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-combine.ppm koala-combine");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/output-images/koala-combine.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
  }


  @Test
  public void faultRgbCombine() throws IOException {
    assertThrows(IllegalArgumentException.class, () -> {

      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala-red");
      ctrl.readAndExecuteCommand(
          "load assignment4/images/koala-green-greyscale.ppm koala-green");
      ctrl.readAndExecuteCommand(
          "load assignment4/images/koala-blue-greyscale.ppm koala-blue");
      ctrl.readAndExecuteCommand(
          "rgb-combine koala-combine-img koala-red koala-green koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-combine-sample.ppm koala-combine-img");
      assertArrayEquals(Files.readAllBytes(
              Path.of("assignment4/output-images/koala-combine-sample.ppm")),
          Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
    });

  }


  @Test
  public void loadFaultyImage() throws IOException {
    assertThrows(IllegalArgumentException.class, () -> {

      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController controller = new EditorControllerImplementor(model, (Readable) System.in,
          view);
      controller.readAndExecuteCommand(
          "load assignment4/images/fault-image.ppm koala");
      assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
          "Invalid image object provided, please load\n");
    });
  }


  @Test
  public void loadFaultyNegativePixelImage() throws IOException {
    assertThrows(IllegalArgumentException.class, () -> {

      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController controller = new EditorControllerImplementor(model, (Readable) System.in,
          view);
      controller.readAndExecuteCommand(
          "load assignment4/images/fault-negative-image.ppm koala");
      assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
          "Invalid image object provided, please load\n");
    });
  }


  @Test
  public void loadingWithWrongScriptFile() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("run re/script.tt");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Illegal script file is trying to run.\n");
  }

  @Test
  public void loadingWithScriptFile() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("run assignment4/res/script.txt");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/res/script-images/koala-save.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
  }


  @Test
  public void loadingWithScriptFileAndExit() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("run assignment4/res/script.txt");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/res/script-images/koala-save.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
    ctrl.readAndExecuteCommand("load assignment/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("save assignment/output-images/Koala-exit.ppm koala");
    assertThrows(Exception.class, () -> {
      Files.readAllBytes(Path.of("assignment4/output-images/Koala-exit.ppm"));
    });


  }


  @Test
  public void loadingWithScriptFileFullFunctionalities() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("run assignment4/res/script.txt");
    assertArrayEquals(Files.readAllBytes(
            Path.of("assignment4/res/script-images/koala-save.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/Koala.ppm")));
    ctrl.readAndExecuteCommand("load assignment/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("save assignment/output-images/Koala-exit.ppm koala");
    assertThrows(Exception.class, () -> {
      Files.readAllBytes(Path.of("assignment4/output-images/Koala-exit.ppm"));
    });


  }

  @Test
  public void loadImageNoFilePath1() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load koala");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void loadImageNotFoundInPath2() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/coola.txt coola");
    });
  }

  @Test
  public void loadInvalidLoadCommand3() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("lood assignment4/images/Koala.ppm koala");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void loadImageWithNegativePixels4() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/negative.ppm koala");
    });
  }

  @Test
  public void loadImageWithNegativePixelsMessage5() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/negative.ppm koala");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Pixels cannot be negative\n");
  }

  //Number of pixel does not match with Image dimension
  @Test
  public void loadImageWithMissingPixels6() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/ImageMissingPixel.ppm koala");
    });
  }

  @Test
  public void loadImageWithMoreThanTwoParameter1() throws IOException {
    //    assertThrows(IOException.class, () -> {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/ImageMissingPixel.ppm koala Koala2");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
    //    });
  }

  /**
   * Semantic error saving object which is not created.
   *
   * @throws IOException if loading file throws an error.
   */
  @Test
  public void saveImageWithDifferentObject2() throws IOException {
    // assertThrows(IOException.class, () -> {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    System.out.println(System.getProperty("user.dir"));
    ctrl.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand(
        "brighten 50 koala koala-brighten");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-brighten.ppm coola-brighten");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Provided object is not loaded.");
    // });
  }


  @Test
  public void invalidSaveCommand4() throws IOException {
    //    assertThrows(IOException.class, () -> {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand(
        "brighten 50 koala koala-brighten");
    ctrl.readAndExecuteCommand(
        "saver assignment4/output/koala-brighten.ppm koala-brighten");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
    //    });
  }

  @Test
  public void brightenWithoutLoad5() throws IOException {
    //    assertThrows(IOException.class, () -> {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand(
        "brighten 50 koala koala-brighten");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Provided object is not loaded.");
    //    });
  }

  @Test
  public void wrongBrightenCommand6() throws IOException {
    // assertThrows(IOException.class, () -> {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    System.out.println(System.getProperty("user.dir"));
    ctrl.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand(
        "brightens 50 koala koala-brighten");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");

    ctrl.readAndExecuteCommand(
        "save assignment4/output/koala-brighten.ppm koala-brighten");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Provided object is not loaded.");
    // });
  }

  @Test
  public void brightenWrongImage1() throws IOException {
    //    assertThrows(IOException.class, () -> {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    System.out.println(System.getProperty("user.dir"));
    ctrl.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand(
        "brightens 50 koala-brighten koala-brighten");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");

    //    });
  }

  @Test
  public void brightenWithOutConstant2() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    System.out.println(System.getProperty("user.dir"));
    ctrl.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand(
        "brightens koala koala-brighten");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }


  @Test
  public void brightenWithDecimalConstant3() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    System.out.println(System.getProperty("user.dir"));
    ctrl.readAndExecuteCommand(
        "load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand(
        "brighten 20.5 koala koala");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void testWrongGreyScaleCommand4() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("grey value-component koala koala-value");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void testWithOnlyGreyScaleCommand5() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("greyscale");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void testGreyScaleBeforeLoadCommand6() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("greyscale red-component koala koala-value");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Provided object is not loaded.");
  }

  @Test
  public void testGreyScaleWrongOperationCommand1() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("greyscale blur-component koala koala-value");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void testGreyScaleNoTargetObject2() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("greyscale green-component koala");
    });
  }

  @Test
  public void testGreyScaleMoreThanThreeParameters3() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("greyscale red-component koala koala-value pool");
    });
  }

  @Test
  public void testVerticalFlipWithoutLoad4() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("vertical-flip koala-1 koala-vertical");
    assertEquals(view.getLoggedMessages().size(), 1);
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Provided object is not loaded.");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-vertical-sample.ppm koala-vertical");
    assertEquals(view.getLoggedMessages().size(), 2);

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Provided object is not loaded.");
  }

  @Test
  public void testWrongFlipCommand5() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("flip koala koala-vertical");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
    ctrl.readAndExecuteCommand(
        "save assignment4/output-images/koala-vertical-sample.ppm koala-vertical");
  }

  @Test
  public void testWithOnlyTheFlipCommand6() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
    ctrl.readAndExecuteCommand("horizontal-flip");
    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void splitImage1() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand("rgb-split koala koala-red koala-green koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-red-split-test.ppm koala-red");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-blue-split-test.ppm koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-green-split-test.ppm koala-green");
      assertArrayEquals(
          Files.readAllBytes(Path.of("assignment4/output-images/koala-red-split-test.ppm")),
          Files.readAllBytes(Path.of("assignment4/images/koala-red-greyscale.ppm")));
      assertArrayEquals(Files.readAllBytes(Path.of(
              "assignment4/output-images/koala-blue-split-test.ppm")),
          Files.readAllBytes(Path.of("assignment4/images/koala-blue-greyscale.ppm")));
      assertArrayEquals(Files.readAllBytes(Path.of(
              "assignment4/output-images/koala-green-split-test.ppm")),
          Files.readAllBytes(Path.of("assignment4/images/koala-green-greyscale.ppm")));
    });
  }

  @Test
  public void splitImageWithoutLoad2() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("rgb-split koala koala-red koala-green koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-red-split-test.ppm koala-red");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-blue-split-test.ppm koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-green-split-test.ppm koala-green");
    });
  }


  @Test
  public void wrongSplitCommands3() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand("split koala koala-red koala-green koala-blue");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void rgbCombineNonGreyScaleImage4() throws IOException {
    assertThrows(IllegalArgumentException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/koala.ppm koala-red");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-green-greyscale.ppm"
          + " koala-green");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-blue-greyscale.ppm"
          + " koala-blue");
      ctrl.readAndExecuteCommand("rgb-combine koala-combine-img koala-red koala-green"
          + " koala-blue");
    });
  }

  @Test
  public void rgbCombineGreyScaleImageWithDifferentDimensions5() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand(
          "load assignment4/images/koala-red-greyscaleDimensionChane.ppm koala-red");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-green-greyscale.ppm "
          + "koala-green");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-blue-greyscale.ppm "
          + "koala-blue");
      ctrl.readAndExecuteCommand("rgb-combine koala-combine-img koala-red koala-green "
          + "koala-blue");
    });
  }

  @Test
  public void rgbCombineWrongCommands6() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageStore model = new ImageStoreImplementor();

    EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
    ctrl.readAndExecuteCommand(
        "load assignment4/images/koala-red-greyscaleDimensionChane.ppm koala-red");
    ctrl.readAndExecuteCommand("load assignment4/images/koala-green-greyscale.ppm koala-green");
    ctrl.readAndExecuteCommand("load assignment4/images/koala-blue-greyscale.ppm koala-blue");
    ctrl.readAndExecuteCommand(
        "rgb-combining koala-combine-img koala-red koala-green koala-blue");

    assertEquals(view.getLoggedMessages().get(view.getLoggedMessages().size() - 1),
        "Invalid command, please re-enter\n");
  }

  @Test
  public void loadImageNoFilePath() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load koala");
    });
  }

  @Test
  public void loadImageNotFoundInPath() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/coola coola");
    });
  }

  @Test
  public void loadInvalidLoadCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("lood assignment4/images/Koala.ppm koala");
    });
  }

  @Test
  public void loadImageWithNegativePixels() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/NegativePixelsImage.ppm koala");
    });
  }

  //Number of pixel does not match with Image dimension
  @Test
  public void loadImageWithMissingPixels() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/ImageMissingPixel.ppm koala");
    });
  }

  @Test
  public void loadImageWithMoreThanTwoParameter() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/ImageMissingPixel.ppm koala Koala2");
    });
  }

  @Test
  public void saveImageWithDifferentObject() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      System.out.println(System.getProperty("user.dir"));
      ctrl.readAndExecuteCommand(
          "load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand(
          "brighten 50 koala koala-brighten");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-brighten.ppm coola-brighten");
    });
  }

  @Test
  public void SaveImagePathNotFound() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      System.out.println(System.getProperty("user.dir"));
      ctrl.readAndExecuteCommand(
          "load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand(
          "brighten 50 koala koala-brighten");
      ctrl.readAndExecuteCommand(
          "save assignment4/output/koala-brighten.ppm koala-brighten");
    });
  }

  @Test
  public void invalidSaveCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand(
          "load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand(
          "brighten 50 koala koala-brighten");
      ctrl.readAndExecuteCommand(
          "saver assignment4/output/koala-brighten.ppm koala-brighten");
    });
  }

  @Test
  public void brightenWithoutLoad() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      System.out.println(System.getProperty("user.dir"));
      ctrl.readAndExecuteCommand(
          "brighten 50 koala koala-brighten");
    });
  }

  @Test
  public void wrongBrightenCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      System.out.println(System.getProperty("user.dir"));
      ctrl.readAndExecuteCommand(
          "load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand(
          "brightens 50 koala koala-brighten");
      ctrl.readAndExecuteCommand(
          "save assignment4/output/koala-brighten.ppm koala-brighten");
    });
  }

  @Test
  public void brightenWrongImage() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      System.out.println(System.getProperty("user.dir"));
      ctrl.readAndExecuteCommand(
          "load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand(
          "brightens 50 koala-brighten koala-brighten");
    });
  }

  @Test
  public void brightenWithOutConstant() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      System.out.println(System.getProperty("user.dir"));
      ctrl.readAndExecuteCommand(
          "load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand(
          "brightens koala koala-brighten");
    });
  }


  @Test
  public void brightenWithDecimalConstant() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      System.out.println(System.getProperty("user.dir"));
      ctrl.readAndExecuteCommand(
          "load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand(
          "brightens 20.5 koala koala-brighten");
    });
  }

  @Test
  public void testWrongGreyScaleCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand("grey value-component koala koala-value");
    });
  }

  @Test
  public void testWithOnlyGreyScaleCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand("greyscale");
    });
  }

  @Test
  public void testGreyScaleBeforeLoadCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("greyscale red-component koala koala-value");
    });
  }

  @Test
  public void testGreyScaleWrongOperationCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("greyscale blur-component koala koala-value");
    });
  }

  @Test
  public void testGreyScaleNoTargetObject() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("greyscale green-component koala");
    });
  }

  @Test
  public void testGreyScaleMoreThanThreeParameters() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("greyscale red-component koala koala-value pool");
    });
  }

  @Test
  public void testVerticalFlipWithoutLoad() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand("vertical-flip koala koala-vertical");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-vertical-sample.ppm koala-vertical");
    });
  }

  @Test
  public void testWrongFlipCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand("flip koala koala-vertical");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-vertical-sample.ppm koala-vertical");
    });
  }

  @Test
  public void testWithOnlyTheFlipCommand() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand("horizontal-flip");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-vertical-sample.ppm koala-vertical");
    });
  }

  @Test
  public void splitImage() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/Koala.ppm koala");
      ctrl.readAndExecuteCommand("rgb-split koala koala-red koala-green koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-red-split-test.ppm koala-red");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-blue-split-test.ppm koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-green-split-test.ppm koala-green");
      assertArrayEquals(
          Files.readAllBytes(Path.of("assignment4/output-images/koala-red-split-test.ppm")),
          Files.readAllBytes(Path.of("assignment4/images/koala-red-greyscale.ppm")));
      assertArrayEquals(Files.readAllBytes(Path.of(
              "assignment4/output-images/koala-blue-split-test.ppm")),
          Files.readAllBytes(Path.of("assignment4/images/koala-blue-greyscale.ppm")));
      assertArrayEquals(Files.readAllBytes(Path.of(
              "assignment4/output-images/koala-green-split-test.ppm")),
          Files.readAllBytes(Path.of("assignment4/images/koala-green-greyscale.ppm")));
    });
  }

  @Test
  public void splitImageWithoutLoad() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("rgb-split koala koala-red koala-green koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-red-split-test.ppm koala-red");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-blue-split-test.ppm koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-green-split-test.ppm koala-green");
    });
  }


  @Test
  public void wrongSplitCommands() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("split koala koala-red koala-green koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-red-split-test.ppm koala-red");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-blue-split-test.ppm koala-blue");
      ctrl.readAndExecuteCommand(
          "save assignment4/output-images/koala-green-split-test.ppm koala-green");
    });
  }

  @Test
  public void rgbCombineNonGreyScaleImage() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand("load assignment4/images/koala.ppm koala-red");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-green-greyscale.ppm koala-green");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-blue-greyscale.ppm koala-blue");
      ctrl.readAndExecuteCommand("rgb-combine koala-combine-img koala-red koala-green koala-blue");
    });
  }

  @Test
  public void rgbCombineGreyScaleImageWithDifferentDimensions() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand(
          "load assignment4/images/koala-red-greyscaleDimensionChane.ppm koala-red");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-green-greyscale.ppm koala-green");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-blue-greyscale.ppm koala-blue");
      ctrl.readAndExecuteCommand("rgb-combine koala-combine-img koala-red koala-green koala-blue");
    });
  }

  @Test
  public void rgbCombineWrongCommands() throws IOException {
    assertThrows(IOException.class, () -> {
      OutputStream out = new ByteArrayOutputStream();
      ViewInterface view = new View(out);
      ImageStore model = new ImageStoreImplementor();

      EditorController ctrl = new EditorControllerImplementor(model, (Readable) System.in, view);
      ctrl.readAndExecuteCommand(
          "load assignment4/images/koala-red-greyscaleDimensionChane.ppm koala-red");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-green-greyscale.ppm koala-green");
      ctrl.readAndExecuteCommand("load assignment4/images/koala-blue-greyscale.ppm koala-blue");
      ctrl.readAndExecuteCommand(
          "rgb-combining koala-combine-img koala-red koala-green koala-blue");
    });
  }


}