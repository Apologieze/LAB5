package view;

import model.Image;
import model.Observer;
import model.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ThumbnailView extends JPanel implements Observer {
    private Perspective perspective;
    private BufferedImage image;

    public ThumbnailView(Perspective perspective) {
        this.perspective = perspective;
        perspective.attach(this);
        setPreferredSize(new Dimension(500, 350));
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (perspective.getImage() != null) {
            try {
                image = ImageIO.read(new ByteArrayInputStream(perspective.getImage().getData()));

                double scaleX = (double) getWidth() / image.getWidth();
                double scaleY = (double) getHeight() / image.getHeight();
                double scale = Math.min(scaleX, scaleY);

                int scaledWidth = (int)(image.getWidth() * scale);
                int scaledHeight = (int)(image.getHeight() * scale);

                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;

                g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}