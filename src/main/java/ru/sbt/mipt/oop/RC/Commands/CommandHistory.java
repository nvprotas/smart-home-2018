package ru.sbt.mipt.oop.RC.Commands;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
@Scope("singleton")
public class CommandHistory {

    Stack<Undoable> history;
    String LastRCID;

    public CommandHistory() {
        history = new Stack<Undoable>();
        LastRCID = "";
    }

    public void addToHist(Undoable command) {
        history.push(command);
    }


    public boolean isLastCommandIsMine(String RCID) {
        if (history.isEmpty()) {return false;}
        return (history.peek().getID().equals(RCID));
    }

    public Undoable getFromHist() {
        return history.pop();
    }
}
