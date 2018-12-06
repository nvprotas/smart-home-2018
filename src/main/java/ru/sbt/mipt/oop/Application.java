package ru.sbt.mipt.oop;

import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.RC.Commands.CloseHallDoor;
import ru.sbt.mipt.oop.RC.Commands.CommandHistory;
import ru.sbt.mipt.oop.RC.Commands.CommandsType;
import ru.sbt.mipt.oop.RC.RCManager;
import ru.sbt.mipt.oop.RC.RemoteController;


public class Application {

    public static void main(String[] args) {

        String log4jConfPath = "src/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        EventManager eventManager = context.getBean(EventManager.class);
        RCManager rcManager = context.getBean(RCManager.class);

//        RemoteController remoteController = rcManager.createRController("123");
//        remoteController.linkButtonAndCommand("A", rcManager.createCommand( CommandsType.TURN_ON_ALL_LIGHTS));
//        remoteController.linkButtonAndCommand("B", rcManager.createCommand( CommandsType.UNDO_LAST_COMMAND));
//        remoteController.onButtonPressed("A");
//        remoteController.onButtonPressed("B");

        eventManager.Loop();
    }
}

