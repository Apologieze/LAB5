package controller;

import model.Perspective;
import services.SaveService;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslateCommand implements Command {
    private Perspective perspective;
    private Point originalPosition;
    private Point newPosition;

    public TranslateCommand(Perspective perspective, Point newPosition) {
        this.perspective = perspective;
        this.originalPosition = perspective.getPosition();
        this.newPosition = newPosition;
    }

    @Override
    public void execute() {
        perspective.setPosition(newPosition);
    }

    @Override
    public void undo() {
        perspective.setPosition(originalPosition);
    }
}

