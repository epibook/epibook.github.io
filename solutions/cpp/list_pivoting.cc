// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
  shared_ptr<ListNode<int>> less_iter = less_head, equal_iter = equal_head,
                            greater_iter = greater_head;
  // Populates the three lists.
  shared_ptr<ListNode<int>> iter = L;
  while (iter) {
    AppendNode(&iter,
               iter->data < x
                   ? &less_iter
                   : iter->data == x ? &equal_iter : &greater_iter);
  }
  // Combines the three lists.
  greater_iter->next = nullptr;
  equal_iter->next = greater_head->next;
  less_iter->next = equal_head->next;
  return less_head->next;
}
// @exclude

void SimpleTest() {
  shared_ptr<ListNode<int>> L =
      make_shared<ListNode<int>>(ListNode<int>{0, nullptr});
  auto result = ListPivoting(L, 0);
  assert(result == L);
  result = ListPivoting(L, 1);
  assert(result == L);
  result = ListPivoting(L, -1);
  assert(result == L);

  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{0, nullptr})});
  result = ListPivoting(L, -1);
  assert(result == L);

  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{0, nullptr})});
  result = ListPivoting(L, 1);
  assert(result->data == 0 && result->next->data == 2);

  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{
             0, make_shared<ListNode<int>>(ListNode<int>{-2, nullptr})})});
  result = ListPivoting(L, 1);
  assert(result->data == 0 && result->next->data == -2 &&
         result->next->next->data == 2);
}

int main(int argc, char** argv) {
  SimpleTest();
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
