// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::make_unique;
using std::unique_ptr;

// @include
// Input nodes are not nonempty and the key at s is less than or equal to that
// at b.
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
  // Now, s->data <= p->data && p->data <= b->data.
  return p;
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4 6
  unique_ptr<BSTNode<int>> tree = make_unique<BSTNode<int>>(BSTNode<int>{3});
  tree->left = make_unique<BSTNode<int>>(BSTNode<int>{2});
  tree->left->left = make_unique<BSTNode<int>>(BSTNode<int>{1});
  tree->right = make_unique<BSTNode<int>>(BSTNode<int>{5});
  tree->right->left = make_unique<BSTNode<int>>(BSTNode<int>{4});
  tree->right->right = make_unique<BSTNode<int>>(BSTNode<int>{6});
  assert(3 == FindLCA(tree, tree->left->left, tree->right->left)->data);
  assert(5 == FindLCA(tree, tree->right->left, tree->right->right)->data);
  assert(2 == FindLCA(tree, tree->left->left, tree->left)->data);
  assert(3 == FindLCA(tree, tree->left->left, tree->right)->data);
  return 0;
}
