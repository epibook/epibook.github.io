package com.epi;

import java.io.*;
import java.util.*;

public class OnlineMedian {
  private static List<Double> globalResult = new ArrayList<>();

  // @include
  public static void onlineMedian(InputStream sequence) {
    // minHeap stores the larger half seen so far.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // maxHeap stores the smaller half seen so far.
    PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(11, Collections.reverseOrder());

    Scanner s = new Scanner(sequence);
    while (s.hasNextInt()) {
      int x = s.nextInt();
      if (minHeap.isEmpty()) {
        // This is the very first element.
        minHeap.add(x);
      } else {
        if (x >= minHeap.peek()) {
          minHeap.add(x);
        } else {
          maxHeap.add(x);
        }
      }
      // Ensure minHeap and maxHeap should have equal number of elements
      // if even number of elements are read, otherwise, minHeap should have
      // one more element.
      if (minHeap.size() > maxHeap.size() + 1) {
        maxHeap.add(minHeap.remove());
      } else if (maxHeap.size() > minHeap.size()) {
        minHeap.add(maxHeap.remove());
      }

      // @exclude
      globalResult.add((minHeap.size() == maxHeap.size()
                            ? 0.5 * (minHeap.peek() + maxHeap.peek())
                            : minHeap.peek()));
      // @include
      System.out.println(minHeap.size() == maxHeap.size()
                             ? 0.5 * (minHeap.peek() + maxHeap.peek())
                             : minHeap.peek());
    }
  }
  // @exclude

  private static ByteArrayInputStream generateStream(List<Integer> stream) {
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
    return new ByteArrayInputStream(baos.toByteArray());
  }

  private static void SmallTest() {
    List<Integer> stream = Arrays.asList(5, 4, 3, 2, 1);
    onlineMedian(generateStream(stream));
    List<Double> golden = Arrays.asList(5.0, 4.5, 4.0, 3.5, 3.0);
    assert golden.equals(globalResult);

    globalResult.clear();
    stream = Arrays.asList(1, 2, 3, 4, 5);
    onlineMedian(generateStream(stream));
    golden = Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0);
    assert golden.equals(globalResult);

    globalResult.clear();
    stream = Arrays.asList(1, 0, 3, 5, 2, 0, 1);
    onlineMedian(generateStream(stream));
    golden = Arrays.asList(1.0, 0.5, 1.0, 2.0, 2.0, 1.5, 1.0);
    assert golden.equals(globalResult);
  }

  public static void main(String[] args) {
    SmallTest();
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
