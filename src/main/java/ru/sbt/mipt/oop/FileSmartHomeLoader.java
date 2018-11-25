package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    @Override
    public SmartHome loadSmartHome() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        System.out.println("smarthome loaded");
        return gson.fromJson(json, SmartHome.class);

    }
}
