package org.julnamoo.immutable;

import java.util.Arrays;
import java.util.Objects;

public class ImmutableQueue<T> implements Queue<T> {

    private T[] items;
    private int size;

    public ImmutableQueue() {
        this(4);
    }

    @SuppressWarnings("unchecked")
    public ImmutableQueue(int initCapacity) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException("initCapacity should be positive integer");
        }
        items = (T[]) new Object[initCapacity];
        size = 0;
    }

    private ImmutableQueue(T[] items) {
        this.items = items;
        size = items.length;
    }

    @Override
    public Queue<T> enQueue(T t) {
        if (Objects.isNull(t)) {
            throw new NullPointerException();
        }

        if (size == Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size + 1 > items.length) {
            int newSize = Integer.min(Integer.MAX_VALUE, size + Integer.max(size / 2, 1));
            items = Arrays.copyOf(items, newSize);
        }

        items[size++] = t;
        return this;
    }

    /**
     * Removes the element at the beginning of the immutable queue, and returns the new queue.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Queue<T> deQueue() {
        if (items.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new ImmutableQueue<>(Arrays.copyOfRange(items, 1, size));
    }

    @Override
    public T head() {
        if (items != null && items.length > 0) {
            return items[0];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public boolean isEmpty() {
        return items == null || size == 0;
    }
}
