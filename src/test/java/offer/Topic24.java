package offer;

import common.LinkUtil;
import common.Node;
import org.junit.Assert;
import org.junit.Test;

public class Topic24 {

    public Node reverse(Node head) {
        Node hold = new Node();
        Node next;
        while (head != null) {
            next = head.next;
            head.next = hold.next;
            hold.next = head;
            head = next;
        }
        return hold.next;
    }

    @Test
    public void main() {
        reverse(LinkUtil.toLink(1,2,3,4,5,6,7,8).getHead()).printLink();
        Assert.assertNull(reverse(null));
    }
}