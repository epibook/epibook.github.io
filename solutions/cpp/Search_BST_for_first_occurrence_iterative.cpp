// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::unique_ptr;

// @include
BSTNode<int>* FindFirstEqualK(const unique_ptr<BSTNode<int>>& T, int k) {
  BSTNode<int>* first = nullptr, *curr = T.get();
  while (curr) {
    if (curr->data < k) {
      curr = curr->right.get();
    } else if (curr->data > k) {
      curr = curr->left.get();
    } else {  // curr->data == k.
      // Searches for the leftmost in the left subtree.
      first = curr;
      curr = curr->left.get();
    }
  }
  return first;
}
// @exclude

int main(int argc, char* argv[]) {
  //    3
  //  2   5
  // 1   4 6
  auto root = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  assert(!FindFirstEqualK(root, 7));
  assert(FindFirstEqualK(root, 6)->data == 6);
  return 0;
}
