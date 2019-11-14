package com.guo.interview.offer;

import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.Test;

public class Topic20 {

    public boolean isNum(String num) {
        if (Strings.isNullOrEmpty(num)) {
            return false;
        }
        boolean isNum = false;
        int start = 0, end = 0;
        while (end < num.length() && !is(num, end,'.') && !is(num, end, 'e') && !is(num, end,'E')) {
            end++;
        }
        isNum = isSignedNum(num.substring(start, end));
        int len = end - start;
        start = end;
        if (is(num, start, '.')) {
            start++;
            while (end < num.length() && !is(num, end, 'e') && !is(num, end, 'E')) {
                end++;
            }
            boolean isUnsignedNum = isUnsignedNum(num.substring(start, end));
            isNum = (isNum && isUnsignedNum)
                    || (len == 0 && isUnsignedNum)
                    || (isNum && end == start);
        }
        start = end;
        if (is(num, start, 'e') || is(num, start, 'E')) {
            isNum = isNum && isSignedNum(num.substring(start + 1));
            end = num.length();
        }
        return isNum && end==num.length();
    }
    private boolean is(String str, int index, char c) {
        if (index >= str.length()) {
            return false;
        }
        return str.charAt(index) == c;
    }

    private boolean isSignedNum(String num) {
        if (Strings.isNullOrEmpty(num)) {
            return false;
        }
        if (num.charAt(0) == '-' || num.charAt(0) == '+') {
            return isUnsignedNum(num.substring(1));
        }
        return isUnsignedNum(num);
    }

    private boolean isUnsignedNum(String num) {
        if (Strings.isNullOrEmpty(num)) {
            return false;
        }
        for (int i=0; i < num.length(); i++) {
            if (!isNumberChar(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumberChar(char c) {
        return c >= '0' && c <= '9';
    }

    @Test
    public void main() {
        Assert.assertTrue(isNum("+100"));
        Assert.assertTrue(isNum("5e2"));
        Assert.assertTrue(isNum("-123"));
        Assert.assertTrue(isNum("3.142"));
        Assert.assertTrue(isNum(".142"));
        Assert.assertTrue(isNum("3."));
        Assert.assertTrue(isNum("-1E-16"));
        Assert.assertTrue(isNum("9.83E16"));
        Assert.assertFalse(isNum("."));
        Assert.assertFalse(isNum("12e"));
        Assert.assertFalse(isNum("1a3.14"));
        Assert.assertFalse(isNum("1a3."));
        Assert.assertFalse(isNum("1.2.3"));
        Assert.assertFalse(isNum("+-3"));
        Assert.assertFalse(isNum("12e+5.4"));
    }
}