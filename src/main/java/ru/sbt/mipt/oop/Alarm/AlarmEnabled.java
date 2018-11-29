package ru.sbt.mipt.oop.Alarm;

public class AlarmEnabled implements AlarmState {
    AlarmSiren alarmSiren;

    public AlarmEnabled(AlarmSiren alarmSiren, String  password) {
        this.alarmSiren = alarmSiren;
        this.alarmSiren.setPassword(password);
//        System.out.println("Alarm system enabled.");
    }

    @Override
    public void changeState(AlarmState state) {
        alarmSiren.changeState(state);

    }

    @Override
    public void activate(String password) {
        System.out.println("Already enabled");
        changeState(new AlarmSiren());
    }

    @Override
    public void deactivate(String password) {
        if (alarmSiren.checkPassword(password)) {
            System.out.println("Alarm disabled with password");
            changeState(new AlarmDisabled(alarmSiren));
        } else {
            System.out.println("Incorrect password");
            changeState(new AlarmSiren());
        }
    }

    @Override
    public void setToAlarm() {
        System.out.println("Wiu - Wiu! Setted to Alarm");
        changeState(new Alarm(alarmSiren));
    }
}
