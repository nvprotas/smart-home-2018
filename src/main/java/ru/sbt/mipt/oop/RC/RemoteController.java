package ru.sbt.mipt.oop.RC;

import ru.sbt.mipt.oop.RC.Commands.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {

    public Map<String, Command> buttonCodeCommandMap = new HashMap<>();
    private String RCiD;

    public RemoteController(String RCiD) {
        this.RCiD = RCiD;
    }

    public void linkButtonAndCommand (String buttonCode, Command command) {
        buttonCodeCommandMap.put(buttonCode,command);
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttonCodeCommandMap.containsKey(buttonCode))
        {
            buttonCodeCommandMap.get(buttonCode).execute();
        }
    }
}
