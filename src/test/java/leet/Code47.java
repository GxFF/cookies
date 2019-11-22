package leet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Code47 {

    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     *
     * 示例:
     *
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> template = Arrays.stream(nums).boxed().collect(Collectors.toList());
        traceBack(result, 0, template);
        return new ArrayList<>(result);
    }

    public void traceBack(Set<List<Integer>> result, int first, List<Integer> template) {
        if (first == template.size()) {
            result.add(new ArrayList<>(template));
            return;
        }
        for (int i = first; i < template.size(); i++) {
            if (i == first || template.get(i) != template.get(first)) {
                Collections.swap(template, i, first);
                traceBack(result, first + 1, template);
                Collections.swap(template, i, first);
            }
        }
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1,1,2],[1,2,1],[2,1,1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permuteUnique(new int[]{1,1,2})));
        Assert.assertEquals(new HashSet<>(new Gson().fromJson("[[1,1,2,2],[1,2,1,2],[1,2,2,1],[2,1,1,2],[2,1,2,1],[2,2,1,1]]", new TypeToken<List<List<Integer>>>(){}.getType())), new HashSet<>(permuteUnique(new int[]{2,2,1,1})));

    }
}