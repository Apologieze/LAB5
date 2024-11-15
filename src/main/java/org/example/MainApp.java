package org.example;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        File file = new File("./image.png");

        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
        BufferedImage img = ImageIO.read(file);
        ImageModel imageModel = new ImageModel();
        imageModel.setImage(img);
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
