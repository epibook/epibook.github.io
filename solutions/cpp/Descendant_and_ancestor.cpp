// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::unique_ptr;

bool SearchTarget(BSTNode<int>* p, const unique_ptr<BSTNode<int>>& t);

// @include
bool IsRSDescendantAncestorOfM(const unique_ptr<BSTNode<int>>& r,
                               const unique_ptr<BSTNode<int>>& s,
                               const unique_ptr<BSTNode<int>>& m) {
  auto* cur_r = r.get(), *cur_s = s.get();

  // Interleaving searches from r and s.
  while (cur_r && cur_r != s.get() && cur_r != m.get() &&
         cur_s && cur_s != r.get() && cur_s != m.get()) {
    cur_r = cur_r->data > m->data ? cur_r->left.get() : cur_r->right.get();
    cur_s = cur_s->data > m->data ? cur_s->left.get() : cur_s->right.get();
  }

  // Reach the other before reaching m.
  if (cur_r == s.get() || cur_s == r.get() ||
      (cur_r != m.get() && cur_s != m.get())) {
    return false;
  }

  // Try to search the other before reaching the other.
  return SearchTarget(m.get(), s) || SearchTarget(m.get(), r);
}

bool SearchTarget(BSTNode<int>* p, const unique_ptr<BSTNode<int>>& t) {
  while (p && p != t.get()) {
    p = p->data > t->data ? p->left.get() : p->right.get();
  }
  return p == t.get();
}
// @exclude

void SmallTest() {
  auto root = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  assert(!IsRSDescendantAncestorOfM(root, root->left, root->left->right));

  //      4
  //    2    6
  //  1  3  5 7
  root = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  root->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{7});

  assert(!IsRSDescendantAncestorOfM(root->right, root->left,
                                    root->right->left));
}

int main(int argc, char* argv[]) {
  SmallTest();
  //      3
  //    2   5
  //  1    4 6
  auto root = unique_ptr<BSTNode<int>>(new BSTNode<int>{3});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{1});
  root->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->right->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  root->right->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{6});
  assert(IsRSDescendantAncestorOfM(root, root->right->right, root->right));
  assert(IsRSDescendantAncestorOfM(root->right->right, root, root->right));
  assert(!IsRSDescendantAncestorOfM(root, root->right, root->right->right));
  assert(!IsRSDescendantAncestorOfM(root->right, root, root->right->right));
  assert(!IsRSDescendantAncestorOfM(root->right->left, root->right->right,
                                    root->right));
  assert(!IsRSDescendantAncestorOfM(root->right->left, root->left->left,
                                    root->right));
  return 0;
}
