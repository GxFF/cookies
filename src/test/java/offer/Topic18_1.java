package com.guo.interview.offer;

import com.guo.interview.offer.common.LinkUtil;
import com.guo.interview.offer.common.Node;
import org.junit.Assert;
import org.junit.Test;

public class Topic18_1 {

    private Node removeSameNode(Node head) {
        boolean same = false;
        Node before = null, now = head;
        while (now != null) {
            if (now.equals(before)) {
                same = true;
                before.next = now.next;
                now.next = null;
                now = before.next;
            } else if (same) {
                same = false;
                before.var = now.var;
                before.next = now.next;
                now.next = null;
                now = before.next;
            } else {
                before = now;
                now = now.next;
            }
        }
        if (same) {
            before = null;
            now = head;
            while (now.next != null) {
                before = now;
                now = now.next;
            }
            if (now.equals(head)) {
                return null;
            }
            if (before != null) {
                before.next = null;
            }
        }
        return head;
    }


    @Test
    public void main() {
        removeSameNode(LinkUtil.toLink(new int[]{1,2,2,3}).getHead()).printLink();
        removeSameNode(LinkUtil.toLink(new int[]{2,2,3}).getHead()).printLink();
        removeSameNode(LinkUtil.toLink(new int[]{2,2,3,3,3,4}).getHead()).printLink();
        removeSameNode(LinkUtil.toLink(new int[]{2}).getHead()).printLink();
        Assert.assertNull(removeSameNode(LinkUtil.toLink(new int[]{2,2}).getHead()));
        Assert.assertNull(removeSameNode(LinkUtil.toLink(new int[]{2,2,2}).getHead()));
        Assert.assertNull(removeSameNode(LinkUtil.toLink(new int[]{1, 1, 2, 2, 2,}).getHead()));
        Assert.assertNull(removeSameNode(null));
    }
}