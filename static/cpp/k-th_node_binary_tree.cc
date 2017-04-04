// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <stdexcept>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::make_unique;
using std::unique_ptr;

template <typename T>
struct BinaryTreeNode {
  T data;
  unique_ptr<BinaryTreeNode<T>> left, right;
  int size;
};

// @include
const BinaryTreeNode<int>* FindKthNodeBinaryTree(
    const unique_ptr<BinaryTreeNode<int>>& tree, int k) {
  const auto* iter = tree.get();
  while (iter != nullptr) {
    int left_size = iter->left ? iter->left->size : 0;
    if (left_size + 1 < k) {  // k-th node must be in right subtree of iter.
      k -= (left_size + 1);
      iter = iter->right.get();
    } else if (left_size == k - 1) {  // k-th is iter itself.
      return iter;
    } else {  // k-th node must be in left subtree of iter.
      iter = iter->left.get();
    }
  }
  // If k is between 1 and the tree size, this line is unreachable.
  return nullptr;
}
// @exclude

int main(int argc, char* argv[]) {
  //  size field
  //      6
  //    2   3
  //  1    1 1
  //
  //  data field
  //      3
  //    2   5
  //  1    4 6
  auto root = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  root->size = 6;
  root->data = 3;
  root->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  root->left->size = 2;
  root->left->data = 2;
  root->left->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  root->left->left->size = 1;
  root->left->left->data = 1;
  root->right = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  root->right->size = 3;
  root->right->data = 5;
  root->right->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  root->right->left->size = 1;
  root->right->left->data = 4;
  root->right->right =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>());
  root->right->right->size = 1;
  root->right->right->data = 6;
  // 0th node does not exist - leftmost node is node 1
  assert(nullptr == FindKthNodeBinaryTree(root, 0));
  // should output 1
  assert(FindKthNodeBinaryTree(root, 1)->data == 1);
  cout << (FindKthNodeBinaryTree(root, 1))->data << endl;
  // should output 2
  assert(FindKthNodeBinaryTree(root, 2)->data == 2);
  cout << (FindKthNodeBinaryTree(root, 2))->data << endl;
  // should output 3
  assert(FindKthNodeBinaryTree(root, 3)->data == 3);
  cout << (FindKthNodeBinaryTree(root, 3))->data << endl;
  // should output 4
  assert(FindKthNodeBinaryTree(root, 4)->data == 4);
  cout << (FindKthNodeBinaryTree(root, 4))->data << endl;
  // should output 5
  assert(FindKthNodeBinaryTree(root, 5)->data == 5);
  cout << (FindKthNodeBinaryTree(root, 5))->data << endl;
  // should output 6
  assert(FindKthNodeBinaryTree(root, 6)->data == 6);
  cout << (FindKthNodeBinaryTree(root, 6))->data << endl;
  assert(nullptr == FindKthNodeBinaryTree(root, 7));
  return 0;
}
