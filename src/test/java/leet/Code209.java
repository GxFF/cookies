package leet;

import org.junit.Assert;
import org.junit.Test;

public class Code209 {

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
     * <p>
     * 示例: 
     * 输入: s = 7, nums = [2,3,1,2,4,3]
     * 输出: 2
     * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
     * <p>
     * 进阶:
     * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     */
    public int minSubArrayLen(int s, int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        int min = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                } else {
                    dp[i][j] = dp[i][j - 1] + nums[j];
                }
                if (dp[i][j] >= s) {
                    min = Math.min(min, j - i);
                }
            }
        }
        return min < nums.length ? min + 1 : 0;
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(2, minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(1, minSubArrayLen(4, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(6, minSubArrayLen(15, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(0, minSubArrayLen(16, new int[]{2, 3, 1, 2, 4, 3}));
    }
}