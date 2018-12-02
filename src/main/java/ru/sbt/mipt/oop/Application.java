package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

public class Application {

    static SmartHome smartHome;
    static RandomSensorEventProvider eventGenerator;
    static HomeEventsObserver homeEventsObserver;

    public static void main(String[] args) throws IOException {

//        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
////        EvensManager evensManager = context.getBean(EvensManager.class);
////        evensManager.Loop();
//        HomeEventsObserver observer = context.getBean(HomeEventsObserver.class);
//        observer.Loop(smartHome);

        smartHome = new FileSmartHomeLoader().loadSmartHome();
        eventGenerator = new RandomSensorEventProvider();
        homeEventsObserver = new HomeEventsObserver(eventGenerator);
        Configurator.configure(homeEventsObserver, smartHome);
        homeEventsObserver.Loop(smartHome);
    }
}

