// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::unique_ptr;

bool HasPathSumHelper(const unique_ptr<BinaryTreeNode<int>>&, int, int);

// @include
bool HasPathSum(const unique_ptr<BinaryTreeNode<int>>& tree, int target_sum) {
  return HasPathSumHelper(tree, 0, target_sum);
}

bool HasPathSumHelper(const unique_ptr<BinaryTreeNode<int>>& node,
                      int partial_path_sum, int target_sum) {
  if (node == nullptr) {
    return false;
  }
  partial_path_sum += node->data;
  if (node->left == nullptr && node->right == nullptr) {  // Leaf.
    return partial_path_sum == target_sum;
  }
  // Non-leaf.
  return HasPathSumHelper(node->left, partial_path_sum, target_sum) ||
         HasPathSumHelper(node->right, partial_path_sum, target_sum);
}
// @exclude

int main(int argc, char** argv) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> tree = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{3, nullptr, nullptr});
  tree->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{2, nullptr, nullptr});
  tree->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  tree->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{5, nullptr, nullptr});
  tree->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{4, nullptr, nullptr});
  tree->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{6, nullptr, nullptr});
  assert(HasPathSum(tree, 6));
  assert(!HasPathSum(tree, 7));
  assert(!HasPathSum(tree, 100));
  return 0;
}
