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
    const unique_ptr<BinaryTreeNode<int>>& T, int k);

// @include
BinaryTreeNode<int>* FindKUnbalancedNode(
    const unique_ptr<BinaryTreeNode<int>>& T, int k) {
  return FindKUnbalancedNodeHelper(T, k).first;
}

// If there is any k-unbalanced node in T, the first of the return value is a
// k-unbalanced node; otherwise, null.  The second of the return value is the
// number of nodes in T.
pair<BinaryTreeNode<int>*, int> FindKUnbalancedNodeHelper(
    const unique_ptr<BinaryTreeNode<int>>& T, int k) {
  // Empty tree.
  if (!T) {
    return {nullptr, 0};
  }

  // Early return if left subtree is not k-balanced.
  auto L = FindKUnbalancedNodeHelper(T->left, k);
  if (L.first) {
    return L;
  }
  // Early return if right subtree is not k-balanced.
  auto R = FindKUnbalancedNodeHelper(T->right, k);
  if (R.first) {
    return R;
  }

  int node_num = L.second + R.second + 1;
  if (abs(L.second - R.second) > k) {
    return {T.get(), node_num};
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
