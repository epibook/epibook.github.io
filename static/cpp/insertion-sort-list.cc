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
shared_ptr<ListNode<int>> InsertionSort(const shared_ptr<ListNode<int>>& L) {
  auto dummy_head = make_shared<ListNode<int>>(ListNode<int>{0, L});
  auto iter = L;
  // The sublist consisting of nodes up to and including iter is sorted in
  // increasing order. We need to ensure that after we move to iter->next
  // this property continues to hold. We do this by swapping iter->next
  // with its predecessors in the list till it's in the right place.
  while (iter && iter->next) {
    if (iter->data > iter->next->data) {
      auto target = iter->next, pre = dummy_head;
      while (pre->next->data < target->data) {
        pre = pre->next;
      }
      auto temp = pre->next;
      pre->next = target;
      iter->next = target->next;
      target->next = temp;
    } else {
      iter = iter->next;
    }
  }
  return dummy_head->next;
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
  auto result = InsertionSort(L);
  shared_ptr<ListNode<int>> pre = nullptr;
  while (result) {
    assert(!pre || pre->data <= result->data);
    pre = result;
    cout << result->data << endl;
    result = result->next;
  }
  return 0;
}
