// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

class DeletionListTemplate {
  // @include
  public static <T> void deletionFromList(Node<T> v) {
    v.data = v.next.data;
    v.next = v.next.next;
  }

  // @exclude

  public static void main(String[] args) {
    Node<Integer> L = new Node<Integer>(1, new Node<Integer>(2,
        new Node<Integer>(3, null)));
    deletionFromList(L);
    assert (L.data == 2 && L.next.data == 3);
  }
}
