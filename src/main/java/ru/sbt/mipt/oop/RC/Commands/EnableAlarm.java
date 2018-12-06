package ru.sbt.mipt.oop.RC.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.HomeConfiguration;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class EnableAlarm implements Undoable {

    final SmartHome smartHome;
    final String RCID;
    CommandHistory commandHistory;

    @Autowired
    public EnableAlarm(SmartHome smartHome, String rcid, CommandHistory commandHistory) {
        this.smartHome = smartHome;
        RCID = rcid;
        this.commandHistory = commandHistory;
    }

    @Override
    public void undo() {
        smartHome.alarmDisable("password");
    }

    @Override
    public void execute() {
        commandHistory.addToHist(this);
        smartHome.alarmEnable("password");
    }

    @Override
    public String getID() {
        return RCID;
    }
}
