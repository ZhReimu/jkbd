package com.mrx.jkbd.ui.util;

import java.util.HashMap;

/**
 * @author Mr.X
 * @since 2022-05-09-0009
 **/
public class BoundedMapQueue<K, V> extends HashMap<K, V> {

    private final int maxSize;

    public BoundedMapQueue(int size) {
        super(size);
        maxSize = size;
    }

    @Override
    public V put(K key, V value) {
        if (size() + 1 > maxSize) {
            throw new IllegalStateException("队列越界, 最大为: " + maxSize);
        }
        return super.put(key, value);
    }

    public boolean isFull() {
        return size() == maxSize;
    }

}
