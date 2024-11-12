package org.example;

import java.awt.*;
import java.util.Observable;

public class Perspective extends Observable {
    private double scale = 1.0;
    private Point position = new Point(0, 0);
    public double getScale() { return scale; }
    public void setScale(double scale) {
        this.scale = scale;
        setChanged();
        notifyObservers();
    }

    public Point getPosition() { return position; }
    public void setPosition(Point position) {
        this.position = position;
        setChanged();
        notifyObservers();
    }
}
