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
using std::make_unique;
using std::max;
using std::pair;
using std::tie;
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

  bool is_left_balanced;
  int left_height;
  tie(is_left_balanced, left_height) = CheckBalanced(tree->left);
  if (!is_left_balanced) {
    return {false, 0};  // Left subtree is not balanced.
  }
  bool is_right_balanced;
  int right_height;
  tie(is_right_balanced, right_height) = CheckBalanced(tree->right);
  if (!is_right_balanced) {
    return {false, 0};  // Right subtree is not balanced.
  }

  bool is_balanced = abs(left_height - right_height) <= 1;
  int height = max(left_height, right_height) + 1;
  return {is_balanced, height};
}
// @exclude

int main(int argc, char* argv[]) {
  //  balanced binary tree test
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> tree =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  tree->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  tree->left->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  tree->right = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  tree->right->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  tree->right->right = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  assert(IsBalanced(tree) == true);
  cout << boolalpha << IsBalanced(tree) << endl;
  // Non-balanced binary tree test.
  tree = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  tree->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  tree->left->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  assert(IsBalanced(tree) == false);
  cout << boolalpha << IsBalanced(tree) << endl;
  return 0;
}
