package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinaryTreeLock {
  // @include
  public static class BinaryTree {
    private BinaryTree left, right, parent;
    private boolean locked;
    private int numChildrenLocks;

    public boolean isLock() {
      return locked;
    }

    public void lock() {
      if (numChildrenLocks == 0 && !locked) {
        // Make sure all parents do not lock.
        BinaryTree n = parent;
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
        BinaryTree n = parent;
        while (n != null) {
          --n.numChildrenLocks;
          n = n.parent;
        }
      }
    }

    // @exclude
    public BinaryTree getLeft() {
      return left;
    }

    public void setLeft(BinaryTree left) {
      this.left = left;
    }

    public BinaryTree getRight() {
      return right;
    }

    public void setRight(BinaryTree right) {
      this.right = right;
    }

    public void setParent(BinaryTree parent) {
      this.parent = parent;
    }
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
