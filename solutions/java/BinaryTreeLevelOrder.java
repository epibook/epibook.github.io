import com.epi.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrder {
  // @include
  public static List<List<Integer>> binaryTreeDepthOrder(
      BinaryTreeNode<Integer> tree) {
    Queue<BinaryTreeNode<Integer>> currDepthNodes = new LinkedList<>();
    currDepthNodes.add(tree);
    List<List<Integer>> result = new ArrayList<>();

    while (!currDepthNodes.isEmpty()) {
      Queue<BinaryTreeNode<Integer>> nextDepthNodes = new LinkedList<>();
      List<Integer> thisLevel = new ArrayList<>();
      while (!currDepthNodes.isEmpty()) {
        BinaryTreeNode<Integer> curr = currDepthNodes.poll();
        if (curr != null) {
          thisLevel.add(curr.data);

          // Defer the null checks to the null test above.
          nextDepthNodes.add(curr.left);
          nextDepthNodes.add(curr.right);
        }
      }

      if (!thisLevel.isEmpty()) {
        result.add(thisLevel);
      }
      currDepthNodes = nextDepthNodes;
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    //      3
    //    2   5
    //  1    4 6
    // 10
    // 13
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
    tree.left = new BinaryTreeNode<>(2);
    tree.left.left = new BinaryTreeNode<>(1);
    tree.left.left.left = new BinaryTreeNode<>(10);
    tree.left.left.left.right = new BinaryTreeNode<>(13);
    tree.right = new BinaryTreeNode<>(5);
    tree.right.left = new BinaryTreeNode<>(4);
    tree.right.right = new BinaryTreeNode<>(6);
    List<List<Integer>> result = binaryTreeDepthOrder(tree);
    List<List<Integer>> goldenRes = Arrays.asList(
        Arrays.asList(3), Arrays.asList(2, 5), Arrays.asList(1, 4, 6),
        Arrays.asList(10), Arrays.asList(13));
    if (!goldenRes.equals(result)) {
      System.err.println("Failed on input " + tree);
      System.err.println("Expected " + goldenRes);
      System.err.println("Your code produced " + result);
      System.exit(-1);
    } else {
      System.out.println("You passed all tests.");
    }
  }
}
