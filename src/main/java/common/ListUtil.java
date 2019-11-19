package common;

import java.util.ArrayList;
import java.util.Objects;

public class ListUtil {


    public static <T> ListHolder<T> toLink(T... data) {
        ListHolder<T> holder = new ListHolder<>();
        ListNode<T> now = holder.head;
        for (int i = 0; i < data.length; i++) {
            now.next = new ListNode<>(data[i]);
            holder.link.add(now.next);
            now = now.next;
        }
        return holder;
    }
    public static class ListHolder<T> {
        ListNode<T> head = new ListNode<>();
        ArrayList<ListNode<T>> link = new ArrayList<>();

        public ListNode<T> get(int index) {
            return link.get(index);
        }
        public ListNode<T> getHead() {
            return head.next;
        }

        public ArrayList<ListNode<T>> getLink() {
            return link;
        }
    }

    public static boolean equals(ListNode list1, ListNode list2) {
        while (Objects.equals(list1, list2)) {
            if (list1 == null) {
                return true;
            }
            list1 = list1.next;
            list2 = list2.next;
        }
        return false;
    }
}
