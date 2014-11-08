// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <iostream>
#include <memory>

using std::boolalpha;
using std::cout;
using std::endl;
using std::make_shared;
using std::shared_ptr;

// @include
class BinaryTreeNode {
 public:
  bool IsLocked() const { return locked_; }

  bool Lock() {
    if (numLockedDescendants_ > 0 || locked_) {
      return false;
    }

    // Tests if any of ancestors are not locked.
    for (auto iter = parent_; iter != nullptr; iter = iter->parent_) {
      if (iter->locked_) {
        return false;
      }
    }

    // Locks itself and increments its ancestors's lock counts.
    locked_ = true;
    for (auto iter = parent_; iter != nullptr; iter = iter->parent_) {
      ++iter->numLockedDescendants_;
    }
    return true;
  }

  void Unlock() {
    if (locked_) {
      // Unlocks itself and decrements its ancestors's lock counts.
      locked_ = false;
      for (auto iter = parent_; iter != nullptr; iter = iter->parent_) {
        --iter->numLockedDescendants_;
      }
    }
  }

  // @exclude
  shared_ptr<BinaryTreeNode>& left() { return left_; }

  shared_ptr<BinaryTreeNode>& right() { return right_; }

  shared_ptr<BinaryTreeNode>& parent() { return parent_; }
  // @include
 private:
  shared_ptr<BinaryTreeNode> left_, right_, parent_;

  bool locked_;
  int numLockedDescendants_;
};
// @exclude

int main(int argc, char* argv[]) {
  auto root = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left()->parent() = root;
  root->right() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->right()->parent() = root;
  root->left()->left() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left()->left()->parent() = root->left();
  root->left()->right() = make_shared<BinaryTreeNode>(BinaryTreeNode());
  root->left()->right()->parent() = root->left();
  // Should output false.
  assert(!root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;
  assert(root->Lock());
  // Should output true.
  assert(root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;
  root->Unlock();
  assert(root->left()->Lock());
  assert(!root->Lock());
  // Should output false.
  assert(!root->IsLocked());
  cout << boolalpha << root->IsLocked() << endl;
  assert(root->right()->Lock());
  // Should output true.
  assert(root->right()->IsLocked());
  cout << boolalpha << root->right()->IsLocked() << endl;
  return 0;
}
