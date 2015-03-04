// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>

#include "./BST_prototype.h"

using std::unique_ptr;

// @include
BSTNode<int>* FindFirstGreaterThanK(const unique_ptr<BSTNode<int>>& tree,
                                    int k) {
  BSTNode<int>* subtree = tree.get(), *first_so_far = nullptr;

  while (subtree) {
    if (subtree->data > k) {
      first_so_far = subtree;
      subtree = subtree->left.get();
    } else {  // subtree->data <= k.
      subtree = subtree->right.get();
    }
  }
  return first_so_far;
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   5
  // 1   4 7
  auto tree = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  tree->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  tree->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  tree->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  tree->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  tree->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{7});
  assert(FindFirstGreaterThanK(tree, 1) == tree->left.get());
  assert(FindFirstGreaterThanK(tree, 5) == tree->right->right.get());
  assert(FindFirstGreaterThanK(tree, 6) == tree->right->right.get());
  assert(!FindFirstGreaterThanK(tree, 7));
  return 0;
}
