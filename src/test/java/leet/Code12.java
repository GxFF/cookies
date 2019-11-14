package leet;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class Code12 {

    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     */
    public String intToRoman(int n){
        if (n > 3999) {
            return "ERROR";
        }
        StringBuilder result = new StringBuilder();
        List<Roman> processLink = Arrays.asList(
            new Roman(1000, "M", ""),
            new Roman(100, "C", "D"),
            new Roman(10, "X", "L"),
            new Roman(1, "I", "V")
            );
        for (int i = 0; i < processLink.size(); i++) {
            int num = n / processLink.get(i).baseNum;
            n = n % processLink.get(i).baseNum;
            if (num == 9) {
                result.append(processLink.get(i).baseString).append(processLink.get(i-1).baseString);
            } else if (num >= 5) {
                result.append(processLink.get(i).superString);
                while (num-- > 5) {
                    result.append(processLink.get(i).baseString);
                }
            } else if (num == 4) {
                result.append(processLink.get(i).baseString).append(processLink.get(i).superString);
            } else {
                while (num-- > 0) {
                    result.append(processLink.get(i).baseString);
                }
            }
        }
        return result.toString();
    }

    class Roman {
        int baseNum;
        String baseString;
        String superString;
        public Roman(int baseNum, String baseString, String superString) {
            this.baseNum = baseNum;
            this.baseString = baseString;
            this.superString = superString;
        }
    }

    @Test
    public void baseTest() {
        Assert.assertEquals("III", intToRoman(3));
        Assert.assertEquals("IV", intToRoman(4));
        Assert.assertEquals("IX", intToRoman(9));
        Assert.assertEquals("LVIII", intToRoman(58));
        Assert.assertEquals("MCMXCIV", intToRoman(1994));
    }
}