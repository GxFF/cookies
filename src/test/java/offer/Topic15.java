package com.guo.interview.offer;

import org.junit.Assert;
import org.junit.Test;

public class Topic15 {

    public int count1(int num) {
        int count=0;
        while (num > 0) {
            count++;
            num = num & (num - 1);
        }
        return count;
    }

    @Test
    public void main() {
        Assert.assertEquals(3, count1(Integer.parseInt("1010100", 2)));
        Assert.assertEquals(6, count1(Integer.parseInt("101011110", 2)));
        Assert.assertEquals(1, count1(Integer.parseInt("010000", 2)));
        Assert.assertEquals(1, count1(Integer.parseInt("1", 2)));
        Assert.assertEquals(0, count1(Integer.parseInt("0", 2)));
    }
}