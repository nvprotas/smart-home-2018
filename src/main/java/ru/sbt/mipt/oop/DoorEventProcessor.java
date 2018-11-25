package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent( SensorEvent event) {
        if (!isDoorEvent(event)) return;
//        System.out.println("Processing started: " + event);
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
