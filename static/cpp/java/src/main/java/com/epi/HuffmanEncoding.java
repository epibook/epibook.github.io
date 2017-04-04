package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

public class HuffmanEncoding {
  private static final double[] ENGLISH_FREQ
      = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966,
         0.153, 0.772, 4.025, 2.406, 6.749,  7.507, 1.929, 0.095, 5.987,
         6.327, 9.056, 2.758, 0.978, 2.360,  0.150, 1.974, 0.074};

  // @include
  public static class CharWithFrequency {
    public char c;
    public double freq;
    public String code;
  }

  public static class BinaryTree implements Comparable<BinaryTree> {
    public double aggregateFreq;
    public CharWithFrequency s;
    public BinaryTree left, right;

    public BinaryTree(double aggregateFreq, CharWithFrequency s,
                      BinaryTree left, BinaryTree right) {
      this.aggregateFreq = aggregateFreq;
      this.s = s;
      this.left = left;
      this.right = right;
    }

    // clang-format off
    @Override
    public int compareTo(BinaryTree o) { 
      return Double.compare(aggregateFreq, o.aggregateFreq); 
    }
    // clang-format on

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof BinaryTree)) {
        return false;
      }
      return this == obj ? true
                         : aggregateFreq == ((BinaryTree)obj).aggregateFreq;
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(aggregateFreq); }
    // clang-format on
  }

  public static Map<Character, String> huffmanEncoding(
      List<CharWithFrequency> symbols) {
    PriorityQueue<BinaryTree> candidates = new PriorityQueue<>();
    // Add leaves for symbols.
    for (CharWithFrequency s : symbols) {
      candidates.add(new BinaryTree(s.freq, s, null, null));
    }

    // Keeps combining two nodes until there is one node left, which is the
    // root.
    while (candidates.size() > 1) {
      BinaryTree left = candidates.remove();
      BinaryTree right = candidates.remove();
      candidates.add(new BinaryTree(left.aggregateFreq + right.aggregateFreq,
                                    null, left, right));
    }

    Map<Character, String> huffmanEncoding = new HashMap<>();
    // Traverses the binary tree, assigning codes to nodes.
    assignHuffmanCode(candidates.peek(), new StringBuilder(), huffmanEncoding);
    return huffmanEncoding;
  }

  private static void assignHuffmanCode(
      BinaryTree tree, StringBuilder code,
      Map<Character, String> huffmanEncoding) {
    if (tree != null) {
      if (tree.s != null) {
        // This node is a leaf.
        huffmanEncoding.put(tree.s.c, code.toString());
      } else { // Non-leaf node.
        code.append('0');
        assignHuffmanCode(tree.left, code, huffmanEncoding);
        code.setLength(code.length() - 1);
        code.append('1');
        assignHuffmanCode(tree.right, code, huffmanEncoding);
        code.setLength(code.length() - 1);
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
    List<CharWithFrequency> symbols = new ArrayList<>();
    int sum = 0;
    if (args.length == 0 || (!"huffman".equals(args[0]))) {
      for (int i = 0; i < n; ++i) {
        CharWithFrequency t = new CharWithFrequency();
        t.c = (char)i;
        t.freq = r.nextInt(100001);
        sum += t.freq;
        symbols.add(t);
      }
      for (int i = 0; i < n; ++i) {
        symbols.get(i).freq /= sum;
      }
    } else {
      for (int i = 0; i < n; ++i) {
        CharWithFrequency t = new CharWithFrequency();
        t.c = (char)('a' + i);
        t.freq = ENGLISH_FREQ[i];
        symbols.add(t);
      }
    }
    Map<Character, String> result = huffmanEncoding(symbols);
    double avg = 0.0;
    for (CharWithFrequency symbol : symbols) {
      System.out.println(symbol.c + " " + symbol.freq + " "
                         + result.get(symbol.c));
      avg += symbol.freq / 100 * result.get(symbol.c).length();
    }
    System.out.println("average huffman code length = " + avg);
  }
}
