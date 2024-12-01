package controller;

import model.Perspective;

public class ZoomCommand implements Command {
    private Perspective perspective;
    private double originalScale;
    private double newScale;

    public ZoomCommand(Perspective perspective, double newScale) {
        this.perspective = perspective;
        this.originalScale = perspective.getScale();
        this.newScale = newScale;
    }

    @Override
    public void execute() {
        perspective.setScale(newScale);
    }

    @Override
    public void undo() {
        perspective.setScale(originalScale);
    }
}
