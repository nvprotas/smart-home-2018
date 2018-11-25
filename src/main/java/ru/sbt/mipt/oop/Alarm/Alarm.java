package ru.sbt.mipt.oop.Alarm;

public class Alarm implements AlarmState {
    private AlarmSirenSounds alarmSirenSounds;

    public Alarm(AlarmSirenSounds alarmSirenSounds) {
        this.alarmSirenSounds = alarmSirenSounds;
        System.out.println("Wiu - Wiu");
    }

    @Override
    public void changeState(AlarmState state) {
        alarmSirenSounds.changeState(state);
    }

    @Override
    public void activate(String code) {
        System.out.println("Wiu-Wiu, already enabled");
    }

    @Override
    public void deactivate(String password) {
        if (alarmSirenSounds.checkPassword(password)){
            alarmSirenSounds.changeState(new AlarmDisabled(alarmSirenSounds));
            System.out.println("Alarm disabled");
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    public void setToAlarm() {

    }
}
