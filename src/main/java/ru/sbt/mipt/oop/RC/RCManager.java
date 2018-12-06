package ru.sbt.mipt.oop.RC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.RC.Commands.*;

@Component
public class RCManager {

    private final CommandHistory commandHistory;
    private final SmartHome smartHome;
    private String RCID = "-1";

    @Autowired
    public RCManager(CommandHistory commandHistory, SmartHome smartHome) {
        this.smartHome = smartHome;
        this.commandHistory = commandHistory;
    }

    public RemoteController createRController(String RCID) {
        System.out.println("Created controller with ID: " + RCID);
        this.RCID = RCID;
        return new RemoteController(RCID, commandHistory);
    }

    public Command createCommand(CommandsType commandType) {
        switch (commandType) {
            case CLOSE_HALL_DOOR:
                return new CloseHallDoor(smartHome, RCID, commandHistory);
            case ENABLE_ALARM:
                return new EnableAlarm(smartHome, RCID, commandHistory);
            case ENABLE_SIREN:
                return new EnableSiren(smartHome, RCID);
            case TURN_OFF_ALL_LIGHTS:
                return new TurnOffAllLights(smartHome, RCID, commandHistory);
            case TURN_OFF_HALL_LIGHT:
                return new TurnOffHallLight(smartHome, RCID, commandHistory);
            case TURN_ON_ALL_LIGHTS:
                return new TurnOnAllLights(smartHome, RCID, commandHistory);
            case UNDO_LAST_COMMAND:
                return new UndoLastCommand(smartHome, RCID, commandHistory);
            default:
                return null;
        }
    }


}
