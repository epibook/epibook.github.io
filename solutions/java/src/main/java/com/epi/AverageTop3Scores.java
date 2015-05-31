package com.epi;

import java.io.*;
import java.util.*;

public class AverageTop3Scores {
  // @include
  public static String findStudentWithHighestBestOfThreeScores(InputStream ifs) {
    Map<String, PriorityQueue<Integer>> studentScores = new HashMap<>();
    try {
      ObjectInputStream ois = new ObjectInputStream(ifs);
      while (true) {
        String name = ois.readUTF();
        int score = ois.readInt();
        PriorityQueue<Integer> scores = studentScores.get(name);
        if (scores == null) {
          scores = new PriorityQueue<>();
        }
        scores.add(score);
        studentScores.put(name, scores);
      }
    } catch (IOException e) {
    }

    String topStudent = "no such student";
    int currentTopThreeScoresSum = 0;
    for (Map.Entry<String, PriorityQueue<Integer>> scores :
         studentScores.entrySet()) {
      if (scores.getValue().size() == 3) {
        int currentScoresSum = getTopThreeScoresSum(scores.getValue());
        if (currentScoresSum > currentTopThreeScoresSum) {
          currentTopThreeScoresSum = currentScoresSum;
          topStudent = scores.getKey();
        }
      }
    }
    return topStudent;
  }

  // Returns the sum of top three scores.
  private static int getTopThreeScoresSum(PriorityQueue<Integer> scores) {
    Iterator<Integer> it = scores.iterator();
    int result = 0;
    while (it.hasNext()) {
      result += it.next();
    }
    return result;
  }
  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'A'));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    try {
      OutputStream ofs = new FileOutputStream("scores.txt");
      ObjectOutputStream oos = new ObjectOutputStream(ofs);
      for (int i = 0; i < n; ++i) {
        int testNum = r.nextInt(21);
        String name = randString(r.nextInt(6) + 5);
        while (testNum-- > 0) {
          oos.writeUTF(name);
          oos.writeInt(r.nextInt(101));
        }
      }
      ofs.close();
    } catch (Exception e) {
      System.out.println("Error creating scores.txt: " + e.getMessage());
    }
    try {
      InputStream ifs = new FileInputStream("scores.txt");
      String name = findStudentWithHighestBestOfThreeScores(ifs);
      System.out.println("top student is " + name);
      ifs.close();
    } catch (Exception e) {
      System.out.println("Error reading scores.txt: " + e.getMessage());
    }
  }
}
