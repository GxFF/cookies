package com.guo.interview.offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class Topic9 {

    private class Queue<T>{
        private LinkedList<T> tailStack = new LinkedList<>();
        private LinkedList<T> headStack = new LinkedList<>();

        public void push(T item) {
            if (tailStack.isEmpty()) {
                while (!headStack.isEmpty()) {
                    tailStack.push(headStack.pop());
                }
            }
            tailStack.push(item);
        }

        public T poll() {
            if (headStack.isEmpty()) {
                while (!tailStack.isEmpty()) {
                    headStack.push(tailStack.pop());
                }
            }
            return headStack.pop();
        }
    }

    @Test
    public void main() {
        Queue<Integer> queue = new Queue<>();
        queue.push(4);
        queue.push(5);
        queue.push(6);
        Assert.assertEquals(4L, (long)queue.poll());
        queue.push(7);
        Assert.assertEquals(5L, (long)queue.poll());
        queue.push(8);
        queue.push(9);
        Assert.assertEquals(6L, (long)queue.poll());
        Assert.assertEquals(7L, (long)queue.poll());
        Assert.assertEquals(8L, (long)queue.poll());
        Assert.assertEquals(9L, (long)queue.poll());
        queue.poll();
    }
}