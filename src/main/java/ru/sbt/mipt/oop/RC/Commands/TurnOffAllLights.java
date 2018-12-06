package ru.sbt.mipt.oop.RC.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.HomeConfiguration;
import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

public class TurnOffAllLights implements Undoable {


    final SmartHome smartHome;
    final String RCID;
    CommandHistory commandHistory;

    @Autowired
    public TurnOffAllLights(SmartHome smartHome, String rcid, CommandHistory commandHistory) {
        this.smartHome = smartHome;
        RCID = rcid;
        this.commandHistory = commandHistory;
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
        commandHistory.addToHist(this);
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
