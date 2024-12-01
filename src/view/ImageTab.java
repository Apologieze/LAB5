package view;

import controller.PerspectiveController;
import model.Image;
import model.Perspective;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class ImageTab extends JPanel {
    private Perspective perspective;
    private PerspectiveView perspectiveView;
    private ThumbnailView thumbnailView;
    private PerspectiveController controller;

    public ImageTab(String imageName, byte[] imageData) {
        setLayout(new BorderLayout());

        // Create model
        Image image = new Image(imageData);
        perspective = new Perspective();
        perspective.setImage(image);

        // Create controller
        controller = new PerspectiveController();

        // Create views
        perspectiveView = new PerspectiveView(perspective, controller);
        thumbnailView = new ThumbnailView(perspective);

        // Layout
        add(perspectiveView, BorderLayout.CENTER);
        add(thumbnailView, BorderLayout.EAST);
    }

    public void undo() {
        controller.undo(perspective);
    }
}
