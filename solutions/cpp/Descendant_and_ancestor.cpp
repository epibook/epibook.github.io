// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::unique_ptr;

bool SearchTarget(BSTNode<int>* p, const unique_ptr<BSTNode<int>>& t);

// @include
bool IsRSDescendantAncestorPairForM(const unique_ptr<BSTNode<int>>& r,
                                    const unique_ptr<BSTNode<int>>& s,
                                    const unique_ptr<BSTNode<int>>& m) {
  auto* cur_r = r.get(), *cur_s = s.get();

  // Perform interleaved searching from r and s for m.
  while (cur_r && cur_r != s.get() && cur_r != m.get() &&
         cur_s && cur_s != r.get() && cur_s != m.get()) {
    cur_r = cur_r->data > m->data ? cur_r->left.get() : cur_r->right.get();
    cur_s = cur_s->data > m->data ? cur_s->left.get() : cur_s->right.get();
  }

  // If both searches were unsuccessful, or we got from r to s without
  // seeing m, or from s to r without seeing m, m cannot lie between r and s.
  if ((cur_r != m.get() && cur_s != m.get()) ||
       cur_r == s.get() || cur_s == r.get()) {
    return false;
  }

  // Check if m has a path to s or to r. If we get here, we already
  // know one of r or s has a path to m.
  return SearchTarget(m.get(), s) || SearchTarget(m.get(), r);
}

bool SearchTarget(BSTNode<int>* p, const unique_ptr<BSTNode<int>>& target) {
  while (p && p != target.get()) {
    p = p->data > target->data ? p->left.get() : p->right.get();
  }
  return p == target.get();
}
// @exclude

void SmallTest() {
  auto root = unique_ptr<BSTNode<int>>(new BSTNode<int>{5});
  root->left = unique_ptr<BSTNode<int>>(new BSTNode<int>{2});
  root->left->right = unique_ptr<BSTNode<int>>(new BSTNode<int>{4});
  assert(!IsRSDescendantAncestorPairForM(root, root->left, root->left->right));

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

  assert(!IsRSDescendantAncestorPairForM(root->right, root->left,
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
  assert(IsRSDescendantAncestorPairForM(root, root->right->right, root->right));
  assert(IsRSDescendantAncestorPairForM(root->right->right, root, root->right));
  assert(!IsRSDescendantAncestorPairForM(root, root->right, root->right->right));
  assert(!IsRSDescendantAncestorPairForM(root->right, root, root->right->right));
  assert(!IsRSDescendantAncestorPairForM(root->right->left, root->right->right,
                                    root->right));
  assert(!IsRSDescendantAncestorPairForM(root->right->left, root->left->left,
                                    root->right));
  return 0;
}
