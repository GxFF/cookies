package com.guo.interview.offer;

import com.guo.interview.offer.common.LinkUtil;
import com.guo.interview.offer.common.Node;
import org.junit.Test;

public class Topic25 {

    public Node merge(Node<Integer> h1, Node<Integer> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        Node<Integer> result = new Node<>();
        Node<Integer> last = result;
        while (h1 != null && h2 != null) {
            if (h1.var <= h2.var) {
                last.next = h1;
                h1 = h1.next;
            } else {
                last.next = h2;
                h2 = h2.next;
            }
            last = last.next;
        }
        if (h1 != null) {
            last.next = h1;
        }
        if (h2 != null) {
            last.next = h2;
        }
        return result.next;
    }

    @Test
    public void main() {
        merge(LinkUtil.toLink(1, 3, 5, 7, 9).getHead(), LinkUtil.toLink(2, 4, 6, 8).getHead()).printLink();
        merge(LinkUtil.toLink(1, 3, 5, 7, 9).getHead(), LinkUtil.toLink(0, 2, 4, 6, 8,10,11,12).getHead()).printLink();
        merge(LinkUtil.toLink(1, 3, 5, 7, 9).getHead(), null).printLink();
        merge(null, LinkUtil.toLink(2, 4, 6, 8).getHead()).printLink();
    }
}