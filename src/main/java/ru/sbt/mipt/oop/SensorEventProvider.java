package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Sensors.SensorEvent;

public interface SensorEventProvider {
    public SensorEvent getNextSensorEvent();
}
