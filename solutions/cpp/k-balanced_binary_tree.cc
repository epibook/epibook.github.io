// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cstdlib>
#include <iostream>
#include <memory>
#include <utility>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::pair;
using std::tie;
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
  BinaryTreeNode<int>* left_subtree_result;
  int left_subtree_node_num;
  tie(left_subtree_result, left_subtree_node_num) =
      FindKUnbalancedNodeHelper(tree->left, k);
  if (left_subtree_result != nullptr) {
    return {left_subtree_result, 0};
  }
  // Early return if right subtree is not k-balanced.
  BinaryTreeNode<int>* right_subtree_result;
  int right_subtree_node_num;
  tie(right_subtree_result, right_subtree_node_num) =
      FindKUnbalancedNodeHelper(tree->right, k);
  if (right_subtree_result != nullptr) {
    return {right_subtree_result, 0};
  }

  int node_num = left_subtree_node_num + right_subtree_node_num + 1;
  if (abs(left_subtree_node_num - right_subtree_node_num) > k) {
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
