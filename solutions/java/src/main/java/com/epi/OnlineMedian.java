package com.epi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class OnlineMedian {
  // @include
  public static void onlineMedian(InputStream sin) {
    // Min-heap stores the bigger part of the stream.
    PriorityQueue<Integer> H = new PriorityQueue<Integer>();
    // Max-heap stores the smaller part of the stream.
    PriorityQueue<Integer> L = new PriorityQueue<Integer>(11,
        new Comparator<Integer>() {
          @Override
          public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
          }
        });

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
    ArrayList<Integer> stream = new ArrayList<Integer>();
    for (int i = 0; i < num; ++i) {
      stream.add(r.nextInt(10000) + 1);
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(baos);
    try {
      for (int i = 0; i < stream.size(); ++i) {
        osw.write(stream.get(i) + " ");
      }
      osw.close();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    onlineMedian(bais);
  }
}
