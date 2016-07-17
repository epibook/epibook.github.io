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
shared_ptr<ListNode<int>> ReverseSublist(shared_ptr<ListNode<int>> L,
                                         int start, int finish) {
  if (start == finish) {  // No need to reverse since start == finish.
    return L;
  }

  auto dummy_head = make_shared<ListNode<int>>(ListNode<int>{0, L});
  auto sublist_head = dummy_head;
  int k = 1;
  while (k++ < start) {
    sublist_head = sublist_head->next;
  }

  // Reverses sublist.
  auto sublist_iter = sublist_head->next;
  while (start++ < finish) {
    auto temp = sublist_iter->next;
    sublist_iter->next = temp->next;
    temp->next = sublist_head->next;
    sublist_head->next = temp;
  }
  return dummy_head->next;
}
// @exclude

void SimpleTest() {
  shared_ptr<ListNode<int>> L = nullptr;
  auto result = ReverseSublist(L, 0, 0);
  assert(result == nullptr);

  L = make_shared<ListNode<int>>(ListNode<int>{1, nullptr});
  result = ReverseSublist(L, 0, 0);
  assert(result == L);

  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  result = ReverseSublist(L, 0, 1);
  assert(result->data == 2 && result->next->data == 1 &&
         result->next->next->data == 3);

  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  result = ReverseSublist(L, 0, 2);
  assert(result->data == 3 && result->next->data == 2 &&
         result->next->next->data == 1);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  shared_ptr<ListNode<int>> L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  auto result = ReverseSublist(L, 3, 3);
  assert(result->data == 1 && result->next->data == 2 &&
         result->next->next->data == 3 && !result->next->next->next);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }

  result = ReverseSublist(L, 2, 3);
  assert(result->data == 1 && result->next->data == 3 &&
         result->next->next->data == 2 && !result->next->next->next);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }

  L = make_shared<ListNode<int>>(ListNode<int>{
      3, make_shared<ListNode<int>>(ListNode<int>{5, nullptr})});
  result = ReverseSublist(L, 1, 2);
  assert(result->data == 5 && result->next->data == 3 && !result->next->next);
  while (result) {
    cout << result->data << endl;
    result = result->next;
  }
  return 0;
}
