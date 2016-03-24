// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>
#include <vector>

#include "./Linked_list_prototype.h"

using std::make_shared;
using std::shared_ptr;
using std::vector;

// @include
// Insert new_node after node.
void InsertAfter(const shared_ptr<ListNode<int>>& node,
                 const shared_ptr<ListNode<int>>& new_node) {
  new_node->next = node->next;
  node->next = new_node;
}
// @exclude

void CheckAnswer(shared_ptr<ListNode<int>> L, const vector<int>& vals) {
  for (int i = 0; i < vals.size(); ++i) {
    assert(L->data == vals[i]);
    L = L->next;
  }
  assert(!L);
}

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> L;
  L = make_shared<ListNode<int>>(ListNode<int>{
      2, make_shared<ListNode<int>>(ListNode<int>{
             4, make_shared<ListNode<int>>(ListNode<int>{3, nullptr})})});
  InsertAfter(L, make_shared<ListNode<int>>(ListNode<int>{1, nullptr}));
  CheckAnswer(L, {2, 1, 4, 3});
  InsertAfter(L->next->next,
              make_shared<ListNode<int>>(ListNode<int>{10, nullptr}));
  CheckAnswer(L, {2, 1, 4, 10, 3});
  InsertAfter(L->next->next->next->next,
              make_shared<ListNode<int>>(ListNode<int>{-1, nullptr}));
  CheckAnswer(L, {2, 1, 4, 10, 3, -1});
  return 0;
}
