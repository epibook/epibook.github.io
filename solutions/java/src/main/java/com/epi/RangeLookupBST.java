package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;
import com.epi.utils.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RangeLookupBST {
  // @include
  public static List<Integer> RangeLookupBST(BSTNode<Integer> tree,
                                             Interval interval) {
    List<Integer> result = new ArrayList<>();
    RangeLookupBSTHelper(tree, interval, result);
    return result;
  }

  public static void RangeLookupBSTHelper(BSTNode<Integer> tree,
                                          Interval interval,
                                          List<Integer> result) {
    if (tree == null) {
      return;
    }
    if (interval.left <= tree.getData() && tree.getData() <= interval.right) {
      // tree.getData() lies in the interval.
      RangeLookupBSTHelper(tree.getLeft(), interval, result);
      result.add(tree.getData());
      RangeLookupBSTHelper(tree.getRight(), interval, result);
    } else if (interval.left > tree.getData()) {
      RangeLookupBSTHelper(tree.getRight(), interval, result);
    } else { // interval.right >= tree.getData()
      RangeLookupBSTHelper(tree.getLeft(), interval, result);
    }
  }
  // @exclude

  public static void main(String[] args) {
    //          19
    //     7          43
    //   3   11    23   47
    // 2  5    17   37    53
    //        13  29  41
    //             31
    BSTNode<Integer> tree = new BSTNode<>(19);
    tree.setLeft(new BSTNode<>(7));
    tree.getLeft().setLeft(new BSTNode<>(3));
    tree.getLeft().getLeft().setLeft(new BSTNode<>(2));
    tree.getLeft().getLeft().setRight(new BSTNode<>(5));
    tree.getLeft().setRight(new BSTNode<>(11));
    tree.getLeft().getRight().setRight(new BSTNode<>(17));
    tree.getLeft().getRight().getRight().setLeft(new BSTNode<>(13));
    tree.setRight(new BSTNode<>(43));
    tree.getRight().setLeft(new BSTNode<>(23));
    tree.getRight().getLeft().setRight(new BSTNode<>(37));
    tree.getRight().getLeft().getRight().setLeft(new BSTNode<>(29));
    tree.getRight().getLeft().getRight().getLeft().setRight(new BSTNode<>(31));
    tree.getRight().getLeft().getRight().setRight(new BSTNode<>(41));
    tree.getRight().setRight(new BSTNode<>(47));
    tree.getRight().getRight().setRight(new BSTNode<>(53));
    Interval interval = new Interval(16, 31);
    List<Integer> result = RangeLookupBST(tree, interval);
    Collections.sort(result);
    List<Integer> goldenResult = Arrays.asList(17, 19, 23, 29, 31);
    assert result.equals(goldenResult);
  }
}
