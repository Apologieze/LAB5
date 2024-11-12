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
        if (imageModel.getImage() != null) {
            Graphics2D g2d = (Graphics2D) g;
            // Apply perspective transformations
            g2d.translate(perspective.getPosition().getX(), perspective.getPosition().getY());
            g2d.scale(perspective.getScale(), perspective.getScale());
            // Draw the image
            g2d.drawImage(imageModel.getImage(), 0, 0, this);
        }
    }
}

