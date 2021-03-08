package com.stuart;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BinaryDecimalConverter {

    private static final String INSTRUCTIONS =
            "Please enter either a positive integer or a binary string.";
    private final JPanel baseArea = new JPanel();
    private final JPanel displayArea = new JPanel();
    private final JPanel controlArea = new JPanel();
    private JTextField userInputTxt;
    private JLabel outputAreaLbl;

    /**
     * Simple constructor that will initialise the GUI and then await user input.
     */
    public BinaryDecimalConverter() {
        setupGUI();
    }

    /**
     * Get the base area panel to allow it to be attached to the JFrame
     *
     * @return - The base area JPanel
     */
    public final JPanel getBaseArea() {
        return baseArea;
    }

    /**
     * Set up all GUI elements, add the action listeners, and set initial values.
     */
    private void setupGUI() {
        baseArea.setLayout(new FlowLayout());

        displayArea.setPreferredSize(new Dimension(500, 250));
        displayArea.setBorder(BorderFactory.createLineBorder(Color.blue));

        outputAreaLbl = new JLabel(INSTRUCTIONS);
        displayArea.add(outputAreaLbl);

        controlArea.setPreferredSize(new Dimension(200, 200));
        controlArea.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton decToBinaryBtn = new JButton("Decimal To Binary Convert");
        decToBinaryBtn.addActionListener(e -> decimalToBinaryConvert());
        controlArea.add(decToBinaryBtn);

        JButton binaryToDecBtn = new JButton("Binary To Decimal Convert");
        binaryToDecBtn.addActionListener(e -> binaryToDecimalConvert());
        controlArea.add(binaryToDecBtn);

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> resetGui(INSTRUCTIONS));
        controlArea.add(resetBtn);

        userInputTxt = new JTextField("0", 8);
        userInputTxt.setBorder(BorderFactory.createLineBorder(Color.black));
        controlArea.add(userInputTxt);

        baseArea.add(displayArea);
        baseArea.add(controlArea);
    }

    /**
     * Action to convert the user supplied information from binary into decimal.
     */
    protected void binaryToDecimalConvert() {
        boolean successfulConversion = convertAndPrint(new BinaryToDecimalConverter());
        if (!successfulConversion) {
            resetGui("Problem converting the supplied value into decimal.");
        }
    }

    /**
     * Action to convert the user supplied information from decimal into binary.
     */
    protected void decimalToBinaryConvert() {
        boolean successfulConversion = convertAndPrint(new DecimalToBinaryConverter());
        if (!successfulConversion) {
            resetGui("Problem encountered converting from decimal to binary.");
        }
    }

    /**
     * Utility method to perform conversion and print the output of a successful
     * conversion to the screen.
     *
     * @param converter - An implementation of the {@link Converter} interface
     * @return True if the conversion was successful, else false
     */
    private boolean convertAndPrint(Converter converter) {
        final ConversionResult result = converter.convert(userInputTxt.getText().trim());
        if (!result.wasSuccessful()) {
            return false;
        }
        printToScreen(result.getConversionResult());
        return true;
    }

    /**
     * Method to print the {@code valueToPrint} in the display area.
     *
     * @param valueToPrint - The value to print to the screen
     */
    private void printToScreen(final String valueToPrint) {
        resetGui("");
        for (int count = 0; count < valueToPrint.length(); count++) {
            JLabel label = loadImageFromDisk(valueToPrint.charAt(count));
            if (label == null) {
                return;
            }
            displayArea.add(label);
            displayArea.validate();
        }
    }

    /**
     * Build the URL for the number image to load from disk.
     *
     * @param fileName - The file name to load from the disk as a single character.
     * @return JLabel containing the image, or a null if a problem was encountered.
     */
    private JLabel loadImageFromDisk(char fileName) {
        // convert character in string to match numbered .gif file
        final String urlToLoad = "/images/" + fileName + ".gif";
        URL resource = BinaryDecimalConverter.class.getResource(urlToLoad);
        if (resource == null) {
            resetGui("Couldn't find the correct file to display.");
            return null;
        }
        ImageIcon temp = new ImageIcon(resource);
        return new JLabel(temp);
    }

    /**
     * Reset the GUI to an initial state, with the supplied message set in the display area.
     *
     * @param message - The message to display in the display area.
     */
    protected void resetGui(final String message) {
        outputAreaLbl.setText(message);
        userInputTxt.setText("0");
        displayArea.removeAll();
        displayArea.repaint();
        displayArea.add(outputAreaLbl);
    }
}
