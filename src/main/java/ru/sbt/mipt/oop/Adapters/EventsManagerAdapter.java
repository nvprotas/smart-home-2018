package ru.sbt.mipt.oop.Adapters;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.EventManager;
import ru.sbt.mipt.oop.Processors.EventProcessor;

public class EventsManagerAdapter implements EventManager {
    private SensorEventsManager sensorEventsManager;

    public EventsManagerAdapter() {
        this.sensorEventsManager = new SensorEventsManager();
    }

    @Override
    public void Loop() {
        System.out.println("EventsManagerAdapter.Loop() started\n============================");
        sensorEventsManager.start();
    }

    @Override
    public void addHomeEventsProcessor(EventProcessor eventProcessor) {
        System.out.println(eventProcessor.toString() + " handled.");
        sensorEventsManager.registerEventHandler(new HandlerProcessorAdapter(eventProcessor));
    }
}
