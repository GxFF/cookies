package com.guo.interview.offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class Topic9_1 {

    private class Stack<T>{
        LinkedList<T> headQueue = new LinkedList<>();
        LinkedList<T> tailQueue = new LinkedList<>();

        public void push(T item) {
            tailQueue.offer(item);
        }
        public T pop() {
            if (tailQueue.isEmpty() && !headQueue.isEmpty()) {
                LinkedList temp = tailQueue;
                tailQueue = headQueue;
                headQueue = temp;
            }
            while (tailQueue.size() > 1) {
                headQueue.offer(tailQueue.poll());
            }
            return tailQueue.poll();
        }
    }
    @Test
    public void main() {
        Stack<Long> stack = new Stack<>();
        stack.push(2L);
        stack.push(3L);
        Assert.assertEquals(3L, (long)stack.pop());
        stack.push(4L);
        Assert.assertEquals(4L, (long)stack.pop());
        stack.push(5L);
        stack.push(6L);
        Assert.assertEquals(6L, (long)stack.pop());
        Assert.assertEquals(5L, (long)stack.pop());
        Assert.assertEquals(2L, (long)stack.pop());
        stack.push(1L);
        Assert.assertEquals(1L, (long)stack.pop());
        Assert.assertNull(stack.pop());
    }
}