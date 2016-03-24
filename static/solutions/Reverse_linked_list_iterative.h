// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_REVERSE_LINKED_LIST_ITERATIVE_H_
#define SOLUTIONS_REVERSE_LINKED_LIST_ITERATIVE_H_

#include <memory>

#include "./Linked_list_prototype.h"

using std::shared_ptr;

// @include
shared_ptr<ListNode<int>> ReverseLinkedList(
    const shared_ptr<ListNode<int>>& head) {
  shared_ptr<ListNode<int>> prev = nullptr, curr = head;
  while (curr) {
    auto next = curr->next;
    curr->next = prev;
    prev = curr;
    curr = next;
  }
  return prev;
}
// @exclude
#endif  // SOLUTIONS_REVERSE_LINKED_LIST_ITERATIVE_H_
