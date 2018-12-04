package ru.sbt.mipt.oop.HomeEntities;

import ru.sbt.mipt.oop.Action;

@Deprecated
public interface HomeLeaf extends HomeEntity {
    public void execute(Action action);
}
