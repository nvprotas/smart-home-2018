package ru.sbt.mipt.oop.Processors;

import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Sensors.SensorEvent;

import static ru.sbt.mipt.oop.Sensors.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {

    SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent( SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) return;
        if (Integer.parseInt(event.getObjectId()) != 4) return;
        smartHome.execute(object1 -> {
            if (object1 instanceof Light) {
                Light light = (Light) object1;
                light.setState(light.getId(),false);
            }
        });
    }
}
