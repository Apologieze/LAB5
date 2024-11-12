package org.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Basic Swing Window");
            frame.setSize(400, 300); // Set window size to 400x300 pixels
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
            frame.setVisible(true); // Make the window visible
        });
    }
}
