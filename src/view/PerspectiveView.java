package view;

import model.Observer;
import model.Perspective;
import controller.PerspectiveController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PerspectiveView extends JPanel implements Observer {
    private Perspective perspective;
    private BufferedImage image;
    private PerspectiveController controller;

    public PerspectiveView(Perspective perspective, PerspectiveController controller) {
        this.perspective = perspective;
        this.controller = controller;
        perspective.attach(this);
        setBackground(new Color(80,80,80));

        setPreferredSize(new Dimension(800, 600));

        // Simplified event listeners that delegate to controller
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.startTranslation(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                controller.executeTranslation(e, perspective);
            }
        });

        addMouseWheelListener((MouseWheelEvent evt) -> {
            controller.handleMouseZoom(evt, perspective);
        });
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

                Graphics2D g2d = (Graphics2D) g;
                g2d.scale(perspective.getScale(), perspective.getScale());

                Point pos = perspective.getPosition();
                g2d.drawImage(image, pos.x, pos.y, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}