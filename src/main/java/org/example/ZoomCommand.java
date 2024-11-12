package org.example;

public class ZoomCommand implements Command {
    private Perspective perspective;
    private double previousScale, newScale;
    public ZoomCommand(Perspective perspective, double newScale) {
        this.perspective = perspective;
        this.newScale = newScale;
    }
    @Override
    public void execute() {
        previousScale = perspective.getScale();
        perspective.setScale(newScale);
    }
    @Override
    public void undo() {
        perspective.setScale(previousScale);
    }
}
