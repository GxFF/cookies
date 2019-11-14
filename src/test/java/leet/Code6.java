package leet;

import org.junit.Assert;
import org.junit.Test;

public class Code6 {

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     * 示例 1:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     */
    public String convert(String s, int numRows){
        if (s == null || s.length() == 0 || numRows < 2) {
            return s;
        }
        int normal = (numRows - 1) * 2;
        StringBuilder result = new StringBuilder();
        int mid;
        for (int i = 0; i < numRows; i++) {
            int index = i;
            while (index < s.length()) {
                result.append(s.charAt(index));
                if (i != 0 && i != numRows - 1 && (mid = index + (numRows - i - 1) * 2) < s.length()) {
                    result.append(s.charAt(mid));
                }
                index += normal;
            }
        }
        return result.toString();
    }


    @Test
    public void baseTest() {
        Assert.assertEquals("LCIRETOESIIGEDHN", convert("LEETCODEISHIRING", 3));
        Assert.assertEquals("LDREOEIIECIHNTSG", convert("LEETCODEISHIRING", 4));
        Assert.assertEquals("LEETCODEISHIRING", convert("LEETCODEISHIRING", 1));
    }
}