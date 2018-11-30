package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Processors.EventProcessor;

public interface EvensManager {
    void Loop();
    void addHomeEventsProcessor(EventProcessor eventProcessor);
}
