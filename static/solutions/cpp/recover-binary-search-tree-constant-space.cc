// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <iterator>
#include <memory>
#include <utility>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::make_unique;
using std::ostream_iterator;
using std::swap;
using std::unique_ptr;

// @include
void recover_BST(unique_ptr<BinaryTreeNode<int>> *root) {
  bool is_first = true;
  BinaryTreeNode<int> *n = root->get(), *parent = nullptr, *first = nullptr,
                      *second = nullptr;
  while (n) {
    if (n->left.get()) {
      // Finds the predecessor of n.
      auto *pre = n->left.get();
      while (pre->right.get() && pre->right.get() != n) {
        pre = pre->right.get();
      }

      // Processes the successor link.
      if (pre->right.get()) {  // pre->right.get() == n.
        // Reverts the successor link if predecessor's successor is n.
        pre->right.release();
        if (parent->data > n->data) {
          if (is_first) {
            first = parent;
            is_first = false;
          }
          second = n;
        }
        parent = n;
        n = n->right.get();
      } else {  // if predecessor's successor is not n.
        pre->right.reset(n);
        n = n->left.get();
      }
    } else {
      if (parent && parent->data > n->data) {
        if (is_first) {
          first = parent;
          is_first = false;
        }
        second = n;
      }
      parent = n;
      n = n->right.get();
    }
  }
  if (first && second) {
    swap(first->data, second->data);
  }
}
// @exclude

int main(int argc, char *argv[]) {
  //      3
  //    2   4
  //  1    5 6
  unique_ptr<BinaryTreeNode<int>> root =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
          3,
          make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
              2, make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{1})}),
          make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{
              4, make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{5}),
              make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{6})})});
  recover_BST(&root);
  auto result = generate_inorder(root);
  copy(result.cbegin(), result.cend(), ostream_iterator<int>(cout, " "));
  assert(is_sorted(result.begin(), result.end()));
  return 0;
}
