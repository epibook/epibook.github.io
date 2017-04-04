// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.LinkedHashMap;
import java.util.Map;

// @include
public class LRUCache {
  LinkedHashMap<Integer, Integer> isbnToPrice;

  LRUCache(final int capacity) {
    this.isbnToPrice
        = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
              return this.size() > capacity;
            }
          };
  }

  public Integer lookup(Integer key) {
    if (!isbnToPrice.containsKey(key)) {
      return null;
    }
    return isbnToPrice.get(key);
  }

  public Integer insert(Integer key, Integer value) {
    // We add the value for key only if key is not present - we don't update
    // existing values.
    Integer currentValue = isbnToPrice.get(key);
    if (!isbnToPrice.containsKey(key)) {
      isbnToPrice.put(key, value);
      return currentValue;
    } else {
      return null;
    }
  }

  public Integer erase(Object key) { return isbnToPrice.remove(key); }
  // @exclude

  public static void main(String[] args) {
    final int CAPACITY = 2;
    LRUCache c = new LRUCache(CAPACITY);
    System.out.println("c.insert(1, 1)");
    c.insert(1, 1);
    System.out.println("c.insert(1, 10)");
    c.insert(1, 10);
    System.out.println("c.lookup(2, val)");
    assert(null == c.lookup(2));
    System.out.println("c.lookup(1, val)");
    assert(c.lookup(1) == 1);
    c.erase(1);
    assert(null == c.lookup(1));

    // test capacity constraints honored, also FIFO ordering
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.insert(3, 1);
    c.insert(4, 1);
    assert(null == c.lookup(1));
    assert(null == c.lookup(2));
    assert(1 == c.lookup(3));
    assert(1 == c.lookup(4));

    // test retrieval moves to front
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.insert(3, 1);
    c.lookup(2);
    c.insert(4, 1);
    assert(null == c.lookup(1));
    assert(1 == c.lookup(2));
    assert(null == c.lookup(3));
    assert(1 == c.lookup(4));

    // test update moves to front
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.insert(3, 1);
    c.insert(2, 2);
    c.insert(4, 1);
    assert(null == c.lookup(1));
    assert(1 == c.lookup(2));
    assert(null == c.lookup(3));
    assert(1 == c.lookup(4));

    // test erase
    c = new LRUCache(CAPACITY);
    c.insert(1, 1);
    c.insert(2, 1);
    c.erase(2);
    c.insert(3, 3);
    assert(1 == c.lookup(1));
    assert(null == c.lookup(2));
    assert(3 == c.lookup(3));
  }
}
