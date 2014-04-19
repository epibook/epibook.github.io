// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

// @include
class CheckingCycleAlternative {
  public static <T> Node<T> hasCycle(Node<T> head) {
    Node<T> fast = head;
    Node<T> slow = head;

    while (slow != null && slow.next != null && fast != null
        && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) { // there is a cycle.
        // Tries to find the start of the cycle.
        slow = head;
        // Both pointers advance at the same time.
        while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow; // slow is the start of cycle.
      }
    }
    return null; // means no cycle.
  }

  // @exclude

  public static void main(String[] args) {
    Node<Integer> l3 = new Node<Integer>(3, null);
    Node<Integer> l2 = new Node<Integer>(2, l3);
    Node<Integer> l1 = new Node<Integer>(1, l2);

    // should output "l1 does not have cycle."
    assert (hasCycle(l1) == null);
    System.out.println("l1 " + (hasCycle(l1) != null ? "has" : "does not have")
        + " cycle.");

    // make it a cycle
    l3.next = l2;
    // should output "l1 has cycle, located at node has value 2"
    assert (hasCycle(l1) != null);
    assert (hasCycle(l1).data == 2);
    Node<Integer> temp = hasCycle(l1);
    if (temp != null) {
      System.out
          .println("l1 has cycle, located at node has value " + temp.data);
    } else {
      System.out.println("l1 does not have cycle");
    }
  }
}
