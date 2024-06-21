package model;

/**
 * ImageOperationsStoreImpl contains the list of operations which are implemented on the
 * ImageOperationsStore where the operation is performed on the model using command design pattern.
 */
public class ImageOperationsStoreImpl implements ImageOperationsStore {

  @Override
  public ImageEditor blur(ImageEditor img) {
    ImageEditor imgObj;
    if (img instanceof ImageEditorWithCommand) {
      ImageModelCommand blurCommand = new GaussianBlurCommand();
      imgObj = ((ImageObjectV2) img).runOperation(blurCommand);
    } else {
      throw new IllegalArgumentException("blur cannot be performed.");
    }
    return imgObj;
  }

  @Override
  public ImageEditor sharpen(ImageEditor img) {
    ImageEditor imgObj;
    if (img instanceof ImageEditorWithCommand) {
      ImageModelCommand sharpCmd = new SharpenCommand();
      imgObj = ((ImageObjectV2) img).runOperation(sharpCmd);
    } else {
      throw new IllegalArgumentException("Sharpen cannot be performed.");
    }
    return imgObj;
  }

  @Override
  public ImageEditor sepiaFilter(ImageEditor img) {
    ImageEditor imgObj;
    if (img instanceof ImageEditorWithCommand) {
      ImageModelCommand sepiaTransformCmd = new ColorTransformCommand("sepia");
      imgObj = ((ImageObjectV2) img).runOperation(sepiaTransformCmd);
    } else {
      throw new IllegalArgumentException("Sepia cannot be performed.");
    }
    return imgObj;
  }

  @Override
  public ImageEditor greyScaleFilter(ImageEditor img) {
    ImageEditor imgObj;
    if (img instanceof ImageEditorWithCommand) {
      ImageModelCommand greyFilterTransformCmd = new ColorTransformCommand("greyscale");
      imgObj = ((ImageObjectV2) img).runOperation(greyFilterTransformCmd);
    } else {
      throw new IllegalArgumentException("Greyfilter cannot be performed.");
    }
    return imgObj;
  }

  @Override
  public ImageEditor ditherOperation(ImageEditor img) {
    ImageEditor imgObj;
    if (img instanceof ImageEditorWithCommand) {
      imgObj = ((ImageObjectV2) img).runOperation(new DitherImageCommand());
    } else {
      throw new IllegalArgumentException("Dither cannot be performed.");
    }
    return imgObj;
  }
}
