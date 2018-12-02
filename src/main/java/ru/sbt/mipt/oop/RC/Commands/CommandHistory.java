package ru.sbt.mipt.oop.RC.Commands;

import java.util.Stack;

public class CommandHistory {
    static Stack<Undoable> history = new Stack<Undoable>();
    static String LastRCID = "";

    public static void addToHist(Undoable command) {
        history.push(command);
    }

//    private static String getLastRCID(){}

    public static boolean isLastCommandIsMine(String RCID) {
        if (history.isEmpty()) {return false;}
        return (history.peek().getID().equals(RCID));
    }

    public static Undoable getFromHist() {
        return history.pop();
    }
}
