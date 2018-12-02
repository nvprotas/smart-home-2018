package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    @Override
    public SmartHome loadSmartHome() throws IOException {
        String path = "smart-home-1.js";
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(path)));
        System.out.println("SmartHome loaded from \"" + path +"\"");
        return gson.fromJson(json, SmartHome.class);

    }
}
