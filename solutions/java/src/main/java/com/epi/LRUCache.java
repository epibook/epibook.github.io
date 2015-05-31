// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

import com.epi.utils.Pair;

import java.util.HashMap;
import java.util.Map;

// @include
public class LRUCache {
  public LRUCache(int c) { capacity = c; }

  public boolean lookup(int isbn) {
    Pair<LinkedList<Integer>.Node, Integer> it = isbnPriceTable.get(isbn);
    if (it == null) {
      return false;
    }

    lookupVal = it.getSecond();
    moveToFront(isbn, it); // Since isbn is the most recently used ISBN.
    return true;
  }

  public void insert(int isbn, int price) {
    Pair<LinkedList<Integer>.Node, Integer> it = isbnPriceTable.get(isbn);
    if (it != null) {
      // Entry is already present, moves it to the front.
      moveToFront(isbn, it);
    } else {
      if (isbnPriceTable.size() == capacity) {
        // Removes the least recently used ISBN to get space.
        isbnPriceTable.remove(lruQueue.back());
      }

      // Adds the new entry into the front.
      isbnPriceTable.put(isbn, new Pair<>(lruQueue.pushFront(isbn), price));
    }
  }

  public boolean erase(int isbn) {
    Pair<LinkedList<Integer>.Node, Integer> it = isbnPriceTable.get(isbn);
    if (it == null) {
      return false;
    }

    lruQueue.erase(it.getFirst());
    isbnPriceTable.remove(isbn);
    return true;
  }

  // Moves isbn to the front of the LRU cache.
  private void moveToFront(int isbn, Pair<LinkedList<Integer>.Node, Integer> it) {
    lruQueue.erase(it.getFirst());
    lruQueue.pushBack(isbn);
    it.setFirst(lruQueue.front());
  }

  public int lookupVal = 0;
  private int capacity;
  private Map<Integer, Pair<LinkedList<Integer>.Node, Integer>> isbnPriceTable =
      new HashMap<>();
  private LinkedList<Integer> lruQueue = new LinkedList<>();
  // @exclude

  public static void main(String[] args) {
    LRUCache c = new LRUCache(3);
    System.out.println("c.insert(1, 1)");
    c.insert(1, 1);
    System.out.println("c.insert(1, 10)");
    c.insert(1, 10);
    System.out.println("c.lookup(2, val)");
    assert(!c.lookup(2));
    System.out.println("c.lookup(1, val)");
    assert(c.lookup(1));
    assert(c.lookupVal == 1);
    c.erase(1);
    assert(!c.lookup(1));
  }
  // @include
}
// @exclude
