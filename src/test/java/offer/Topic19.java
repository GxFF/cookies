package com.guo.interview.offer;

import com.google.common.base.Strings;
import com.guo.interview.offer.common.Node;
import org.junit.Assert;
import org.junit.Test;

public class Topic19 {

//    boolean match(String str, String pattern) {
//        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(pattern)) {
//            return false;
//        }
//        int sIndex = 0, pIndex = 0;
//        while (sIndex < str.length() && pIndex < pattern.length()) {
//            if (match(str.charAt(sIndex), pattern.charAt(pIndex))) {
//                sIndex++;
//                pIndex++;
//            } else if (pattern.charAt(pIndex) == '*'){
//                if (pIndex - 1 >= 0
//                        && match(str.charAt(sIndex), pattern.charAt(pIndex - 1))) {
//                    sIndex++;
//                } else {
//                    pIndex++;
//                }
//            } else if (pIndex + 1 < pattern.length() && pattern.charAt(pIndex + 1) == '*') {
//                pIndex += 1;
//            } else {
//                return false;
//            }
//        }
//        if (sIndex < str.length()) {
//            return false;
//        }
//
//    }
//
//    boolean match(char s, char p) {
//        return s == p || p == '.';
//    }

    private boolean match(String str, String pattern) {
        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(pattern)) {
            return false;
        }
        return match(str, 0, pattern, 0);
    }

    private boolean match(String str, int sIndex, String pattern, int pIndex) {
        if (sIndex == str.length() && pIndex == pattern.length()) {
            return true;
        }
        if (pIndex == pattern.length()) {
            return false;
        }
        if (pIndex + 1 < pattern.length() && pattern.charAt(pIndex+1) == '*') {
            if (matchPos(str, sIndex, pattern, pIndex)) {
                return match(str, sIndex + 1, pattern, pIndex)
                        || match(str, sIndex + 1, pattern, pIndex + 2)
                        || match(str, sIndex, pattern, pIndex + 2);
            } else {
                return match(str, sIndex, pattern, pIndex + 2);
            }
        }
        if (matchPos(str, sIndex, pattern, pIndex)) {
            return match(str, sIndex + 1, pattern, pIndex + 1);
        }
        return false;
    }

    private boolean matchPos(String str, int sIndex, String pattern, int pIndex) {
        return sIndex < str.length()
                && (str.charAt(sIndex) == pattern.charAt(pIndex) || pattern.charAt(pIndex) == '.');
    }
    @Test
    public void main() {
        Assert.assertTrue(match("aaa", "aa."));
        Assert.assertFalse(match("aaa", "aa*a"));
        Assert.assertTrue(match("aaa", "ab*ac*a"));
        Assert.assertFalse(match("aaa", "aa.a"));
        Assert.assertTrue(match("aaa", "a*a*a*a*a*"));

    }
}