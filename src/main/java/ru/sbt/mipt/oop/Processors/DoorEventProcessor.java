package ru.sbt.mipt.oop.Processors;

import ru.sbt.mipt.oop.HomeEntities.Door;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Sensors.SensorEvent;

import static ru.sbt.mipt.oop.Sensors.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.Sensors.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent( SensorEvent event) {
        if (!isDoorEvent(event)) return;
        smartHome.execute(object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (event.getType() == DOOR_OPEN) {
                    door.setState(event.getObjectId(), true);
                } else {
                    door.setState(event.getObjectId(), false);
                }
            }
        });
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
