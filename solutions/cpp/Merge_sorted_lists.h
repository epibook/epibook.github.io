// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include "./Linked_list_prototype.h"

void AppendNode(shared_ptr<ListNode<int>>* node,
                shared_ptr<ListNode<int>>* tail);

// @include
shared_ptr<ListNode<int>> MergeTwoSortedLists(shared_ptr<ListNode<int>> F,
                                              shared_ptr<ListNode<int>> L) {
  // Creates a placeholder for the result.
  shared_ptr<ListNode<int>> dummy_head(new ListNode<int>);
  auto tail = dummy_head;

  while (F && L) {
    AppendNode(F->data < L->data ? &F : &L, &tail);
  }

  if (F) {
    // Appends the remaining nodes of F.
    tail->next = F;
  } else if (L) {
    // Appends the remaining nodes of L.
    tail->next = L;
  }
  return dummy_head->next;
}

void AppendNode(shared_ptr<ListNode<int>>* node,
                shared_ptr<ListNode<int>>* tail) {
  (*tail)->next = *node;
  *tail = *node;  // Updates tail.
  *node = (*node)->next;
}
// @exclude
