// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

// @include
class DoublyListNode<T> {
  public T data;
  public DoublyListNode<T> prev, next;
  // @exclude

  DoublyListNode(T data, DoublyListNode<T> prev, DoublyListNode<T> next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }
  // @include
}
// @exclude
