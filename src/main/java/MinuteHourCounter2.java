import lombok.Getter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 計算量：O(1)
 * メモリ使用量：保持するイベントの数たび増える
 * 直近1分間および直近1時間の類的カウントを記録する。
 * 例えば。帯域幅の使用状況を確認するのに使える。
 */
public class MinuteHourCounter2 {

    // 直近1分間のイベント
    private List<Event> minute_events = new ArrayList<Event>();
    // 直近1時間のイベント（直近1分間は含まない）
    private List<Event> hour_events = new ArrayList<Event>();

    // 直近1分間のカウント
    private int minute_count = 0;
    // 直近1時間のカウント
    private int hour_count = 0;

    /**
     * 新しいデータ点を追加する（count>=0）
     * それから1分間は、minuteCount()の返す値が+countだけ増える
     * それから1時間は、hourCount()の返す値が+countだけ増える
     *
     * @param count
     */
    public void add(int count, LocalTime time) {
        minute_events.add(new Event(count, time));
        minute_count += count;
        hour_count += count;

        shiftOldEvents(LocalTime.now());
    }

    /**
     * 直近60秒間の累積カウントを返す。
     *
     * @return
     */
    public int minuteCount() {
        shiftOldEvents(LocalTime.now());
        return minute_count;
    }

    /**
     * 直近3600秒間の累積カウントを返す。
     *
     * @return
     */
    public int hourCount() {
        shiftOldEvents(LocalTime.now());
        return hour_count;
    }

    /**
     * 古いイベントの削除
     *
     * @param now_secs
     * @return
     */
    private void shiftOldEvents(LocalTime now_secs) {
        LocalTime minute_ago = now_secs.minusSeconds(60);
        LocalTime hour_ago = now_secs.minusSeconds(3600);

        // 1分以上経過したイベントをminute_eventsからhour_eventsに移動する
        while (!minute_events.isEmpty() && minute_events.get(0).getTime().isBefore(minute_ago)) {
            hour_events.add(minute_events.get(0));
            minute_count -= minute_events.get(0).getCount();
            minute_events.remove(0);
        }

        // 1時間以上経過したイベントをhour_eventsから削除する
        while (!hour_events.isEmpty() && hour_events.get(0).getTime().isBefore(hour_ago)) {
            hour_count -= hour_events.get(0).getCount();
            hour_events.remove(0);
        }
    }
}
