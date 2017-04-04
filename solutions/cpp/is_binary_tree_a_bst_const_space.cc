// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::boolalpha;
using std::cout;
using std::endl;
using std::make_unique;
using std::numeric_limits;
using std::unique_ptr;

// @include
bool IsBinaryTreeBST(const unique_ptr<BinaryTreeNode<int>>& root) {
  auto* n = root.get();
  // Stores the value of previous visited node.
  int prevValue = numeric_limits<int>::min();
  bool result = true;

  while (n) {
    if (n->left.get()) {
      // Finds the predecessor of n.
      auto* pre = n->left.get();
      while (pre->right.get() && pre->right.get() != n) {
        pre = pre->right.get();
      }

      // Processes the successor link.
      if (pre->right.get()) {  // pre->right == n.
        // Reverts the successor link if predecessor's successor is n.
        pre->right.release();
        if (prevValue > n->data) {
          result = false;
        }
        prevValue = n->data;
        n = n->right.get();
      } else {  // If predecessor's successor is not n.
        pre->right.reset(n);
        n = n->left.get();
      }
    } else {
      if (prevValue > n->data) {
        result = false;
      }
      prevValue = n->data;
      n = n->right.get();
    }
  }
  return result;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  auto root = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{3});
  root->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{2});
  root->left->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{1});
  root->right = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{5});
  root->right->left =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{4});
  root->right->right =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{6});
  assert(IsBinaryTreeBST(root) == true);
  cout << boolalpha << IsBinaryTreeBST(root) << endl;
  //      10
  //    2   5
  //  1    4 6
  root->data = 10;
  // should output false
  assert(!IsBinaryTreeBST(root));
  cout << boolalpha << IsBinaryTreeBST(root) << endl;
  // should output true
  assert(IsBinaryTreeBST(nullptr) == true);
  cout << boolalpha << IsBinaryTreeBST(nullptr) << endl;
  return 0;
}
