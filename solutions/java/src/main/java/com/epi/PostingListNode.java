package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class PostingListNode {
  private int order;
  private PostingListNode next, jump;

  public PostingListNode(int order, PostingListNode next, PostingListNode jump) {
    this.order = order;
    this.next = next;
    this.jump = jump;
  }

  public int getOrder() { return order; }

  public void setOrder(int order) { this.order = order; }

  public PostingListNode getNext() { return next; }

  public void setNext(PostingListNode next) { this.next = next; }

  public PostingListNode getJump() { return jump; }

  public void setJump(PostingListNode jump) { this.jump = jump; }
}
