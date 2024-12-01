package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Perspective extends Subject {
    private double scale = 1.0;
    private Point position = new Point(0, 0);
    private Image image;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
        notifyObservers();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        notifyObservers();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        notifyObservers();
    }

    public void save(String filePath) {
        // Placeholder for save functionality
        // In a real implementation, this would use SaveService
        System.out.println("Saving perspective to: " + filePath);
    }
}