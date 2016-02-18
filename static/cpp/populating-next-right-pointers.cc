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
  BinaryTreeNode<T>* next;  // Populates this field.
};

void PopulateLowerLevelNextField(BinaryTreeNode<int>*);

// @include
void ConstructRightSibling(BinaryTreeNode<int>* tree) {
  auto left_start = tree;
  while (left_start && left_start->left) {
    PopulateLowerLevelNextField(left_start);
    left_start = left_start->left.get();
  }
}

void PopulateLowerLevelNextField(BinaryTreeNode<int>* start_node) {
  auto iter = start_node;
  while (iter) {
    // Populate left child's next field.
    iter->left->next = iter->right.get();
    // Populate right child's next field if iter is not the last node of this
    // level.
    if (iter->next) {
      iter->right->next = iter->next->left.get();
    }
    iter = iter->next;
  }
}
// @exclude

void SimpleTest() {
  //      3
  //    2   5
  unique_ptr<BinaryTreeNode<int>> root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr, nullptr});
  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr, nullptr});
  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr, nullptr});
  ConstructRightSibling(root.get());
  assert(root->next == nullptr);
  assert(root->left->next == root->right.get());
  assert(root->right->next == nullptr);
}

int main(int argc, char* argv[]) {
  SimpleTest();
  //      3
  //    2   5
  //  1  7 4 6
  unique_ptr<BinaryTreeNode<int>> root = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr, nullptr});
  root->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr, nullptr});
  root->left->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{7, nullptr, nullptr, nullptr});
  root->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr, nullptr});
  root->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr, nullptr});
  root->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr, nullptr});
  root->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr, nullptr});
  ConstructRightSibling(root.get());
  assert(root->next == nullptr);
  assert(root->left->next == root->right.get());
  assert(root->left->left->next == root->left->right.get());
  assert(root->left->right->next == root->right->left.get());
  assert(root->right->left->next == root->right->right.get());
  return 0;
}
