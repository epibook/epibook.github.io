// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.HashSet;
import java.util.Set;

// @include
class ListNode<T> {
  public T data;
  public ListNode<T> next;
  // @exclude

  ListNode(T data, ListNode<T> next) {
    this.data = data;
    this.next = next;
  }

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    Set<ListNode<T>> visited = new HashSet<ListNode<T>>();
    ListNode<T> iter = this;
    while (iter != null) {
      if (visited.contains(iter)) {
        result.append(" Loop back to " + data.toString());
        break;
      }
      visited.add(iter);
      result.append(iter.data.toString() + (iter.next != null ? " -> " : " "));
    }
    return result.toString();
  }

  // @include
}
// @exclude
