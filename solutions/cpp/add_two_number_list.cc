// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <stdexcept>

#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::exception;
using std::make_shared;
using std::shared_ptr;

// @include
shared_ptr<ListNode<int>> AddTwoNumbers(shared_ptr<ListNode<int>> L1,
                                        shared_ptr<ListNode<int>> L2) {
  shared_ptr<ListNode<int>> dummy_head(new ListNode<int>);
  auto place_iter = dummy_head;
  int carry = 0;
  while (L1 || L2 || carry) {
    int val = carry + (L1 ? L1->data : 0) + (L2 ? L2->data : 0);
    L1 = L1 ? L1->next : nullptr;
    L2 = L2 ? L2->next : nullptr;
    place_iter->next =
        make_shared<ListNode<int>>(ListNode<int>{val % 10, nullptr});
    carry = val / 10, place_iter = place_iter->next;
  }
  return dummy_head->next;
}
// @exclude

void SimpleTest() {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{
             4, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  shared_ptr<ListNode<int>> R;
  R = make_shared<ListNode<int>>(ListNode<int>{0, nullptr});
  auto S = AddTwoNumbers(L, R);
  assert(S->data == 2 && S->next->data == 4 && S->next->next->data == 3);

  L = make_shared<ListNode<int>>(ListNode<int>{
      3, make_shared<ListNode<int>>(ListNode<int>{
             4, make_shared<ListNode<int>>(ListNode<int>{2, nullptr})})});
  R = make_shared<ListNode<int>>(ListNode<int>{
      7, make_shared<ListNode<int>>(ListNode<int>{
             5, make_shared<ListNode<int>>(ListNode<int>{7, nullptr})})});
  S = AddTwoNumbers(L, R);
  assert(S->data == 0 && S->next->data == 0 && S->next->next->data == 0 &&
         S->next->next->next->data == 1);

  L = make_shared<ListNode<int>>(ListNode<int>{1, nullptr});
  R = make_shared<ListNode<int>>(ListNode<int>{1, nullptr});
  S = AddTwoNumbers(L, R);
  assert(S->data == 2 && S->next == nullptr);

  L = make_shared<ListNode<int>>(ListNode<int>{5, nullptr});
  R = make_shared<ListNode<int>>(ListNode<int>{5, nullptr});
  S = AddTwoNumbers(L, R);
  assert(S->data == 0 && S->next->data == 1 && S->next->next == nullptr);

  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{
             4, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  R = make_shared<ListNode<int>>(ListNode<int>{
      5, make_shared<ListNode<int>>(ListNode<int>{
             6, make_shared<ListNode<int>>(ListNode<int>{7, nullptr})})});
  S = AddTwoNumbers(L, R);
  assert(S->data == 7 && S->next->data == 0 && S->next->next->data == 1 &&
         S->next->next->next->data == 1);
  L = make_shared<ListNode<int>>(ListNode<int>{
      9, make_shared<ListNode<int>>(ListNode<int>{9, nullptr})});
  R = make_shared<ListNode<int>>(ListNode<int>{9, nullptr});
  S = AddTwoNumbers(L, R);
  assert(S->data == 8 && S->next->data == 0 && S->next->next->data == 1);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  return 0;
}
