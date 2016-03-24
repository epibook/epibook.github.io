// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <iterator>
#include <memory>
#include <utility>

#include "./Binary_tree_prototype.h"
#include "./Binary_tree_utils.h"

using std::cout;
using std::endl;
using std::make_unique;
using std::ostream_iterator;
using std::swap;
using std::unique_ptr;

struct Inversion;
void ReconstructBSTHelper(BinaryTreeNode<int>*, Inversion*, Inversion*,
                          BinaryTreeNode<int>**);

// @include
struct Inversion {
  BinaryTreeNode<int> *prev, *next;
};

void ReconstructBST(unique_ptr<BinaryTreeNode<int>>* almost_BST) {
  Inversion inversion_0 = {nullptr, nullptr};
  Inversion inversion_1 = {nullptr, nullptr};
  BinaryTreeNode<int>* prev = nullptr;
  ReconstructBSTHelper(almost_BST->get(), &inversion_0, &inversion_1, &prev);
  if (inversion_1.next) {  // Swaps the out of order nodes.
    swap(inversion_0.prev->data, inversion_1.next->data);
  } else {
    swap(inversion_0.prev->data, inversion_0.next->data);
  }
}

void ReconstructBSTHelper(BinaryTreeNode<int>* almost_BST,
                          Inversion* inversion_0, Inversion* inversion_1,
                          BinaryTreeNode<int>** prev) {
  if (almost_BST == nullptr) {
    return;
  }

  ReconstructBSTHelper(almost_BST->left.get(), inversion_0, inversion_1,
                       prev);
  if (*prev && (*prev)->data > almost_BST->data) {
    // Inversion detected.
    if (inversion_0->prev == nullptr && inversion_0->next == nullptr) {
      *inversion_0 = {*prev, almost_BST};
    } else {
      *inversion_1 = {*prev, almost_BST};
    }
  }
  *prev = almost_BST;  // Records the previous node as the current node.
  ReconstructBSTHelper(almost_BST->right.get(), inversion_0, inversion_1,
                       prev);
}
// @exclude

int main(int argc, char* argv[]) {
  //      3
  //    2   4
  //  1    5 6
  unique_ptr<BinaryTreeNode<int>> almost_BST =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{3});
  almost_BST->left = make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{2});
  almost_BST->left->left =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{1});
  almost_BST->right =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{4});
  almost_BST->right->left =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{5});
  almost_BST->right->right =
      make_unique<BinaryTreeNode<int>>(BinaryTreeNode<int>{6});
  ReconstructBST(&almost_BST);
  auto result = generate_inorder(almost_BST);
  copy(result.cbegin(), result.cend(), ostream_iterator<int>(cout, " "));
  assert(is_sorted(result.begin(), result.end()));
  return 0;
}
