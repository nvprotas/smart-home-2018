package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Alarm.*;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;

public class SendSMSDecorator implements EventProcessor {
    private EventProcessor eventProcessor;
    private SmartHome smartHome;

    public SendSMSDecorator(EventProcessor eventProcessor, SmartHome smartHome) {
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
//        System.out.println(smartHome.getAlarmSiren().toString());
        if (smartHome.getAlarmSiren().getState() instanceof AlarmEnabled) {
            System.out.println("SMS:Home invasion! " + event.toString());
            return;
        }
        if (smartHome.getAlarmSiren().getState() instanceof Alarm) {
            System.out.println("SMS:Home invasion! " + event.toString());
            return;
        }
//        System.out.println("событие: " +event.toString() + " послано дальше в " + eventProcessor.toString());
        eventProcessor.processEvent(event);
    }
}
