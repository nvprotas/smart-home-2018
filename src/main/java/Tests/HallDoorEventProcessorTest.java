package Tests;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.HomeEntities.Door;
import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.DoorEventProcessor;
import ru.sbt.mipt.oop.Processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;
import ru.sbt.mipt.oop.Sensors.SensorEventType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class HallDoorEventProcessorTest {

    private FileSmartHomeLoader loader = new FileSmartHomeLoader();
    private SmartHome smartHome = loader.loadSmartHome();
    private HallDoorEventProcessor processor = new HallDoorEventProcessor(smartHome);
    private String id;

    HallDoorEventProcessorTest() throws IOException {
    }

    @Before
    void DoorEventProcessorTestInit() throws IOException {
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    door.setState(door.getId(), true);
                    id = door.getId();
                }
            }

            for (Light light : room.getLights()) {
                light.setState(light.getId(), true);
            }
        }
    }

    @Test
    void processEventHallDOOR_CLOSE() {

        processor.processEvent(createEvent());

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    assertFalse(door.isOpen());
                }
            }

            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }

    private SensorEvent createEvent() {
        SensorEvent event;
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    event = new SensorEvent(SensorEventType.DOOR_CLOSED, door.getId());
                    return event;
                }
            }
        }
        return null;
    }
}