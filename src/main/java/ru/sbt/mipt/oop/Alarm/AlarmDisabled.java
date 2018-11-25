package ru.sbt.mipt.oop.Alarm;

public class AlarmDisabled implements AlarmState {
    private AlarmSirenSounds alarmSirenSounds;

    public AlarmDisabled(AlarmSirenSounds alarmSirenSounds) {
        this.alarmSirenSounds = alarmSirenSounds;
        this.alarmSirenSounds.setPassword("StRoNgPaSsWoRd");
        System.out.println("System disabled");
    }

    @Override
    public void changeState(AlarmState state) {
        alarmSirenSounds.changeState(state);
    }

    @Override
    public void activate(String password) {
        alarmSirenSounds.changeState(new AlarmEnabled(alarmSirenSounds,password));
        System.out.println("Alarm is enabled");
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Alarm is already disabled.");
    }

    @Override
    public void setToAlarm() {
        System.out.println("Wiu - Wiu! Setted to Alarm");
        changeState(new Alarm(alarmSirenSounds));
    }
}
