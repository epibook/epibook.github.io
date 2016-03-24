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
// Assumes node_to_delete is not tail.
void DeletionFromList(const shared_ptr<ListNode<int>>& node_to_delete) {
  node_to_delete->data = node_to_delete->next->data;
  node_to_delete->next = node_to_delete->next->next;
}
// @exclude

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      1, make_shared<ListNode<int>>(ListNode<int>{
             2, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  DeletionFromList(L);
  assert(L->data == 2 && L->next->data == 3);
  return 0;
}
