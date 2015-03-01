// @author Andrey Pavlov

package com.epi;

class PNode<T> {
  public T data;
  public PNode<T> next, jump;

  PNode(T dt, PNode<T> n, PNode<T> j) {
    data = dt;
    next = n;
    jump = j;
  }
}
