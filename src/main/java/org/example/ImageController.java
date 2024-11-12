package org.example;

public class ImageController {
    private CommandManager commandManager;
    public ImageController() {
        this.commandManager = CommandManager.getInstance();
    }
    public void zoom(Perspective perspective, double newScale) {
        commandManager.executeCommand(new ZoomCommand(perspective, newScale));
    }
    public void undoLastAction() {
        commandManager.undo();
    }
}
