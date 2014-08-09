// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import com.epi.utils.Pair;

import java.util.HashMap;
import java.util.Map;

// @include
public class LRUCache {

  public LRUCache(int c) {
    capacity = c;
  }

  public boolean lookup(int isbn) {
    Pair<LinkedList<Integer>.Node, Integer> it = cache.get(isbn);
    if (it == null) {
      return false;
    }

    lookupVal = it.getSecond();
    moveToFront(isbn, it);
    return true;
  }

  public void insert(int isbn, int price) {
    Pair<LinkedList<Integer>.Node, Integer> it = cache.get(isbn);
    if (it != null) {
      moveToFront(isbn, it);
    } else {
      // Remove the least recently used.
      if (cache.size() == capacity) {
        cache.remove(data.back());
      }
      cache.put(isbn,
          new Pair<>(data.pushFront(isbn),
              price)
      );
    }
  }

  public boolean erase(int isbn) {
    Pair<LinkedList<Integer>.Node, Integer> it = cache.get(isbn);
    if (it == null) {
      return false;
    }

    data.erase(it.getFirst());
    cache.remove(isbn);
    return true;
  }

  // Moves the most recent accessed item to the front.
  private void moveToFront(int isbn,
                           Pair<LinkedList<Integer>.Node, Integer> it) {
    data.erase(it.getFirst());
    data.pushBack(isbn);
    it.setFirst(data.front());
  }

  public int lookupVal = 0;
  private int capacity;
  private Map<Integer, Pair<LinkedList<Integer>.Node, Integer>>
      cache = new HashMap<>();
  private LinkedList<Integer> data = new LinkedList<>();
  // @exclude

  public static void main(String[] args) {
    LRUCache c = new LRUCache(3);
    System.out.println("c.insert(1, 1)");
    c.insert(1, 1);
    System.out.println("c.insert(1, 10)");
    c.insert(1, 10);
    System.out.println("c.lookup(2, val)");
    assert (!c.lookup(2));
    System.out.println("c.lookup(1, val)");
    assert (c.lookup(1));
    assert (c.lookupVal == 1);
    c.erase(1);
    assert (!c.lookup(1));
  }
  // @include
}
// @exclude
