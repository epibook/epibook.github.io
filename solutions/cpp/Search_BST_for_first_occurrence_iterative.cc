// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::make_unique;
using std::unique_ptr;

// @include
BSTNode<int>* FindFirstEqualK(const unique_ptr<BSTNode<int>>& tree, int k) {
  BSTNode<int> *first_so_far = nullptr, *curr = tree.get();
  while (curr) {
    if (curr->data < k) {
      curr = curr->right.get();
    } else if (curr->data > k) {
      curr = curr->left.get();
    } else {  // curr->data == k.
      // Record this node, and search for the first node in the left subtree.
      first_so_far = curr;
      curr = curr->left.get();
    }
  }
  return first_so_far;
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   5
  // 1   4 6
  auto tree = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left = make_unique<BSTNode<int>>(BSTNode<int>{2});
  tree->left->left = make_unique<BSTNode<int>>(BSTNode<int>{1});
  tree->right = make_unique<BSTNode<int>>(BSTNode<int>{5});
  tree->right->left = make_unique<BSTNode<int>>(BSTNode<int>{4});
  tree->right->right = make_unique<BSTNode<int>>(BSTNode<int>{6});
  assert(!FindFirstEqualK(tree, 7));
  assert(FindFirstEqualK(tree, 6)->data == 6);

  //    3
  //  3   5
  // 2   5 6
  tree = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left->left = make_unique<BSTNode<int>>(BSTNode<int>{2});
  tree->right = make_unique<BSTNode<int>>(BSTNode<int>{5});
  tree->right->left = make_unique<BSTNode<int>>(BSTNode<int>{5});
  tree->right->right = make_unique<BSTNode<int>>(BSTNode<int>{6});
  assert(!FindFirstEqualK(tree, 7));
  assert(FindFirstEqualK(tree, 3) == tree->left.get());
  assert(FindFirstEqualK(tree, 5) == tree->right->left.get());
  assert(FindFirstEqualK(tree, 6)->data == 6);

  return 0;
}
