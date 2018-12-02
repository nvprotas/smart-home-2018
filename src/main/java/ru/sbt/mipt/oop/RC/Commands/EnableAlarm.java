package ru.sbt.mipt.oop.RC.Commands;

import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class EnableAlarm implements Undoable {

    final SmartHome smartHome;
    final String RCID;

    public EnableAlarm(SmartHome smartHome, String rcid) {
        this.smartHome = smartHome;
        RCID = rcid;
    }

    @Override
    public void undo() {
        smartHome.alarmDisable("password");
    }

    @Override
    public void execute() {
        CommandHistory.addToHist(this);
        smartHome.alarmEnable("password");
    }

    @Override
    public String getID() {
        return RCID;
    }
}
