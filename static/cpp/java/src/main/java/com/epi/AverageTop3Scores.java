package com.epi;

import com.epi.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class AverageTop3Scores {
  // @include
  public static String findStudentWithHighestBestOfThreeScores(
      Iterator<Object> nameScoreData) {
    Map<String, PriorityQueue<Integer>> studentScores = new HashMap<>();
    while (nameScoreData.hasNext()) {
      String name = (String)nameScoreData.next();
      Integer score = (Integer)nameScoreData.next();
      PriorityQueue<Integer> scores = studentScores.get(name);
      if (scores == null) {
        scores = new PriorityQueue<>();
        studentScores.put(name, scores);
      }
      scores.add(score);
      if (scores.size() > 3) {
        scores.poll(); // Only keep the top 3 scores.
      }
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

  private static void simpleTest() {
    List<Object> nameScoreData = new ArrayList<>();
    nameScoreData.add("adnan");
    nameScoreData.add(100);
    nameScoreData.add("amit");
    nameScoreData.add(99);
    nameScoreData.add("adnan");
    nameScoreData.add(98);
    nameScoreData.add("thl");
    nameScoreData.add(90);
    nameScoreData.add("adnan");
    nameScoreData.add(10);
    nameScoreData.add("amit");
    nameScoreData.add(100);
    nameScoreData.add("thl");
    nameScoreData.add(99);
    nameScoreData.add("thl");
    nameScoreData.add(95);
    nameScoreData.add("adnan");
    nameScoreData.add(99);
    String result
        = findStudentWithHighestBestOfThreeScores(nameScoreData.iterator());
    System.out.println("result = " + result);
    assert("adnan".equals(result));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    List<Object> nameScoreData = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      int testNum = r.nextInt(21);
      String name = randString(r.nextInt(6) + 5);
      while (testNum-- > 0) {
        nameScoreData.add(name);
        nameScoreData.add(r.nextInt(101));
      }
    }
    String name
        = findStudentWithHighestBestOfThreeScores(nameScoreData.iterator());
  }
}
