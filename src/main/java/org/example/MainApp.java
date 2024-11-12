package org.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        ImageModel imageModel = new ImageModel();
        Perspective perspective = new Perspective();
        ImageController controller = new ImageController();

        JFrame frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ImageView mainView = new ImageView(imageModel, perspective);
        ThumbnailView thumbnail = new ThumbnailView(imageModel);

        frame.add(thumbnail, BorderLayout.WEST);
        frame.add(mainView, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
