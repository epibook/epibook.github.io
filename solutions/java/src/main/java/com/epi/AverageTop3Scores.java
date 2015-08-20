package com.epi;

import java.io.*;
import java.util.*;

public class AverageTop3Scores {
  // @include
  public static String findStudentWithHighestBestOfThreeScores(ObjectInputStream ois) {
    Map<String, PriorityQueue<Integer>> studentScores = new HashMap<>();
    try {
      while (true) {
        String name = (String) ois.readObject();
        Integer score = (Integer) ois.readObject();
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
    } catch (IOException e) {
    } catch (ClassNotFoundException e) {
    }

    String topStudent = "no such student";
    int currentTopThreeScoresSum = 0;
    for (Map.Entry<String, PriorityQueue<Integer>> scores :
         studentScores.entrySet()) {
      System.out.println(scores.getKey());
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

  private static void SimpleTest() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ByteArrayInputStream sin = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try {
      oos = new ObjectOutputStream(baos);
      oos.writeObject("adnan");
      oos.writeObject(100);
      oos.writeObject("amit");
      oos.writeObject(99);
      oos.writeObject("adnan");
      oos.writeObject(98);
      oos.writeObject("thl");
      oos.writeObject(90);
      oos.writeObject("adnan");
      oos.writeObject(10);
      oos.writeObject("amit");
      oos.writeObject(100);
      oos.writeObject("thl");
      oos.writeObject(99);
      oos.writeObject("thl");
      oos.writeObject(95);
      oos.writeObject("adnan");
      oos.writeObject(99);
      oos.close();
      sin = new ByteArrayInputStream(baos.toByteArray());
      ois = new ObjectInputStream(sin);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
    String result = findStudentWithHighestBestOfThreeScores(ois);
    System.out.println("result = " + result);
    assert("adnan".equals(result));
  }

  public static void main(String[] args) {
    SimpleTest();
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
      ObjectInputStream ois = new ObjectInputStream(ifs);
      String name = findStudentWithHighestBestOfThreeScores(ois);
      System.out.println("top student is " + name);
      ifs.close();
    } catch (Exception e) {
      System.out.println("Error reading scores.txt: " + e.getMessage());
    }
  }
}
