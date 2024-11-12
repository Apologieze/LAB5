package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class ImageModel extends Observable {
    private BufferedImage image;
    public BufferedImage getImage() { return image; }
    public void setImage(BufferedImage image) {
        this.image = image;
        setChanged();
        notifyObservers();
    }
}

