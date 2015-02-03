// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::unique_ptr;

// @include
BSTNode<int>* FindLCA(const unique_ptr<BSTNode<int>>& root,
                      const unique_ptr<BSTNode<int>>& s,
                      const unique_ptr<BSTNode<int>>& b) {
  auto* p = root.get();
  while (p->data < s->data || p->data > b->data) {
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
  unique_ptr<BSTNode<int>> root = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  assert(3 == FindLCA(root, root->left->left, root->right->left)->data);
  assert(5 == FindLCA(root, root->right->left, root->right->right)->data);
  assert(2 == FindLCA(root, root->left->left, root->left)->data);
  return 0;
}
