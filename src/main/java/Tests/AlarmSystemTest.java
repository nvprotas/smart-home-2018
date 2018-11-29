package Tests;

import org.junit.Test;

import ru.sbt.mipt.oop.Alarm.AlarmDisabled;
import ru.sbt.mipt.oop.Alarm.AlarmEnabled;
import ru.sbt.mipt.oop.Alarm.AlarmSiren;

import static org.junit.Assert.*;

public class AlarmSystemTest {

    String pass = "asdf";

    @Test
    public void activateTest() {
        AlarmSiren alarm = new AlarmSiren();
        alarm.activate(pass);
        assertTrue(alarm.getState() instanceof AlarmEnabled);
    }

    @Test
    public void deactivateTest() {
        AlarmSiren alarm = new AlarmSiren();
        alarm.activate(pass);
        alarm.deactivate(pass);
        assertTrue(alarm.getState() instanceof AlarmDisabled);
    }

    @Test
    public void activateActivatedTest() {
        AlarmSiren alarm = new AlarmSiren();
        alarm.activate(pass);
        alarm.activate(pass);
        assertTrue(alarm.getState() instanceof AlarmSiren);
    }

    @Test
    public void deactivateDeactivatedTest() {
        AlarmSiren alarm = new AlarmSiren();
        alarm.activate(pass);
        alarm.deactivate(pass);
        alarm.deactivate(pass);
        assertTrue(alarm.getState() instanceof AlarmDisabled);
    }

    @Test
    public void deactivateWithWrongPasswordTest() {
        AlarmSiren alarm = new AlarmSiren();
        alarm.activate(pass);
        alarm.deactivate(pass + pass);
        assertTrue(alarm.getState() instanceof AlarmSiren);
    }

}