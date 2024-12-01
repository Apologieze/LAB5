package controller;

import model.Perspective;
import services.SaveService;

public class SaveCommand implements Command {
    private static SaveCommand instance;
    private SaveService saveService;
    private Perspective perspective;
    private String filePath;

    private SaveCommand(SaveService saveService) {
        this.saveService = saveService;
    }

    public static SaveCommand getInstance(SaveService saveService) {
        if (instance == null) {
            instance = new SaveCommand(saveService);
        }
        return instance;
    }

    public void configure(Perspective perspective, String filePath) {
        this.perspective = perspective;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        saveService.savePerspective(perspective, filePath);
    }

    @Override
    public void undo() {
        // Typically, save operations cannot be undone
        System.out.println("Cannot undo save operation");
    }
}
