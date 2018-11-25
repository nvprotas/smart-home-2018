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
        homeEventsProcessorCollection.add(new SendSMSDecorator(new AlarmSirenDecorator(
                new LightsEventProcessor(smartHome), smartHome), smartHome));

        homeEventsProcessorCollection.add(new SendSMSDecorator(new AlarmSirenDecorator(
                new DoorEventProcessor(smartHome), smartHome), smartHome));

        homeEventsProcessorCollection.add(new SendSMSDecorator(new AlarmSirenDecorator(
                new HallDoorEventProcessor(smartHome), smartHome), smartHome));
        homeEventsProcessorCollection.add(new AlarmEventProcessor(smartHome));

        homeEventObserver.addAllEventProcessors(homeEventsProcessorCollection);
    }
}
