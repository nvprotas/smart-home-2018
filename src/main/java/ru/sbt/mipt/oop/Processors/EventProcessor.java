package ru.sbt.mipt.oop.Processors;

import ru.sbt.mipt.oop.Sensors.SensorEvent;

public interface EventProcessor {

    void processEvent(SensorEvent event);

}
