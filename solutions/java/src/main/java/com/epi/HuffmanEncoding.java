package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

public class HuffmanEncoding {
  private static final double[] ENGLISH_FREQ
      = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966,
         0.153, 0.772, 4.025, 2.406, 6.749,  7.507, 1.929, 0.095, 5.987,
         6.327, 9.056, 2.758, 0.978, 2.360,  0.150, 1.974, 0.074};

  // @include
  public static class Symbol {
    public char c;
    public double prob;
    public String code;
  }

  public static class BinaryTree implements Comparable<BinaryTree> {
    public double prob;
    public Symbol s;
    public BinaryTree left, right;

    public BinaryTree(double prob, Symbol s, BinaryTree left,
                      BinaryTree right) {
      this.prob = prob;
      this.s = s;
      this.left = left;
      this.right = right;
    }

    // clang-format off
    @Override
    public int compareTo(BinaryTree o) { return Double.compare(prob, o.prob); }
    // clang-format on

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof BinaryTree)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      BinaryTree that = (BinaryTree)obj;
      return this.prob == that.prob;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(prob); }
    // clang-format on
  }

  public static void huffmanEncoding(List<Symbol> symbols) {
    // Initially assigns each symbol into minHeap.
    PriorityQueue<BinaryTree> minHeap = new PriorityQueue<>();
    for (Symbol s : symbols) {
      minHeap.add(new BinaryTree(s.prob, s, null, null));
    }

    // Keeps combining two nodes until there is one node left.
    while (minHeap.size() > 1) {
      BinaryTree l = minHeap.remove();
      BinaryTree r = minHeap.remove();
      minHeap.add(new BinaryTree(l.prob + r.prob, null, l, r));
    }

    // Traverses the binary tree, assigning codes to nodes.
    assignHuffmanCode(minHeap.peek(), "");
  }

  private static void assignHuffmanCode(BinaryTree r, String code) {
    if (r != null) {
      // This node (i.e., leaf) contains a symbol.
      if (r.s != null) {
        r.s.code = code;
      } else { // Non-leaf node.
        assignHuffmanCode(r.left, code + "0");
        assignHuffmanCode(r.right, code + "1");
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    int n;
    Random r = new Random();
    if (args.length == 1) {
      if (!"huffman".equals(args[0])) {
        n = Integer.parseInt(args[0]);
      } else {
        n = 26;
      }
    } else {
      n = r.nextInt(255) + 1;
    }
    List<Symbol> symbols = new ArrayList<>();
    int sum = 0;
    if (args.length == 0 || (!"huffman".equals(args[0]))) {
      for (int i = 0; i < n; ++i) {
        Symbol t = new Symbol();
        t.c = (char)i;
        t.prob = r.nextInt(100001);
        sum += t.prob;
        symbols.add(t);
      }
      for (int i = 0; i < n; ++i) {
        symbols.get(i).prob /= sum;
      }
    } else {
      for (int i = 0; i < n; ++i) {
        Symbol t = new Symbol();
        t.c = (char)('a' + i);
        t.prob = ENGLISH_FREQ[i];
        symbols.add(t);
      }
    }
    huffmanEncoding(symbols);
    double avg = 0.0;
    for (Symbol symbol : symbols) {
      System.out.println(symbol.c);
      avg += symbol.prob / 100 * symbol.code.length();
    }
    System.out.println("average huffman code length = " + avg);
  }
}
