package com.epi;

public class BinaryTreeLock {
  // @include
  public static class BinaryTree {
    private BinaryTree left, right, parent;
    private boolean locked = false;
    private int numLockedDescendants = 0;

    public boolean isLocked() { return locked; }

    public boolean lock() {
      // We cannot lock if any of this node's descendants are locked.
      if (numLockedDescendants > 0 || locked) {
        return false;
      }

      // We cannot lock if any of this node's ancestors are locked.
      for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
        if (iter.locked) {
          return false;
        }
      }

      // Lock this node and increments all its ancestors's descendant lock
      // counts.
      locked = true;
      for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
        ++iter.numLockedDescendants;
      }
      return true;
    }

    public void unlock() {
      if (locked) {
        // Unlocks itself and decrements its ancestors's descendant lock counts.
        locked = false;
        for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
          --iter.numLockedDescendants;
        }
      }
    }
    // @exclude

    public BinaryTree getLeft() { return left; }

    public void setLeft(BinaryTree left) { this.left = left; }

    public BinaryTree getRight() { return right; }

    public void setRight(BinaryTree right) { this.right = right; }

    public void setParent(BinaryTree parent) { this.parent = parent; }
    // @include
  }
  // @exclude

  public static void main(String[] args) {
    BinaryTree root = new BinaryTree();
    root.setLeft(new BinaryTree());
    root.getLeft().setParent(root);
    root.setRight(new BinaryTree());
    root.getRight().setParent(root);
    root.getLeft().setLeft(new BinaryTree());
    root.getLeft().getLeft().setParent(root.getLeft());
    root.getLeft().setRight(new BinaryTree());
    root.getLeft().getRight().setParent(root.getLeft());

    assert(!root.isLocked());
    System.out.println(root.isLocked());

    assert(root.lock());
    assert(root.isLocked());
    System.out.println(root.isLocked());
    assert(!root.getLeft().lock());
    assert(!root.getLeft().isLocked());
    assert(!root.getRight().lock());
    assert(!root.getRight().isLocked());
    assert(!root.getLeft().getLeft().lock());
    assert(!root.getLeft().getLeft().isLocked());
    assert(!root.getLeft().getRight().lock());
    assert(!root.getLeft().getRight().isLocked());

    root.unlock();
    assert(root.getLeft().lock());
    assert(!root.lock());
    assert(!root.getLeft().getLeft().lock());
    assert(!root.isLocked());

    System.out.println(root.isLocked());
    assert(root.getRight().lock());
    assert(root.getRight().isLocked());
    System.out.println(root.isLocked());
  }
}
