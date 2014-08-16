// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::boolalpha;
using std::cout;
using std::endl;
using std::max;
using std::unique_ptr;

int GetHeight(const unique_ptr<BinaryTreeNode<int>>& T);

// @include
bool IsBalancedBinaryTree(const unique_ptr<BinaryTreeNode<int>>& T) {
  return GetHeight(T) != -2;
}

int GetHeight(const unique_ptr<BinaryTreeNode<int>>& T) {
  if (!T) {
    return -1;  // Base case.
  }

  int l_height = GetHeight(T->left);
  if (l_height == -2) {
    return -2;  // Left subtree is not balanced.
  }
  int r_height = GetHeight(T->right);
  if (r_height == -2) {
    return -2;  // Right subtree is not balanced.
  }

  if (abs(l_height - r_height) > 1) {
    return -2;  // Current node T is not balanced.
  }
  return max(l_height, r_height) + 1;  // Returns the height.
}
// @exclude

int main(int argc, char* argv[]) {
  //  balanced binary tree test
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->right->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->right->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  assert(IsBalancedBinaryTree(root) == true);
  cout << boolalpha << IsBalancedBinaryTree(root) << endl;
  // Non-balanced binary tree test.
  root = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  assert(IsBalancedBinaryTree(root) == false);
  cout << boolalpha << IsBalancedBinaryTree(root) << endl;
  return 0;
}
