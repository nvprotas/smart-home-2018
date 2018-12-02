package ru.sbt.mipt.oop.Processors;

import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;
import ru.sbt.mipt.oop.Sensors.SensorEventType;

import static ru.sbt.mipt.oop.Sensors.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.Sensors.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public AlarmEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (!isAlarmEvent(event)) return;
        if (event.getType() == ALARM_ACTIVATE) {
            smartHome.alarmEnable(event.getObjectId());
        } else {
            smartHome.alarmDisable(event.getObjectId());
        }
    }
    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE || event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }
}