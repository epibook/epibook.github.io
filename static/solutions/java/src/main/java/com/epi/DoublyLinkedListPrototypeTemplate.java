// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

// @include
class DoublyLinkedListNode<T> {
  public T data;
  public DoublyLinkedListNode<T> prev, next;
  // @exclude

  DoublyLinkedListNode(T data, DoublyLinkedListNode<T> prev,
                       DoublyLinkedListNode<T> next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }
  // @include
}
// @exclude
