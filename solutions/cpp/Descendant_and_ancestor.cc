// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <memory>

#include "./BST_prototype.h"

using std::unique_ptr;

bool SearchTarget(BSTNode<int>*, const unique_ptr<BSTNode<int>>&);

// @include
bool IsRSDescendantAncestorPairForM(const unique_ptr<BSTNode<int>>& anc_des_0,
                                    const unique_ptr<BSTNode<int>>& anc_des_1,
                                    const unique_ptr<BSTNode<int>>& middle) {
  auto* cur_anc_des_0 = anc_des_0.get(), *cur_anc_des_1 = anc_des_1.get();

  // Perform interleaved searching from anc_des_0 and anc_des_1 for middle.
  while (cur_anc_des_0 && cur_anc_des_0 != anc_des_1.get() && 
         cur_anc_des_0 != middle.get() &&
         cur_anc_des_1 && cur_anc_des_1 != anc_des_0.get() && 
         cur_anc_des_1 != middle.get()) {
    cur_anc_des_0 = cur_anc_des_0->data > middle->data ? 
                    cur_anc_des_0->left.get() : cur_anc_des_0->right.get();
    cur_anc_des_1 = cur_anc_des_1->data > middle->data ? 
                    cur_anc_des_1->left.get() : cur_anc_des_1->right.get();
  }

  // If both searches were unsuccessful, or we got from anc_des_0 to 
  // anc_des_1 without seeing middle, or from anc_des_1 to anc_des_0 without 
  // seeing middle, middle cannot lie between anc_des_0 and anc_des_1.
  if ((cur_anc_des_0 != middle.get() && cur_anc_des_1 != middle.get()) ||
       cur_anc_des_0 == anc_des_1.get() || cur_anc_des_1 == anc_des_0.get()) {
    return false;
  }

  // If we get here, we already know one of anc_des_0 or anc_des_1 has a path 
  // to middle. Check if middle has a path to anc_des_1 or to anc_des_0.
  return cur_anc_des_0 == middle.get() ? 
         SearchTarget(middle.get(), anc_des_1) : 
         SearchTarget(middle.get(), anc_des_0);
}

bool SearchTarget(BSTNode<int>* from, 
                  const unique_ptr<BSTNode<int>>& target) {
  while (from && from != target.get()) {
    from = from->data > target->data ? from->left.get() : from->right.get();
  }
  return from == target.get();
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
