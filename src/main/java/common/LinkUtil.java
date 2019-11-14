package com.guo.interview.offer.common;

import java.util.ArrayList;

public class LinkUtil {

    public static <T> LinkHolder<T> toLink(T... data) {
        LinkHolder<T> holder = new LinkHolder<>();
        Node<T> now = holder.head;
        for (int i = 0; i < data.length; i++) {
            now.next = new Node<>(data[i]);
            holder.link.add(now.next);
            now = now.next;
        }
        return holder;
    }
    public static class LinkHolder<T> {
        Node<T> head = new Node<>();
        ArrayList<Node<T>> link = new ArrayList<>();

        public Node<T> get(int index) {
            return link.get(index);
        }
        public Node<T> getHead() {
            return head.next;
        }

        public ArrayList<Node<T>> getLink() {
            return link;
        }
    }
}
