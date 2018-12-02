package ru.sbt.mipt.oop.RC.Commands;

import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class TurnOnAllLights implements Undoable{

    final SmartHome smartHome;
    final String RCID;

    public TurnOnAllLights(SmartHome smartHome, String RCID) {
        this.smartHome = smartHome;
        this.RCID = RCID;
    }
    @Override
    public void undo() {
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
    public void execute() {
        CommandHistory.addToHist(this);
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
    public String getID() {
        return RCID;
    }
}
