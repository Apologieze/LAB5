package org.example;

import java.util.Stack;

public interface Command {
    void execute();
    void undo();
}

