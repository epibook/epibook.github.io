// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>

#include "./BST_prototype.h"

using std::unique_ptr;

// @include
BSTNode<int>* FindFirstLargerKWithKExist(const unique_ptr<BSTNode<int>>& T,
                                         int k) {
  bool found_k = false;
  BSTNode<int>* curr = T.get(), *first = nullptr;

  while (curr) {
    if (curr->data == k) {
      found_k = true;
      curr = curr->right.get();
    } else if (curr->data > k) {
      first = curr;
      curr = curr->left.get();
    } else {  // curr->data < k.
      curr = curr->right.get();
    }
  }
  return found_k ? first : nullptr;
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
  assert(FindFirstLargerKWithKExist(root, 1) == root->left.get());
  assert(FindFirstLargerKWithKExist(root, 5) == root->right->right.get());
  assert(!FindFirstLargerKWithKExist(root, 6));
  assert(!FindFirstLargerKWithKExist(root, 7));
  return 0;
}
