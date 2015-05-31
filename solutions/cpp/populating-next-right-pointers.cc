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

void PopulateChildrenNextField(BinaryTreeNode<int>* start_node);

// @include
void ConstructRightSibling(BinaryTreeNode<int>* tree) {
  auto left_start = tree;
  while (left_start) {
    PopulateChildrenNextField(left_start);
    left_start = left_start->left.get();
  }
}

void PopulateChildrenNextField(BinaryTreeNode<int>* start_node) {
  auto iter = start_node;
  while (iter && iter->left) {
    iter->left->next = iter->right.get();
    if (iter->next) {
      iter->right->next = iter->next->left.get();
    }
    iter = iter->next;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1  7 4 6
  unique_ptr<BinaryTreeNode<int>> root = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{3, nullptr, nullptr, nullptr});
  root->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{2, nullptr, nullptr, nullptr});
  root->left->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{7, nullptr, nullptr, nullptr});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{1, nullptr, nullptr, nullptr});
  root->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{5, nullptr, nullptr, nullptr});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{4, nullptr, nullptr, nullptr});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(
      new BinaryTreeNode<int>{6, nullptr, nullptr, nullptr});
  ConstructRightSibling(root.get());
  assert(root->next == nullptr);
  assert(root->left->next == root->right.get());
  assert(root->left->left->next == root->left->right.get());
  assert(root->left->right->next == root->right->left.get());
  assert(root->right->left->next == root->right->right.get());
  return 0;
}
