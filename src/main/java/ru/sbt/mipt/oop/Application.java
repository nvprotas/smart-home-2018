package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.RC.Commands.TurnOffAllLights;
import ru.sbt.mipt.oop.RC.Commands.TurnOnAllLights;
import ru.sbt.mipt.oop.RC.RemoteControlRegistry;
import ru.sbt.mipt.oop.RC.RemoteController;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

public class Application {

//    static SmartHome smartHome;
//    static RandomSensorEventProvider eventGenerator;
//    static HomeEventsObserver homeEventsObserver;

    public static void main(String[] args) throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        EventManager eventManager = context.getBean(EventManager.class);


//        RemoteControlRegistry registry = new RemoteControlRegistry();
        RemoteController controller = new RemoteController("42");
        SmartHome smartHome = context.getBean(SmartHome.class);
        controller.linkButtonAndCommand("A", new TurnOnAllLights(smartHome, "42"));
        controller.linkButtonAndCommand("B", new TurnOffAllLights(smartHome, "42"));
        RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);
        remoteControlRegistry.registerRemoteControl(controller,"42");

        eventManager.Loop();
    }
}

