// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
// Assumes L has at least k nodes, deletes the k-th last node in L.
shared_ptr<ListNode<int>> RemoveKthLast(const shared_ptr<ListNode<int>>& L,
                                        int k) {
  auto dummy_head = make_shared<ListNode<int>>(ListNode<int>{0, L});
  auto first = dummy_head->next;
  while (k--) {
    first = first->next;
  }

  auto second = dummy_head;
  while (first) {
    second = second->next, first = first->next;
  }
  // second points to the (k + 1)-th last node, deletes its successor.
  second->next = second->next->next;
  return dummy_head->next;
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  L = RemoveKthLast(L, 2);
  assert(L->data == 1 && L->next->data == 3);
  L = RemoveKthLast(L, 2);
  assert(L->data == 3 && L->next == nullptr);
  L = RemoveKthLast(L, 1);
  assert(L == nullptr);
  return 0;
}
