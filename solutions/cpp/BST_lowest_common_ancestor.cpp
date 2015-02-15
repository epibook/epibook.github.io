// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::unique_ptr;

// @include
BSTNode<int>* FindLCA(const unique_ptr<BSTNode<int>>& tree,
                      const unique_ptr<BSTNode<int>>& s,
                      const unique_ptr<BSTNode<int>>& b) {
  auto* p = tree.get();
  while (p->data < s->data || p->data > b->data) {
    // Keep searching since p is outside of [s, b].
    while (p->data < s->data) {
      p = p->right.get();  // LCA must be in p's right child.
    }
    while (p->data > b->data) {
      p = p->left.get();  // LCA must be in p's left child.
    }
  }

  // s->data <= p->data && p->data <= b->data.
  return p;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BSTNode<int>> tree = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  tree->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  tree->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  tree->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  tree->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  tree->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  assert(3 == FindLCA(tree, tree->left->left, tree->right->left)->data);
  assert(5 == FindLCA(tree, tree->right->left, tree->right->right)->data);
  assert(2 == FindLCA(tree, tree->left->left, tree->left)->data);
  return 0;
}
