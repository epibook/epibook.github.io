// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Linked_list_prototype.h"
#include "./Merge_sorted_lists.h"  // uses AppendNode().

using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
shared_ptr<ListNode<int>> ListPivoting(const shared_ptr<ListNode<int>>& L,
                                       int x) {
  shared_ptr<ListNode<int>> less_head(new ListNode<int>),
      equal_head(new ListNode<int>), greater_head(new ListNode<int>);
  auto less_iter = less_head, equal_iter = equal_head,
       greater_iter = greater_head;
  // Populates the three lists.
  auto iter = L;
  while (iter) {
    if (iter->data < x) {
      AppendNode(&iter, &less_iter);
    } else if (iter->data == x) {
      AppendNode(&iter, &equal_iter);
    } else {  // iter->data > x.
      AppendNode(&iter, &greater_iter);
    }
  }
  less_iter->next = equal_iter->next = greater_iter->next = nullptr;

  // Combines the three lists.
  if (greater_head->next) {
    equal_iter->next = greater_head->next;
  }
  if (equal_head->next) {
    less_iter->next = equal_head->next;
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
