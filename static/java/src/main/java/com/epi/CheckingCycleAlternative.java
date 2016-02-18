// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

public class CheckingCycleAlternative {
  // @include
  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
    ListNode<Integer> fast = head, slow = head;

    while (fast != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) { // There is a cycle.
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
    return null; // No cycle.
  }
  // @exclude

  public static void main(String[] args) {
    ListNode<Integer> l3 = new ListNode<>(3, null);
    ListNode<Integer> l2 = new ListNode<>(2, l3);
    ListNode<Integer> l1 = new ListNode<>(1, l2);

    // should output "l1 does not have cycle."
    assert(hasCycle(l1) == null);
    System.out.println("l1 " + (hasCycle(l1) != null ? "has" : "does not have")
                       + " cycle.");

    // make it a cycle
    l3.next = l2;
    // should output "l1 has cycle, located at node has value 2"
    assert(hasCycle(l1) != null);
    assert(hasCycle(l1).data == 2);
    ListNode<Integer> temp = hasCycle(l1);
    if (temp != null) {
      System.out.println("l1 has cycle, located at node has value "
                         + temp.data);
    } else {
      System.out.println("l1 does not have cycle");
    }
  }
}
