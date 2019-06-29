package org.julnamoo.immutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

public class ImmutableQueueTest {

    private ImmutableQueue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new ImmutableQueue<>(1);
    }

    @Test
    public void enQueue() throws Exception {
        IntStream.range(0, 1000000)
                 .forEach(i -> queue.enQueue(i));
    }

    @Test
    public void deQueue() throws Exception {
        // enqueue first
        IntStream.range(0, 1000000)
                 .forEach(i -> queue.enQueue(i));

        // try dequeue as much as items
        IntStream.range(0, 1000000)
                 .forEach(i -> queue.deQueue());
    }

    @Test
    public void head() throws Exception {
        // enqueue first
        IntStream.range(0, 1000000)
                 .forEach(i -> queue.enQueue(i));

        // try dequeue as much as items
        long head = IntStream.range(0, 1000000)
                             .mapToObj(i -> queue.head())
                             .count();
        assertEquals(1000000, head);
    }

    @Test
    public void isEmpty() throws Exception {
        // didn't enqueue
        assertTrue(queue.isEmpty());

        queue.enQueue(Integer.MAX_VALUE);
        assertFalse(queue.isEmpty());

        queue.deQueue();
        assertTrue(queue.isEmpty());
    }
}
