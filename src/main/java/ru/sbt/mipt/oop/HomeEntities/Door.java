package ru.sbt.mipt.oop.HomeEntities;

import ru.sbt.mipt.oop.Action;

public class Door implements HomeEntity {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setState(String componentID, boolean open) {
        if (componentID.equals(id)) {
            isOpen = open;
            if (open) {
                System.out.println("Door " + id  + " was opened.");
            } else {
                System.out.println("Door " + id  + " was closed.");
            }
        }
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
