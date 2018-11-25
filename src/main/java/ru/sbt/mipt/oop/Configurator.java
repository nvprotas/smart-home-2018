package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.DoorEventProcessor;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.Processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.Processors.LightsEventProcessor;

import java.util.ArrayList;
import java.util.Collection;

public class Configurator {
    public static void configure(HomeEventsObserver homeEventObserver, SmartHome smartHome) {

        Collection<EventProcessor> homeEventsProcessorCollection = new ArrayList<>();
        homeEventsProcessorCollection.add(new LightsEventProcessor(smartHome));
        homeEventsProcessorCollection.add(new DoorEventProcessor(smartHome));
        homeEventsProcessorCollection.add(new HallDoorEventProcessor(smartHome));

        homeEventObserver.addAllEventProcessors(homeEventsProcessorCollection);
    }
}
