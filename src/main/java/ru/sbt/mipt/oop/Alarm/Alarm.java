package ru.sbt.mipt.oop.Alarm;

public class Alarm implements AlarmState {
    private AlarmSiren alarmSiren;

    public Alarm(AlarmSiren alarmSiren) {
        this.alarmSiren = alarmSiren;
        System.out.println("Wiu - Wiu");
    }

    @Override
    public void changeState(AlarmState state) {
        alarmSiren.changeState(state);
    }

    @Override
    public void activate(String code) {
        System.out.println("Wiu-Wiu, already enabled");
    }

    @Override
    public void deactivate(String password) {
        if (alarmSiren.checkPassword(password)){
            alarmSiren.changeState(new AlarmDisabled(alarmSiren));
            System.out.println("Alarm disabled");
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    public void setToAlarm() {

    }
}
