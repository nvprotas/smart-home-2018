package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {

    SmartHome smartHome;

    public LightsEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent( SensorEvent event) {
        if (!isLightEvent(event)) return;
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light)object;
                if (event.getType() == LIGHT_ON) {
                    light.setState(event.getObjectId(), true);
//                    System.out.println("Light " + light.getId() + " was switched on");
                } else {
                    light.setState(event.getObjectId(), false);
//                    System.out.println("Light " + light.getId() + " was switched off");
                }
            }
        });
    }


    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
