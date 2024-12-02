package view;

import controller.PerspectiveController;
import model.Image;
import model.Perspective;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class ImageTab extends JPanel implements Serializable {
    private Perspective perspective;
    private transient PerspectiveView perspectiveView;
    private transient ThumbnailView thumbnailView;
    private transient PerspectiveController controller;
    private String title;

    public ImageTab(String title, Image image) {
        this(title, new Perspective());
        perspective.setImage(image);
    }

    public ImageTab(String title, Perspective perspective) {
        this.title = title;
        setLayout(new BorderLayout());
        this.perspective = perspective;

        controller = new PerspectiveController();

        perspectiveView = new PerspectiveView(perspective, controller);
        thumbnailView = new ThumbnailView(perspective);

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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("Serializing ImageTab"+title);
        out.defaultWriteObject();
    }
}
