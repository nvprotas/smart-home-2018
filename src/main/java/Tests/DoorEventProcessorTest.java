package Tests;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.HomeEntities.Door;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.DoorEventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;
import ru.sbt.mipt.oop.Sensors.SensorEventType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventProcessorTest {

    private FileSmartHomeLoader loader = new FileSmartHomeLoader();
    private SmartHome smartHome = loader.loadSmartHome();
    private DoorEventProcessor processor = new DoorEventProcessor(smartHome);
    private String id1 = "1";
    private String id2 = "2";

    private SensorEvent event_open = new SensorEvent(SensorEventType.DOOR_OPEN, id1);
    private SensorEvent event_close = new SensorEvent(SensorEventType.DOOR_CLOSED, id2);

    DoorEventProcessorTest() throws IOException {
    }

    @BeforeEach
    void DoorEventProcessorTestInit() throws IOException {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                door.setState(id1, false);
                door.setState(id2, true);
            }
        }
    }

    @Test
    void processEventDOOR_OPEN() {

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id1)) {
                    assertFalse(door.isOpen());
                }
            }
        }

        processor.processEvent(event_open);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id1)) {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

    @Test
    void processEventDOOR_CLOSE() {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id2)) {
                    assertTrue(door.isOpen());
                }
            }
        }

        processor.processEvent(event_close);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id2)) {
                    assertFalse(door.isOpen());
                }
            }
        }
    }
}