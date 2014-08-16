// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <stdexcept>

#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::make_shared;
using std::shared_ptr;

// @include
shared_ptr<ListNode<int>> RemoveKthLast(const shared_ptr<ListNode<int>>& L,
                                        int k) {
  auto dummy_head = make_shared<ListNode<int>>(ListNode<int>{0, L});
  // Advances k steps first.
  auto ahead = dummy_head;
  while (k--) {
    ahead = ahead->next;
  }

  auto pre = dummy_head;
  while (ahead && ahead->next) {
    pre = pre->next, ahead = ahead->next;
  }
  pre->next = pre->next->next;
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
  return 0;
}
