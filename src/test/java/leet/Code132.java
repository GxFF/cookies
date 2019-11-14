package leet;

import org.junit.Assert;
import org.junit.Test;

public class Code132 {

    /**
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * 返回符合要求的最少分割次数。
     *
     * 示例:
     *
     * 输入: "aab" 输出: 1 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     */
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int[][] result = new int[s.length()][s.length()];
        for (int start = s.length(); start >= 0; start--) {
            for (int end = start; end < s.length(); end++) {
                if (isPalindrome(s, result, start, end)) {
                    result[start][end] = 0;
                } else {
                    int minCut = Integer.MAX_VALUE;
                    for (int x = start; x < end; x++) {
                        minCut = Math.min(minCut, result[start][x] + result[x + 1][end] + 1);
                    }
                    result[start][end] = minCut;
                }
            }
        }
        return result[0][s.length() - 1];
    }

    private boolean isPalindrome(String s, int[][] result, int start, int end) {
        int i = start + 1;
        int j = end -1;
        if (i <= j && result[i][j] != Integer.MAX_VALUE) {
            if (result[i][j] == 0) {
                return s.charAt(start) == s.charAt(end);
            } else {
                return false;
            }
        }
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(1, minCut("aab"));
        Assert.assertEquals(1, minCut("aabb"));
        Assert.assertEquals(0, minCut("abccba"));
        Assert.assertEquals(0, minCut("abaaba"));
        Assert.assertEquals(1, minCut("aaabaa"));
    }
}