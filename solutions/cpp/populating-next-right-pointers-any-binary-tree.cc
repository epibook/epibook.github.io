// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <queue>
#include <vector>

using std::cout;
using std::endl;
using std::queue;
using std::make_unique;
using std::unique_ptr;
using std::vector;

template <typename T>
struct BinaryTreeNode {
  T data;
  unique_ptr<BinaryTreeNode<T>> left, right;
  BinaryTreeNode<T>* next;  // populate this field.
};

// @include
void populate_next_pointer(BinaryTreeNode<int>* node) {
  while (node) {
    BinaryTreeNode<int> *next_level_start = nullptr, *prev = nullptr;
    while (node) {
      if (!next_level_start) {
        next_level_start = node->left ? node->left.get() : node->right.get();
      }

      if (node->left) {
        if (prev) {
          prev->next = node->left.get();
        }
        prev = node->left.get();
      }
      if (node->right) {
        if (prev) {
          prev->next = node->right.get();
        }
        prev = node->right.get();
      }
      node = node->next;
    }
    node = next_level_start;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2    5
  //       4   6
  //        8 9
  unique_ptr<BinaryTreeNode<int>> root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr, nullptr});
  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr, nullptr});
  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr, nullptr});
  root->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr, nullptr});
  root->right->left->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{8, nullptr, nullptr, nullptr});
  root->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr, nullptr});
  root->right->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{9, nullptr, nullptr, nullptr});
  populate_next_pointer(root.get());
  assert(root->next == nullptr);
  assert(root->left->next == root->right.get());
  assert(root->right->left->next == root->right->right.get());
  assert(root->right->left->right->next == root->right->right->left.get());
  return 0;
}
