// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CHECKING_CYCLE_H_
#define SOLUTIONS_CHECKING_CYCLE_H_

#include <memory>

#include "./Linked_list_prototype.h"

// @include
shared_ptr<ListNode<int>> HasCycle(const shared_ptr<ListNode<int>>& head) {
  shared_ptr<ListNode<int>> fast = head, slow = head;

  while (slow && slow->next && fast && fast->next && fast->next->next) {
    slow = slow->next, fast = fast->next->next;
    if (slow == fast) {  // There is a cycle.
      // Calculates the cycle length.
      int cycle_len = 0;
      do {
        ++cycle_len;
        fast = fast->next;
      } while (slow != fast);

      // Tries to find the start of the cycle.
      slow = head, fast = head;
      // Fast pointer advances cycle_len first.
      while (cycle_len--) {
        fast = fast->next;
      }
      // Both pointers advance at the same time.
      while (slow != fast) {
        slow = slow->next, fast = fast->next;
      }
      return slow;  // The start of cycle.
    }
  }
  return nullptr;  // No cycle.
}
// @exclude
#endif  // SOLUTIONS_SWAP_BITS_H_
