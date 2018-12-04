package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.HomeEventsObserver;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.Processors.LightsEventProcessor;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileSmartHomeLoaderTest {
    private SmartHome home;

    @Before
    public void init() throws IOException {
        home = new FileSmartHomeLoader().loadSmartHome();
    }

    @Test
    public void fileLoadTest(){
        Assert.assertTrue(home != null);
//        Assert.assertTrue(home.getChildren() != null);
    }
}