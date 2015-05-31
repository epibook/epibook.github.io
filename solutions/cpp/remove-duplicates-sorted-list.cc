// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <cassert>
#include <memory>

#include "./Linked_list_prototype.h"

using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
shared_ptr<ListNode<int>> RemoveDuplicates(const shared_ptr<ListNode<int>>& L) {
  auto iter = L;
  while (iter) {
    // Uses next_distinct to find the next distinct value.
    auto next_distinct = iter->next;
    while (next_distinct && next_distinct->data == iter->data) {
      next_distinct = next_distinct->next;
    }
    iter->next = next_distinct;
    iter = next_distinct;
  }
  return L;
}
// @exclude

int main(int argc, char** argv) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{
                    2, make_shared<ListNode<int>>(ListNode<int>{
                           2, make_shared<ListNode<int>>(
                                  ListNode<int>{2, nullptr})})})})});
  shared_ptr<ListNode<int>> pre = nullptr;
  auto result = RemoveDuplicates(L);
  int count = 0;
  while (result) {
    ++count;
    if (pre) {
      assert(pre->data != result->data);
    }
    cout << result->data << endl;
    pre = result;
    result = result->next;
  }
  assert(count == 1);
  return 0;
}
