package leet;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Code17 {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例:
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        char[][] chars = new char[][]{
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
        };
        LinkedList<String> result = new LinkedList<>();
        result.add("");
        StringBuilder temp = new StringBuilder(digits.length());
        for (char num : digits.toCharArray()) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                temp.append(result.poll());
                temp.append("-");
                for (char c : chars[num - '2']) {
                    temp.setLength(temp.length() - 1);
                    temp.append(c);
                    result.offer(temp.toString());
                }
                temp.setLength(0);
            }
        }
        return result;
    }


    @Test
    public void baseTest() {
        Assert.assertEquals(Sets.newHashSet("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), new HashSet<>(letterCombinations("23")));
        Assert.assertEquals(Sets.newHashSet("p","q","r","s"), new HashSet<>(letterCombinations("7")));
    }
}