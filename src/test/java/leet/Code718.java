package leet;

import org.junit.Assert;
import org.junit.Test;

public class Code718 {

    /**
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     * <p>
     * 示例 1:
     * 输入:
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出: 3
     * 解释:
     * 长度最长的公共子数组是 [3, 2, 1]。
     * <p>
     * 说明:
     * 1 <= len(A), len(B) <= 1000
     * 0 <= A[i], B[i] < 100
     */
    public int findLength(int[] A, int[] B) {
        int max = 0;
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return max;
        }
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    max = Math.max(max, dp[i+1][j+1]);
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        return max;
    }

    public int findLengthBetter(int[] A, int[] B) {
        int max = 0;
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return max;
        }
        int[] dp = new int[B.length + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[j+1] = dp[j] + 1;
                    max = Math.max(max, dp[j + 1]);
                } else {
                    dp[j+1] = 0;
                }
            }
        }
        return max;
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(3, findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
        Assert.assertEquals(0, findLength(new int[]{70,39,25,40,7}, new int[]{52,20,67,5,31}));
        Assert.assertEquals(3, findLengthBetter(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
        Assert.assertEquals(0, findLengthBetter(new int[]{70,39,25,40,7}, new int[]{52,20,67,5,31}));
    }
}