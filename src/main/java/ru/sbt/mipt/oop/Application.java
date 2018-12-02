package ru.sbt.mipt.oop;

import org.apache.log4j.PropertyConfigurator;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.RC.Commands.TurnOffAllLights;
import ru.sbt.mipt.oop.RC.Commands.TurnOnAllLights;
import ru.sbt.mipt.oop.RC.RemoteControlRegistry;
import ru.sbt.mipt.oop.RC.RemoteController;

public class Application {

    public static void main(String[] args) throws IOException {

        String log4jConfPath = "src/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        EventManager eventManager = context.getBean(EventManager.class);

        eventManager.Loop();
    }
}

