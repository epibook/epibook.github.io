package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.LinkedList;

public class IsBinaryTreeABSTBFS {
  // @include
  public static class QueueEntry {
    public BinaryTreeNode<Integer> treeNode;
    public Integer lowerBound, upperBound;

    public QueueEntry(BinaryTreeNode<Integer> treeNode, Integer lowerBound, Integer upperBound) {
      this.treeNode = treeNode;
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
    }
  }

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    LinkedList<QueueEntry> BFSQueue = new LinkedList<>();
    BFSQueue.addLast(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));

    while (!BFSQueue.isEmpty()) {
      if (BFSQueue.getFirst().treeNode != null) {
        if (BFSQueue.getFirst().treeNode.getData() < BFSQueue.getFirst().lowerBound ||
            BFSQueue.getFirst().treeNode.getData() > BFSQueue.getFirst().upperBound) {
          return false;
        }

        BFSQueue.addLast(new QueueEntry(BFSQueue.getFirst().treeNode.getLeft(),
                                   BFSQueue.getFirst().lowerBound,
                                   BFSQueue.getFirst().treeNode.getData()));
        BFSQueue.addLast(new QueueEntry(BFSQueue.getFirst().treeNode.getRight(),
                                   BFSQueue.getFirst().treeNode.getData(),
                                   BFSQueue.getFirst().upperBound));
      }
      BFSQueue.removeFirst();
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.setLeft(new BinaryTreeNode<>(2));
    tree.getLeft().setLeft(new BinaryTreeNode<>(1));
    tree.setRight(new BinaryTreeNode<>(5));
    tree.getRight().setLeft(new BinaryTreeNode<>(4));
    tree.getRight().setRight(new BinaryTreeNode<>(6));
    // should output true.
    assert isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.setData(10);
    // should output false.
    assert !isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // should output true.
    assert isBinaryTreeBST(null);
    System.out.println(isBinaryTreeBST(null));
  }
}
