package com.epi;

import com.epi.BinaryTreePrototypeTemplate.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epi.BinaryTreeUtils.generateInOrder;

public class UniqueBinaryTreesAll {
  // @include
  public static List<BinaryTree<Integer>> generateAllBinaryTrees(int n) {
    return generateAllBinaryTreesHelper(1, n);
  }

  private static List<BinaryTree<Integer>> generateAllBinaryTreesHelper(
      int start, int end) {
    List<BinaryTree<Integer>> result = new ArrayList<>();
    if (start > end) {
      result.add(null);
      return result;
    }

    for (int i = start; i <= end; ++i) {
      // Tries all possible combinations of left subtrees and right subtrees.
      List<BinaryTree<Integer>> leftresult = generateAllBinaryTreesHelper(start,
          i - 1);
      List<BinaryTree<Integer>> rightresult = generateAllBinaryTreesHelper(
          i + 1, end);
      for (BinaryTree<Integer> left : leftresult) {
        for (BinaryTree<Integer> right : rightresult) {
          result.add(new BinaryTree<>(i, left, right));
        }
      }
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10) + 1;
    }
    System.out.println("n = " + n);
    List<BinaryTree<Integer>> result = generateAllBinaryTrees(n);
    for (BinaryTree<Integer> tree : result) {
      List<Integer> sequence = generateInOrder(tree);
      for (int i = 1; i < sequence.size(); i++) {
        assert (sequence.get(i - 1) < sequence.get(i));
      }
    }
  }
}
