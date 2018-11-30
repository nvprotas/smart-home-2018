package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.DoorEventProcessor;
import ru.sbt.mipt.oop.Processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.Processors.LightsEventProcessor;

import java.io.IOException;


@Configuration
@ComponentScan
public class HomeConfiguration {
//    private static SmartHome smartHome;

    @Bean
    SmartHome smartHome() throws IOException {
        return smartHomeLoader().loadSmartHome();
    }

    @Bean
    SmartHomeLoader smartHomeLoader() {
        return new FileSmartHomeLoader();
    }

    @Bean
    SensorEventProvider sensorEventProvider() {
        RandomSensorEventProvider eventProvider = new RandomSensorEventProvider();
        return eventProvider;
    }

    @Bean
    HomeEventsObserver eventsObserver() throws IOException {
        HomeEventsObserver observer = new HomeEventsObserver(sensorEventProvider());
        observer.addEventProcessor(new SendSMSDecorator(new AlarmSirenDecorator(new LightsEventProcessor(smartHome()), smartHome()), smartHome()));
        observer.addEventProcessor(new SendSMSDecorator(new AlarmSirenDecorator(new DoorEventProcessor(smartHome()), smartHome()), smartHome()));
        observer.addEventProcessor(new SendSMSDecorator(new AlarmSirenDecorator(new HallDoorEventProcessor(smartHome()), smartHome()), smartHome()));
        observer.addEventProcessor(new AlarmEventProcessor(smartHome()));

        return observer;
    }
}
