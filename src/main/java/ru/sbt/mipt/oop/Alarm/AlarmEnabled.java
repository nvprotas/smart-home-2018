package ru.sbt.mipt.oop.Alarm;

public class AlarmEnabled implements AlarmState {
    AlarmSirenSounds alarmSirenSounds;

    public AlarmEnabled(AlarmSirenSounds alarmSirenSounds, String  password) {
        this.alarmSirenSounds = alarmSirenSounds;
        this.alarmSirenSounds.setPassword(password);
        System.out.println("Alarm system enabled.");
    }

    @Override
    public void changeState(AlarmState state) {
        alarmSirenSounds.changeState(state);

    }

    @Override
    public void activate(String password) {
        System.out.println("Already enabled");
        changeState(new Alarm(alarmSirenSounds));
    }

    @Override
    public void deactivate(String password) {
        if (alarmSirenSounds.checkPassword(password)) {
            System.out.println("Alarm disabled with password");
            changeState(new AlarmDisabled(alarmSirenSounds));
        } else {
            System.out.println("Incorrect password");
            changeState(new Alarm(alarmSirenSounds));
        }
    }

    @Override
    public void setToAlarm() {
        System.out.println("Wiu - Wiu! Setted to Alarm");
        changeState(new Alarm(alarmSirenSounds));
    }
}
