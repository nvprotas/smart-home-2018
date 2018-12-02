package ru.sbt.mipt.oop;

import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {

    public static void main(String[] args) throws IOException {

        String log4jConfPath = "src/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        ApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        EventManager eventManager = context.getBean(EventManager.class);

        eventManager.Loop();
    }
}

