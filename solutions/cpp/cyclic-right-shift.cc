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
shared_ptr<ListNode<int>> CyclicallyRightShiftList(
    shared_ptr<ListNode<int>> L, int k) {
  if (!L || !k) {
    return L;
  }

  // Computes n, the length of L, and stores the tails of the list.
  auto tail = L;
  int n = 1;
  while (tail->next) {
    ++n, tail = tail->next;
  }

  tail->next = L;  // Makes a cycle by connecting the tail to the head.
  int steps = n - k % n;
  auto prev = tail;
  while (steps--) {
    prev = prev->next;;
  }
  L = prev->next;
  prev->next = nullptr;
  return L;
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  auto result = CyclicallyRightShiftList(L, 2);
  assert(result->data == 2 && result->next->data == 3 && result->next->next->data == 1 && !result->next->next->next);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }
  return 0;
}
