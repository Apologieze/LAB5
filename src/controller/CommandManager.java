package controller;

import model.Perspective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private static CommandManager instance;
    private Map<Perspective, List<Command>> commands = new HashMap<>();

    private CommandManager() {}

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void addCommand(Perspective perspective, Command command) {
        commands.computeIfAbsent(perspective, k -> new ArrayList<>()).add(command);
        command.execute();
    }

    public void undo(Perspective perspective) {
        List<Command> perspectiveCommands = commands.get(perspective);
        if (perspectiveCommands != null && !perspectiveCommands.isEmpty()) {
            Command lastCommand = perspectiveCommands.remove(perspectiveCommands.size() - 1);
            lastCommand.undo();
        }
    }
}
