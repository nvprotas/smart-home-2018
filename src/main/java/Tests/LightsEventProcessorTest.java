package Tests;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.HomeEntities.Door;
import ru.sbt.mipt.oop.HomeEntities.Light;
import ru.sbt.mipt.oop.HomeEntities.Room;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.DoorEventProcessor;
import ru.sbt.mipt.oop.Processors.LightsEventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;
import ru.sbt.mipt.oop.Sensors.SensorEventType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LightsEventProcessorTest {

    private FileSmartHomeLoader loader = new FileSmartHomeLoader();
    private SmartHome smartHome = loader.loadSmartHome();
    private LightsEventProcessor processor = new LightsEventProcessor(smartHome);
    private String id1 = "1";
    private String id2 = "2";

    private SensorEvent event_on = new SensorEvent(SensorEventType.LIGHT_ON, id1);
    private SensorEvent event_off = new SensorEvent(SensorEventType.LIGHT_OFF, id2);

    LightsEventProcessorTest() throws IOException {
    }


    @BeforeEach
    void DoorEventProcessorTestInit() throws IOException {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setState(id1, false);
                light.setState(id2, true);
            }
        }
    }

    @Test
    void processEventLIGHT_ON() {

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id1)) {
                    assertFalse(light.isOn());
                }
            }
        }

        processor.processEvent(event_on);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id1)) {
                    assertTrue(light.isOn());
                }
            }
        }
    }

    @Test
    void processEventLIGHT_OFF() {

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id2)) {
                    assertTrue(light.isOn());
                }
            }
        }

        processor.processEvent(event_off);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id2)) {
                    assertFalse(light.isOn());
                }
            }
        }
    }

}