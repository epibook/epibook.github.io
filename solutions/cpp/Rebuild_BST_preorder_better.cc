// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::endl;
using std::numeric_limits;
using std::unique_ptr;
using std::vector;

unique_ptr<BSTNode<int>> RebuildBSTFromPreorderHelper(const vector<int>&, int,
                                                      int, int*);

// @include
unique_ptr<BSTNode<int>> RebuildBSTFromPreorder(
    const vector<int>& preorder_sequence) {
  int root_idx = 0;
  return RebuildBSTFromPreorderHelper(preorder_sequence,
                                      numeric_limits<int>::min(),
                                      numeric_limits<int>::max(), &root_idx);
}

// Builds a BST from preorder_sequence on keys in (lower_bound : upper_bound).
unique_ptr<BSTNode<int>> RebuildBSTFromPreorderHelper(
    const vector<int>& preorder_sequence, int lower_bound, int upper_bound,
    int* root_idx) {
  if (*root_idx == preorder_sequence.size()) {
    return nullptr;
  }

  int root = preorder_sequence[*root_idx];
  if (root < lower_bound || root > upper_bound) {
    return nullptr;
  }
  ++*root_idx;
  return unique_ptr<BSTNode<int>>(new BSTNode<int>{
      root, RebuildBSTFromPreorderHelper(preorder_sequence, lower_bound, root,
                                         root_idx),
      RebuildBSTFromPreorderHelper(preorder_sequence, root, upper_bound,
                                   root_idx)});
}
// @exclude

template <typename T>
void CheckAns(const unique_ptr<BSTNode<T>>& n, const T& pre) {
  if (n) {
    CheckAns(n->left, pre);
    assert(pre <= n->data);
    cout << n->data << endl;
    CheckAns(n->right, n->data);
  }
}

int main(int argc, char* argv[]) {
  //      3
  //    2   5
  //  1    4  6
  // should output 1, 2, 3, 4, 5, 6
  // preorder [3, 2, 1, 5, 4, 6]
  vector<int> preorder;
  preorder.emplace_back(3);
  preorder.emplace_back(2);
  preorder.emplace_back(1);
  preorder.emplace_back(5);
  preorder.emplace_back(4);
  preorder.emplace_back(6);
  unique_ptr<BSTNode<int>> tree(RebuildBSTFromPreorder(preorder));
  CheckAns<int>(tree, numeric_limits<int>::min());
  return 0;
}
