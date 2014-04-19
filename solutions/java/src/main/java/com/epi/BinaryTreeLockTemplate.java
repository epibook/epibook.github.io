package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinaryTreeLockTemplate {
  // @include
  public static class BinaryTree<T> {
    private BinaryTree<T> left, right, parent;
    private boolean locked;
    private int numChildrenLocks;

    public boolean isLock() {
      return locked;
    }

    public void lock() {
      if (numChildrenLocks == 0 && !locked) {
        // Make sure all parents do not lock.
        BinaryTree<T> n = parent;
        while (n != null) {
          if (n.locked) {
            return;
          }
          n = n.parent;
        }

        // Lock itself and update its parents.
        locked = true;
        n = parent;
        while (n != null) {
          ++n.numChildrenLocks;
          n = n.parent;
        }
      }
    }

    public void unlock() {
      if (locked) {
        // Unlock itself and update its parents.
        locked = false;
        BinaryTree<T> n = parent;
        while (n != null) {
          --n.numChildrenLocks;
          n = n.parent;
        }
      }
    }

    // @exclude

    public BinaryTree<T> getLeft() {
      return left;
    }

    public void setLeft(BinaryTree<T> left) {
      this.left = left;
    }

    public BinaryTree<T> getRight() {
      return right;
    }

    public void setRight(BinaryTree<T> right) {
      this.right = right;
    }

    public void setParent(BinaryTree<T> parent) {
      this.parent = parent;
    }
    // @include
  }

  // @exclude

  public static void main(String[] args) {
    BinaryTree<Integer> root = new BinaryTree<Integer>();
    root.setLeft(new BinaryTree<Integer>());
    root.getLeft().setParent(root);
    root.setRight(new BinaryTree<Integer>());
    root.getRight().setParent(root);
    root.getLeft().setLeft(new BinaryTree<Integer>());
    root.getLeft().getLeft().setParent(root.getLeft());
    root.getLeft().setRight(new BinaryTree<Integer>());
    root.getLeft().getRight().setParent(root.getLeft());
    // Should output false.
    assert (!root.isLock());
    System.out.println(root.isLock());
    root.lock();
    // Should output true.
    assert (root.isLock());
    System.out.println(root.isLock());
    root.unlock();
    root.getLeft().lock();
    root.lock();
    // Should output false.
    assert (!root.isLock());
    System.out.println(root.isLock());
    root.getRight().lock();
    // Should output true.
    assert (root.getRight().isLock());
    System.out.println(root.isLock());
  }
}
