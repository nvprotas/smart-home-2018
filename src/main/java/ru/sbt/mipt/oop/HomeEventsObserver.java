package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver {
    static Collection<EventProcessor> eventProcessors = null;
    SensorEventProvider eventGenerator;

    public HomeEventsObserver(SensorEventProvider eventGenerator) {
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
//            System.out.println(event +" processed");
            event = eventGenerator.getNextSensorEvent();
        }
    }

    public void addAllEventProcessors( Collection<EventProcessor> processors) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.addAll(processors);
//        System.out.println("Processors added: " + processors.toString());
        System.out.println("Processors added");
    }

    public void addEventProcessor(EventProcessor processor) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.add(processor);
        System.out.println("Processor added");
    }
}
