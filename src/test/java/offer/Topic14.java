package offer;

import org.junit.Assert;
import org.junit.Test;

public class Topic14 {

    private int max(int length) {
        if (length <= 1) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        int[] max = new int[length+1];
        max[0] = 0;
        max[1] = 1;
        max[2] = 2;
        max[3] = 3;
        for (int i = 4; i <= length; i++) {
            for (int j = 1; j < i; j++) {
                max[i] = Math.max(max[i], max[j] * max[i-j]);
            }
        }
        return max[length];
    }

    @Test
    public void main() {
        Assert.assertEquals(4, max(4));
        Assert.assertEquals(6, max(5));
        Assert.assertEquals(9, max(6));
        Assert.assertEquals(12, max(7));
        Assert.assertEquals(18, max(8));
    }
}