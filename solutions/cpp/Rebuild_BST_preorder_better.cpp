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

BSTNode<int>* RebuildBSTFromPreorderHelper(const vector<int>& preorder, int min,
                                           int max, size_t* idx);

// @include
BSTNode<int>* RebuildBSTFromPreorder(const vector<int>& preorder) {
  size_t idx = 0;
  return RebuildBSTFromPreorderHelper(preorder, numeric_limits<int>::min(),
                                      numeric_limits<int>::max(), &idx);
}

BSTNode<int>* RebuildBSTFromPreorderHelper(
    const vector<int>& preorder, int min, int max, size_t* idx) {
  if (*idx == preorder.size()) {
    return nullptr;
  }

  int curr = preorder[*idx];
  if (curr < min || curr > max) {
    return nullptr;
  }

  ++*idx;
  return new BSTNode<int>{
      curr, unique_ptr<BSTNode<int>>(
                RebuildBSTFromPreorderHelper(preorder, min, curr, idx)),
      unique_ptr<BSTNode<int>>(
          RebuildBSTFromPreorderHelper(preorder, curr, max, idx))};
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
  unique_ptr<BSTNode<int>> root(RebuildBSTFromPreorder(preorder));
  CheckAns<int>(root, numeric_limits<int>::min());
  return 0;
}
