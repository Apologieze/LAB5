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
    private String title;

    public ImageTab(String title, byte[] imageData) {
        this.title = title;

        setLayout(new BorderLayout());

        perspective = new Perspective();
        Image image = new Image(imageData);
        perspective.setImage(image);

        controller = new PerspectiveController();

        perspectiveView = new PerspectiveView(perspective, controller);
        thumbnailView = new ThumbnailView(perspective);

        // Layout
        add(perspectiveView, BorderLayout.CENTER);
        add(thumbnailView, BorderLayout.EAST);
    }

    public void undo() {
        controller.undo(perspective);
    }

    public String getTitle() {
        return title;
    }

    public Perspective getPerspective() {
        return perspective;
    }
}
