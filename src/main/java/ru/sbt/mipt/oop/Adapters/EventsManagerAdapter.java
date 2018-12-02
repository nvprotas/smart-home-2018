package ru.sbt.mipt.oop.Adapters;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.EvensManager;
import ru.sbt.mipt.oop.Processors.EventProcessor;

@Component
public class EventsManagerAdapter implements EvensManager {
    SensorEventsManager sensorEventsManager;

    public EventsManagerAdapter(SensorEventsManager sensorEventsManager) {
        this.sensorEventsManager = sensorEventsManager;
    }

    @Override
    public void Loop() {
        sensorEventsManager.start();
    }

    @Override
    public void addHomeEventsProcessor(EventProcessor eventProcessor) {
        sensorEventsManager.registerEventHandler(new HandlerProcessorAdapter(eventProcessor));
    }
}
