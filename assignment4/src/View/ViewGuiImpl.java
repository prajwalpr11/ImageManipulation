package view;

import controller.ViewUIFeatures;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import model.ImageReadOnlyObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.statistics.HistogramDataset;

/**
 * ViewGuiImpl implementation of ViewGuiInterface and ActionListener user interface for the image
 * editing program responsible to displays the Image loaded and the output of the operations
 * performed on the Image.
 */
public class ViewGuiImpl extends JFrame implements ViewGuiInterface, ActionListener {

  private static final int BINS = 256;
  private final ViewInterface viewImpl;
  private final ImageReadOnlyObject roModelObject;

  private JPanel imageDisplayPanel;
  private JPanel editorOptionsPanel;
  private JLabel fileOpenDisplay;
  private JLabel redPath;
  private JLabel greenPath;
  private JLabel bluePath;


  private HistogramDataset histDataSetValues;
  private XYLineAndShapeRenderer renderer;


  private JButton hFlipButton;
  private JButton vFlipButton;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private JButton greyScaleUnaryButton;
  private JButton sharpenButton;
  private JButton blurButton;
  private JButton ditherButton;
  private JButton rgbSplitButton;
  private JButton rgbCombineButton;
  private JButton brightenButton;
  private JButton greyScaleButton;
  private JButton sepiaToneButton;
  private JButton redFileButton;
  private JButton greenFileButton;
  private JButton blueFileButton;
  private boolean isImageLoaded = false;

  /**
   * Constructs a ViewGuiImpl object with the given title for the view, the given ViewInterface.
   *
   * @param viewBoxTitle  the title for the view
   * @param viewObj       initialises the ViewInterface object
   * @param roModelObject initialises the ImageReadOnlyObject
   */
  public ViewGuiImpl(String viewBoxTitle, ViewInterface viewObj,
      ImageReadOnlyObject roModelObject) {
    super(viewBoxTitle);
    this.viewImpl = viewObj;
    this.roModelObject = roModelObject;
    this.initializeView();
  }

