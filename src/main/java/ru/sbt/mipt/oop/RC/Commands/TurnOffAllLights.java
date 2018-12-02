package ru.sbt.mipt.oop.RC.Commands;

import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class TurnOffAllLights implements Undoable {

    final SmartHome smartHome;
    final String RCID;

    public TurnOffAllLights(SmartHome smartHome, String rcid) {
        this.smartHome = smartHome;
        RCID = rcid;
    }


    @Override
    public void undo() {
        smartHome.execute(o -> {
            if (o instanceof Room){
                Room room = (Room)o;
                room.execute(o1 -> {
                    if (o1 instanceof Light) {
                        Light light= (Light) o1;
                        light.setState(light.getId(), true);
                    }
                });
            }
        });
    }

    @Override
    public void execute() {
        CommandHistory.addToHist(this);
        smartHome.execute(o -> {
            if (o instanceof Room){
                Room room = (Room)o;
                room.execute(o1 -> {
                    if (o1 instanceof Light) {
                        Light light= (Light) o1;
                        light.setState(light.getId(), false);
                    }
                });
            }
        });


    }

    @Override
    public String getID() {
        return RCID;
    }
}
