package leet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Code16 {

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     *
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int minDistance = Integer.MAX_VALUE;
        int resultSum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1, sum;
            while (start < end) {
                sum = nums[i] + nums[start] + nums[end];
                int tempDistance = Math.abs(target - sum);
                if (tempDistance < minDistance) {
                    minDistance = tempDistance;
                    resultSum = sum;
                }
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return resultSum;
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(2, threeSumClosest(new Gson().fromJson("[-1,2,1,-4]", new TypeToken<int[]>(){}.getType()), 1));
        Assert.assertEquals(6, threeSumClosest(new int[]{1,2,3,4,5,6,7,8}, 1));
        Assert.assertEquals(1, threeSumClosest(new int[]{-2,1,2,3,4,5,6,7,8}, 1));
    }
}