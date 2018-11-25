package ru.sbt.mipt.oop.HomeEntities;

import ru.sbt.mipt.oop.Action;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeContainer{
    Collection<Room> rooms;
    Collection<HomeEntity> components;

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        components.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public Collection<HomeEntity> getChildren() {
        return components;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        if (components == null) {
            components = new ArrayList<>();
            components.addAll(rooms);
        }
        components.forEach(c -> c.execute(action));
    }
}
