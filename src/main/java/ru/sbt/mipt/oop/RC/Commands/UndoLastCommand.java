package ru.sbt.mipt.oop.RC.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.HomeConfiguration;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class UndoLastCommand implements Command {


    final String RCID;
    final SmartHome smartHome;
    CommandHistory commandHistory;

    @Autowired
    public UndoLastCommand(SmartHome smartHome, String rcid, CommandHistory commandHistory) {
        this.smartHome = smartHome;
        RCID = rcid;
        this.commandHistory = commandHistory;
    }
    @Override
    public void execute() {
        if (commandHistory.isLastCommandIsMine(RCID)) {
            commandHistory.getFromHist().undo();
        }
    }

    @Override
    public String getID() {
        return RCID;
    }
}
