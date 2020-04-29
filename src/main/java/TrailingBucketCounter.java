import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TrailingBucketCounter {

    private int secs_per_bucket;

    private ConveyorQueue buckets;

    // updateメソッドが最後に呼び出された時刻
    private LocalTime last_update_time;

    public TrailingBucketCounter(int num_buckets, int secs_per_bucket) {
        buckets = new ConveyorQueue(num_buckets);
        this.secs_per_bucket = secs_per_bucket;
    }

    /**
     * 新しいデータ点を追加する（count>=0）
     *
     * @param count
     */
    public void add(int count, LocalTime time) {
        update(time);
        buckets.addToBack(count);
    }

    /**
     * @param now
     * @return
     */
    public int trailingCount(LocalTime now) {
        update(LocalTime.now());
        return buckets.totalSum();
    }

    /**
     * @param now
     */
    private void update(LocalTime now) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HHmmss");
        int current_bucket = now.toSecondOfDay() / secs_per_bucket;
        int last_update_bucket;
        if (last_update_time != null) {
            last_update_bucket = last_update_time.toSecondOfDay() / secs_per_bucket;
        } else {
            last_update_bucket = now.toSecondOfDay() / secs_per_bucket;
        }

        buckets.shift(current_bucket - last_update_bucket);
        last_update_time = now;
    }
}
