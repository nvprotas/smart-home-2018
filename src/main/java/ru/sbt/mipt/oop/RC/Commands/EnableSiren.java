package ru.sbt.mipt.oop.RC.Commands;

import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class EnableSiren implements Command {

    final SmartHome smartHome;
    final String RCID;

    public EnableSiren(SmartHome smartHome, String rcid) {
        this.smartHome = smartHome;
        RCID = rcid;
    }

//    @Override
//    public void undo() {
//        smartHome.alarmDisable("password");
//    }

    @Override
    public void execute() {
//        CommandHistory.addToHist(this);
        smartHome.sirenEnable();
    }

    @Override
    public String getID() {
        return RCID;
    }
}
