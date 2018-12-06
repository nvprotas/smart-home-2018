package Tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.RC.Commands.Command;
import ru.sbt.mipt.oop.RC.Commands.CommandHistory;
import ru.sbt.mipt.oop.RC.Commands.Undoable;
import ru.sbt.mipt.oop.RC.RemoteController;

import static org.junit.jupiter.api.Assertions.*;

class RCTest {

    CommandHistory commandHistory = new CommandHistory();
    class TestCommand implements Undoable {

            private String RCID;
            private boolean testValue;

            public TestCommand(String RCID) {
                this.RCID = RCID;
            }

            @Override
            public void execute() {
                testValue = true;
            }

            @Override
            public String getID() {
                return RCID;
            }

            @Override
            public void undo() {
                testValue = false;
            }

            boolean getState(){ return  testValue;}
        }
    class TestController extends RemoteController {
            public TestController(String RCiD) {
                super(RCiD, commandHistory);
            }

            public Command getCommndByKey(String key){
                return buttonCodeCommandMap.get(key);
            }

        }

    @Test
    public void testUndoable(){
        TestCommand command = new TestCommand("42");
        Assert.assertFalse(command.getState());
        command.execute();
        Assert.assertTrue(command.getState());
        command.undo();
        Assert.assertFalse(command.getState());
    }

    @Test
    public void testLinking (){
        TestController controller = new TestController("42");
        TestCommand cmd = new TestCommand("42");
        controller.linkButtonAndCommand("A",cmd);
        Assert.assertEquals(cmd,controller.getCommndByKey("A"));
    }

    @Test
    public void testHistory () {
        CommandHistory history = new CommandHistory();
        assertFalse(history.isLastCommandIsMine("asdf"));

        String rcid1 = "4_8_15";
        String rcid2 = "16_23_42";

        TestCommand command1 = new TestCommand(rcid1);
        TestCommand command2 = new TestCommand(rcid2);

        history.addToHist(command1);
        history.addToHist(command2);

        assertFalse(history.isLastCommandIsMine(rcid1));
        assertTrue(history.isLastCommandIsMine(rcid2));
        assertEquals(command2, history.getFromHist());
        assertEquals(command1, history.getFromHist());
    }

}