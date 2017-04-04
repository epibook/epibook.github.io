// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CHECKING_CYCLE_H_
#define SOLUTIONS_CHECKING_CYCLE_H_

#include <memory>

#include "./Linked_list_prototype.h"

// @include
shared_ptr<ListNode<int>> HasCycle(const shared_ptr<ListNode<int>>& head) {
  shared_ptr<ListNode<int>> fast = head, slow = head;

  while (fast && fast->next) {
    slow = slow->next, fast = fast->next->next;
    if (slow == fast) {
      // There is a cycle, so now let's calculate the cycle length.
      int cycle_len = 0;
      do {
        ++cycle_len;
        fast = fast->next;
      } while (slow != fast);

      // Finds the start of the cycle.
      auto cycle_len_advanced_iter = head;
      while (cycle_len--) {
        cycle_len_advanced_iter = cycle_len_advanced_iter->next;
      }

      auto iter = head;
      // Both iterators advance in tandem.
      while (iter != cycle_len_advanced_iter) {
        iter = iter->next;
        cycle_len_advanced_iter = cycle_len_advanced_iter->next;
      }
      return iter;  // iter is the start of cycle.
    }
  }
  return nullptr;  // No cycle.
}
// @exclude
#endif  // SOLUTIONS_SWAP_BITS_H_
