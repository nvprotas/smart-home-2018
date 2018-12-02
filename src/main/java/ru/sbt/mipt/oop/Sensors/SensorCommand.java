package ru.sbt.mipt.oop.Sensors;

public class SensorCommand {
    private final SensorEventType type;
    private final String objectId;

    public SensorCommand(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
