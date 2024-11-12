package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ImageView extends JPanel implements Observer {
    private ImageModel imageModel;
    private Perspective perspective;
    public ImageView(ImageModel imageModel, Perspective perspective) {
        this.imageModel = imageModel;
        this.perspective = perspective;
        imageModel.addObserver(this);
        perspective.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the scaled and translated image based on perspective
    }
}

