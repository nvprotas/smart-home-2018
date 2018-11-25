package ru.sbt.mipt.oop.Processors;

import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Sensors.SensorEvent;

import static ru.sbt.mipt.oop.Sensors.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.Sensors.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {

    SmartHome smartHome;

    public LightsEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent( SensorEvent event) {
//        System.out.println(smartHome.getChildren().toString());
        if (!isLightEvent(event)) return;
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light)object;
                if (event.getType() == LIGHT_ON) {
                    light.setState(event.getObjectId(), true);
                } else {
                    light.setState(event.getObjectId(), false);
                }
            }
        });
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
