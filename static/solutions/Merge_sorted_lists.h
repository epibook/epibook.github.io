// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_MERGE_SORTED_LISTS_H_
#define SOLUTIONS_MERGE_SORTED_LISTS_H_

#include "./Linked_list_prototype.h"

void AppendNode(shared_ptr<ListNode<int>> *, shared_ptr<ListNode<int>> *);

// @include
shared_ptr<ListNode<int>> MergeTwoSortedLists(shared_ptr<ListNode<int>> L1,
                                              shared_ptr<ListNode<int>> L2) {
  // Creates a placeholder for the result.
  shared_ptr<ListNode<int>> dummy_head(new ListNode<int>);
  auto tail = dummy_head;

  while (L1 && L2) {
    AppendNode(L1->data <= L2->data ? &L1 : &L2, &tail);
  }

  // Appends the remaining nodes of L1 or L2.
  tail->next = L1 ? L1 : L2;
  return dummy_head->next;
}

void AppendNode(shared_ptr<ListNode<int>> *node,
                shared_ptr<ListNode<int>> *tail) {
  (*tail)->next = *node;
  *tail = *node;  // Updates tail.
  *node = (*node)->next;
}
// @exclude
#endif  // SOLUTIONS_MERGE_SORTED_LISTS_H_
