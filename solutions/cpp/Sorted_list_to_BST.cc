// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./Doubly_linked_list_prototype.h"

using std::cout;
using std::endl;
using std::make_shared;

shared_ptr<ListNode<int>> BuildBSTFromSortedDoublyListHelper(
    shared_ptr<ListNode<int>>*, int, int);

// @include
// Returns the root of the corresponding BST. The prev and next fields of the
// list nodes are used as the BST nodes left and right fields, respectively.
// n is the length of the list.
shared_ptr<ListNode<int>> BuildBSTFromSortedDoublyList(
    shared_ptr<ListNode<int>> L, int n) {
  return BuildBSTFromSortedDoublyListHelper(&L, 0, n);
}

// Builds a BST from the (start + 1)-th to the end-th node, inclusive, in L,
// and returns the root.
shared_ptr<ListNode<int>> BuildBSTFromSortedDoublyListHelper(
    shared_ptr<ListNode<int>>* L_ref, int start, int end) {
  if (start >= end) {
    return nullptr;
  }

  int mid = start + ((end - start) / 2);
  auto left = BuildBSTFromSortedDoublyListHelper(L_ref, start, mid);
  auto curr = *L_ref;  // The last function call sets L_ref to the successor
                       // of the maximum node in the tree rooted at left.
  *L_ref = (*L_ref)->next;
  curr->prev = left;
  curr->next = BuildBSTFromSortedDoublyListHelper(L_ref, mid + 1, end);
  return curr;
}
// @exclude

template <typename T>
void InorderTraversal(const shared_ptr<ListNode<T>>& node, const T& pre, int depth) {
  if (node) {
    InorderTraversal(node->prev, pre, depth + 1);
    assert(pre <= node->data);
    cout << node->data << ' ' << "; depth = " << depth << endl;
    InorderTraversal(node->next, node->data, depth + 1);
  }
}

int main(int argc, char* argv[]) {
  shared_ptr<ListNode<int>> A[1000];

  for (int i = 0; i < 1000; i++) {
    A[i] = make_shared<ListNode<int>>(ListNode<int>{0});
  }

  shared_ptr<ListNode<int>> temp0 = make_shared<ListNode<int>>(ListNode<int>{0});
  shared_ptr<ListNode<int>> temp1 = make_shared<ListNode<int>>(ListNode<int>{1});
  shared_ptr<ListNode<int>> temp2 = make_shared<ListNode<int>>(ListNode<int>{2});
  shared_ptr<ListNode<int>> temp3 = make_shared<ListNode<int>>(ListNode<int>{3});
  temp0->next = temp1;
  temp1->next = temp2;
  temp2->next = temp3;
  temp3->next = nullptr;
  temp0->prev = nullptr;
  temp1->prev = temp0;
  temp2->prev = temp1;
  temp3->prev = temp2;

  shared_ptr<ListNode<int>> L = temp0;
  auto tree = BuildBSTFromSortedDoublyList(L, 4);
  InorderTraversal(tree, -1, 0);
  return 0;
}
