package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class DoublyLinkedListPrototypeTemplate {
  // @include
  public static class NodeT<T> {
    private T data;
    private NodeT<T> prev, next;

    public NodeT(T data) {
      this.data = data;
    }

    public T getData() {
      return data;
    }

    public void setData(T data) {
      this.data = data;
    }

    public NodeT<T> getPrev() {
      return prev;
    }

    public void setPrev(NodeT<T> prev) {
      this.prev = prev;
    }

    public NodeT<T> getNext() {
      return next;
    }

    public void setNext(NodeT<T> next) {
      this.next = next;
    }
  }
  // @exclude
}
