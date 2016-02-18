// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>

#include "./BST_prototype.h"

using std::make_unique;
using std::unique_ptr;

// @include
BSTNode<int>* FindFirstGreaterThanK(const unique_ptr<BSTNode<int>>& tree,
                                    int k) {
  BSTNode<int> *subtree = tree.get(), *first_so_far = nullptr;
  while (subtree) {
    if (subtree->data > k) {
      first_so_far = subtree;
      subtree = subtree->left.get();
    } else {  // Root and all keys in left subtree are <= k, so skip them.
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
  auto tree = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left = make_unique<BSTNode<int>>(BSTNode<int>{2});
  tree->left->left = make_unique<BSTNode<int>>(BSTNode<int>{1});
  tree->right = make_unique<BSTNode<int>>(BSTNode<int>{5});
  tree->right->left = make_unique<BSTNode<int>>(BSTNode<int>{4});
  tree->right->right = make_unique<BSTNode<int>>(BSTNode<int>{7});
  assert(FindFirstGreaterThanK(tree, 1) == tree->left.get());
  assert(FindFirstGreaterThanK(tree, 5) == tree->right->right.get());
  assert(FindFirstGreaterThanK(tree, 6) == tree->right->right.get());
  assert(!FindFirstGreaterThanK(tree, 7));
  return 0;
}
