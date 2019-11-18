package offer;

import common.LinkUtil;
import common.Node;
import org.junit.Assert;
import org.junit.Test;

public class Topic22 {

    public Node lastK(Node head, int k) {
        if (null == head || k == 0) {
            return null;
        }
        Node before = head, after = head;
        int count = k;
        while (before != null && count > 0) {
            before = before.next;
            count--;
        }
        if (count > 0) {
            return null;
        }
        while (before != null) {
            before = before.next;
            after = after.next;
        }
        return after;
    }

    @Test
    public void main() {
        LinkUtil.LinkHolder holder = LinkUtil.toLink(1, 2, 3, 4, 5, 6, 7);
        Assert.assertEquals(holder.getLink().get(4), lastK(holder.getHead(), 3));
        Assert.assertEquals(holder.getLink().get(0), lastK(holder.getHead(), 7));
        Assert.assertNull(lastK(holder.getHead(), 8));
        Assert.assertNull(lastK(null, 7));
        LinkUtil.LinkHolder holder2 = LinkUtil.toLink(1);
        Assert.assertEquals(holder2.getLink().get(0), lastK(holder2.getHead(),1));
    }
}