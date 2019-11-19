package common;

import java.util.Objects;

public class ListNode <T> {
    public T val;
    public ListNode next;
    public ListNode(T x) {
        val = x;
    }
    public ListNode() {
    }
    public void printLink() {
        ListNode now = this;
        while (!Objects.isNull(now.next)) {
            now.print();
            System.out.print("-");
            now = now.next;
        }
        now.print();
        System.out.println();
    }
    public void print(){
        System.out.print("[" + val.toString() + "]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode<?> listNode = (ListNode<?>) o;
        return Objects.equals(val, listNode.val) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(val, next);
    }
}
