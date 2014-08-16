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
shared_ptr<ListNode<int>> ReverseSublistSF(
    shared_ptr<ListNode<int>> L, int s, int f) {
  if (s == f) {  // No need to reverse since s == f.
    return L;
  }

  auto dummy_head = make_shared<ListNode<int>>(ListNode<int>{0, L});
  auto p = dummy_head;
  int k = 1;
  while (k++ < s) {
    p = p->next;
  }

  // Reverses sublist.
  auto q = p->next;
  while (s++ < f) {
    auto temp = q->next;
    q->next = temp->next;
    temp->next = p->next;
    p->next = temp;
  }
  return dummy_head->next;
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  auto result = ReverseSublistSF(L, 3, 3);
  assert(result->data == 1 && result->next->data == 2 &&
         result->next->next->data == 3 && !result->next->next->next);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }

  result = ReverseSublistSF(L, 2, 3);
  assert(result->data == 1 && result->next->data == 3 &&
         result->next->next->data == 2 && !result->next->next->next);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }

  L = make_shared<ListNode<int>>(ListNode<int>{
      3, make_shared<ListNode<int>>(ListNode<int>{5, nullptr})});
  result = ReverseSublistSF(L, 1, 2);
  assert(result->data == 5 && result->next->data == 3 && !result->next->next);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }
  return 0;
}
