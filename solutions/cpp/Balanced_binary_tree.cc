// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>
#include <utility>

#include "./Binary_tree_prototype.h"

using std::boolalpha;
using std::cout;
using std::endl;
using std::max;
using std::pair;
using std::unique_ptr;

pair<bool, int> CheckBalanced(const unique_ptr<BinaryTreeNode<int>>&);

// @include
bool IsBalanced(const unique_ptr<BinaryTreeNode<int>>& tree) {
  return CheckBalanced(tree).first;
}

// First value of the return value indicates if tree is balanced, and if
// balanced the second value of the return value is the height of tree.
pair<bool, int> CheckBalanced(const unique_ptr<BinaryTreeNode<int>>& tree) {
  if (tree == nullptr) {
    return {true, -1};  // Base case.
  }

  auto left_result = CheckBalanced(tree->left);
  if (!left_result.first) {
    return {false, 0};  // Left subtree is not balanced.
  }
  auto right_result = CheckBalanced(tree->right);
  if (!right_result.first) {
    return {false, 0};  // Right subtree is not balanced.
  }

  bool is_balanced = abs(left_result.second - right_result.second) <= 1;
  int height = max(left_result.second, right_result.second) + 1;
  return {is_balanced, height};
}
// @exclude

int main(int argc, char* argv[]) {
  //  balanced binary tree test
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> tree =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  tree->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  tree->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  tree->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  tree->right->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  tree->right->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  assert(IsBalanced(tree) == true);
  cout << boolalpha << IsBalanced(tree) << endl;
  // Non-balanced binary tree test.
  tree = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  tree->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  tree->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  assert(IsBalanced(tree) == false);
  cout << boolalpha << IsBalanced(tree) << endl;
  return 0;
}
