// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Checking_cycle.h"
#include "./Linked_list_prototype.h"
#include "./Overlapping_lists_no_cycle.h"

using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

int DistanceAB(shared_ptr<ListNode<int>> a, shared_ptr<ListNode<int>> b);

// @include
shared_ptr<ListNode<int>> OverlappingLists(shared_ptr<ListNode<int>> L1,
                                           shared_ptr<ListNode<int>> L2) {
  // Store the start of cycle if any.
  shared_ptr<ListNode<int>> s1 = HasCycle(L1), s2 = HasCycle(L2);

  if (!s1 && !s2) {
    return OverlappingNoCycleLists(L1, L2);
  } else if (s1 && s2) {  // Both lists have cycles.
    shared_ptr<ListNode<int>> temp = s2;
    do {
      temp = temp->next;
    } while (temp != s1 && temp != s2);

    // L1 and L2 do not end in the same cycle.
    if (temp != s1) {
      return nullptr;
    }

    // Since L1 and L2 are end in the same cycle, find the overlapping node
    // if it happens before cycle starts.
    int L1_s1_dis = DistanceAB(L1, s1), L2_s2_dis = DistanceAB(L2, s2);
    AdvanceListByK(L1_s1_dis > L2_s2_dis ? &L1 : &L2,
                   abs(L1_s1_dis - L2_s2_dis));
    while (L1 != L2 && L1 != s1 && L2 != s2) {
      L1 = L1->next, L2 = L2->next;
    }

    // If L1 == L2 before reaching s1, it means overlapping node happens
    // before cycle starts; otherwise, the node cycle starts is one of the
    // overlapping nodes.
    return L1 == L2 ? L1 : s1;
  }
  return nullptr;  // One list has cycle, and one list has no cycle.
}

// Calculates the distance between a and b.
int DistanceAB(shared_ptr<ListNode<int>> a, shared_ptr<ListNode<int>> b) {
  int dis = 0;
  while (a != b) {
    a = a->next, ++dis;
  }
  return dis;
}
// @exclude

void SmallTest() {
  shared_ptr<ListNode<int>> L1, L2;
  // L1: 1->2->3->4->5->6-
  //              ^  ^    |
  //              |  |____|
  // L2: 7->8-----
  L1 = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{
                    3, make_shared<ListNode<int>>(ListNode<int>{
                           4, make_shared<ListNode<int>>(ListNode<int>{
                                  5, make_shared<ListNode<int>>(
                                         ListNode<int>{6, nullptr})})})})})});
  L1->next->next->next->next->next->next = L1->next->next->next->next;

  L2 = make_shared<ListNode<int>>(
      ListNode<int>{7, make_shared<ListNode<int>>(ListNode<int>{8, nullptr})});
  L2->next->next = L1->next->next->next;
  assert(OverlappingLists(L1, L2)->data == 4);

  // L1: 1->2->3->4->5->6-
  //           ^     ^    |
  //           |     |____|
  // L2: 7->8---
  L2->next->next = L1->next->next;
  assert(OverlappingLists(L1, L2)->data == 3);
}

int main(int argc, char* argv[]) {
  SmallTest();
  shared_ptr<ListNode<int>> L1, L2;
  // L1: 1->2->3->null
  L1 = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  L2 = L1->next->next;
  assert(OverlappingLists(L1, L2)->data == 3);
  // L2: 4->5->null
  L2 = make_shared<ListNode<int>>(
      ListNode<int>{4, make_shared<ListNode<int>>(ListNode<int>{5, nullptr})});
  assert(!OverlappingLists(L1, L2));
  L1->next->next->next = L1;
  assert(!OverlappingLists(L1, L2));
  L2->next->next = L2;
  assert(!OverlappingLists(L1, L2));
  L2->next->next = L1;
  assert(OverlappingLists(L1, L2));
  return 0;
}
