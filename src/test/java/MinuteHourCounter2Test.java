import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MinuteHourCounter2Test {

    @Test
    public void test_minuteCount() {
        MinuteHourCounter2 minuteHourCounter = new MinuteHourCounter2();
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(60));
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(59));
        minuteHourCounter.add(10, LocalTime.now());
        assertThat(minuteHourCounter.minuteCount(), is(20));
    }

    @Test
    public void test_hourCount() {
        MinuteHourCounter2 minuteHourCounter = new MinuteHourCounter2();
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(3600));
        minuteHourCounter.add(10, LocalTime.now().minusSeconds(3599));
        minuteHourCounter.add(10, LocalTime.now());
        assertThat(minuteHourCounter.hourCount(), is(20));
    }
}