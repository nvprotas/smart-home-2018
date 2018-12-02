package ru.sbt.mipt.oop.RC.Commands;

import java.util.Stack;

class CommandHistory {
    private static Stack<Undoable> history = new Stack<Undoable>();
    private static String LastRCID = "";

    static void addToHist(Undoable command) {
        history.push(command);
    }

//    private static String getLastRCID(){}

    static boolean isLastCommandIsMine(String RCID) {
        if (history.isEmpty()) {return false;}
        return (history.peek().getID().equals(RCID));
    }

    static Undoable getFromHist() {
        return history.pop();
    }
}
