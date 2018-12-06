package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sbt.mipt.oop.Adapters.EventsManagerAdapter;
import ru.sbt.mipt.oop.HomeEntities.SmartHome;
import ru.sbt.mipt.oop.Processors.AlarmEventProcessor;
import ru.sbt.mipt.oop.Processors.DoorEventProcessor;
import ru.sbt.mipt.oop.Processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.Processors.LightsEventProcessor;
import ru.sbt.mipt.oop.RC.Commands.CommandHistory;
import ru.sbt.mipt.oop.RC.RemoteControlRegistry;
import ru.sbt.mipt.oop.RC.RemoteController;

import java.io.IOException;


@Configuration
@ComponentScan
public class HomeConfiguration {

    private SmartHome smartHome;

    @Bean
    SmartHome smartHome(SmartHomeLoader loader) throws IOException {
        this.smartHome = loader.loadSmartHome();
        return this.smartHome;
    }

    @Bean
    SmartHomeLoader smartHomeLoader() {
        return new FileSmartHomeLoader();
    }

    @Bean
    EventManager eventManager() throws IOException {

        EventManager manager = new EventsManagerAdapter();
        if (smartHome == null){
            smartHome = smartHome(new FileSmartHomeLoader());
        }

        manager.addHomeEventsProcessor(new SendSMSDecorator(new AlarmSirenDecorator(
                new LightsEventProcessor(smartHome), smartHome), smartHome));
        manager.addHomeEventsProcessor(new SendSMSDecorator(new AlarmSirenDecorator(
                new DoorEventProcessor(smartHome), smartHome), smartHome));
        manager.addHomeEventsProcessor(new SendSMSDecorator(new AlarmSirenDecorator(
                new HallDoorEventProcessor(smartHome), smartHome), smartHome));
        manager.addHomeEventsProcessor(new AlarmEventProcessor(smartHome));
        return manager;
    }

    @Bean
    SensorEventsManager sensorEventsManager(){
        return new SensorEventsManager();
    }

//    @Bean
//    RemoteControlRegistry remoteControlRegistry() {
//        return new RemoteControlRegistry();
//    }

//    @Bean
//    @Scope("sigleton")
//    CommandHistory commandHistory() {
//        return new CommandHistory();
//    }

//    @Bean
//    RemoteController addRemoteController(String RCID) {
//        CommandHistory commandHistory = new CommandHistory();
//        RemoteController remoteController = new RemoteController(RCID);
//        return remoteController;
//    }
}
