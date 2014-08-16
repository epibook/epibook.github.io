// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>
#include <vector>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::unique_ptr;
using std::vector;

vector<int> result;

// @include
void InorderTraversal(const unique_ptr<BinaryTreeNode<int>>& root) {
  auto* n = root.get();
  while (n) {
    if (n->left.get()) {
      // Finds the predecessor of n.
      auto* pre = n->left.get();
      while (pre->right.get() && pre->right.get() != n) {
        pre = pre->right.get();
      }

      // Processes the successor link.
      if (pre->right.get()) {  // pre->right.get() == n.
        // Reverts the successor link if predecessor's successor is n.
        pre->right.release();
        cout << n->data << endl;
        // @exclude
        result.emplace_back(n->data);
        // @include
        n = n->right.get();
      } else {  // If predecessor's successor is not n.
        pre->right.reset(n);
        n = n->left.get();
      }
    } else {
      cout << n->data << endl;
      // @exclude
      result.emplace_back(n->data);
      // @include
      n = n->right.get();
    }
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3});
  root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1});
  root->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6});
  // should output 1 2 3 4 5 6
  InorderTraversal(root);
  vector<int> golden_res = {1, 2, 3, 4, 5, 6};
  assert(result.size() == golden_res.size());
  assert(equal(result.begin(), result.end(), golden_res.begin()));
  return 0;
}
