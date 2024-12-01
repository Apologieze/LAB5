package controller;

import model.Perspective;
import services.SaveService;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.Serializable;

public class PerspectiveController {
    private transient CommandManager commandManager = CommandManager.getInstance();

    private Point lastDragPoint;

    public void startTranslation(MouseEvent e) {
        lastDragPoint = e.getPoint();
    }

    public void executeTranslation(MouseEvent e, Perspective perspective) {
        if (lastDragPoint != null) {
            int dx = e.getX() - lastDragPoint.x;
            int dy = e.getY() - lastDragPoint.y;

            Point currentPos = perspective.getPosition();
            Point newPos = new Point(
                    currentPos.x + dx,
                    currentPos.y + dy
            );

            TranslateCommand translateCommand = new TranslateCommand(perspective, newPos);
            commandManager.addCommand(perspective, translateCommand);

            lastDragPoint = e.getPoint();
        }
    }

    public void handleMouseZoom(MouseWheelEvent evt, Perspective perspective) {
        double currentScale = perspective.getScale();

        double zoomFactor = evt.getWheelRotation() < 0 ? 1.1 : 0.9;
        double newScale = currentScale * zoomFactor;

        ZoomCommand zoomCommand = new ZoomCommand(perspective, newScale);
        commandManager.addCommand(perspective, zoomCommand);
    }

    public void handleZoom(Perspective perspective, double scale) {
        ZoomCommand zoomCommand = new ZoomCommand(perspective, scale);
        commandManager.addCommand(perspective, zoomCommand);
    }

    public void handleTranslate(Perspective perspective, int x, int y) {
        TranslateCommand translateCommand = new TranslateCommand(perspective, new Point(x, y));
        commandManager.addCommand(perspective, translateCommand);
    }


    public void undo(Perspective perspective) {
        commandManager.undo(perspective);
    }
}