package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.HomeEventsObserver;
import ru.sbt.mipt.oop.Processors.EventProcessor;
import ru.sbt.mipt.oop.SensorEventProvider;
import ru.sbt.mipt.oop.Sensors.SensorEvent;
import ru.sbt.mipt.oop.Sensors.SensorEventType;

public class HomeEventsObserverNumTest {
    int num = 100000;
    HomeEventsObserver observer;
    @Before
    public void define(){
        SensorEventProvider provider = new SensorEventProvider() {
            int count;
            @Override
            public SensorEvent getNextSensorEvent() {
                if (count < num) {
                    count ++;
                    return new SensorEvent(SensorEventType.LIGHT_ON, "1");
                } else {
                    return null;
                }

            }
        };

        observer = new HomeEventsObserver(provider);
    }

    class ProcessorTest implements EventProcessor {

        int count;

        public int getCount() {
            return count;
        }

        @Override
        public void processEvent(SensorEvent event) {
            if (event.getType() == SensorEventType.LIGHT_ON) {
                count++;
            }
        }
    }

    @Test
    public void observerTestZeroEvents() {
        EventProcessor processor = new ProcessorTest();
        observer.addEventProcessor(processor);
        observer.Loop(null);
        Assert.assertEquals(num, ((ProcessorTest) processor).getCount());
    }
}
