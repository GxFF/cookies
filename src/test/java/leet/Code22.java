package leet;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code22 {

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     *
     * 例如，给出 n = 3，生成结果为：
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        traceBack("", 0, n, result);
        return result;
    }

    public void traceBack(String now, int left, int n, List<String> result) {
        if (left == 0 && n == 0) {
            result.add(now);
            return;
        }
        if (n > 0) {
            traceBack(now + "(", left + 1, n - 1, result);
        }
        if (left > 0) {
            traceBack(now + ")", left - 1, n, result);
        }
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(Arrays.asList("((()))","(()())","(())()","()(())","()()()"), generateParenthesis(3));
        Assert.assertEquals(Arrays.asList("(())","()()"), generateParenthesis(2));
    }
}
