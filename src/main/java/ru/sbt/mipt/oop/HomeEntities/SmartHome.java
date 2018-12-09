package ru.sbt.mipt.oop.HomeEntities;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Alarm.AlarmSiren;
import ru.sbt.mipt.oop.RC.Commands.CommandHistory;

import java.util.Collection;

public class SmartHome implements HomeEntity {
    private Collection<Room> rooms;
    private AlarmSiren alarmSiren;
    public CommandHistory commandHistory;


    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        this.alarmSiren = new AlarmSiren();
    }

    public SmartHome() {
        alarmSiren = new AlarmSiren();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        rooms.forEach(c -> c.execute(action));
    }
    public AlarmSiren getAlarmSiren() {
        return alarmSiren;
    }
    public void alarmEnable(String password) {
        this.alarmSiren.activate(password);
    }
    public void alarmDisable(String password) {
        this.alarmSiren.deactivate(password);
    }
    public void sirenEnable() {this.alarmSiren.setToAlarm();}
}
