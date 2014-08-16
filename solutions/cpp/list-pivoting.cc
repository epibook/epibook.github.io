// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Linked_list_prototype.h"
#include "./Merge_sorted_lists.h"  // uses append_node().

using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
shared_ptr<ListNode<int>> ListPivoting(const shared_ptr<ListNode<int>>& L,
                                       int x) {
  auto now = L;
  shared_ptr<ListNode<int>> less_head(new ListNode<int>),
                            equal_head(new ListNode<int>),
                            larger_head(new ListNode<int>);
  auto less_tail = less_head, equal_tail = equal_head,
       larger_tail = larger_head;
  while (now) {
    if (now->data < x) {
      AppendNode(&now, &less_tail);
    } else if (now->data == x) {
      AppendNode(&now, &equal_tail);
    } else {  // now->data > x.
      AppendNode(&now, &larger_tail);
    }
  }

  less_tail->next = equal_tail->next = larger_tail->next = nullptr;
  if (larger_head->next) {
    equal_tail->next = larger_head->next;
  }
  if (equal_head->next) {
    less_tail->next = equal_head->next;
  }
  return less_head->next;
}
// @exclude

int main(int argc, char** argv) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             4, make_shared<ListNode<int>>(ListNode<int>{
                    3, make_shared<ListNode<int>>(ListNode<int>{
                           2, make_shared<ListNode<int>>(
                                  ListNode<int>{5, nullptr})})})})});
  int x = 4;
  auto result = ListPivoting(L, x);
  cout << endl;
  bool before_x = true;
  while (result) {
    if (result->data >= x) {
      before_x = false;
    }
    if (before_x) {
      assert(result->data < x);
    } else {
      assert(result->data >= x);
    }
    cout << result->data << endl;
    result = result->next;
  }
  return 0;
}
