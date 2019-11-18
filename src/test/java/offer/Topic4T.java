package offer;

import org.junit.Assert;
import org.junit.Test;

public class Topic4T {
    public boolean find(int[][] data, int target) {
        if (null == data || data.length<1) {
            throw new IllegalArgumentException("not null");
        }
        int i = 0;
        int j = data.length - 1;
        while (i < data[0].length && j >= 0) {
            int now = data[i][j];
            if (now == target) {
                return true;
            } else if (now < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    @Test
    public void main() {
        int[][] data = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        Assert.assertTrue(find(data, 7));
        Assert.assertFalse(find(data, 5));
    }
}
