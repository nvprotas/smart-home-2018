package ru.sbt.mipt.oop.RC.Commands;

import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class UndoLastCommand implements Command {
    final String RCID;
    final SmartHome smartHome;

    public UndoLastCommand(String rcid, SmartHome smartHome) {
        RCID = rcid;
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        if (CommandHistory.isLastCommandIsMine(RCID)){
            CommandHistory.getFromHist().undo();
        }
    }

    @Override
    public String getID() {
        return RCID;
    }
}
