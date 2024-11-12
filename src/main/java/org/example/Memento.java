package org.example;

import java.awt.*;

public class Memento {
    private double scale;
    private Point position;
    public Memento(Perspective perspective) {
        this.scale = perspective.getScale();
        this.position = perspective.getPosition();
    }
    public void restore(Perspective perspective) {
        perspective.setScale(scale);
        perspective.setPosition(position);
    }
}