  private void initializeView() {
    JScrollPane mainScrollPane;
    JPanel mainPanel;
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //Editor Options Commands boxes
    editorOptionsPanel = new JPanel();
    editorOptionsPanel.setBorder(BorderFactory.createTitledBorder("Editor Options Commands"));
    editorOptionsPanel.setLayout(new BoxLayout(editorOptionsPanel, BoxLayout.Y_AXIS));
    editorOptionsPanel.setMinimumSize(new Dimension(300, 150));
    mainPanel.add(editorOptionsPanel, BorderLayout.CENTER);

    //Image Display area
    imageDisplayPanel = new JPanel();
    imageDisplayPanel.setBorder(BorderFactory.createTitledBorder("Image Display Area"));
    imageDisplayPanel.setLayout(new BoxLayout(imageDisplayPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(imageDisplayPanel);

    fileOpenButton = new JButton("   Load Image   ");
    fileOpenButton.setActionCommand("Open file");
    fileOpenDisplay = new JLabel("Image name");
    editorOptionsPanel.add(fileOpenDisplay);
    editorOptionsPanel.add(fileOpenButton);
    fileSaveButton = new JButton("   Save Image   ");
    fileSaveButton.setActionCommand("Save file");
    editorOptionsPanel.add(fileSaveButton);

    //RGB Combine button
    rgbCombineButton = new JButton(" RGB Combine ");
    rgbCombineButton.setActionCommand(" RGB Combine Button ");
    editorOptionsPanel.add(rgbCombineButton);

    //horizontal flip button
    hFlipButton = new JButton("Horizontal Flip");
    hFlipButton.setActionCommand("Horizontal Flip Button");

    //vertical flip button
    vFlipButton = new JButton("   Vertical Flip   ");
    vFlipButton.setActionCommand("Vertical Flip Button");

    //Sharpen Image button
    sharpenButton = new JButton(" Sharpen Image ");
    sharpenButton.setActionCommand("Sharpen Image Button");

    //Blur Image button
    blurButton = new JButton("    Blur Image    ");
    blurButton.setActionCommand("Blur Image Button");
    blurButton.setPreferredSize(new Dimension(100, 20));

    //dither button
    ditherButton = new JButton("  Dither Image  ");
    ditherButton.setActionCommand("Dither Image Button");

    //RGB Split button
    rgbSplitButton = new JButton("RGB Split Image");
    rgbSplitButton.setActionCommand("RGB Split Button");

    //Brighten button
    brightenButton = new JButton(" Brighten Image ");
    brightenButton.setActionCommand("Brighten Button");

    //greyScale Specific Component button
    greyScaleButton = new JButton("GreyScale Comp");
    greyScaleButton.setActionCommand("GreyScale Button");

    //greyScale Specific Component button
    this.greyScaleUnaryButton = new JButton(" GreyScale Filter ");
    this.greyScaleUnaryButton.setActionCommand("GreyScale Filter");

    //sepia Specific Component button
    sepiaToneButton = new JButton("Sepia Tone Filter");
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editorOptionsPanel,
        imageDisplayPanel);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(150);
    getContentPane().add(splitPane, BorderLayout.CENTER);
    pack();
    setVisible(true);
  }

  private void addToOptionsPanel() {
    editorOptionsPanel.add(hFlipButton);
    editorOptionsPanel.add(vFlipButton);
    editorOptionsPanel.add(sharpenButton);
    editorOptionsPanel.add(blurButton);
    editorOptionsPanel.add(ditherButton);
    editorOptionsPanel.add(rgbSplitButton);
    editorOptionsPanel.add(brightenButton);
    editorOptionsPanel.add(greyScaleButton);
    editorOptionsPanel.add(this.greyScaleUnaryButton);
    editorOptionsPanel.add(sepiaToneButton);
    editorOptionsPanel.repaint();
    setVisible(true);
  }

  @Override
  public void addFeatures(ViewUIFeatures features) {
    try {

      hFlipButton.addActionListener(evt -> {
        features.horizontalFlipFromView();
      });
      vFlipButton.addActionListener(evt -> {
        features.verticalFlipFromView();
      });
      fileOpenButton.addActionListener(evt -> {
        try {
          this.fileOpenDialog(features);
        } catch (IOException e) {
          this.logOutput(String.valueOf(e));
        }
      });
      fileSaveButton.addActionListener(evt -> {
        try {
          this.saveDialog(features);
        } catch (IOException e) {
          this.logOutput(String.valueOf(e));
        }
      });
      greyScaleUnaryButton.addActionListener(evt -> features.greyScaleFilterFromGui());
      sepiaToneButton.addActionListener(evt -> features.sepiaFilterFromGui());
      sharpenButton.addActionListener(evt -> features.sharpenFromGui());
      blurButton.addActionListener(evt -> features.blurFromGui());
      ditherButton.addActionListener(evt -> features.ditherFromGui());
      rgbSplitButton.addActionListener(evt -> {
        this.openRgbSplitDialog(features);
      });
      rgbCombineButton.addActionListener(evt -> {
        this.openRgbCombineDialog(features);
      });
      brightenButton.addActionListener(evt -> {
        this.openBrightenDialog(features);
      });
      greyScaleButton.addActionListener(evt -> {
        this.openGreyScaleCompDialog(features);
      });
    } catch (Exception e) {
      this.logOutput(e.getMessage());
    }
  }


  private boolean isValidFilePaths() {
    return !this.redPath.getText().isEmpty() && !this.greenPath.getText().isEmpty()
        && !this.bluePath.getText().isEmpty();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(e);
  }


  private void compButtonEventCallBacks() {
    this.redFileButton.addActionListener(evt -> {
      this.redPath.setText(this.filePathSelected());
    });
    this.greenFileButton.addActionListener(evt -> {
      this.greenPath.setText(this.filePathSelected());
    });
    this.blueFileButton.addActionListener(evt -> {
      this.bluePath.setText(this.filePathSelected());
    });
  }

  private void splitButtonEventCallBacks() {
    this.redFileButton.addActionListener(evt -> {
      this.redPath.setText(this.saveFilePathSelected());
    });
    this.greenFileButton.addActionListener(evt -> {
      this.greenPath.setText(this.saveFilePathSelected());
    });
    this.blueFileButton.addActionListener(evt -> {
      this.bluePath.setText(this.saveFilePathSelected());
    });
  }


  private void fileOpenDialog(ViewUIFeatures features) throws IOException {

    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif", "png");
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      String filePathName = f.getAbsolutePath();
      if (filePathName.length() > 0) {
        String[] pathValues = filePathName.split("/");
        features.loadFileTriggeredFromView(filePathName);
        this.fileOpenDisplay.setText(pathValues[pathValues.length - 1]);
        this.updateDisplayImage();
        if (!isImageLoaded) {
          this.isImageLoaded = true;
          this.addToOptionsPanel();
        }
      }
    }
  }

