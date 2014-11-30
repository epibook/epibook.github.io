// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

// @include
class Node<T> {
  public T data;
  public Node<T> next;
// @exclude

  public Node(T dt, Node<T> n) {
    data = dt;
    next = n;
  }
// @include
}
// @exclude
