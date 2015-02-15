// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <iterator>
#include <memory>
#include <utility>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::endl;
using std::ostream_iterator;
using std::swap;
using std::unique_ptr;

void ReconstructBSTHelper(BinaryTreeNode<int>* root,
                          BinaryTreeNode<int>** p1,
                          BinaryTreeNode<int>** p2,
                          BinaryTreeNode<int>** prev);

// @include
void ReconstructBST(unique_ptr<BinaryTreeNode<int>>* root) {
  BinaryTreeNode<int> *p1 = nullptr, *p2 = nullptr, *prev = nullptr;
  ReconstructBSTHelper(root->get(), &p1, &p2, &prev);
  if (p1 && p2) {  // Swaps the out of order nodes.
    swap(p1->data, p2->data);
  }
}

void ReconstructBSTHelper(BinaryTreeNode<int>* root,
                          BinaryTreeNode<int>** p1,
                          BinaryTreeNode<int>** p2,
                          BinaryTreeNode<int>** prev) {
  if (!root) {
    return;
  }

  ReconstructBSTHelper(root->left.get(), p1, p2, prev);
  if (*prev && (*prev)->data > root->data) {
    // Inversion detected.
    *p2 = root;  // Assigns p2 as the current node.
    if (!*p1) {
      *p1 = *prev;  // Assigns p1 as the first node.
    }
  }
  *prev = root;  // Records the previous node as the current node.
  ReconstructBSTHelper(root->right.get(), p1, p2, prev);
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   4
  //  1    5 6
  unique_ptr<BinaryTreeNode<int>> root =
      unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{3});
  root->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{2});
  root->left->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{1});
  root->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{4});
  root->right->left = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{5});
  root->right->right = unique_ptr<BinaryTreeNode<int>>(new BinaryTreeNode<int>{6});
  ReconstructBST(&root);
  auto result = generate_inorder(root);
  copy(result.cbegin(), result.cend(), ostream_iterator<int>(cout, " "));
  assert(is_sorted(result.begin(), result.end()));
  return 0;
}
