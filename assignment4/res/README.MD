Model View Controller is the preferred design pattern for this application.

In our design, the interface of a model (ImageEditor) provides the available set of operations,
and the implementation of this interface (ImageObject) is with respect to PPM file implementation.
The (ImageObjectBuilder) which is a static class in the (ImageObject) helps to create the model.
Once the model is built we cannot rebuild it. Each ImageObject contains a two dimensional list of
pixel objects. Each pixel object is contains red, green and blue components of a pixel.

EditorController interface provides methods for the user to interact with the application.
EditorControllerImplementor implementation acts as a delegator of tasks between the model and
viewer. ImageModelCommand interface provides operationOnImage method to modify the image model.
Various operations on the model will implement different implementations of ImageModelCommand.

ViewInterface interface provides the operations to log the message and view the message. The View
implementation logs the passed messages to the outputStream of the view. It also provides the list
of logged messages by the application.

The new GUI view enables the user to interact with the application with mouse pad and perform image
editor operations which are provided in the previous version. The user can load the image, save
and perform image filtering and manipulating operations on the image.

The histogram of the image which is a line chart graphical notation of the distribution of the
image pixel values across the frequency is displayed under the image. Histogram alters according to
the latest loaded imaged and latest operation performed. User can view the corresponding image
component in the histogram chart by checking and unchecking the checkboxes.

A sample image of the load image and its corresponding histogram is stored in the res folder.

In our approach, the controller's function is to take command-line or file input from the user,
validate it, and then transmit the operation to the model.
The controller then stores the outcome of the action taken by the model.

We manage arising exceptions and pass a helpful message to the user through a view, notifying them
about the exception that has occurred because our controller constantly executes the application.
Unless it receives a exit command, the controller continues to execute while waiting for
instructions from the user. The image editor application enables user to load a ppm image file and
perform multiple operations on the image such as brighten by a constant integer value, darken by a
constant integer value, flip the image horizontally, vertically, extract the greyscale image
components of the image such as red, green, blue, value i.e maximum of red, green and blue
components, luma component and intensity component.

Command design pattern implemented for the model commands, so that only controller will call
only operation object to perform any command on the image model. Moved the load and save images
to the controller as the image extensions may get varied in future which should not affect
the nature of the implemented model and its behavior.
New model version without affecting the old model interface, object and supporting the latest
methods.

New operations added are blur, sharpen, sepia, greyscale and dither on the images.

Execution:
Application gets started by executing the startApplication() in the controller.

The application takes the command from user from input stream provided by the user. Various
available commands include
load, save, horizontal flip, vertical flip, greyscale component, combine, split. Following are the
valid commands

Eg: load image.ppm img1

Please check the script.txt file for the reference of valid commands. If an invalid command is
entered by the user then the viewer will log the exception message to the user. So that the user
can verify and correct the command.

User can enter the commands dynamically or execute the list of commands in a single go by
executing the command "run script.txt" where script.txt contains the list of executing commands.

If the command line in the script file starts with '#' it is considered as a comment line.

Exit:
User can exit the application by providing the "exit" command.

Disclaimer:
Please check that the file path doesn't contain spaces in the folder names or file names.

Commands:

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

10. exit:
    Terminates the application

11. blur image-name image-name:
    Blurs the source image and stores in the target image.

12. sharpen image-name image-name:
    Sharpens the source image and stores in the target image.

13. greyscale image-name image-name:
    Greyscale transformation of the source image and stores in the target image.

14. sepia image-name image-name:
    Sepia transformation of the source image and stores in the target image.

15. dither image-name image-name:
    Dither transformation of the source image and stores in the target image.

Code snippet to execute the script file:
Application can be started by executing the jar file. There are three variants of running the
application.

1) java -jar APP.jar -> Runs the application in GUI mode.
2) java -jar APP.jar -file script.txt -> Runs the application, consumes the script.txt and exits.
3) java -jar APP.jar -text -> Runs the application in user interaction key board mode.

All the resources used are referred in the res folder.

Image Reference:
Used the image reference from the following:
Copyright @: https://unsplash.com/photos/IgQo1_gUzBY
Copyright @: https://unsplash.com/photos/Sg3XwuEpybU
Appreciating your resources.

