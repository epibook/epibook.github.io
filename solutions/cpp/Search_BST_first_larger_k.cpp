// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>

#include "./BST_prototype.h"

using std::unique_ptr;

// @include
BSTNode<int>* FindFirstGreaterThanK(const unique_ptr<BSTNode<int>>& T, int k) {
  bool found_k = false;
  BSTNode<int>* subtree = T.get(), *first_so_far = nullptr;

  while (subtree) {
    if (subtree->data == k) {
      found_k = true;
      subtree = subtree->right.get();
    } else if (subtree->data > k) {
      first_so_far = subtree;
      subtree = subtree->left.get();
    } else {  // subtree->data < k.
      subtree = subtree->right.get();
    }
  }
  return found_k ? first_so_far : nullptr;
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   5
  // 1   4 7
  auto root = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{7});
  assert(FindFirstGreaterThanK(root, 1) == root->left.get());
  assert(FindFirstGreaterThanK(root, 5) == root->right->right.get());
  assert(!FindFirstGreaterThanK(root, 6));
  assert(!FindFirstGreaterThanK(root, 7));
  return 0;
}
