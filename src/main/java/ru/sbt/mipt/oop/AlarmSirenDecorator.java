package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Alarm.Alarm;
import ru.sbt.mipt.oop.Alarm.AlarmEnabled;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;

public class AlarmSirenDecorator implements EventProcessor {

    private EventProcessor eventProcessor;
    private SmartHome smartHome;

    public AlarmSirenDecorator(EventProcessor eventProcessor, SmartHome smartHome) {
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (smartHome.getAlarmSirenSounds().getState() instanceof AlarmEnabled) {
            smartHome.getAlarmSirenSounds().setToAlarm();
            return;
        }
        if (smartHome.getAlarmSirenSounds().getState() instanceof Alarm) {
            return;
        }
//        System.out.println("событие: " +event.toString() + " послано дальше в " + eventProcessor.toString());
        eventProcessor.processEvent(event);
    }
}
