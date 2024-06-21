package controller;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertArrayEquals;

import controller.EditorControllerImplementor.GeneralImageFormats;
import controller.EditorControllerImplementor.PPMImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import model.ColorTransformCommand;
import model.DitherImageCommand;
import model.GaussianBlurCommand;
import model.ImageEditor;
import model.ImageModelCommand;
import model.ImageObject;
import model.ImageObjectV2;
import model.ImageStore;
import model.ImageStoreImplementor;
import model.SharpenCommand;
import org.junit.Test;
import view.View;
import view.ViewInterface;

/**
 * EditorControllerFilterTransformTest test class tests the functionalities of editor application
 * over operations of blur, sharpen, dither, greyscale, sepia filters. Along with enabling previous
 * operations.
 */
public class EditorControllerFilterTransformTest {

  @Test
  public void pngLoadTest() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.png"));
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream((
        "load assignment4/images/apply/dog1.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.ppm blue-img"
            + "\n" +
            "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }

  @Test
  public void jpgLoadTest() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.jpg"));
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream((
        "load assignment4/images/apply/dog1.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.ppm blue-img"
            + "\n" +
            "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }

  @Test
  public void jpegLoadTest() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.jpeg"));
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream((
        "load assignment4/images/apply/dog1.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.ppm blue-img"
            + "\n" +
            "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }

  @Test
  public void bmpLoadTest() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.bmp"));
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream((
        "load assignment4/images/apply/dog1.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.ppm blue-img"
            + "\n" +
            "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }


  @Test
  public void testBlur() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/apply/dog1.ppm"));
    ImageModelCommand blur = new GaussianBlurCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.ppm koala"
            + "\n" +
            "blur koala blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.ppm blue-img"
            + "\n" +
            "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }

  @Test
  public void testSharpen() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/apply/dog1.ppm"));
    ImageModelCommand shp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(shp);
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.ppm koala"
            + "\n" +
            "sharpen koala blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.ppm blue-img"
            + "\n" +
            "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }


  @Test
  public void testSepiaTone() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/apply/dog1.ppm"));
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.ppm koala"
            + "\n" +
            "sepia koala blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.jpeg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.jpg blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.bmp blue-img"
            + "\n" +
            "save assignment4/output-images/apply/dog1-blur-save.ppm blue-img"
            + "\n" +
            "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }


  @Test
  public void testGreyScaleNewCmd() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/apply/dog1.ppm"));
    ImageModelCommand sepia = new ColorTransformCommand("greyscale");
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.ppm koala"
            + "\n"
            + "greyscale koala blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.jpeg blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.jpg blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.bmp blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.ppm blue-img"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }

  @Test
  public void testDitherCmd() throws IOException {
    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/apply/dog1.ppm"));
    ImageModelCommand dither = new DitherImageCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.ppm koala"
            + "\n"
            + "dither koala blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-save.png blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.jpeg blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.jpg blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.bmp blue-img"
            + "\n"
            + "save assignment4/output-images/apply/dog1-blur-save.ppm blue-img"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }


  @Test
  public void allNewOpsOnJPG() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.jpg"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);

    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.jpg koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }


  @Test
  public void allNewOpsOnPng() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.png"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);

    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.png koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }

  @Test
  public void allNewOpsOnJPEG() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.jpeg"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);

    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.jpeg koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }


  @Test
  public void allNewOpsOnbmp() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.bmp"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);

    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.bmp koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }

  @Test
  public void allNewOpsOnPpm() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/apply/dog1.ppm"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);

    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.ppm koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }


  @Test
  public void allNewOldOpsOnJPG() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.jpg"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);
    imageObject = imageObject.horizontalFlip();
    imageObject = imageObject.brighten(50);
    imageObject = imageObject.verticalFlip();

    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.jpg koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "horizontal-flip k5 k5"
            + "\n"
            + "brighten 50 k5 k5"
            + "\n"
            + "vertical-flip k5 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }


  @Test
  public void allNewOldOpsOnPng() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.png"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);
    imageObject = imageObject.horizontalFlip();
    imageObject = imageObject.brighten(50);
    imageObject = imageObject.verticalFlip();
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.png koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "horizontal-flip k5 k5"
            + "\n"
            + "brighten 50 k5 k5"
            + "\n"
            + "vertical-flip k5 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }

  @Test
  public void allNewOldOpsOnJPEG() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.jpeg"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);
    imageObject = imageObject.horizontalFlip();
    imageObject = imageObject.brighten(50);
    imageObject = imageObject.verticalFlip();
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.jpeg koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "horizontal-flip k5 k5"
            + "\n"
            + "brighten 50 k5 k5"
            + "\n"
            + "vertical-flip k5 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }


  @Test
  public void allNewOldOpsOnBmp() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/apply/dog1.bmp"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);
    imageObject = imageObject.horizontalFlip();
    imageObject = imageObject.brighten(50);
    imageObject = imageObject.verticalFlip();
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.bmp koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "horizontal-flip k5 k5"
            + "\n"
            + "brighten 50 k5 k5"
            + "\n"
            + "vertical-flip k5 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }

  @Test
  public void allNewOldOpsOnPpm() throws IOException {

    OutputStream out = new ByteArrayOutputStream();
    ViewInterface view = new View(out);
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/apply/dog1.ppm"));
    ImageModelCommand dither = new DitherImageCommand();
    ImageModelCommand sepia = new ColorTransformCommand("sepia");
    ImageModelCommand grey = new ColorTransformCommand("greyscale");
    ImageModelCommand blur = new GaussianBlurCommand();
    ImageModelCommand sharp = new SharpenCommand();
    imageObject = ((ImageObjectV2) imageObject).runOperation(dither);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sepia);
    imageObject = ((ImageObjectV2) imageObject).runOperation(grey);
    imageObject = ((ImageObjectV2) imageObject).runOperation(blur);
    imageObject = ((ImageObjectV2) imageObject).runOperation(sharp);
    imageObject = imageObject.horizontalFlip();
    imageObject = imageObject.brighten(50);
    imageObject = imageObject.verticalFlip();
    ImageStore model = new ImageStoreImplementor();
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(
        ("load assignment4/images/apply/dog1.ppm koala"
            + "\n"
            + "dither koala k1"
            + "\n"
            + "sepia k1 k2"
            + "\n"
            + "greyscale k2 k3"
            + "\n"
            + "blur k3 k4"
            + "\n"
            + "sharpen k4 k5"
            + "\n"
            + "horizontal-flip k5 k5"
            + "\n"
            + "brighten 50 k5 k5"
            + "\n"
            + "vertical-flip k5 k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.png k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpeg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.jpg k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.bmp k5"
            + "\n"
            + "save assignment4/output-images/apply/dog1-d-s-g-b-s.ppm k5"
            + "\n"
            + "exit").getBytes()));
    EditorController ctrl = new EditorControllerImplementor(model, rd, view);
    ctrl.startApplication();
    assertEquals(imageObject, model.getImageModel("k5"));
  }

