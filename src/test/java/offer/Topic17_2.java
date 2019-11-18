package offer;

import org.junit.Assert;
import org.junit.Test;



public class Topic17_2 {

    char[] sum(char[] a, char[] b) {
        return null;
    }

    @Test
    public void main() {
        char[] a = new char[]{'1', '1', '1', '1'};
        char[] b = new char[]{'2', '2'};
        char[] c = new char[]{'-', '5'};
        Assert.assertEquals(new char[]{'1','1','3','3'}, sum(a, b));
        Assert.assertEquals(new char[]{'1','1','0','6'}, sum(a, c));
        Assert.assertEquals(new char[]{'1','7'}, sum(b, c));
        Assert.assertEquals(new char[]{'1','7'}, sum(c, b));
    }
}