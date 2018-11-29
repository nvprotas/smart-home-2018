package ru.sbt.mipt.oop.Alarm;

public class AlarmDisabled implements AlarmState {
    private AlarmSiren alarmSiren;

    public AlarmDisabled(AlarmSiren alarmSiren) {
        this.alarmSiren = alarmSiren;
        this.alarmSiren.setPassword("StRoNgPaSsWoRd");
//        System.out.println("System disabled");
    }

    @Override
    public void changeState(AlarmState state) {
        alarmSiren.changeState(state);
    }

    @Override
    public void activate(String password) {
        alarmSiren.changeState(new AlarmEnabled(alarmSiren,password));
//        System.out.println("Alarm is enabled");
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Alarm is already disabled.");
    }

    @Override
    public void setToAlarm() {
        System.out.println("Wiu - Wiu! Setted to Alarm");
        changeState(new Alarm(alarmSiren));
    }
}
