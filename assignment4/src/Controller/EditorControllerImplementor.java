package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.ImageEditor;
import model.ImageEditorWithCommand;
import model.ImageObject;
import model.ImageOperationsStore;
import model.ImageOperationsStoreImpl;
import model.ImageReadOnlyObject;
import model.ImageStore;
import view.ViewInterface;

/**
 * EditorControllerImplementor implements the EditorController in which the sequence of commands are
 * taken from the user using command line and also as a script file. The provided operations are
 * executed on the individual models and viewer is used to log messages to user.
 */
public class EditorControllerImplementor implements EditorController {

  private ImageStore imgStore;
  private final Map<String, ArrayList<String>> funtionMap = new HashMap<>();
  private static final String exitString = "exit";
  private boolean isExitApplication = false;
  private boolean isApplicationProcessingCommand = false;
  private final Readable inputStream;
  private final ViewInterface viewObject;

  public ImageReadOnlyObject roImageStore;

  /**
   * Constructor to initialise the input stream, output stream i.e expected user input reader and
   * stream used for the viewer.
   *
   * @param inStream InputStream provided to initialise the inStream to take input from user.
   * @param view     to initialise the output stream of the application for the viewer.
   */
  public EditorControllerImplementor(ImageStore model, Readable inStream, ViewInterface view) {
    this.inputStream = inStream;
    this.viewObject = view;
    this.imgStore = model;
    this.initializeFunctionMap();
  }

  /**
   * Starts the application and waits for individual user commands. Involves reading files and
   * various operations done on images.
   *
   * @throws IOException thrown when provided input file is not accessible.
   */
  @Override
  public void startApplication() throws IOException {
    String cmd;
    try {
      Scanner scan = new Scanner(this.inputStream);
      while (!isExitApplication && scan.hasNextLine()) {
        if (!this.isApplicationProcessingCommand) {
          this.viewObject.logOutput("Please enter a command:");
          cmd = scan.nextLine();
          this.readAndExecuteCommand(cmd);
          this.viewObject.logOutput("Executed command:" + cmd);
        }
      }
      this.isApplicationProcessingCommand = false;
    } catch (Exception e) {
      this.viewObject.logOutput(e.getMessage() + "\n");
      this.isApplicationProcessingCommand = false;

    }
  }

  /**
   * Executes the user given command individually. Command is provided as a string.
   *
   * @param cmd command string given to run.
   */
  public void readAndExecuteCommand(String cmd) {
    if (!isApplicationProcessingCommand) {
      try {
        cmd = this.transformCommand(cmd);
        if (this.isValidCommand(cmd)) {
          this.isExitApplication = this.runCommand(cmd);
        } else {
          this.viewObject.logOutput("Invalid command, please re-enter");
        }
        this.isApplicationProcessingCommand = false;
      } catch (Exception e) {
        this.viewObject.logOutput(e.getMessage() + "\n");
        this.isApplicationProcessingCommand = false;
      }
    } else {
      this.viewObject.logOutput("Editor is executing a command, please wait...");
    }
  }

