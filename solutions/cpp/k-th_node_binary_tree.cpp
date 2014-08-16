// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <stdexcept>

using std::cout;
using std::endl;
using std::exception;
using std::length_error;
using std::unique_ptr;

template <typename T>
struct BinaryTreeNode {
  T data;
  unique_ptr<BinaryTreeNode<T>> left, right;
  int size;
};

// @include
const BinaryTreeNode<int>* FindKthNodeBinaryTree(
    const unique_ptr<BinaryTreeNode<int>>& root, int k) {
  const auto* n = root.get();
  while (n) {
    int left_size = n->left ? n->left->size : 0;
    if (left_size < k - 1) {
      k -= (left_size + 1);
      n = n->right.get();
    } else if (left_size == k - 1) {
      return n;
    } else {  // left_size > k - 1.
      n = n->left.get();
    }
  }
  throw length_error("no k-th node in binary tree");
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
  auto root = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->size = 6;
  root->data = 3;
  root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->left->size = 2;
  root->left->data = 2;
  root->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->left->left->size = 1;
  root->left->left->data = 1;
  root->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->right->size = 3;
  root->right->data = 5;
  root->right->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->right->left->size = 1;
  root->right->left->data = 4;
  root->right->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>());
  root->right->right->size = 1;
  root->right->right->data = 6;
  // should throw
  try {
    FindKthNodeBinaryTree(root, 0);
  }
  catch (const exception& e) {
    cout << e.what() << endl;
  }
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
  // should throw
  try {
    FindKthNodeBinaryTree(root, 7);
  }
  catch (const exception& e) {
    cout << e.what() << endl;
  }
  return 0;
}