  @Test
  public void testValidInputToModel3() throws IOException {
    ImageEditor imageObject = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.png"));
    Readable
        rd = new InputStreamReader(new ByteArrayInputStream(("load "
        + "assignment4/images/filter-additional-operations/blue.png blue-img"
        + "\nexit").getBytes()));
    ViewInterface view = new View(new ByteArrayOutputStream());
    ImageStore model = new ImageStoreImplementor();
    EditorControllerImplementor controller = new EditorControllerImplementor(model, (Readable)
        rd,
        view);
    controller.startApplication();
    //check if the contents of controller are working correctly.
    assertEquals(imageObject, model.getImageModel("blue-img"));
  }

  @Test
  public void testValidInputToModel() throws IOException {
    ImageEditor imgPng = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.png"));
    ImageEditor imgJpg = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.jpg"));
    ImageEditor imgJpeg = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.jpeg"));
    ImageEditor imgPpm = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.ppm"));
    ImageEditor imgBmp = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.bmp"));
    Readable rd = new InputStreamReader(new ByteArrayInputStream((
        "load assignment4/images/filter-additional-operations/blue.png img-png\n"
            + "load assignment4/images/filter-additional-operations/blue.jpg img-jpg\n"
            + "load assignment4/images/filter-additional-operations/blue.jpeg img-jpeg\n"
            + "load assignment4/images/filter-additional-operations/blue.bmp img-bmp\n"
            + "load assignment4/images/filter-additional-operations/blue.ppm img-ppm\n"
            + "exit").getBytes()));
    ViewInterface view = new View(new ByteArrayOutputStream());
    ImageStore model = new ImageStoreImplementor();
    EditorController controller = new EditorControllerImplementor(model, rd, view);
    controller.startApplication();
    //check if the contents of controller are working correctly.
    assertEquals(imgPng, model.getImageModel("img-png"));
    assertEquals(imgJpeg, model.getImageModel("img-jpeg"));
    assertEquals(imgJpg, model.getImageModel("img-jpg"));
    assertEquals(imgBmp, model.getImageModel("img-bmp"));
    assertEquals(imgPpm, model.getImageModel("img-ppm"));

  }

