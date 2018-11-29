package ru.sbt.mipt.oop.Alarm;

public class AlarmSiren implements AlarmState {
    private String password = "StRoNgPaSsWoRd";
    private AlarmState state = new AlarmDisabled(this);
//    private AlarmState state = new AlarmEnabled(this, "");

    public AlarmSiren()  {
    }

    public AlarmSiren(String password) {
        this.password = password;
    }

    public void changeState(AlarmState state) {
        this.state = state;
    }

    public void activate(String password) {
        state.activate(password);
    }

    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    void setPassword(String password) {
        this.password = password;
    }

    public void deactivate(String password){
        state.deactivate(password);
    }

    public AlarmState getState() {
        return state;
    }

    public void setToAlarm() {
        System.out.println("Wiu - Wiu! Setted to Alarm");
        state.setToAlarm();
    }
}
