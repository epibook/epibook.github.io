// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>

#include "./BST_prototype_shared_ptr.h"

using std::cout;
using std::endl;
using std::make_shared;
using std::numeric_limits;
using std::shared_ptr;

shared_ptr<BSTNode<int>> BSTToDoublyListHelper(
    const shared_ptr<BSTNode<int>>& T);

// @include
shared_ptr<BSTNode<int>> BSTToDoublyList(
    const shared_ptr<BSTNode<int>>& T) {
  auto res = BSTToDoublyListHelper(T);
  res->left->right = nullptr;  // Breaks the link from tail to head.
  res->left = nullptr;  // Breaks the link from head to tail.
  return res;
}

// Transforms a BST into a circular sorted circular doubly linked list
// in-place, and return the head of the list.
shared_ptr<BSTNode<int>> BSTToDoublyListHelper(
    const shared_ptr<BSTNode<int>>& T) {
  // Empty subtree.
  if (!T) {
    return nullptr;
  }

  // Recursively builds the list from left and right subtrees.
  auto l_head(BSTToDoublyListHelper(T->left));
  auto r_head(BSTToDoublyListHelper(T->right));

  // Appends T to the list from left subtree.
  shared_ptr<BSTNode<int>> l_tail = nullptr;
  if (l_head) {
    l_tail = l_head->left;
    l_tail->right = T;
    T->left = l_tail;
    l_tail = T;
  } else {
    l_head = l_tail = T;
  }

  // Appends the list from right subtree to T.
  shared_ptr<BSTNode<int>> r_tail = nullptr;
  if (r_head) {
    r_tail = r_head->left;
    l_tail->right = r_head;
    r_head->left = l_tail;
  } else {
    r_tail = l_tail;
  }
  r_tail->right = l_head, l_head->left = r_tail;

  return l_head;
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   5
  // 1   4 6
  auto root = make_shared<BSTNode<int>>(BSTNode<int>{3});
  root->left = make_shared<BSTNode<int>>(BSTNode<int>{2});
  root->left->left = make_shared<BSTNode<int>>(BSTNode<int>{1});
  root->right = make_shared<BSTNode<int>>(BSTNode<int>{5});
  root->right->left = make_shared<BSTNode<int>>(BSTNode<int>{4});
  root->right->right = make_shared<BSTNode<int>>(BSTNode<int>{6});
  shared_ptr<BSTNode<int>> L = BSTToDoublyList(root);
  shared_ptr<BSTNode<int>> curr = L;
  int pre = numeric_limits<int>::min();
  do {
    assert(pre <= curr->data);
    cout << curr->data << endl;
    pre = curr->data;
    curr = curr->right;
  } while (curr);
  return 0;
}
