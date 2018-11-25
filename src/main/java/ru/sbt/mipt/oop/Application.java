package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    static SmartHome smartHome;
    static RandomSensorEventProvider eventGenerator;
    static HomeEventsObserver homeEventsObserver;

    public static void main(String[] args) throws IOException {
        smartHome = new FileSmartHomeLoader().loadSmartHome();
        eventGenerator = new RandomSensorEventProvider();
        homeEventsObserver = new HomeEventsObserver(eventGenerator);
        Configurator.configure(homeEventsObserver, smartHome);
        homeEventsObserver.Loop(smartHome);
    }
}

