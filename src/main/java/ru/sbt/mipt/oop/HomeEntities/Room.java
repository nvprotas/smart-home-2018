package ru.sbt.mipt.oop.HomeEntities;

import ru.sbt.mipt.oop.Action;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements HomeEntity {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;
    private Collection<HomeEntity> components;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;

        components = new ArrayList<>();
        components.addAll(lights);
        components.addAll(doors);
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public Light getLightById(String objectId) {
        return null;
    }

//    @Override
//    public Collection<HomeEntity> getChildren() {
//        return components;
//    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        if (components == null) {
            components = new ArrayList<>();
            components.addAll(doors);
            components.addAll(lights);
        }
        components.forEach((c) -> c.execute(action));
    }
}

