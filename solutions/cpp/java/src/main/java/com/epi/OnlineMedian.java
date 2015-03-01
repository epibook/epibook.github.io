package com.epi;

import java.io.*;
import java.util.*;

public class OnlineMedian {
  // @include
  public static void onlineMedian(InputStream sequence) {
    // minHeap stores the larger half seen so far.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // maxHeap stores the smaller half seen so far.
    PriorityQueue<Integer> maxHeap
        = new PriorityQueue<>(11, Collections.reverseOrder());

    Scanner s = new Scanner(sequence);
    while (s.hasNextInt()) {
      minHeap.add(s.nextInt());
      if (minHeap.size() > maxHeap.size() + 1) {
        maxHeap.add(minHeap.remove());
      }

      System.out.println(minHeap.size() == maxHeap.size() ?
          0.5 * (minHeap.peek() + maxHeap.peek()) : minHeap.peek());
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int num;
    if (args.length == 1) {
      num = Integer.parseInt(args[0]);
    } else {
      num = r.nextInt(100000) + 1;
    }
    List<Integer> stream = new ArrayList<>();
    for (int i = 0; i < num; ++i) {
      stream.add(r.nextInt(10000) + 1);
    }
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
    onlineMedian(bais);
  }
}
