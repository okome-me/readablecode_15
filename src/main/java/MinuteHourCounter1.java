import lombok.Getter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 計算量：O(n) [nはイベント数]
 * 直近1分間および直近1時間の類的カウントを記録する。
 * 例えば。帯域幅の使用状況を確認するのに使える。
 */
@Getter
public class MinuteHourCounter1 {

    private List<Event> events = new ArrayList<Event>();

    /**
     * 新しいデータ点を追加する（count>=0）
     * それから1分間は、minuteCount()の返す値が+countだけ増える
     * それから1時間は、hourCount()の返す値が+countだけ増える
     *
     * @param count
     */
    public void add(int count, LocalTime time) {
        events.add(new Event(count, time));
    }


    /**
     * 直近60秒間の累積カウントを返す。
     *
     * @return
     */
    public int minuteCount() {
        return countSince(LocalTime.now().minusSeconds(60));
    }


    /**
     * 直近3600秒間の累積カウントを返す。
     *
     * @return
     */
    public int hourCount() {
        return countSince(LocalTime.now().minusSeconds(3600));
    }


    /**
     * cutoff秒前からの現在までの累計カウントを返す。
     *
     * @param cutoff
     * @return
     */
    private int countSince(LocalTime cutoff) {
        var count = 0;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getTime().isBefore(cutoff)) {
                break;
            }
            count += events.get(i).getCount();
        }
        return count;
    }
}
