// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

// @include
class ListNode<T> {
  public T data;
  public ListNode<T> next;
  // @exclude

  ListNode(T data, ListNode<T> next) {
    this.data = data;
    this.next = next;
  }
  // @include
}
// @exclude
