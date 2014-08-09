package com.epi;

import java.io.*;
import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class OnlineMedian {
  // @include
  public static void onlineMedian(InputStream sin) {
    // Min-heap stores the bigger part of the stream.
    PriorityQueue<Integer> H = new PriorityQueue<>();
    // Max-heap stores the smaller part of the stream.
    PriorityQueue<Integer> L = new PriorityQueue<>(11,
        new Comparator<Integer>() {
          @Override
          public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
          }
        }
    );

    Scanner s = new Scanner(sin);
    while (s.hasNextInt()) {
      int x = s.nextInt();
      if (!L.isEmpty() && x > L.peek()) {
        H.add(x);
      } else {
        L.add(x);
      }
      if (H.size() > L.size() + 1) {
        L.add(H.remove());
      } else if (L.size() > H.size() + 1) {
        H.add(L.remove());
      }

      if (H.size() == L.size()) {
        System.out.println(0.5 * (H.peek() + L.peek()));
      } else {
        System.out.println((H.size() > L.size() ? H.peek() : L.peek()));
      }
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
