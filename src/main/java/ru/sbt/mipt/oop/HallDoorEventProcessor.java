package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

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





//        for (Room room : smartHome.getRooms()) {
//            for (Door door : room.getDoors()) {
//                if (door.getId().equals(event.getObjectId())) {
//                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
//                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
//                    if (room.getName().equals("hall")) {
//                        smartHome.turnOffLights();
//                    }
//                }
//            }
//        }
    }
}
