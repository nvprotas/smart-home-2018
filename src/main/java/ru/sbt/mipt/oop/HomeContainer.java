package ru.sbt.mipt.oop;

import java.util.Collection;

//сущность, содержащая другие элементы дома
public interface HomeContainer extends HomeEntity {
    Collection<HomeEntity> getChildren();
}
