package leet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class Code826 {

    /**
     * 有一些工作：difficulty[i] 表示第i个工作的难度，profit[i]表示第i个工作的收益。
     *
     * 现在我们有一些工人。worker[i]是第i个工人的能力，即该工人只能完成难度小于等于worker[i]的工作。
     *
     * 每一个工人都最多只能安排一个工作，但是一个工作可以完成多次。
     *
     * 举个例子，如果3个工人都尝试完成一份报酬为1的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。
     *
     * 我们能得到的最大收益是多少？
     *
     * 示例：
     * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
     * 输出: 100
     * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
     *
     * 提示:
     *   1 <= difficulty.length = profit.length <= 10000
     *   1 <= worker.length <= 10000
     *   difficulty[i], profit[i], worker[i]  的范围是 [1, 10^5]
     */
//    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
//        int maxProfit = 0;
//        for (int i = 0; i < worker.length; i++) {
//            int tempProfit=0;
//            for (int j = 0; j < difficulty.length; j++) {
//                if (difficulty[j] <= worker[i] && profit[j] > tempProfit) {
//                    tempProfit = profit[j];
//                }
//            }
//            maxProfit += tempProfit;
//        }
//        return maxProfit;
//    }
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        List<Work> works = new ArrayList<>(difficulty.length);
        for (int i = 0; i < difficulty.length; i++) {
            works.add(new Work(difficulty[i], profit[i]));
        }
        Collections.sort(works, Comparator.comparing(Work::getProfit).reversed());
        Arrays.sort(worker);
        int maxProfit = 0;
        for (int i = 0, j = worker.length - 1; j >= 0; j--) {
            while (i < works.size() && worker[j] < works.get(i).difficult) {
                i++;
            }
            if (i < works.size()) {
                maxProfit+=works.get(i).profit;
            }
        }
        return maxProfit;
    }

    class Work {
        private int difficult;
        private int profit;

        public Work(int difficult, int profit) {
            this.difficult = difficult;
            this.profit = profit;
        }

        public int getProfit() {
            return profit;
        }
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(100, maxProfitAssignment(
            new Gson().fromJson("[2,4,6,8,10]", new TypeToken<int[]>(){}.getType()),
            new Gson().fromJson("[10,20,30,40,50]", new TypeToken<int[]>(){}.getType()),
            new Gson().fromJson("[4,5,6,7]", new TypeToken<int[]>(){}.getType())
            ));
        Assert.assertEquals(0, maxProfitAssignment(
            new Gson().fromJson("[85,47,57]", new TypeToken<int[]>(){}.getType()),
            new Gson().fromJson("[24,66,99]", new TypeToken<int[]>(){}.getType()),
            new Gson().fromJson("[40,25,25]", new TypeToken<int[]>(){}.getType())
        ));
    }

}