  @Test(expected = FileNotFoundException.class)
  public void testInvalidFileLoad() throws IOException {
    ImageEditor imgPng = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.pnag"));
    Readable rd = new InputStreamReader(new ByteArrayInputStream((
        "load assignment4/images/filter-additional-operations/blue.png img-pnag\n"
            + "lad blue.png\n"
            + "exit").getBytes()));
    ViewInterface view = new View(new ByteArrayOutputStream());
    ImageStore model = new ImageStoreImplementor();
    EditorController controller = new EditorControllerImplementor(model, rd, view);
    controller.startApplication();
    //check if the contents of controller are working correctly.
    assertEquals(imgPng, model.getImageModel("img-png"));
  }

  @Test
  public void transitionCaseFromPPMtoPNGtoPPM() throws IOException {
    ImageStore model = new ImageStoreImplementor();
    ViewInterface view = new View(new ByteArrayOutputStream());
    Readable rd = new InputStreamReader(new ByteArrayInputStream(("load JD.ppm JD"
        + "\nbrighten 50 JD JD-b"
        + "\nsave JD.jpg JD-b"
        + "\nload JD.jpg JDJPG"
        + "\ndither JDJPG JDJPG-d"
        + "\nsave JD.png JDJPG-d"
        + "\nload JD.png JDPNG"
        + "\nvertical-flip JDPNG JDPNG-VF"
        + "\nsave JD.bmp JDPNG-VF"
        + "\nload JD.bmp JDBMP"
        + "\nhorizontal-flip JDBMP JDBMP-HF"
        + "\nsave JDTransition.ppm JDBMP-HF"
        + "\nquit").getBytes()));
    EditorController controller = new EditorControllerImplementor(model, rd, view);
    controller.startApplication();
    ImageEditor actualImg = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("JD.ppm"));
    ImageEditor actualImgPNG = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("JD.png"));
    ImageEditor actualImgJPG = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("actImg.png"));
    ImageEditor actualImgBMP = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("bmpimg.bmp"));
    ImageEditor actualImgNewPPM = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("newImg.ppm"));
    assertEquals(actualImg, model.getImageModel("JD"));
    assertEquals(actualImgPNG, model.getImageModel("JDJPG-d"));
    assertEquals(actualImgJPG, model.getImageModel("JD-b"));
    assertEquals(actualImgBMP, model.getImageModel("JDPNG-VF"));
    assertEquals(actualImgNewPPM, model.getImageModel("JDBMP-HF"));
  }


  /**
   * Test sequence of operations passed to the controller to manipulate a BMP image.
   *
   * @throws IOException if image doesn't exist.
   */
  @Test
  public void testMultipleOperationsOnBMP() throws IOException {

    ImageEditor actualImg = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("tiger.ppm"));
    ImageEditor actualImgini = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("tiger.bmp"));
    ImageModelCommand command = new DitherImageCommand();
    ImageModelCommand command1 = new ColorTransformCommand("sepia");
    ImageModelCommand command2 = new GaussianBlurCommand();
    ImageModelCommand command3 = new ColorTransformCommand("greyscale");
    ImageModelCommand command4 = new SharpenCommand();
    actualImg = ((ImageObjectV2) actualImg).runOperation(command);
    actualImg = ((ImageObjectV2) actualImg).runOperation(command1);
    actualImg = ((ImageObjectV2) actualImg).runOperation(command2);
    actualImg = ((ImageObjectV2) actualImg).runOperation(command3);
    actualImg = ((ImageObjectV2) actualImg).runOperation(command4);
    actualImg = actualImg.horizontalFlip();
    actualImg = actualImg.verticalFlip();
    actualImg = actualImg.horizontalFlip();
    actualImg = actualImg.verticalFlip();
    actualImg = actualImg.brighten(50);
    actualImg = actualImg.brighten(-50);
    ImageEditor actualImgR = actualImg.greyScale("red-component");
    ImageEditor actualImgG = actualImg.greyScale("green-component");
    ImageEditor actualImgB = actualImg.greyScale("blue-component");
    ImageEditor actualImgI = actualImg.greyScale("intensity-component");
    ImageEditor actualImgL = actualImg.greyScale("luma-component");
    ImageEditor actualImgV = actualImg.greyScale("value-component");
    ImageEditor imgSplit = (ImageEditor) actualImg.rgbSplit();
    ImageEditor finalIMG = actualImgR.rgbCombine(actualImgG, actualImgB);
    Readable rd = new InputStreamReader(new ByteArrayInputStream(("load TIGER.bmp tiger"
        + "\ndither tiger tiger-d"
        + "\nsepia tiger-ds tiger-d"
        + "\nimage-blur tiger-dsib tiger-ds"
        + "\ngrey-scaled tiger-dsibgs tiger dsib"
        + "\nimage-sharpen tiger-dsibgss tiger-dsibgs"
        + "\nhorizontal-flip tiger-h tiger-dsibgss"
        + "\nvertical-flip tiger-hv tiger-h"
        + "\nhorizontal-flip tiger-hvh tiger-hv"
        + "\nvertical-flip tiger-hvhv tiger-hvh"
        + "\nbrighten 50 tiger-hvhvb tiger-hvhv"
        + "\nbrighten -50 tiger-hvhvbd tiger-hvhvb"
        + "\ngreyscale red-component tiger-hvhvbdr tiger-hvhvbd"
        + "\ngreyscale green-component tiger-hvhvbdrg tiger-hvhvbdr"
        + "\ngreyscale blue-component tiger-hvhvbdrgb tiger-hvhvbdrg"
        + "\ngreyscale intensity-component tiger-hvhvbdrgbi tiger-hvhvbdrgb"
        + "\ngreyscale luma-component tiger-hvhvbdrgbil tiger-hvhvbdrgbi"
        + "\ngreyscale value-component tiger-hvhvbdrgbilv tiger-hvhvbdrgbil"
        + "\nrgb-combine final tiger-hvhvbdr tiger-hvhvbdrg tiger-hvhvbdrgb"
        + "\nsave tigerFinal.bmp final"
        + "\nquit").getBytes()));
    ImageStore model = new ImageStoreImplementor();
    ViewInterface view = new View(new ByteArrayOutputStream());
    EditorController controller = new EditorControllerImplementor(model, rd, view);
    controller.startApplication();
    //check if the contents of controller are working correctly.
    assertEquals(actualImgini, model.getImageModel("tiger"));
    assertEquals(finalIMG, model.getImageModel("final"));

  }


  @Test
  public void testValidInputScript() throws IOException {
    ImageEditor imgPng = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/"
            + "filter-additional-operations"
            + "/blue.png"));
    ImageEditor imgPpm = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new PPMImage().loadImage("assignment4/images/filter-additional-operations"
            + "/blue.ppm"));
    Readable rd = new InputStreamReader(new ByteArrayInputStream((
        "run assignment4/res/script.txt\n"
            + "exit").getBytes()));
    ViewInterface view = new View(new ByteArrayOutputStream());
    ImageStore model = new ImageStoreImplementor();
    EditorController controller = new EditorControllerImplementor(model, rd, view);
    controller.startApplication();
    //check if the contents of controller are working correctly.
    assertEquals(imgPng, model.getImageModel("img-blur-png"));
    assertEquals(imgPpm, model.getImageModel("img-sharp-ppm"));

  }


  @Test
  public void testInvalidCommandsInScript() throws IOException {
    ImageEditor imgPng = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/"
            + "filter-additional-operations"
            + "/blue.png"));

    Readable rd = new InputStreamReader(new ByteArrayInputStream((
        "run assignment4/res/script.txt\n"
            + "exit").getBytes()));

    ViewInterface view = new View(new ByteArrayOutputStream());
    ImageStore model = new ImageStoreImplementor();
    EditorController controller = new EditorControllerImplementor(model, rd, view);
    controller.startApplication();
    assertNull(model.getImageModel("image-not-loaded"));

  }

  @Test
  public void testSaveCommand() throws IOException {
    ImageEditor imgPng = new ImageObject.ImageObjectBuilder().buildImageWithFile(
        new GeneralImageFormats().loadImage("assignment4/images/"
            + "filter-additional-operations"
            + "/blue.png"));

    Readable rd = new InputStreamReader(new ByteArrayInputStream((
        "load assignment4/images/filter-additional-operations/blue.ppm"
            + "\n"
            + "save assignment4/output-images/blue.png"
            + "\n"
            + "save assignment4/output-images/blue.jpg"
            + "\n"
            + "save assignment4/output-images/blue.jpeg"
            + "\n"
            + "save assignment4/output-images/blue.bmp"
            + "\n"
            + "save assignment4/output-images/blue.ppm"
            + "exit").getBytes()));

    ViewInterface view = new View(new ByteArrayOutputStream());
    ImageStore model = new ImageStoreImplementor();
    EditorController controller = new EditorControllerImplementor(model, rd, view);
    controller.startApplication();
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/blue.jpg")),
        Files.readAllBytes(Path.of("assignment4/images/filter-additional-ops/blue.jpg")));
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/blue.ppm")),
        Files.readAllBytes(Path.of("assignment4/images/filter-additional-ops/blue.ppm")));
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/blue.jpeg")),
        Files.readAllBytes(Path.of("assignment4/images/filter-additional-ops/blue.jpeg")));
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/blue.png")),
        Files.readAllBytes(Path.of("assignment4/images/filter-additional-ops/blue.png")));
    assertArrayEquals(Files.readAllBytes(Path.of("assignment4/output-images/blue.bmp")),
        Files.readAllBytes(Path.of("assignment4/images/filter-additional-ops/blue.bmp")));

  }
}