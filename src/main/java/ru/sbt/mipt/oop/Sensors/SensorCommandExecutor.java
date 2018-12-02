package ru.sbt.mipt.oop.Sensors;

@Deprecated
public class SensorCommandExecutor {
    public static void executeCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
