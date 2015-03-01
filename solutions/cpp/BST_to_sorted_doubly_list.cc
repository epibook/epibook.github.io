// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>

#include "./BST_prototype_shared_ptr.h"

using std::cout;
using std::endl;
using std::make_shared;
using std::numeric_limits;
using std::pair;
using std::shared_ptr;

pair<shared_ptr<BSTNode<int>>, shared_ptr<BSTNode<int>>>
    BSTToDoublyLinkedListHelper(const shared_ptr<BSTNode<int>>&);

// @include
shared_ptr<BSTNode<int>> BSTToDoublyLinkedList(
    const shared_ptr<BSTNode<int>>& tree) {
  return BSTToDoublyLinkedListHelper(tree).first;
}

// Transforms a BST into a sorted doubly linked list in-place,
// and return the head and tail of the list as a pair.
pair<shared_ptr<BSTNode<int>>, shared_ptr<BSTNode<int>>>
    BSTToDoublyLinkedListHelper(const shared_ptr<BSTNode<int>>& tree) {
  // Empty subtree.
  if (!tree) {
    return {nullptr, nullptr};
  }

  // Recursively builds the list from left and right subtrees.
  auto left = BSTToDoublyLinkedListHelper(tree->left);
  auto right = BSTToDoublyLinkedListHelper(tree->right);

  // Appends tree to the list from left subtree.
  if (left.second) {
    left.second->right = tree;
  }
  tree->left = left.second;

  // Appends the list from right subtree to tree.
  tree->right = right.first;
  if (right.first) {
    right.first->left = tree;
  }

  return {left.first ? left.first : tree, right.second ? right.second : tree};
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
  shared_ptr<BSTNode<int>> L = BSTToDoublyLinkedList(root);
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
