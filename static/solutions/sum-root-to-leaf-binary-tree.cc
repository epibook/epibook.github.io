// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::equal;
using std::make_unique;
using std::unique_ptr;

int SumRootToLeafHelper(const unique_ptr<BinaryTreeNode<int>>&, int);

// @include
int SumRootToLeaf(const unique_ptr<BinaryTreeNode<int>>& tree) {
  return SumRootToLeafHelper(tree, 0);
}

int SumRootToLeafHelper(const unique_ptr<BinaryTreeNode<int>>& tree,
                        int partial_path_sum) {
  if (tree == nullptr) {
    return 0;
  }

  partial_path_sum = partial_path_sum * 2 + tree->data;
  if (tree->left == nullptr && tree->right == nullptr) {  // Leaf.
    return partial_path_sum;
  }
  // Non-leaf.
  return SumRootToLeafHelper(tree->left, partial_path_sum) +
         SumRootToLeafHelper(tree->right, partial_path_sum);
}
// @exclude

int main(int argc, char** argv) {
  //      1
  //    1   0
  //  0    1 0
  unique_ptr<BinaryTreeNode<int>> root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  assert(SumRootToLeaf(root) == 1);
  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  assert(SumRootToLeaf(root) == 3);
  root->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{0, nullptr, nullptr});
  assert(SumRootToLeaf(root) == 6);
  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{0, nullptr, nullptr});
  assert(SumRootToLeaf(root) == 8);
  root->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  assert(SumRootToLeaf(root) == 11);
  root->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{0, nullptr, nullptr});
  assert(SumRootToLeaf(root) == 15);
  return 0;
}
