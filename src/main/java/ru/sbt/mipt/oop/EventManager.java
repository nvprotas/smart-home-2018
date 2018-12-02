package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Processors.EventProcessor;

public interface EventManager {
    void Loop();
    void addHomeEventsProcessor(EventProcessor eventProcessor);
}
