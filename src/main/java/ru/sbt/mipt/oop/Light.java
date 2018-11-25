package ru.sbt.mipt.oop;

public class Light implements HomeLeaf{
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    public void setState(String componentId,boolean on) {
        if (componentId.equals(id)){
            isOn = on;
            if (on) {
                System.out.println("Light " + id  + " was switched on.");
            } else {
                System.out.println("light " + id  + "  was switched off.");
            }
        }
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
