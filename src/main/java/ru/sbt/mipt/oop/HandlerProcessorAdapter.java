package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.Sensors.SensorEvent;
import ru.sbt.mipt.oop.Sensors.SensorEventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class HandlerProcessorAdapter implements EventHandler {

    private EventProcessor eventProcessor;

    // Available types :
    // "LightIsOn", "LightIsOff", "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked"

    private static Map<String, SensorEventType> sensorEventTypeMap = new HashMap<>();

    public HandlerProcessorAdapter(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
        sensorEventTypeMap.put("LightIsOn", SensorEventType.LIGHT_ON);
        sensorEventTypeMap.put("LightIsOff", SensorEventType.LIGHT_OFF);
        sensorEventTypeMap.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        sensorEventTypeMap.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        sensorEventTypeMap.put("DoorIsLocked", SensorEventType.ALARM_ACTIVATE);
        sensorEventTypeMap.put("DoorIsUnlocked", SensorEventType.ALARM_DEACTIVATE);
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEvent(sensorEventTypeMap.get(event.getEventType()),event.getObjectId());
        eventProcessor.processEvent(sensorEvent);
    }
}
