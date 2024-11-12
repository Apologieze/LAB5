package org.example;

import java.awt.*;
import java.util.Observable;

public class Perspective extends Observable {
    private double scale;
    private Point position;
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
