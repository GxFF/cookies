package leet;

import com.google.gson.reflect.TypeToken;
import common.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

public class Code1105 {

    /**
     * 附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。
     *
     * 你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。
     *
     * 按顺序 将这些书摆放到总宽度为 shelf_width 的书架上。
     *
     * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
     *
     * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
     *
     * 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
     *
     * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
     *
     * 以这种方式布置书架，返回书架整体可能的最小高度。
     *
     * 提示：
     * 1 <= books.length <= 1000
     * 1 <= books[i][0] <= shelf_width <= 1000
     * 1 <= books[i][1] <= 1000
     */
    public int minHeightShelves(int[][] books, int shelfWidth){
        int[] dp = new int[books.length + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1000 * 1000;
        }
        for (int i = 1; i < dp.length; i++) {
            int tempWidth = 0, bookPoint = i, tempHeight = 0;
            while (bookPoint > 0) {
                tempWidth += books[bookPoint - 1][0];
                if (tempWidth > shelfWidth) {
                    break;
                }
                tempHeight = Math.max(tempHeight, books[bookPoint - 1][1]);
                dp[i] = Math.min(dp[i], dp[bookPoint - 1] + tempHeight);
                bookPoint--;
            }
        }
        return dp[dp.length - 1];
    }


    @Test
    public void baseTest() {
        int[][] books = ArrayUtil.parseArray("[[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]", new TypeToken<int[][]>() {}.getType());
        int shelfWidth = 4;
        Assert.assertEquals(6, minHeightShelves(books, shelfWidth));
    }
}