package controller;

import model.Perspective;
import services.SaveService;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class PerspectiveController {
    private CommandManager commandManager = CommandManager.getInstance();
    private SaveService saveService = new SaveService();

    // Translation-related fields
    private Point lastDragPoint;


    public void startTranslation(MouseEvent e) {
        lastDragPoint = e.getPoint();
    }

    public void executeTranslation(MouseEvent e, Perspective perspective) {
        if (lastDragPoint != null) {
            int dx = e.getX() - lastDragPoint.x;
            int dy = e.getY() - lastDragPoint.y;

            // Get current position and calculate new position
            Point currentPos = perspective.getPosition();
            Point newPos = new Point(
                    currentPos.x + dx,
                    currentPos.y + dy
            );

            // Create and execute translation command
            TranslateCommand translateCommand = new TranslateCommand(perspective, newPos);
            commandManager.addCommand(perspective, translateCommand);

            lastDragPoint = e.getPoint();
        }
    }

    public void handleMouseZoom(MouseWheelEvent evt, Perspective perspective) {
        double currentScale = perspective.getScale();

        // Adjust zoom sensitivity
        double zoomFactor = evt.getWheelRotation() < 0 ? 1.1 : 0.9;
        double newScale = currentScale * zoomFactor;

        // Create and execute zoom command
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

    public void handleSave(Perspective perspective, String filePath) {
        SaveCommand saveCommand = SaveCommand.getInstance(saveService);
        saveCommand.configure(perspective, filePath);
        commandManager.addCommand(perspective, saveCommand);
    }

    public void undo(Perspective perspective) {
        commandManager.undo(perspective);
    }
}