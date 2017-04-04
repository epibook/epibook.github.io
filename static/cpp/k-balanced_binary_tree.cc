// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cstdlib>
#include <iostream>
#include <memory>
#include <utility>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::unique_ptr;

struct TreeWithSize;
TreeWithSize FindKUnbalancedNodeHelper(const unique_ptr<BinaryTreeNode<int>>&,
                                       int);

// @include
struct TreeWithSize {
  BinaryTreeNode<int>* root;
  int size;
};

BinaryTreeNode<int>* FindKUnbalancedNode(
    const unique_ptr<BinaryTreeNode<int>>& tree, int k) {
  return FindKUnbalancedNodeHelper(tree, k).root;
}

// If there is any k-unbalanced node in tree, the root field is a k-unbalanced
// node; otherwise, it is null.  If the root is not null, the size field is
// the
// number of nodes in tree.
TreeWithSize FindKUnbalancedNodeHelper(
    const unique_ptr<BinaryTreeNode<int>>& tree, int k) {
  if (tree == nullptr) {
    return {nullptr, 0};  // Base case.
  }

  // Early return if left subtree is not k-balanced.
  TreeWithSize left_result = FindKUnbalancedNodeHelper(tree->left, k);
  if (left_result.root != nullptr) {
    return {left_result.root, 0};
  }
  // Early return if right subtree is not k-balanced.
  TreeWithSize right_result = FindKUnbalancedNodeHelper(tree->right, k);
  if (right_result.root != nullptr) {
    return {right_result.root, 0};
  }

  int node_num = left_result.size + right_result.size + 1;
  if (abs(left_result.size - right_result.size) > k) {
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
  unique_ptr<BinaryTreeNode<int>> root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  root->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  root->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  root->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  int k = 0;
  BinaryTreeNode<int>* ans(FindKUnbalancedNode(root, k));
  assert(ans->data == 2);
  if (ans) {
    cout << ans->data << endl;
  }
  return 0;
}
