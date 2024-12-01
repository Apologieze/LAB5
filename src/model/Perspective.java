package model;

import java.awt.Point;
import java.io.Serializable;

public class Perspective extends Subject implements Serializable {
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

}