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

BSTNode<int>* rebuild_BST_from_postorder_helper(const vector<int>& postorder,
                                                int* idx, int min, int max);

// @include
BSTNode<int>* rebuild_BST_from_postorder(const vector<int>& postorder) {
  int idx = postorder.size() - 1;
  return rebuild_BST_from_postorder_helper(
      postorder, &idx, numeric_limits<int>::min(), numeric_limits<int>::max());
}

BSTNode<int>* rebuild_BST_from_postorder_helper(const vector<int>& postorder,
                                                int* idx, int min, int max) {
  if (*idx < 0) {
    return nullptr;
  }

  int curr = postorder[*idx];
  if (curr < min || curr > max) {
    return nullptr;
  }

  --*idx;
  return new BSTNode<int>{
      curr, unique_ptr<BSTNode<int>>(
                rebuild_BST_from_postorder_helper(postorder, idx, min, curr)),
      unique_ptr<BSTNode<int>>(
          rebuild_BST_from_postorder_helper(postorder, idx, curr, max))};
}
// @exclude

template <typename T>
void check_ans(const unique_ptr<BSTNode<T>>& n, int pre) {
  if (n) {
    check_ans(n->left, pre);
    assert(pre <= n->data);
    cout << n->data << endl;
    check_ans(n->right, n->data);
  }
}

int main(int argc, char* argv[]) {
  //      1
  //        2
  //          3
  //            4
  //              5
  //                6
  // should output 1, 2, 3, 4, 5, 6
  // postorder [6, 5, 4, 3, 2, 1]
  vector<int> postorder;
  postorder.emplace_back(6);
  postorder.emplace_back(5);
  postorder.emplace_back(4);
  postorder.emplace_back(3);
  postorder.emplace_back(2);
  postorder.emplace_back(1);
  unique_ptr<BSTNode<int>> root(rebuild_BST_from_postorder(postorder));
  check_ans(root, numeric_limits<int>::min());
  return 0;
}
