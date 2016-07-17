// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

#include "./BST_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::unique_ptr;

// @include
BSTNode<int>* SearchBST(const unique_ptr<BSTNode<int>>& tree, int key) {
  if (tree == nullptr) {
    return nullptr;
  }
  if (tree->data == key) {
    return tree.get();
  }
  return key < tree->data ? SearchBST(tree->left, key)
                          : SearchBST(tree->right, key);
}
// @exclude

int main(int argc, char* argv[]) {
  //        43
  //    23     47
  //      37      53
  //    29  41
  //     31
  auto tree = make_unique<BSTNode<int>>(BSTNode<int>{
      43, make_unique<BSTNode<int>>(BSTNode<int>{
              23, nullptr,
              make_unique<BSTNode<int>>(BSTNode<int>{
                  37, make_unique<BSTNode<int>>(BSTNode<int>{
                          29, nullptr, make_unique<BSTNode<int>>(
                                           BSTNode<int>{31, nullptr})}),
                  make_unique<BSTNode<int>>(BSTNode<int>{41, nullptr})})}),
      make_unique<BSTNode<int>>(BSTNode<int>{
          47, nullptr,
          make_unique<BSTNode<int>>(BSTNode<int>{53, nullptr})})});
  assert(tree.get() == SearchBST(tree, 43));
  assert(tree->left.get() == SearchBST(tree, 23));
  assert(tree->right.get() == SearchBST(tree, 47));
  assert(tree->right->right.get() == SearchBST(tree, 53));
  assert(tree->left->right->left.get() == SearchBST(tree, 29));
  assert(nullptr == SearchBST(tree, 1000));
  return 0;
}
