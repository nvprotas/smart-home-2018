package ru.sbt.mipt.oop.HomeEntities;

import ru.sbt.mipt.oop.Action;

public interface HomeLeaf extends HomeEntity {
    public void execute(Action action);
}
