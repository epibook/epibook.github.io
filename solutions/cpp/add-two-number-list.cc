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
shared_ptr<ListNode<int>> AddTwoNumbers(shared_ptr<ListNode<int>> L1,
                                        shared_ptr<ListNode<int>> L2) {
  shared_ptr<ListNode<int>> dummy_head(new ListNode<int>);
  auto digit = dummy_head;
  int sum = 0;
  while (L1 || L2) {
    if (L1) {
      sum += L1->data;
      L1 = L1->next;
    }
    if (L2) {
      sum += L2->data;
      L2 = L2->next;
    }
    digit->next =
        make_shared<ListNode<int>>(ListNode<int>{sum % 10, nullptr});
    sum /= 10, digit = digit->next;
  }
  if (sum) {
    digit->next = make_shared<ListNode<int>>(ListNode<int>{sum, nullptr});
  }
  return dummy_head->next;
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{
             4, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  shared_ptr<ListNode<int>> R;
  R = make_shared<ListNode<int>>(ListNode<int>{
      5, make_shared<ListNode<int>>(ListNode<int>{
             6, make_shared<ListNode<int>>(ListNode<int>{7, nullptr})})});
  auto S = AddTwoNumbers(L, R);
  assert(S->data == 7 && S->next->data == 0 && S->next->next->data == 1 &&
         S->next->next->next->data == 1);
  L = make_shared<ListNode<int>>(ListNode<int>{
      9, make_shared<ListNode<int>>(ListNode<int>{9, nullptr})});
  R = make_shared<ListNode<int>>(ListNode<int>{9, nullptr});
  S = AddTwoNumbers(L, R);
  assert(S->data == 8 && S->next->data == 0 && S->next->next->data == 1);
  return 0;
}
