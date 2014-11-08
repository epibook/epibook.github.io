// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cstdlib>
#include <iostream>
#include <memory>
#include <utility>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::pair;
using std::unique_ptr;

pair<BinaryTreeNode<int>*, int> FindKUnbalancedNodeHelper(
    const unique_ptr<BinaryTreeNode<int>>&, int);

// @include
BinaryTreeNode<int>* FindKUnbalancedNode(
    const unique_ptr<BinaryTreeNode<int>>& tree, int k) {
  return FindKUnbalancedNodeHelper(tree, k).first;
}

// If there is any k-unbalanced node in tree, the first value of the return
// value is a k-unbalanced node; otherwise, null.  If the first is not null,
// the second value of the return value is the number of nodes in tree.
pair<BinaryTreeNode<int>*, int> FindKUnbalancedNodeHelper(
    const unique_ptr<BinaryTreeNode<int>>& tree, int k) {
  if (tree == nullptr) {
    return {nullptr, 0};  // Base case.
  }

  // Early return if left subtree is not k-balanced.
  auto left_result = FindKUnbalancedNodeHelper(tree->left, k);
  if (left_result.first != nullptr) {
    return {left_result.first, 0};
  }
  // Early return if right subtree is not k-balanced.
  auto right_result = FindKUnbalancedNodeHelper(tree->right, k);
  if (right_result.first != nullptr) {
    return {right_result.first, 0};
  }

  int node_num = left_result.second + right_result.second + 1;
  if (abs(left_result.second - right_result.second) > k) {
    // tree is not k-balanced but its children are k-balanced.
    return {tree.get(), node_num};
  }
  return {nullptr, node_num};
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{3, nullptr, nullptr});
  root->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{6, nullptr, nullptr});
  int k = 0;
  BinaryTreeNode<int>* ans(FindKUnbalancedNode(root, k));
  assert(ans->data == 2);
  if (ans) {
    cout << ans->data << endl;
  }
  return 0;
}
