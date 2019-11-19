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
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int dp = 0;
        int min = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    dp = nums[i];
                } else {
                    dp = dp + nums[j];
                }
                if (dp >= s) {
                    min = Math.min(min, j - i);
                }
            }
        }
        return min < nums.length ? min + 1 : 0;
    }

    public int minSubArrayLenBetter(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int minLen = nums.length;
        for (int i = 1; i < sums.length; i++) {
            int target = sums[i-1] + s;
            int minBand = minBand(sums, i, sums.length-1, target);
            if (sums[minBand] >= target) {
                minLen = Math.min(minLen, minBand - i);
            }
        }
        return minLen == nums.length ? 0 : minLen + 1;
    }

    private int minBand(int[] array, int start, int end, int target) {
        int mid;
        do {
            mid = (start + end) / 2;
            if (array[mid] > target) {
                end = mid - 1;
            } else if (array[mid] < target) {
                start = mid + 1;
            } else {
                break;
            }
        } while (start <= end);
        return mid;
    }

    public int minSubArrayLenBest(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left  = 0, sum = 0, minLen = nums.length;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left++];
            }
        }
        return minLen == nums.length ? 0 : minLen + 1;
    }


    @Test
    public void baseTest() {
        Assert.assertEquals(2, minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(1, minSubArrayLen(4, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(6, minSubArrayLen(15, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(0, minSubArrayLen(16, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(2, minSubArrayLenBetter(7, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(1, minSubArrayLenBetter(4, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(6, minSubArrayLenBetter(15, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(0, minSubArrayLenBetter(16, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(8, minSubArrayLenBetter(213, new int[]{12,28,83,4,25,26,25,2,25,25,25,12}));
        Assert.assertEquals(2, minSubArrayLenBest(7, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(1, minSubArrayLenBest(4, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(6, minSubArrayLenBest(15, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(0, minSubArrayLenBest(16, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(8, minSubArrayLenBest(213, new int[]{12,28,83,4,25,26,25,2,25,25,25,12}));
    }
}