// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <list>
#include <memory>

#include "./Binary_tree_prototype.h"
#include "./Reconstruct_preorder_with_null.h"

using std::cout;
using std::endl;
using std::list;
using std::make_unique;
using std::unique_ptr;

list<const unique_ptr<BinaryTreeNode<int>>*> LeftBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>&, bool);
list<const unique_ptr<BinaryTreeNode<int>>*> RightBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>&, bool);
bool IsLeaf(const unique_ptr<BinaryTreeNode<int>>&);

// @include
list<const unique_ptr<BinaryTreeNode<int>>*> ExteriorBinaryTree(
    const unique_ptr<BinaryTreeNode<int>>& tree) {
  list<const unique_ptr<BinaryTreeNode<int>>*> exterior;
  if (tree != nullptr) {
    exterior.emplace_back(&tree);
    exterior.splice(exterior.end(), LeftBoundaryAndLeaves(tree->left, true));
    exterior.splice(exterior.end(),
                    RightBoundaryAndLeaves(tree->right, true));
  }
  return exterior;
}

// Computes the nodes from the root to the leftmost leaf followed by all the
// leaves in subtree.
list<const unique_ptr<BinaryTreeNode<int>>*> LeftBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>& subtree, bool is_boundary) {
  list<const unique_ptr<BinaryTreeNode<int>>*> result;
  if (subtree != nullptr) {
    if (is_boundary || IsLeaf(subtree)) {
      result.emplace_back(&subtree);
    }
    result.splice(result.end(),
                  LeftBoundaryAndLeaves(subtree->left, is_boundary));
    result.splice(result.end(), LeftBoundaryAndLeaves(
                                    subtree->right,
                                    is_boundary && subtree->left == nullptr));
  }
  return result;
}

// Computes the leaves in left-to-right order followed by the rightmost leaf
// to the root path in subtree.
list<const unique_ptr<BinaryTreeNode<int>>*> RightBoundaryAndLeaves(
    const unique_ptr<BinaryTreeNode<int>>& subtree, bool is_boundary) {
  list<const unique_ptr<BinaryTreeNode<int>>*> result;
  if (subtree != nullptr) {
    result.splice(
        result.end(),
        RightBoundaryAndLeaves(subtree->left,
                               is_boundary && subtree->right == nullptr));
    result.splice(result.end(),
                  RightBoundaryAndLeaves(subtree->right, is_boundary));
    if (is_boundary || IsLeaf(subtree)) {
      result.emplace_back(&subtree);
    }
  }
  return result;
}

bool IsLeaf(const unique_ptr<BinaryTreeNode<int>>& node) {
  return node->left == nullptr && node->right == nullptr;
}
// @exclude

list<int> CreateOutputList(
    const list<const unique_ptr<BinaryTreeNode<int>>*>& L) {
  list<int> output;
  for (const auto* l : L) {
    output.push_back((*l)->data);
  }
  return output;
}

void SimpleTest(void) {
  // The example in the book.
  vector<int> A = {314, 6, 271, 28,  0,   561, 3,   17,
                   6,   2, 1,   401, 641, 257, 271, 28};
  unique_ptr<BinaryTreeNode<int>> tree = ReconstructPreorder(
      {&A[0],   &A[1],   &A[2],   &A[3],   nullptr, nullptr, &A[4],
       nullptr, nullptr, &A[5],   nullptr, &A[6],   &A[7],   nullptr,
       nullptr, nullptr, &A[8],   &A[9],   nullptr, &A[10],  &A[11],
       nullptr, &A[12],  nullptr, nullptr, &A[13],  nullptr, nullptr,
       &A[14],  nullptr, &A[15],  nullptr, nullptr});

  list<int> res = CreateOutputList(ExteriorBinaryTree(tree));
  list<int> golden = {314, 6, 271, 28, 0, 17, 641, 257, 28, 271, 6};
  assert(equal(begin(res), end(res), begin(golden), end(golden)));

  tree->left->left = nullptr;
  res = CreateOutputList(ExteriorBinaryTree(tree));
  golden = {314, 6, 561, 3, 17, 641, 257, 28, 271, 6};
  assert(equal(begin(res), end(res), begin(golden), end(golden)));

  tree->right->right = nullptr;
  res = CreateOutputList(ExteriorBinaryTree(tree));
  golden = {314, 6, 561, 3, 17, 641, 257, 1, 2, 6};
  assert(equal(begin(res), end(res), begin(golden), end(golden)));

  tree->right = nullptr;
  res = CreateOutputList(ExteriorBinaryTree(tree));
  golden = {314, 6, 561, 3, 17};
  assert(equal(begin(res), end(res), begin(golden), end(golden)));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  //        3
  //    2      5
  //  1  0    4 6
  //   -1 -2
  unique_ptr<BinaryTreeNode<int>> tree = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{3, nullptr, nullptr});
  auto L = ExteriorBinaryTree(tree);
  list<int> result = CreateOutputList(L);
  list<int> golden_result = {3};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));

  tree->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{2, nullptr, nullptr});
  L = ExteriorBinaryTree(tree);
  result = CreateOutputList(L);
  golden_result = {3, 2};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));

  tree->left->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{0, nullptr, nullptr});
  tree->left->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{-1, nullptr, nullptr});
  tree->left->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{-2, nullptr, nullptr});
  tree->left->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{1, nullptr, nullptr});
  tree->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{5, nullptr, nullptr});
  tree->right->left = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{4, nullptr, nullptr});
  tree->right->right = make_unique<BinaryTreeNode<int>>(
      BinaryTreeNode<int>{6, nullptr, nullptr});
  L = ExteriorBinaryTree(tree);
  result = CreateOutputList(L);
  golden_result = {3, 2, 1, -1, -2, 4, 6, 5};
  assert(equal(result.begin(), result.end(), golden_result.begin(),
               golden_result.end()));
  return 0;
}
