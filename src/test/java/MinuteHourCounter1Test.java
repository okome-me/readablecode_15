import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MinuteHourCounter1Test {

    @Test
    public void test_add() {
        MinuteHourCounter1 minuteHourCounter = new MinuteHourCounter1();
        minuteHourCounter.add(10, LocalTime.now());
        assertThat(minuteHourCounter.getEvents().get(0).getCount(), is(10));
    }

    @Test
    public void test_minuteCount() {
        MinuteHourCounter1 minuteHourCounter = new MinuteHourCounter1();
        minuteHourCounter.add(10, LocalTime.now());
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(59));
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(60));
        assertThat(minuteHourCounter.minuteCount(), is(20));
        assertThat(minuteHourCounter.hourCount(), is(10));
    }

    @Test
    public void test_hourCount() {
        MinuteHourCounter1 minuteHourCounter = new MinuteHourCounter1();
        minuteHourCounter.add(10, LocalTime.now());
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(3599));
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(3600));
        assertThat(minuteHourCounter.hourCount(), is(20));
    }
}