import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MinuteHourCounter3Test {

    @Test
    public void test_add() {
        MinuteHourCounter3 minuteHourCounter = new MinuteHourCounter3();
        minuteHourCounter.add(10, LocalTime.now());
        assertThat(minuteHourCounter.minuteCount(), is(10));
    }

    @Test
    public void test_minuteCount() {
        MinuteHourCounter3 minuteHourCounter = new MinuteHourCounter3();
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(60));
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(50));
        minuteHourCounter.add(10, LocalTime.now());
        assertThat(minuteHourCounter.minuteCount(), is(20));
    }

    @Test
    public void test_hourCount() {
        MinuteHourCounter3 minuteHourCounter = new MinuteHourCounter3();
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(3600));
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(3500));
        minuteHourCounter.add(10, LocalTime.now());
        assertThat(minuteHourCounter.hourCount(), is(20));
    }
}