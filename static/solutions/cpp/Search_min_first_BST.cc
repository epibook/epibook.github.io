// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::make_unique;
using std::unique_ptr;

// @include
bool SearchMinFirstBST(const unique_ptr<BinaryTreeNode<int>>& min_first_BST,
                       int k) {
  // First handle the base cases.
  if (!min_first_BST || min_first_BST->data > k) {
    return false;
  } else if (min_first_BST->data == k) {
    return true;
  }

  // Recursively search just the right subtree if the smallest key in the
  // right subtree is greater than or equal to k.
  if (min_first_BST->right && k >= min_first_BST->right->data) {
    return SearchMinFirstBST(min_first_BST->right, k);
  }
  return SearchMinFirstBST(min_first_BST->left, k);
}
// @exclude

int main(int argc, char* argv[]) {
  // A min-first BST
  //    1
  //  2   4
  // 3   5 7
  auto tree = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{1});
  tree->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{2});
  tree->left->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{3});
  tree->right = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{4});
  tree->right->left =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{5});
  tree->right->right =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{7});
  assert(SearchMinFirstBST(tree, 1));
  assert(SearchMinFirstBST(tree, 3));
  assert(SearchMinFirstBST(tree, 5));
  assert(!SearchMinFirstBST(tree, 6));
  return 0;
}
