package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class IsBinaryTreeABSTBFS {
  // @include
  public static class QueueEntry {
    public BinaryTreeNode<Integer> treeNode;
    public Integer lowerBound, upperBound;

    public QueueEntry(BinaryTreeNode<Integer> treeNode, Integer lowerBound,
                      Integer upperBound) {
      this.treeNode = treeNode;
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
    }
  }

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    Queue<QueueEntry> BFSQueue = new LinkedList<>();
    BFSQueue.add(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));

    QueueEntry headEntry;
    while ((headEntry = BFSQueue.poll()) != null) {
      if (headEntry.treeNode != null) {
        if (headEntry.treeNode.data < headEntry.lowerBound
            || headEntry.treeNode.data > headEntry.upperBound) {
          return false;
        }

        BFSQueue.add(new QueueEntry(headEntry.treeNode.left,
                                    headEntry.lowerBound,
                                    headEntry.treeNode.data));
        BFSQueue.add(new QueueEntry(headEntry.treeNode.right,
                                    headEntry.treeNode.data,
                                    headEntry.upperBound));
      }
    }
    return true;
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    // should output true.
    assert isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // 10
    // 2 5
    // 1 4 6
    tree.data = 10;
    // should output false.
    assert !isBinaryTreeBST(tree);
    System.out.println(isBinaryTreeBST(tree));
    // should output true.
    assert isBinaryTreeBST(null);
    System.out.println(isBinaryTreeBST(null));
  }
}
