package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver {
    static Collection<EventProcessor> eventProcessors = null;
    RandomSensorEventProvider eventGenerator;

    public HomeEventsObserver(RandomSensorEventProvider eventGenerator) {
        this.eventGenerator = eventGenerator;
    }

    public void Loop(SmartHome smartHome) {
        System.out.println("Loop started");
        SensorEvent event = eventGenerator.getNextSensorEvent();
        while (event != null) {
            System.out.println();
            System.out.println("Got event: " + event);
            for (EventProcessor p : eventProcessors) {
                p.processEvent(event);
            }
            System.out.println(event +" processed");
            event = eventGenerator.getNextSensorEvent();
        }
    }

    public void addEventProcessor(EventProcessor processor) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.add(processor);
    }

    public void addAllEventProcessors( Collection<EventProcessor> processors) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.addAll(processors);
        System.out.println("Processors added");
    }
}