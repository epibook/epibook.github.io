package com.epi;

import java.io.*;
import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class KthElementStreaming {
  // @include
  public static void findKthLargestStream(InputStream sin, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // The first k elements, output the minimum element.
    Scanner s = new Scanner(sin);
    for (int i = 0; i < k && s.hasNextInt(); ++i) {
      int x = s.nextInt();
      minHeap.add(x);
      System.out.println(minHeap.peek());
    }

    // After the first k elements, output the k-th largest one.
    while (s.hasNextInt()) {
      int x = s.nextInt();
      if (minHeap.peek() < x) {
        minHeap.remove();
        minHeap.add(x);
      }
      System.out.println(minHeap.peek());
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int num, k;
    if (args.length == 1) {
      num = Integer.parseInt(args[0]);
      k = r.nextInt(num) + 1;
    } else if (args.length == 2) {
      num = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      num = r.nextInt(101);
      k = r.nextInt(num) + 1;
    }
    List<Integer> stream = new ArrayList<>();
    for (int i = 0; i < num; ++i) {
      stream.add(r.nextInt(10000000));
    }
    Collections.sort(stream);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(baos);
    try {
      for (Integer aStream : stream) {
        osw.write(aStream + " ");
      }
      osw.close();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    findKthLargestStream(bais, k);
    System.out.println("n = " + num + ", k = " + k);
  }
}
