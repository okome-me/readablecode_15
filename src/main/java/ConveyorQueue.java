import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 最大数を持ったキュー
 * 古いデータは橋から落ちる
 */
@Getter
public class ConveyorQueue {

    private List<Integer> q = new ArrayList<Integer>();

    private int max_items;

    // キューに含まれる全ての合計
    private int total_sum;

    public ConveyorQueue(int max_items) {
        this.max_items = max_items;
        this.total_sum = 0;
    }


    /**
     * キューの最後の値を増加する
     *
     * @param count
     */
    public void addToBack(int count) {
        if (q.isEmpty()) {
            shift(1);
        }
        int end_index = q.size() - 1;
        q.set(end_index, q.get(end_index) + count);
        total_sum += count;
    }

    /**
     * キューの値をnum_shifted分だけシフトする
     * 新しい項目は0で初期化する
     * 最古の項目はmax_items以下なら削除する
     *
     * @param num_shifted
     */
    public void shift(int num_shifted) {
        // 項目がシフトされすぎた場合、キューをクリアする
        if (num_shifted >= max_items) {
            q = new ArrayList<Integer>();
            total_sum = 0;
            return;
        }

        // 必要な分だけ0をプッシュする
        while (num_shifted > 0) {
            q.add(0);
            num_shifted--;
        }

        // 超過した項目はすべて落とす
        while (q.size() > max_items) {
            total_sum -= q.get(0);
            q.remove(0);
        }
    }

    /**
     * 現在のキューに含まれる項目の合計値を返す
     *
     * @return
     */
    public int totalSum() {
        return total_sum;
    }
}
