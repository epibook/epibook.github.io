// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <queue>
#include <vector>

using std::cout;
using std::endl;
using std::queue;
using std::unique_ptr;
using std::vector;

template <typename T>
struct BinaryTreeNode {
  T data;
  unique_ptr<BinaryTreeNode<T>> left, right;
  BinaryTreeNode<T>* next;  // Populates this field.
};

// @include
void PopulateNextPointer(BinaryTreeNode<int>* root) {
  auto left_start = root;
  while (left_start) {
    auto parent = left_start;
    while (parent && parent->left) {
      parent->left->next = parent->right.get();
      if (parent->next) {
        parent->right->next = parent->next->left.get();
      }
      parent = parent->next;
    }
    left_start = left_start->left.get();
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1  7 4 6
  unique_ptr<BinaryTreeNode<int>> root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3, nullptr, nullptr, nullptr});
  root->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2, nullptr, nullptr, nullptr});
  root->left->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{7, nullptr, nullptr, nullptr});
  root->left->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1, nullptr, nullptr, nullptr});
  root->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5, nullptr, nullptr, nullptr});
  root->right->left =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4, nullptr, nullptr, nullptr});
  root->right->right =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6, nullptr, nullptr, nullptr});
  PopulateNextPointer(root.get());
  assert(root->next == nullptr);
  assert(root->left->next == root->right.get());
  assert(root->left->left->next == root->left->right.get());
  assert(root->left->right->next == root->right->left.get());
  assert(root->right->left->next == root->right->right.get());
  return 0;
}
