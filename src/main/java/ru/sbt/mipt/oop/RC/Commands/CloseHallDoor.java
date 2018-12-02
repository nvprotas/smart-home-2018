package ru.sbt.mipt.oop.RC.Commands;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.HomeEntities.Door;
import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Sensors.SensorEvent;
import ru.sbt.mipt.oop.Sensors.SensorEventType;
import static ru.sbt.mipt.oop.Sensors.SensorEventType.DOOR_CLOSED;

public class CloseHallDoor implements Undoable {

    final SmartHome smartHome;
    final String RCID;

    public CloseHallDoor(SmartHome smartHome, String rcid) {
        this.smartHome = smartHome;
        RCID = rcid;
    }

    @Override
    public void undo() {
        smartHome.execute(o -> {
            if (o instanceof Room){
                Room room = (Room)o;
                if (room.getName().equals("hall")) {
                    room.execute(o1 -> {
                        if (o1 instanceof Door) {
                            Door door = (Door)o1;
                            door.setState(door.getId(), false);
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
                        if (o1 instanceof Door) {
                            Door door = (Door)o1;
                            door.setState(door.getId(), false);
                        }
                    });
                    smartHome.execute(o2 -> {
                        if (o2 instanceof Light) {
                            Light light = (Light) o2;
                            light.setState(light.getId(),false);
                        }
                    });
                }
            }
        }
        );
    }

    @Override
    public String getID() {
        return RCID;
    }
}