  private void saveDialog(ViewUIFeatures features) throws IOException {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      if (this.isImageLoaded) {
        features.saveImageFromView(f.getAbsolutePath());
      } else {
        this.logOutput("No image is loaded cannot save.");
      }
    }
  }

  private String filePathSelected() {
    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif", "png");
    fileChooser.setFileFilter(filter);
    String filePath = null;
    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      filePath = f.getAbsolutePath();
    }
    return filePath;
  }

  private String saveFilePathSelected() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(this);
    String filePath = null;
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      filePath = f.getAbsolutePath();
    }
    return filePath;
  }


  /**
   * Display the updated Image on to the View.
   */
  public void updateViewImage() {
    this.updateDisplayImage();
    if (!isImageLoaded) {
      this.isImageLoaded = true;
      this.addToOptionsPanel();
    }
  }

  @Override
  public void infoMessage(String str) {
    JOptionPane.showMessageDialog(this, str, "Info", JOptionPane.INFORMATION_MESSAGE);

  }

  private BufferedImage getLoadedImageObject() {
    return this.roModelObject.getBufferedImageOfObject("editing-image");
  }

  @Override
  public void logOutput(String str) {
    JOptionPane.showMessageDialog(this, str, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public List<String> getLoggedMessages() {
    return this.viewImpl.getLoggedMessages();
  }

  private void updateDisplayImage() {
    BufferedImage imgObj = this.getLoadedImageObject();
    this.imageDisplayPanel.removeAll();
    JPanel imagePanel = new JPanel();
    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(imgObj));
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    int imageWidth = 500;
    if (imgObj.getWidth() > this.imageDisplayPanel.getWidth()) {
      imageWidth = this.imageDisplayPanel.getWidth();
    } else {
      imageWidth = imgObj.getWidth();
    }
    imageScrollPane.setPreferredSize(new Dimension(this.imageDisplayPanel.getWidth(),
        this.imageDisplayPanel.getHeight() / 2));
    imageScrollPane.setMinimumSize(new Dimension(500, 400));
    imagePanel.add(imageScrollPane);
    this.imageDisplayPanel.add(imagePanel, BorderLayout.CENTER);
    this.updateHistogramChart();
  }

  private void updateHistogramChart() {
    ChartPanel histogramChartPanel = createHistogramChartPanel(this.getLoadedImageObject());
    JPanel histogramControlPanel = createControlPanel();
    this.imageDisplayPanel.add(histogramChartPanel);
    this.imageDisplayPanel.add(histogramControlPanel);
    this.imageDisplayPanel.repaint();
    setVisible(true);
  }


  private JPanel createControlPanel() {
    JPanel panel = new JPanel();
    panel.add(new JCheckBox(new CheckBoxAction(0)));
    panel.add(new JCheckBox(new CheckBoxAction(1)));
    panel.add(new JCheckBox(new CheckBoxAction(2)));
    panel.add(new JCheckBox(new CheckBoxAction(3)));
    return panel;
  }

  private class CheckBoxAction extends AbstractAction {

    private final int seriesVal;

    public CheckBoxAction(int legendNumber) {
      this.seriesVal = legendNumber;
      this.putValue(NAME, (String) histDataSetValues.getSeriesKey(legendNumber));
      this.putValue(SELECTED_KEY, true);
      renderer.setSeriesVisible(legendNumber, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      renderer.setSeriesVisible(seriesVal, !renderer.getSeriesVisible(seriesVal));
    }
  }

  private double[] getIntensityValues(BufferedImage image) {
    double[] intensity = new double[image.getWidth() * image.getHeight()];
    int index = 0;
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        Color color = new Color(image.getRGB(col, row), true);
        intensity[index] = (color.getRed() + color.getGreen() + color.getBlue()) / 3.0;
        index++;
      }
    }
    return intensity;
  }

  private ChartPanel createHistogramChartPanel(BufferedImage image) {
    this.histDataSetValues = new HistogramDataset();
    Raster raster = image.getRaster();
    final int w = image.getWidth();
    final int h = image.getHeight();
    double[] r = new double[w * h];
    double[] intensity = new double[w * h];
    r = raster.getSamples(0, 0, w, h, 0, r);
    this.histDataSetValues.addSeries("Red", r, BINS);
    r = raster.getSamples(0, 0, w, h, 1, r);
    this.histDataSetValues.addSeries("Green", r, BINS);
    r = raster.getSamples(0, 0, w, h, 2, r);
    this.histDataSetValues.addSeries("Blue", r, BINS);
    intensity = this.getIntensityValues(image);
    this.histDataSetValues.addSeries("Intensity", intensity, BINS);
    JFreeChart lineChart = ChartFactory.createXYLineChart(
        "Histogram Chart",
        "Pixel Distribution", "Frequency",
        this.histDataSetValues,
        PlotOrientation.VERTICAL,
        true, true, false);
    XYPlot plot = (XYPlot) lineChart.getPlot();
    renderer = (XYLineAndShapeRenderer) plot.getRenderer();
    renderer.setDrawSeriesLineAsPath(true);
    Paint[] paintArray = {
        new Color(0x80ff0050, true),
        new Color(0x82010000, true),
        new Color(0x88ffff11, true),
        new Color(0x8111df11, true),
    };
    plot.setDrawingSupplier(new DefaultDrawingSupplier(
        paintArray,
        DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
        DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
        DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
        DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
        DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
    ChartPanel panel = new ChartPanel(lineChart);
    panel.setMouseWheelEnabled(true);
    return panel;
  }

  private void openRgbSplitDialog(ViewUIFeatures feat) {
    JPanel splitDialogPanel = new JPanel();
    splitDialogPanel.setLayout(new BoxLayout(splitDialogPanel, BoxLayout.PAGE_AXIS));
    this.redFileButton = new JButton("Save Red Comp Image as");
    this.greenFileButton = new JButton("Save Green Comp Image as");
    this.blueFileButton = new JButton("Save Blue Comp Image as");
    this.redPath = new JLabel("");
    this.greenPath = new JLabel("");
    this.bluePath = new JLabel("");
    splitDialogPanel.add(redFileButton);
    splitDialogPanel.add(this.redPath);
    splitDialogPanel.add(greenFileButton);
    splitDialogPanel.add(this.greenPath);
    splitDialogPanel.add(blueFileButton);
    splitDialogPanel.add(this.bluePath);
    splitDialogPanel.setPreferredSize(new Dimension(200, 150));
    this.splitButtonEventCallBacks();
    int result = JOptionPane.showConfirmDialog(null, splitDialogPanel,
        "Split An Image Process", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      if (this.isValidFilePaths()) {
        feat.splitImageFromGui(this.redPath.getText(), this.greenPath.getText(),
            this.bluePath.getText());
      } else {
        this.logOutput("Not valid file paths provided.");
      }
    }
  }

  private void openRgbCombineDialog(ViewUIFeatures feat) {
    JPanel combineDialogPanel = new JPanel();
    combineDialogPanel.setLayout(new BoxLayout(combineDialogPanel, BoxLayout.PAGE_AXIS));
    this.redFileButton = new JButton("Load Red Comp Image");
    this.greenFileButton = new JButton("Load Green Comp Image");
    this.blueFileButton = new JButton("Load Blue Comp Image");
    this.redPath = new JLabel("");
    this.greenPath = new JLabel("");
    this.bluePath = new JLabel("");
    combineDialogPanel.add(redFileButton);
    combineDialogPanel.add(this.redPath);
    combineDialogPanel.add(greenFileButton);
    combineDialogPanel.add(this.greenPath);
    combineDialogPanel.add(blueFileButton);
    combineDialogPanel.add(this.bluePath);
    combineDialogPanel.setPreferredSize(new Dimension(200, 150));
    this.compButtonEventCallBacks();
    int result = JOptionPane.showConfirmDialog(null, combineDialogPanel,
        "RGB Combine Image Process", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      if (this.isValidFilePaths()) {
        feat.combineImageFromGui(this.redPath.getText(), this.greenPath.getText(),
            this.bluePath.getText());
      } else {
        this.logOutput("Image files are missing to combine.");
      }
    }
  }

  private void openGreyScaleCompDialog(ViewUIFeatures feat) {
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Greyscale Types"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[6];
    ButtonGroup radioGroup = new ButtonGroup();
    for (int index = 0; index < radioButtons.length; index++) {
      switch (index) {
        case 0:
          radioButtons[index] = new JRadioButton("red-component");
          radioButtons[index].setActionCommand("red-component");
          break;
        case 1:
          radioButtons[index] = new JRadioButton("green-component");
          radioButtons[index].setActionCommand("green-component");
          break;
        case 2:
          radioButtons[index] = new JRadioButton("blue-component");
          radioButtons[index].setActionCommand("blue-component");
          break;
        case 3:
          radioButtons[index] = new JRadioButton("value-component");
          radioButtons[index].setActionCommand("value-component");
          break;
        case 4:
          radioButtons[index] = new JRadioButton("intensity-component");
          radioButtons[index].setActionCommand("intensity-component");
          break;
        case 5:
          radioButtons[index] = new JRadioButton("luma-component");
          radioButtons[index].setActionCommand("luma-component");
          break;
        default:
          break;
      }
      radioGroup.add(radioButtons[index]);
      radioPanel.add(radioButtons[index]);
    }
    int result = JOptionPane.showConfirmDialog(null, radioPanel,
        "Greyscale Specific Image Component", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      if (radioGroup.getSelection().getActionCommand().isEmpty()) {
        this.logOutput("No radio button selected.");
      } else {
        feat.greyScaleCompFromGui(radioGroup.getSelection().getActionCommand());
      }
    }
  }

  private void openBrightenDialog(ViewUIFeatures feat) {
    JFormattedTextField brightenField;
    JPanel brightenDialogPanel = new JPanel();
    brightenDialogPanel.setLayout((new BoxLayout(brightenDialogPanel, BoxLayout.Y_AXIS)));
    // Set the layout to BoxLayout with vertical alignment);
    NumberFormat format = NumberFormat.getIntegerInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(-255);
    formatter.setMaximum(255);
    formatter.setAllowsInvalid(false);
    formatter.setCommitsOnValidEdit(true);
    brightenField = new JFormattedTextField(formatter);
    JLabel infoLabel = new JLabel("Positive - Brighten, Negative - Darken");
    brightenDialogPanel.add(brightenField);
    brightenDialogPanel.add(infoLabel);
    int result = JOptionPane.showConfirmDialog(null, brightenDialogPanel,
        "Brighten or Darken Image", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      if (brightenField.getText().equals("0")) {
        this.logOutput("Brighten value cannot be zero.");
      } else {
        feat.brightenFromView((Integer) brightenField.getValue());
      }
    }
  }

}
