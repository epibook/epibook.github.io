// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Map;
import java.util.LinkedHashMap;

// @include
class LRUCache<K,V> extends LinkedHashMap<K,V> {
  LRUCache(int capacity) {
      super();
      this.capacity = capacity;
  }
  int capacity;

  @Override
  protected boolean removeEldestEntry(Map.Entry<K,V> e) {
      return this.size() > capacity;
  }

  @Override 
  public V get(Object key) {
    if (!super.containsKey(key)) {
        return null;
    }
    V value = super.get(key);
    // Since key has just been accessed, move it to the front.
    moveToFront((K) key, value); 
    return value;
  }

  @Override
  public V put(K key, V value) {
    V result = super.get(key);
    super.put(key,value);
    // The call to super.put(key, value) will not move  key to the front
    // if it was already present, so we explicitly move it to the front.
    moveToFront(key, value); 
    return result;
  }

  // Forces this key-value pair to move to the front.
  private void moveToFront(K key, V value) {
    super.remove(key);
    super.put(key,value);
  }
// @exclude


  private static final int CAPACITY = 2;

  public static void main(String[] args) {
    LRUCache<Integer, Integer> c = new LRUCache<>(CAPACITY);
    System.out.println("c.put(1, 1)");
    c.put(1, 1);
    System.out.println("c.put(1, 10)");
    c.put(1, 10);
    System.out.println("c.get(2, val)");
    assert(null == c.get(2));
    System.out.println("c.get(1, val)");
    assert(c.get(1) == 10);
    c.remove(1);
    assert(null == c.get(1));

    // test capacity contraints honored, also FIFO ordering
    c = new LRUCache<>(CAPACITY);
    c.put(1,1);
    c.put(2,1);
    c.put(3,1);
    c.put(4,1);
    assert(null == c.get(1));
    assert(null == c.get(2));
    assert(1 == c.get(3));
    assert(1 == c.get(4));

    // test retrieval moves to front
    c = new LRUCache<>(CAPACITY);
    c.put(1,1);
    c.put(2,1);
    c.put(3,1);
    c.get(2);
    c.put(4,1);
    assert(null == c.get(1));
    assert(1 == c.get(2));
    assert(null == c.get(3));
    assert(1 == c.get(4));

    // test update moves to front
    c = new LRUCache<>(CAPACITY);
    c.put(1,1);
    c.put(2,1);
    c.put(3,1);
    c.put(2,2);
    c.put(4,1);
    assert(null == c.get(1));
    assert(2 == c.get(2));
    assert(null == c.get(3));
    assert(1 == c.get(4));

  }
  // @include
}
// @exclude
