// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <memory>

#include "./Binary_tree_prototype.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::unique_ptr;

// @include
void TreeTraversal(const unique_ptr<BinaryTreeNode<int>>& root) {
  if (root) {
    // Preorder: Processes the root before the traversals of left and right
    // children.
    cout << "Preorder: " << root->data << endl;
    TreeTraversal(root->left);
    // Inorder: Processes the root after the traversal of left child and
    // before the traversal of right child.
    cout << "Inorder: " << root->data << endl;
    TreeTraversal(root->right);
    // Postorder: Processes the root after the traversals of left and right
    // children.
    cout << "Postorder: " << root->data << endl;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BinaryTreeNode<int>> tree = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  tree->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  tree->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  tree->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  tree->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  tree->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  TreeTraversal(tree);
  return 0;
}
