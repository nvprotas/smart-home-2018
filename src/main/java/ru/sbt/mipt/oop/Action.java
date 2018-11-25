package ru.sbt.mipt.oop;

public interface Action<O> {
    void execute(O object);
}
