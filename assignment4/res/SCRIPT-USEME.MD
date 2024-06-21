1. load image-file-path image-name:
   Load an image from path and referred with image name given.

2. save image-file-path image-name:
   Save the referred image name to the specified path this should include image name and extension.

3. greyscale red-component image-name target-image-name:
   Create a greyscale image with the red-component of the referred image, and store in target image
   name. Similarly, commands are avaialbe for green, blue, value, luma, intensity components.

4. horizontal-flip image-name target-image-name:
   Flip an image horizontally and store in the target image name reference.

5. vertical-flip image-name target-image-name:
   Flip an image vertically and store in the target image name reference.

6. brighten increment image-name target-image-name:
   Brighten the image by the given integer value to create a new image.
   The increment may be positive (brightening) or negative (darkening).

7. rgb-split image-name target-image-name-red target-image-name-green target-image-name-blue:
   Split the referred image into three greyscale images containing its red, green and
   blue components respectively.

8. rgb-combine image-name red-image green-image blue-image:
   Combine the three greyscale images into single image that uses the red, green and blue components
   from the three images respectively.

9. run script-file:
   Load and run the script commands in the file we are allowing .txt files.
   '#' is used at start of line to refer as a comment line.

10. blur image-name image-name:
    Blurs the source image and stores in the target image.

11. sharpen image-name image-name:
    Sharpens the source image and stores in the target image.

12. greyscale image-name image-name:
    Greyscale transformation of the source image and stores in the target image.

13. sepia image-name image-name:
    Sepia transformation of the source image and stores in the target image.

14. dither image-name image-name:
    Dither transformation of the source image and stores in the target image.

15. exit
    To exit the application

Code snippet to execute the script file:

public class Main {
public static void main(String[] args) {
// Viewer initialisation.
OutputStream out = new ByteArrayOutputStream();
ViewInterface view = new View(out);
// Controller object creation.
EditorController ctrl = new EditorControllerImplementor(System.in, view);
// Executing the run command.
ctrl.readAndExecuteCommand("run assignment4/res/script.txt");
}
}

All the resources used are referred in the res folder.

Image Reference:
Used the image reference from the following:
Copyright @: https://unsplash.com/photos/IgQo1_gUzBY
Copyright @: https://unsplash.com/photos/Sg3XwuEpybU
Appreciating your resources.

