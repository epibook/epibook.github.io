// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::unique_ptr;

bool HasPathSumHelper(const unique_ptr<BinaryTreeNode<int>>& root, int partial_path_sum,
                      int target_sum);

// @include
bool HasPathSum(const unique_ptr<BinaryTreeNode<int>>& root, int target_sum) {
  return HasPathSumHelper(root, 0, target_sum);
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
  assert(HasPathSum(root, 6));
  assert(!HasPathSum(root, 7));
  assert(!HasPathSum(root, 100));
  return 0;
}
