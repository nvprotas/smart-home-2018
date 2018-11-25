package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeContainer{
    Collection<Room> rooms;
    Collection<HomeEntity> components;

//    public SmartHome() {
//        components = new ArrayList<>();
//    }

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
//        System.out.println("SmartHome.components :" + components);
//        System.out.println("SmartHome.rooms :" + rooms);
        action.execute(this);
        if (components == null) {
            components = new ArrayList<>();
            components.addAll(rooms);
//            System.out.println("SmartHomeExecuting Started. Action: " + action);
        }
        components.forEach(c -> c.execute(action));
    }
}
