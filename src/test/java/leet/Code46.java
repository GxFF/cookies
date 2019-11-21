package leet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;

public class Code46 {

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    public List<List<Integer>> permute(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length * 2);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int[] pos = new int[nums.length];
        LinkedList<List<Integer>> result = new LinkedList<>();
        result.add(new ArrayList<>());
        boolean changed = true;
        while (changed) {
            Arrays.fill(pos, 0);
            List<Integer> temp = result.poll();
            for (int num : temp) {
                pos[map.get(num)] = 1;
            }
            changed = false;
            for (int i = 0; i < pos.length; i++) {
                if (pos[i] == 0) {
                    List<Integer> newList = new ArrayList<>(temp);
                    newList.add(nums[i]);
                    result.offer(newList);
                    changed = true;
                }
            }
            if (!changed) {
                result.offer(temp);
            }
        }
        return result;
    }

    public List<List<Integer>> permuteBetter(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> template = Arrays.stream(nums).boxed().collect(Collectors.toList());
        traceBack(nums.length, result, template, 0);
        return result;
    }
    private void traceBack(int n, List<List<Integer>> result, List<Integer> template, int first) {
        if (first == n) {
            result.add(new ArrayList<>(template));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(template, first, i);
            traceBack(n, result, template, first + 1);
            Collections.swap(template, first, i);
        }
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permute(new int[]{1,2,3})));
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1, -1, 0], [0, -1, 1], [0, 1, -1], [-1, 1, 0], [-1, 0, 1], [1, 0, -1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permute(new int[]{0,-1,1})));
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1,2],[2,1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permute(new int[]{1,2})));
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permute(new int[]{1})));

        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permuteBetter(new int[]{1,2,3})));
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1, -1, 0], [0, -1, 1], [0, 1, -1], [-1, 1, 0], [-1, 0, 1], [1, 0, -1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permuteBetter(new int[]{0,-1,1})));
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1,2],[2,1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permuteBetter(new int[]{1,2})));
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permuteBetter(new int[]{1})));
    }
}
