package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        changeLightState(room, light, true, " was switched on");
                    }else {
                        changeLightState(room, light, true, " was switched off");
                    }
                }
            }
        }
    }

    private void changeLightState(Room room, Light light, boolean isTurnOn, String s) {
        light.setOn(isTurnOn);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + s);
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
