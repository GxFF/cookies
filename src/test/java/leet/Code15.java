package leet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class Code15 {

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     */
    public List<List<Integer>> threeSum(int[] nums){
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return new ArrayList<>();
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            int target = 0 - nums[i];
            for (int start = i + 1, end = nums.length - 1; start < end; ) {
                if (nums[end] < 0) {
                    break;
                }
                int sum = nums[start] + nums[end];
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    end--;
                }
            }
        }
        return new ArrayList<>(result);
    }

    public List<List<Integer>> threeSum_better
        (int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int negSize = 0;
        int posSize = 0;
        int zeroSize = 0;
        for (int v : nums) {
            if (v < minValue) {
                minValue = v;
            }
            if (v > maxValue) {
                maxValue = v;
            }
            if (v > 0) {
                posSize++;
            } else if (v < 0) {
                negSize++;
            } else {
                zeroSize++;
            }
        }
        if (zeroSize >= 3) {
            res.add(Arrays.asList(0, 0, 0));
        }
        if (negSize == 0 || posSize == 0) {
            return res;
        }
        if (minValue * 2 + maxValue > 0) {
            maxValue = -minValue * 2;
        } else if (maxValue * 2 + minValue < 0) {
            minValue = -maxValue * 2;
        }

        int[] map = new int[maxValue - minValue + 1];
        int[] negs = new int[negSize];
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        for (int v : nums) {
            if (v >= minValue && v <= maxValue) {
                if (map[v - minValue]++ == 0) {
                    if (v > 0) {
                        poses[posSize++] = v;
                    } else if (v < 0) {
                        negs[negSize++] = v;
                    }
                }
            }
        }
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int basej = 0;
        for (int i = negSize - 1; i >= 0; i--) {
            int nv = negs[i];
            int minp = (-nv) >>> 1;
            while (basej < posSize && poses[basej] < minp) {
                basej++;
            }
            for (int j = basej; j < posSize; j++) {
                int pv = poses[j];
                int cv = 0 - nv - pv;
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        if (map[nv - minValue] > 1) {
                            res.add(Arrays.asList(nv, nv, pv));
                        }
                    } else if (cv == pv) {
                        if (map[pv - minValue] > 1) {
                            res.add(Arrays.asList(nv, pv, pv));
                        }
                    } else {
                        if (map[cv - minValue] > 0) {
                            res.add(Arrays.asList(nv, cv, pv));
                        }
                    }
                } else if (cv < nv) {
                    break;
                }
            }
        }
        return res;
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(
            new Gson().fromJson("[[-1, 0, 1],[-1, -1, 2]]", new TypeToken<List<List<Integer>>>(){}.getType()),
            threeSum(new Gson().fromJson("[-1, 0, 1, 2, -1, -4]", new TypeToken<int[]>(){}.getType()))
            );
    }
}
