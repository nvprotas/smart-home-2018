package ru.sbt.mipt.oop.RC.Commands;

import ru.sbt.mipt.oop.HomeEntities.Door;
import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class TurnOffHallLight implements Undoable {

    final SmartHome smartHome;
    final String RCID;

    public TurnOffHallLight(SmartHome smartHome, String RCID) {
        this.smartHome = smartHome;
        this.RCID = RCID;
    }

    @Override
    public void undo() {
        smartHome.execute(o -> {
            if (o instanceof Room){
                Room room = (Room)o;
                if (room.getName().equals("hall")){
                    room.execute(o1 -> {
                        if (o1 instanceof Light) {
                            Light light= (Light) o1;
                            light.setState(light.getId(), true);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void execute() {
        CommandHistory.addToHist(this);
        smartHome.execute(o -> {
            if (o instanceof Room){
                Room room = (Room)o;
                if (room.getName().equals("hall")) {
                    room.execute(o1 -> {
                        if (o1 instanceof Light) {
                            Light light = (Light) o1;
                            light.setState(light.getId(), false);
                        }
                    });
                }
            }
        });
    }

    @Override
    public String getID() {
        return RCID;
    }
}
