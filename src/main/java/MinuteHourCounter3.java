import lombok.Getter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 直近1分間および直近1時間の類的カウントを記録する。
 * 例えば。帯域幅の使用状況を確認するのに使える。
 */
public class MinuteHourCounter3 {

    // 直近1分間のカウント
    private TrailingBucketCounter minute_count;
    // 直近1時間のカウント
    private TrailingBucketCounter hour_count;

    public MinuteHourCounter3() {
        minute_count = new TrailingBucketCounter(60, 1);
        hour_count = new TrailingBucketCounter(60, 60);
    }

    /**
     * 新しいデータ点を追加する（count>=0）
     * それから1分間は、minuteCount()の返す値が+countだけ増える
     * それから1時間は、hourCount()の返す値が+countだけ増える
     *
     * @param count
     */
    public void add(int count, LocalTime time) {
        minute_count.add(count, time);
        hour_count.add(count, time);
    }

    /**
     * 直近60秒間の累積カウントを返す。
     *
     * @return
     */
    public int minuteCount() {
        LocalTime now = LocalTime.now();
        return minute_count.trailingCount(now);
    }


    /**
     * 直近3600秒間の累積カウントを返す。
     *
     * @return
     */
    public int hourCount() {
        LocalTime now = LocalTime.now();
        return hour_count.trailingCount(now);
    }
}
