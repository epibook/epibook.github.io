// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <limits>
#include <memory>
#include <string>
#include <vector>

#include "./BST_prototype.h"

using std::cout;
using std::endl;
using std::numeric_limits;
using std::unique_ptr;
using std::vector;

BSTNode<int>* RebuildBSTFromPreorderHelper(
    const vector<int> &preorder, size_t s, size_t e);

// @include
BSTNode<int>* RebuildBSTFromPreorder(const vector<int>& preorder_sequence) {
  return RebuildBSTFromPreorderHelper(preorder_sequence, 
                                      0, preorder_sequence.size());
}

// Builds a BST from preorder_sequence[s : e - 1].
BSTNode<int>* RebuildBSTFromPreorderHelper(
    const vector<int>& preorder_sequence, size_t s, size_t e) {
  if (s < e) {
    size_t transition_point = s + 1;
    while (x < e && 
           preorder_sequence[transition_point] < preorder_sequence[s]) {
      ++transition_point;
    }
    return new BSTNode<int>{
        preorder_sequence[s],
        unique_ptr<BSTNode<int>>(
            RebuildBSTFromPreorderHelper(preorder_sequence, s + 1, 
                                         transition_point)),
        unique_ptr<BSTNode<int>>(
            RebuildBSTFromPreorderHelper(preorder_sequence, transition_point, 
                                         e))
    };
  }
  return nullptr;
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
  CheckAns(root, numeric_limits<int>::min());
  return 0;
}
