// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

// @include
class Node<T> {
  public T data;
  public Node<T> next;

  Node(T data, Node<T> next) {
    this.data = data;
    this.next = next;
  }
}
// @exclude
