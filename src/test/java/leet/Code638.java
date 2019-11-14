package leet;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class Code638 {

    /**
     * 在LeetCode商店中， 有许多在售的物品。
     * 然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
     * 现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
     * 每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。
     * 任意大礼包可无限次购买。
     *
     * 示例 1:
     * 输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
     * 输出: 14
     * 解释:
     * 有A和B两种物品，价格分别为¥2和¥5。
     * 大礼包1，你可以以¥5的价格购买3A和0B。
     * 大礼包2， 你可以以¥10的价格购买1A和2B。
     * 你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
     *
     * 示例 2:
     * 输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
     * 输出: 11
     * 解释:
     * A，B，C的价格分别为¥2，¥3，¥4.
     * 你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。
     * 你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
     * 你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
     *
     * 说明:
     * 最多6种物品， 100种大礼包。
     * 每种物品，你最多只需要购买6个。
     * 你不可以购买超出待购清单的物品，即使更便宜。
     */

    private Integer minTotalCost;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs){
        minTotalCost = Integer.MAX_VALUE;
        if (price == null || special == null || needs == null) {
            return 0;
        }
        Iterator<List<Integer>> it = special.iterator();
        while (it.hasNext()) {
            List<Integer> item = it.next();
            int normalSum = 0;
            boolean moreGoods = false;
            for (int i = 0; i < item.size() - 1; i++) {
                if (needs.get(i) < item.get(i)) {
                    moreGoods = true;
                    break;
                }
                normalSum += price.get(i) * item.get(i);
            }
            if (moreGoods || normalSum <= item.get(item.size() - 1)) {
                it.remove();
            }
        }
        traceBack(price, special, needs, 0);
        return minTotalCost;
    }
    private void traceBack(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int costNow){
        if (costNow > minTotalCost) {
            return;
        }
        int needCount = needs.stream().reduce(Integer::sum).get();
        if (needCount == 0) {
            minTotalCost = Math.min(costNow, minTotalCost);
        }
        boolean hadValidBag = false;
        for (List<Integer> bag : special) {
            if (invalid(bag, needs)) {
                continue;
            }
            hadValidBag = true;
            for (int i = 0; i < bag.size() - 1; i++) {
                needs.set(i, needs.get(i) - bag.get(i));
            }
            traceBack(price, special, needs, costNow + bag.get(bag.size() - 1));
            for (int i = 0; i < bag.size() - 1; i++) {
                needs.set(i, needs.get(i) + bag.get(i));
            }
        }
        if (!hadValidBag) {
            for (int i = 0; i < needs.size(); i++) {
                costNow += price.get(i) * needs.get(i);
            }
            minTotalCost = Math.min(costNow, minTotalCost);
        }
    }

    private boolean invalid(List<Integer> bag, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < bag.get(i)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void baseTest() {
        /**
         * [2,5]
         * [[3,0,5],[1,2,10]]
         * [3,2]
         *
         * 14
         */
        List<Integer> price1 = Lists.newArrayList(2, 5);
        List<Integer> bag11 = Lists.newArrayList(3, 0, 5);
        List<Integer> bag12 = Lists.newArrayList(1, 2, 10);
        List<List<Integer>> special1 = Lists.newArrayList(bag11, bag12);
        List<Integer> needs1 = Lists.newArrayList(3, 2);
        Assert.assertEquals(14, shoppingOffers(price1, special1, needs1));

        /**
         * [0,0,0]
         * [[1,1,0,4],[2,2,1,9]]
         * [2,2,1]
         *
         * 0
         */
        List<Integer> price2 = Lists.newArrayList(0,0,0);
        List<Integer> bag21 = Lists.newArrayList(1,1,0,4);
        List<Integer> bag22 = Lists.newArrayList(2,2,1,9);
        List<List<Integer>> special2 = Lists.newArrayList(bag21, bag22);
        List<Integer> needs2 = Lists.newArrayList(2,2,1);
        Assert.assertEquals(0, shoppingOffers(price2, special2, needs2));

        /**
         * [9,6,1,5,3,4]
         * [[1,2,2,1,0,4,14],[6,3,4,0,0,1,16],[4,5,6,6,2,4,26],[1,1,4,3,4,3,15],[4,2,5,4,4,5,15],[4,0,0,2,3,5,13],[2,4,6,4,3,5,7],[3,3,4,2,2,6,21],[0,3,0,2,3,3,15],[0,2,4,2,2,5,24],[4,1,5,4,5,4,25],[6,0,5,0,1,1,14],[4,0,5,2,1,5,8],[4,1,4,4,3,1,10],[4,4,2,1,5,0,14],[2,4,4,1,3,1,16],[4,2,3,1,2,1,26],[2,4,1,6,5,3,2],[0,2,0,4,0,0,19],[3,1,6,3,3,1,23],[6,2,3,2,4,4,16],[5,3,5,5,0,4,5],[5,0,4,3,0,2,20],[5,3,1,2,2,5,8],[3,0,6,1,0,2,10],[5,6,6,1,0,4,12],[0,6,6,4,6,4,21],[0,4,6,5,0,0,22],[0,4,2,4,4,6,16],[4,2,1,0,6,5,14],[0,1,3,5,0,3,8],[5,5,3,3,2,0,4],[1,0,3,6,2,3,18],[4,2,6,2,2,5,2],[0,2,5,5,3,6,12],[1,0,6,6,5,0,10],[6,0,0,5,5,1,24],[1,4,6,5,6,3,19],[2,2,4,2,4,2,20],[5,6,1,4,0,5,3],[3,3,2,2,1,0,14],[0,1,3,6,5,0,9],[5,3,6,5,3,3,11],[5,3,3,1,0,2,26],[0,1,1,4,2,1,16],[4,2,3,2,1,4,6],[0,2,1,3,3,5,15],[5,6,4,1,2,5,18],[1,0,0,1,6,1,16],[2,0,6,6,2,2,17],[4,4,0,2,4,6,12],[0,5,2,5,4,6,6],[5,2,1,6,2,1,24],[2,0,2,2,0,1,14],[1,1,0,5,3,5,16],[0,2,3,5,5,5,6],[3,2,0,6,4,6,8],[4,0,1,4,5,1,6],[5,0,5,6,6,3,7],[2,6,0,0,2,1,25],[0,4,6,1,4,4,6],[6,3,1,4,1,1,24],[6,2,1,2,1,4,4],[0,1,2,3,0,1,3],[0,2,5,6,5,2,13],[2,6,4,2,2,3,17],[3,4,5,0,5,4,20],[6,2,3,4,1,3,4],[6,4,0,0,0,5,16],[3,1,2,5,0,6,11],[1,3,2,2,5,6,14],[1,3,4,5,3,5,18],[2,1,1,2,6,1,1],[4,0,4,0,6,6,8],[4,6,0,5,0,2,1],[3,1,0,5,3,2,26],[4,0,4,0,6,6,6],[5,0,0,0,0,4,26],[4,3,2,2,0,2,14],[5,2,4,0,2,2,26],[3,4,6,0,2,4,25],[2,1,5,5,1,3,26],[0,5,2,4,0,2,24],[5,2,5,4,5,0,1],[5,3,0,1,5,4,15],[6,1,5,1,2,1,21],[2,5,1,2,1,4,15],[1,4,4,0,0,0,1],[5,0,6,1,1,4,22],[0,1,1,6,1,4,1],[1,6,0,3,2,2,17],[3,4,3,3,1,5,17],[1,5,5,4,5,2,27],[0,6,5,5,0,0,26],[1,4,0,3,1,0,13],[1,0,3,5,2,4,5],[2,2,2,3,0,0,11],[3,2,2,1,1,1,6],[6,6,1,1,1,6,26],[1,5,1,2,5,2,12]]
         * [6,6,6,1,6,6]
         *
         * 34
         */

        List<Integer> price3 = new Gson().fromJson("[9,6,1,5,3,4]", new TypeToken<List<Integer>>() {}.getType());
        String specialStr3 = "[[1,2,2,1,0,4,14],[6,3,4,0,0,1,16],[4,5,6,6,2,4,26],[1,1,4,3,4,3,15],[4,2,5,4,4,5,15],[4,0,0,2,3,5,13],[2,4,6,4,3,5,7],[3,3,4,2,2,6,21],[0,3,0,2,3,3,15],[0,2,4,2,2,5,24],[4,1,5,4,5,4,25],[6,0,5,0,1,1,14],[4,0,5,2,1,5,8],[4,1,4,4,3,1,10],[4,4,2,1,5,0,14],[2,4,4,1,3,1,16],[4,2,3,1,2,1,26],[2,4,1,6,5,3,2],[0,2,0,4,0,0,19],[3,1,6,3,3,1,23],[6,2,3,2,4,4,16],[5,3,5,5,0,4,5],[5,0,4,3,0,2,20],[5,3,1,2,2,5,8],[3,0,6,1,0,2,10],[5,6,6,1,0,4,12],[0,6,6,4,6,4,21],[0,4,6,5,0,0,22],[0,4,2,4,4,6,16],[4,2,1,0,6,5,14],[0,1,3,5,0,3,8],[5,5,3,3,2,0,4],[1,0,3,6,2,3,18],[4,2,6,2,2,5,2],[0,2,5,5,3,6,12],[1,0,6,6,5,0,10],[6,0,0,5,5,1,24],[1,4,6,5,6,3,19],[2,2,4,2,4,2,20],[5,6,1,4,0,5,3],[3,3,2,2,1,0,14],[0,1,3,6,5,0,9],[5,3,6,5,3,3,11],[5,3,3,1,0,2,26],[0,1,1,4,2,1,16],[4,2,3,2,1,4,6],[0,2,1,3,3,5,15],[5,6,4,1,2,5,18],[1,0,0,1,6,1,16],[2,0,6,6,2,2,17],[4,4,0,2,4,6,12],[0,5,2,5,4,6,6],[5,2,1,6,2,1,24],[2,0,2,2,0,1,14],[1,1,0,5,3,5,16],[0,2,3,5,5,5,6],[3,2,0,6,4,6,8],[4,0,1,4,5,1,6],[5,0,5,6,6,3,7],[2,6,0,0,2,1,25],[0,4,6,1,4,4,6],[6,3,1,4,1,1,24],[6,2,1,2,1,4,4],[0,1,2,3,0,1,3],[0,2,5,6,5,2,13],[2,6,4,2,2,3,17],[3,4,5,0,5,4,20],[6,2,3,4,1,3,4],[6,4,0,0,0,5,16],[3,1,2,5,0,6,11],[1,3,2,2,5,6,14],[1,3,4,5,3,5,18],[2,1,1,2,6,1,1],[4,0,4,0,6,6,8],[4,6,0,5,0,2,1],[3,1,0,5,3,2,26],[4,0,4,0,6,6,6],[5,0,0,0,0,4,26],[4,3,2,2,0,2,14],[5,2,4,0,2,2,26],[3,4,6,0,2,4,25],[2,1,5,5,1,3,26],[0,5,2,4,0,2,24],[5,2,5,4,5,0,1],[5,3,0,1,5,4,15],[6,1,5,1,2,1,21],[2,5,1,2,1,4,15],[1,4,4,0,0,0,1],[5,0,6,1,1,4,22],[0,1,1,6,1,4,1],[1,6,0,3,2,2,17],[3,4,3,3,1,5,17],[1,5,5,4,5,2,27],[0,6,5,5,0,0,26],[1,4,0,3,1,0,13],[1,0,3,5,2,4,5],[2,2,2,3,0,0,11],[3,2,2,1,1,1,6],[6,6,1,1,1,6,26],[1,5,1,2,5,2,12]]";
        List<List<Integer>> special3 = new Gson().fromJson(specialStr3, new TypeToken<List<List<Integer>>>(){}.getType());
        List<Integer> needs3 = new Gson().fromJson("[6,6,6,1,6,6]", new TypeToken<List<Integer>>(){}.getType());
        Assert.assertEquals(34, shoppingOffers(price3, special3, needs3));
    }
}