// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::equal;
using std::unique_ptr;

int PreorderTraversal(const unique_ptr<BinaryTreeNode<int>>& root, int num);

// @include
int SumRootToLeaf(const unique_ptr<BinaryTreeNode<int>>& root) {
  return PreorderTraversal(root, 0);
}

int PreorderTraversal(const unique_ptr<BinaryTreeNode<int>>& root, int num) {
  if (!root) {
    return 0;
  }

  num = (num * 2) + root->data;
  if (!root->left && !root->right) {  // Leaf.
    return num;
  }
  // Non-leaf.
  return PreorderTraversal(root->left, num) +
         PreorderTraversal(root->right, num);
}
// @exclude

int main(int argc, char** argv) {
  //      1
  //    1   0
  //  0    1 0
  unique_ptr<BinaryTreeNode<int>> root = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{0, nullptr, nullptr});
  root->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{0, nullptr, nullptr});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{0, nullptr, nullptr});
  auto result = SumRootToLeaf(root);
  assert(result == 15);
  return 0;
}
