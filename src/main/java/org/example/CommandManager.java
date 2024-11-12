package org.example;

import java.util.Stack;

public class CommandManager {
    private static CommandManager instance;
    private Stack<Command> commandStack = new Stack<>();
    public static CommandManager getInstance() {
        if (instance == null) instance = new CommandManager();
        return instance;
    }
    public void executeCommand(Command command) {
        command.execute();
        commandStack.push(command);
    }
    public void undo() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.pop();
            command.undo();
        }
    }
}
