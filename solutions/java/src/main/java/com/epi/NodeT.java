// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

// @include
class NodeT<T> {
  public T data;
  public NodeT<T> next;
// @exclude

  public NodeT(T dt, NodeT<T> n) {
    data = dt;
    next = n;
  }
// @include
}
// @exclude
