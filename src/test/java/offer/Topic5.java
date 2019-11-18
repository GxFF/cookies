package offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Topic5 {

    public char[] replace(char[] src) {
        if (null == src) {
            return null;
        }
        int whiteCount = 0;
        int i = 0;
        for (; i < src.length; i++) {
            if ((int)src[i] == 0) {
                i--;
                break;
            }
            if (src[i] == ' ') {
                whiteCount++;
            }
        }
        if (whiteCount == 0) {
            return src;
        }
        int end = i+2*whiteCount;
        for (;whiteCount>0&&i>=0;i--){
            if (src[i] == ' ') {
                whiteCount--;
                src[end--] = '0';
                src[end--] = '2';
                src[end--] = '%';
            } else {
                src[end--]=src[i];
            }
        }
        return src;
    }

    @Test
    public void main() {
        char[] t1 = "we%20are%20happy".toCharArray();
        char[] s1 = new char[t1.length];
        copy("we are happy".toCharArray(), s1);
        char[] t2 = "that%20is%20not%20true".toCharArray();
        char[] s2 = new char[t2.length];
        copy("that is not true".toCharArray(), s2);
        Assert.assertTrue(Arrays.equals(t1,replace(s1)));
        Assert.assertTrue(Arrays.equals(t2,replace(s2)));
    }

    private void copy(char[] src, char[] desc) {
        if (null == src || null == desc || src.length > desc.length) {
            return;
        }
        for (int i = 0; i < src.length; i++) {
            desc[i] = src[i];
        }
    }
}
