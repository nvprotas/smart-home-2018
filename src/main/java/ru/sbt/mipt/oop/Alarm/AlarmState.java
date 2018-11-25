package ru.sbt.mipt.oop.Alarm;

/*
 Общий интерфейс состояний
 */

public interface AlarmState {
    void changeState( AlarmState state);
    void activate(String password);
    void deactivate(String password);
    void setToAlarm();
}
