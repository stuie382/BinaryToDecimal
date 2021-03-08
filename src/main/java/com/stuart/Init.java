package com.stuart;

import javax.swing.*;

public class Init {
    /**
     * Main method.
     *
     * @param args - the command line arguments, not used in this program.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Binary/Decimal conversion program.");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BinaryDecimalConverter converterApp = new BinaryDecimalConverter();
        frame.setContentPane(converterApp.getBaseArea());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
