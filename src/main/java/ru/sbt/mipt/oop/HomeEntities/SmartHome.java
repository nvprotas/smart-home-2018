package ru.sbt.mipt.oop.HomeEntities;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Alarm.Alarm;
import ru.sbt.mipt.oop.Alarm.AlarmEnabled;
import ru.sbt.mipt.oop.Alarm.AlarmSirenSounds;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeContainer{
    Collection<Room> rooms;
    Collection<HomeEntity> components;
    private AlarmSirenSounds alarmSirenSounds;


    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        this.alarmSirenSounds = new AlarmSirenSounds();
        System.out.println("SmartHome Constructor");
    }

    public SmartHome() {
        alarmSirenSounds = new AlarmSirenSounds();
        components = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public Collection<HomeEntity> getChildren() {
        return components;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        if (components.isEmpty()) {
            components = new ArrayList<>();
            components.addAll(rooms);
        }
        components.forEach(c -> c.execute(action));
    }
    public AlarmSirenSounds getAlarmSirenSounds() {
        return alarmSirenSounds;
    }
    public void alarmEnable(String password) {
        this.alarmSirenSounds.activate(password);
    }
    public void alarmDisable(String password) {
        this.alarmSirenSounds.deactivate(password);
    }
}
