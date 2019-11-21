package leet;

import org.junit.Assert;
import org.junit.Test;

public class Code29 {

    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * <p>
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * <p>
     * 示例 1:
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * <p>
     * 示例 2:
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * <p>
     * 说明:
     * 被除数和除数均为 32 位有符号整数。
     * 除数不为 0。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
     */
    public int divide(int dividend, int divisor) {
        boolean sign = (dividend ^ divisor) < 0;
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;
        int temp;
        int min = Integer.MIN_VALUE >> 1;
        int result = 0;
        int tempResult;
        while (dividend <= divisor) {
            temp = divisor;
            tempResult = 1;
            while (dividend <= temp << 1 && temp > min) {
                temp <<= 1;
                tempResult <<= 1;
            }
            dividend -= temp;
            result += tempResult;
        }
        if (!sign) {
            if (result == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return result;
        }
        return -result;
    }

    @Test
    public void baseTest() {
//        Assert.assertEquals(3, divide(10, 3));
//        Assert.assertEquals(-2, divide(7, -3));
//        Assert.assertEquals(0, divide(0, -3));
        Assert.assertEquals(2147483647, divide(-2147483648,-1));
    }
}