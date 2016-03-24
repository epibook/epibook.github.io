// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_OVERLAPPING_LISTS_NO_CYCLE_H_
#define SOLUTIONS_OVERLAPPING_LISTS_NO_CYCLE_H_

#include "./Linked_list_prototype.h"

int Length(shared_ptr<ListNode<int>> L);
void AdvanceListByK(int k, shared_ptr<ListNode<int>>* L);

// @include
shared_ptr<ListNode<int>> OverlappingNoCycleLists(
    shared_ptr<ListNode<int>> L1, shared_ptr<ListNode<int>> L2) {
  int L1_len = Length(L1), L2_len = Length(L2);

  // Advances the longer list to get equal length lists.
  AdvanceListByK(abs(L1_len - L2_len), L1_len > L2_len ? &L1 : &L2);

  while (L1 && L2 && L1 != L2) {
    L1 = L1->next, L2 = L2->next;
  }
  return L1;  // nullptr implies there is no overlap between L1 and L2.
}

int Length(shared_ptr<ListNode<int>> L) {
  int length = 0;
  while (L) {
    ++length, L = L->next;
  }
  return length;
}

// Advances L by k steps.
void AdvanceListByK(int k, shared_ptr<ListNode<int>>* L) {
  while (k--) {
    *L = (*L)->next;
  }
}
// @exclude
#endif  // SOLUTIONS_OVERLAPPING_LISTS_NO_CYCLE_H_