  private boolean runCommand(String command) throws IOException, IllegalArgumentException {
    List<String> executingCommands = Arrays.asList(command.split(" "));
    try {
      String operator = executingCommands.get(0);
      String targetImageObject;
      String sourceImageObject;
      ImageOperationsStore opsStore = new ImageOperationsStoreImpl();
      if (operator.equals(exitString)) {
        return true;
      }
      this.isApplicationProcessingCommand = true;
      switch (operator) {
        case "load": {
          this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
              this.loadImageObject(executingCommands.get(1)));
          break;
        }
        case "brighten": {
          int q = Integer.parseInt(executingCommands.get(1));
          this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
              this.getClone(this.getImageObjectHashMap(executingCommands.get(2))).brighten(q));
          break;

        }
        case "horizontal-flip": {
          this.setImageObjectHashMap(
              this.getTargetImageName(executingCommands),
              this.getImageModel(executingCommands).horizontalFlip());
          break;

        }
        case "vertical-flip": {
          this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
              this.getImageModel(executingCommands).verticalFlip());
          break;

        }
        case "greyscale": {
          this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
              this.getClone(this.getImageObjectHashMap(executingCommands.get(2)))
                  .greyScale(executingCommands.get(1)));
          break;

        }
        case "save": {
          this.saveImageObject(executingCommands.get(2), executingCommands.get(1));
          break;

        }
        case "rgb-split": {
          String targetRedComp = executingCommands.get(2);
          String targetGreenComp = executingCommands.get(3);
          String targetBlueComp = executingCommands.get(4);
          Map<String, ImageEditor> splitMappedObject = this.getImageModel(executingCommands)
              .rgbSplit();
          this.setImageObjectHashMap(targetRedComp, splitMappedObject.get("red"));
          this.setImageObjectHashMap(targetGreenComp, splitMappedObject.get("green"));
          this.setImageObjectHashMap(targetBlueComp, splitMappedObject.get("blue"));
          break;

        }
        case "rgb-combine": {
          ImageEditor redComp = this.getImageObjectHashMap(executingCommands.get(2));
          ImageEditor greenComp = this.getImageObjectHashMap(executingCommands.get(3));
          ImageEditor blueComp = this.getImageObjectHashMap(executingCommands.get(4));
          this.setImageObjectHashMap(
              executingCommands.get(1),
              redComp.rgbCombine(greenComp, blueComp));
          break;

        }
        case "blur": {
          ImageEditor image = this.getImageModel(executingCommands);
          if (image instanceof ImageEditorWithCommand) {
            this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
                opsStore.blur(image));
          }
          break;
        }

        case "sharpen": {
          ImageEditor image = this.getImageModel(executingCommands);
          if (image instanceof ImageEditorWithCommand) {
            this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
                opsStore.sharpen(image));
          }
          break;
        }

        case "sepia": {
          ImageEditor image = this.getImageModel(executingCommands);
          if (image instanceof ImageEditorWithCommand) {
            this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
                opsStore.sepiaFilter(image));
          }
          break;
        }

        case "greyscaleV1": {
          ImageEditor image = this.getImageModel(executingCommands);
          if (image instanceof ImageEditorWithCommand) {
            this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
                opsStore.greyScaleFilter(image));
          }
          break;
        }
        case "dither": {
          ImageEditor image = this.getImageModel(executingCommands);
          if (image instanceof ImageEditorWithCommand) {
            this.setImageObjectHashMap(this.getTargetImageName(executingCommands),
                opsStore.ditherOperation(image));
          }
          break;
        }
        // To let the flow of commands to execute from the script file.
        case "run": {
          this.isApplicationProcessingCommand = false;
          this.executeScriptFile(executingCommands.get(executingCommands.size() - 1));
          break;

        }
        default: {
          break;
        }
      }
      this.isApplicationProcessingCommand = false;
      return false;
    } catch (Exception e) {
      this.viewObject.logOutput(e.getMessage());
      this.isApplicationProcessingCommand = false;
      return false;
    }
  }


  private void executeScriptFile(String fileLoaded) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(fileLoaded));
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine() && !this.isExitApplication) {
      String s = sc.nextLine();
      if (s.length() > 0 && s.charAt(0) != '#') {
        this.viewObject.logOutput("Executed command from script file: " + s);
        this.readAndExecuteCommand(s);
      }
    }
  }

  private ImageEditor getClone(ImageEditor img) {
    return new ImageObject.ImageObjectBuilder().buildImageObject(img.getImagePixels(),
        img.getWidth(),
        img.getHeight(), img.getMaxPixel());

  }

  private void checkIfImageObjectExists(ImageEditor image) throws IllegalArgumentException {
    if (image == null || image.getImagePixels() == null) {
      throw new IllegalArgumentException("Provided object is not loaded.");
    }
  }

  private void setImageObjectHashMap(String imageName, ImageEditor imgObj) {
    if (imgObj != null) {
      this.imgStore.setImageModel(imageName, imgObj);
    }
  }

  private ImageEditor getImageObjectHashMap(String imgName) {
    ImageEditor imgObj = this.imgStore.getImageModel(imgName);
    this.checkIfImageObjectExists(imgObj);
    return imgObj;
  }

  private String getTargetImageName(List<String> commandArray) {
    return commandArray.get(commandArray.size() - 1);
  }

  private String getSourceImageName(List<String> commandArray) {
    return commandArray.get(1);
  }

  private void initializeFunctionMap() {
    this.funtionMap.put("load", new ArrayList<>(Arrays.asList("file-path", "string")));
    this.funtionMap.put("brighten", new ArrayList<>(Arrays.asList("int", "string", "string")));
    this.funtionMap.put("vertical-flip", new ArrayList<>(Arrays.asList("string", "string")));
    this.funtionMap.put("horizontal-flip", new ArrayList<>(Arrays.asList("string", "string")));
    this.funtionMap.put("greyscale",
        new ArrayList<>(Arrays.asList("value-comp", "string", "string")));
    this.funtionMap.put("save", new ArrayList<>(Arrays.asList("file-path", "string")));
    this.funtionMap.put("rgb-split",
        new ArrayList<>(Arrays.asList("string", "string", "string", "string")));
    this.funtionMap.put("rgb-combine",
        new ArrayList<>(Arrays.asList("string", "string", "string", "string")));
    this.funtionMap.put("blur", new ArrayList<>(Arrays.asList("string", "string")));
    this.funtionMap.put("sharpen", new ArrayList<>(Arrays.asList("string", "string")));
    this.funtionMap.put("sepia", new ArrayList<>(Arrays.asList("string", "string")));
    this.funtionMap.put("greyscaleV1", new ArrayList<>(Arrays.asList("string", "string")));
    this.funtionMap.put("dither", new ArrayList<>(Arrays.asList("string", "string")));
    this.funtionMap.put("run", new ArrayList<>(Arrays.asList("script-file")));
    this.funtionMap.put("exit", new ArrayList<>());
  }

  private boolean isValidCommand(String commandText) {
    if (commandText.charAt(0) == '#') {
      return true;
    }
    String[] words = commandText.split(" ");
    int wordsLength = words.length;
    if (wordsLength < 1 || wordsLength >= 6) {
      return false;
    } else {
      return this.commandValidator(words);
    }
  }

  private String transformCommand(String wordstring) {
    String[] wordArray = wordstring.split(" ");
    if (wordArray[0].equals("greyscale") && wordArray.length == 3) {
      wordArray[0] = "greyscaleV1";
    }
    String transformedString = "";
    for (String s : wordArray) {
      if (transformedString.equals("")) {
        transformedString = s;
      } else {
        transformedString = transformedString + " " + s;
      }
    }
    return transformedString;
  }

  private boolean commandValidator(String[] commandWords) {
    if (this.funtionMap.containsKey(commandWords[0])) {
      List<String> operands = new ArrayList<>(this.funtionMap.get(commandWords[0]));
      if (commandWords.length == operands.size() + 1) {
        String[] commands = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        int index = 0;
        for (String op : operands) {
          switch (op) {
            case "file-path": {
              if (!this.filePathValidator(commands[index])) {
                return false;
              }
              break;
            }
            case "script-file": {
              if (!this.scriptFilePathValidator(commands[index])) {
                return false;
              }
              break;
            }
            case "string": {
              if (!this.stringValidator(commands[index])) {
                return false;
              }
              break;
            }
            case "int": {
              if (!this.intValidator(commands[index])) {
                return false;
              }
              break;
            }
            case "value-comp": {
              if (!this.componentValidator(commands[index])) {
                return false;
              }
              break;
            }
            default: {
              return false;
            }
          }
          index += 1;
        }
        return true;
      }
    }
    return false;
  }

  private boolean filePathValidator(String fileName) {
    if (!(fileName.contains(".ppm") || fileName.contains(".png") || fileName.contains(".jpg")
        || fileName.contains(".jpeg") || fileName.contains("bmp"))) {
      throw new IllegalStateException("Illegal image file is trying to read.");
    } else {
      return true;
    }
  }

  private boolean scriptFilePathValidator(String fileName) {
    if (!fileName.contains(".txt")) {
      throw new IllegalStateException("Illegal script file is trying to run.");
    } else {
      return true;
    }
  }

  private boolean stringValidator(String name) {
    return true;
  }

  private boolean intValidator(String integerValue) {
    return integerValue.matches("-?\\d+");
  }

  private boolean componentValidator(String compValue) {
    List<String> validValueComps = Arrays.asList("red-component", "green-component",
        "blue-component",
        "value-component", "intensity-component", "luma-component");
    return validValueComps.contains(compValue);
  }

  private ImageEditor getImageModel(List<String> executingCommands) {
    return this.getClone(this.getImageObjectHashMap(
        this.getSourceImageName(executingCommands)));
  }

  private void saveImageObject(String imageRef, String fileName) throws IOException {
    try {
      StringBuilder imgStringBuilder = this.getImageObjectHashMap(imageRef).getImageEditorContent();
      if (fileName.contains("ppm")) {
        new PPMImage().saveFile(fileName, this.getImageObjectHashMap(imageRef));
      } else {
        new GeneralImageFormats().saveFile(fileName, this.getImageObjectHashMap(imageRef));
      }
    } catch (Exception e) {
      this.viewObject.logOutput(e.getMessage() + "\n");
    }
  }

  private ImageEditor loadImageObject(String fileName) throws IOException {
    try {
      if (fileName.contains("ppm")) {
        return new ImageObject.ImageObjectBuilder().buildImageWithFile(
            new PPMImage().loadImage(fileName));
      } else {
        return new ImageObject.ImageObjectBuilder().buildImageWithFile(
            new GeneralImageFormats().loadImage(fileName));
      }
    } catch (Exception e) {
      this.viewObject.logOutput(e.getMessage() + "\n");
      return null;
    }
  }


  /**
   * PPMImage static inner class to load the PPM image file and create the ImageEditor object. This
   * implementation is specific to the PPM image file extension.
   */
  public static class PPMImage {

    /**
     * LoadImage method creates the string builder in a format which is acceptable by the image
     * builder.
     *
     * @param filename String file path.
     * @return StringBuilder which is consumable by the ImageEditor Object.
     * @throws IOException           if file is not valid.
     * @throws FileNotFoundException if file not found.
     */
    public StringBuilder loadImage(String filename) throws IOException, FileNotFoundException {
      Scanner sc = null;
      try {
        sc = new Scanner(new FileInputStream(filename));
      } catch (IOException e) {
        throw new IOException(e.getMessage());
      }
      StringBuilder builder = new StringBuilder();
      // read the file line by line, and populate a string. This will throw away any comment lines
      while (Objects.requireNonNull(sc).hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      }
      // now set up the scanner to read from the string we just built
      sc = new Scanner(builder.toString());
      StringBuilder transformedFileString = new StringBuilder();
      String token;
      token = sc.next();
      if (!token.equals("P3")) {
        throw new IllegalArgumentException("Invalid PPM file: Plain file should start with P3");
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxVal = sc.nextInt();
      transformedFileString.append(width).append(System.lineSeparator());
      transformedFileString.append(height).append(System.lineSeparator());
      transformedFileString.append(maxVal).append(System.lineSeparator());
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          transformedFileString.append(r).append(System.lineSeparator());
          transformedFileString.append(g).append(System.lineSeparator());
          transformedFileString.append(b).append(System.lineSeparator());
          if ((r > 255 || b > 255 || g > 255) || (r < 0 || b < 0 || g < 0)) {
            throw new IllegalStateException("Invalid pixel value found in file.");
          }
        }
      }
      return transformedFileString;
    }


    private void saveFile(String filePath, ImageEditor imageObj)
        throws IllegalArgumentException, IOException {
      StringBuilder builder = imageObj.getImageEditorContent();
      OutputStream os = new FileOutputStream(filePath);
      OutputStreamWriter writer = new OutputStreamWriter(os);

      writer.write("P3\n");
      writer.write(builder.toString());
      writer.close();
    }
  }

  /**
   * GeneralImageFormats static inner class to load the image files of type jpg, png, jpeg and bmp.
   * This implementation creates the string builder which is acceptable by the image builder.
   */
  public static class GeneralImageFormats {

    /**
     * LoadImage method creates the string builder in a format which is acceptable by the image
     * builder.
     *
     * @param filename String file path.
     * @return StringBuilder which is consumable by the ImageEditor Object.
     * @throws IOException if file is not valid.
     */
    public StringBuilder loadImage(String filename) throws IOException {
      BufferedImage image = null;
      StringBuilder transformedString = null;
      try {
        image = ImageIO.read(new File(filename));
        transformedString = new StringBuilder();
        transformedString.append(image.getWidth()).append(System.lineSeparator());
        transformedString.append(image.getHeight()).append(System.lineSeparator());
        transformedString.append(255).append(System.lineSeparator());
        for (int y = 0; y < image.getHeight(); y++) {
          for (int x = 0; x < image.getWidth(); x++) {
            // Retrieving contents of a pixel
            int pixel = image.getRGB(x, y);
            // Creating a Color object from pixel value
            Color color = new Color(pixel, true);
            // Retrieving the R G B values
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            transformedString.append(red).append(System.lineSeparator());
            transformedString.append(green).append(System.lineSeparator());
            transformedString.append(blue).append(System.lineSeparator());
          }
        }
      } catch (IOException e) {
        throw new IOException("Error: " + e.getMessage());
      }
      return transformedString;
    }

    private void saveFile(String filePath, ImageEditor imageObj) throws IOException {
      try {
        BufferedImage bufferedImage = imageObj.getBufferedImageObject();
        String[] fileName = filePath.split("\\.");
        String fileExt = fileName[fileName.length - 1];
        ImageIO.write(bufferedImage, fileExt, new File(filePath));
      } catch (IOException e) {
        throw new IOException("Error Occurred: " + e.getMessage());
      }
    }
  }


}