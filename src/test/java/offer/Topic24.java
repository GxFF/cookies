package com.guo.interview.offer;

import com.guo.interview.offer.common.LinkUtil;
import com.guo.interview.offer.common.Node;
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