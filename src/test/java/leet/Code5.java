package leet;

import org.junit.Assert;
import org.junit.Test;

public class Code5 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        boolean[][] pal = new boolean[s.length()][s.length()];
        int start = 0, end = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                    if (j - i > end - start) {
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end +1);
    }

    @Test
    public void baseTest(){
        Assert.assertEquals("aba", longestPalindrome("abaa"));
        Assert.assertEquals("dd", longestPalindrome("abddaa"));
        Assert.assertEquals("a", longestPalindrome("a"));
//        Assert.assertEquals("", longestPalindrome(""));

    }
}
