package ru.sbt.mipt.oop.HomeEntities;

import java.util.Collection;

@Deprecated
public interface HomeContainer extends HomeEntity {
    Collection<HomeEntity> getChildren();
}
