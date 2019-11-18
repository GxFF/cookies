package leet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Code18 {

    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * 注意：
     * 答案中不可以包含重复的四元组。
     *
     * 示例：
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * 满足要求的四元组集合为：
     * [
     * [-1,  0, 0, 1],
     * [-2, -1, 1, 2],
     * [-2,  0, 0, 2]
     * ]
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int start = j + 1, end = nums.length - 1, sum;
                while (start < end) {
                    sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum > target) {
                        end--;
                    } else if (sum < target) {
                        start++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        end--;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(
                new HashSet<>(new Gson().fromJson("[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]", new TypeToken<List<List<Integer>>>(){}.getType())),
                new HashSet<>(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0))
                );
        //[[-2,-1,0,3],[-2,0,0,2],[-3,-1,1,3],[-3,0,1,2],[-3,0,0,3],[-1,0,0,1],[-3,-2,2,3]]
        //[-2,-1,1,2]
        Assert.assertEquals(
                new HashSet<>(new Gson().fromJson("[[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]", new TypeToken<List<List<Integer>>>(){}.getType())),
                new HashSet<>(fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0))
                );
        Assert.assertEquals(
                new HashSet<>(new Gson().fromJson("[[-5,-4,-3,1]]", new TypeToken<List<List<Integer>>>(){}.getType())),
                new HashSet<>(fourSum(new int[]{1,-2,-5,-4,-3,3,3,5},-11))
                );
    }
}