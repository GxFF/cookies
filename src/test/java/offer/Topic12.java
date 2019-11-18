package offer;

import org.junit.Assert;
import org.junit.Test;

public class Topic12 {

    private boolean contains(char[][] data, String string) {
        if (data == null || data.length ==0|| string==null|| string.equals("")) {
            return false;
        }
        boolean[][] visited = new boolean[data.length][data[0].length];
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                boolean contain = find(data, i, j, string, index, visited);
                if (contain) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] data, int row, int col, String str, int index, boolean[][] visit) {
        if (row < 0 || row >= data.length || col < 0 || col >= data[row].length) {
            return false;
        }
        if (visit[row][col]) {
            return false;
        }
        if (index >= str.length()) {
            return true;
        }
        if (data[row][col] != str.charAt(index)) {
            return false;
        }
        index++;
        visit[row][col]=true;
        boolean left = find(data, row, col - 1, str, index, visit);
        boolean right = find(data, row, col + 1, str, index, visit);
        boolean top = find(data, row - 1, col, str, index, visit);
        boolean bottom = find(data, row + 1, col, str, index, visit);
        boolean find = left || right || top || bottom;
        visit[row][col]=find;
        return find;
    }

    @Test
    public void main() {
        char[][] data = new char[][]{
                {'a', 'b', 't', 'g'},
                {'d', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'},
                {'m', 'q', 'x', 'l'},
        };
        Assert.assertTrue(contains(data, "bfce"));
        Assert.assertTrue(contains(data, "bfcexqmjd"));
        Assert.assertTrue(contains(data, "csh"));
        Assert.assertFalse(contains(data, "cshm"));
        Assert.assertFalse(contains(data, "xhfg"));
        Assert.assertFalse(contains(data, "ded"));
    }
}