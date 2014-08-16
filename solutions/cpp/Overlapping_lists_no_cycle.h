// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_OVERLAPPING_LISTS_NO_CYCLE_H_
#define SOLUTIONS_OVERLAPPING_LISTS_NO_CYCLE_H_

#include "./Linked_list_prototype.h"

int CountLength(shared_ptr<ListNode<int>> L);
void AdvanceListByK(shared_ptr<ListNode<int>>* L, int k);

// @include
shared_ptr<ListNode<int>> OverlappingNoCycleLists(
    shared_ptr<ListNode<int>> L1, shared_ptr<ListNode<int>> L2) {
  // Counts the lengths of L1 and L2.
  int L1_len = CountLength(L1), L2_len = CountLength(L2);

  // Advances the longer list.
  AdvanceListByK(L1_len > L2_len ? &L1 : &L2, abs(L1_len - L2_len));

  while (L1 && L2 && L1 != L2) {
    L1 = L1->next, L2 = L2->next;
  }
  return L1;  // nullptr means no overlap between L1 and L2.
}

// Counts the list length till end.
int CountLength(shared_ptr<ListNode<int>> L) {
  int length = 0;
  while (L) {
    ++length, L = L->next;
  }
  return length;
}

// Advances L by k steps.
void AdvanceListByK(shared_ptr<ListNode<int>>* L, int k) {
  while (k--) {
    *L = (*L)->next;
  }
}
// @exclude
#endif  // SOLUTIONS_OVERLAPPING_LISTS_NO_CYCLE_H_
