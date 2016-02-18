package com.epi;

import com.epi.utils.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class AverageTop3Scores {
  // @include
  public static String findStudentWithHighestBestOfThreeScores(
      ObjectInputStream ois) throws ClassNotFoundException, IOException {
    Map<String, PriorityQueue<Integer>> studentScores = new HashMap<>();
    try {
      while (true) {
        String name = (String)ois.readObject();
        Integer score = (Integer)ois.readObject();
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
    } catch (EOFException e) {
      // Do nothing since the end of the stream has been reached.
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

  private static void simpleTest() throws ClassNotFoundException, IOException {
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

  public static void main(String[] args)
      throws ClassNotFoundException, IOException {
    simpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    OutputStream ofs = null;
    ObjectOutputStream oos = null;
    try {
      ofs = new FileOutputStream("scores.txt");
      oos = new ObjectOutputStream(ofs);
      for (int i = 0; i < n; ++i) {
        int testNum = r.nextInt(21);
        String name = randString(r.nextInt(6) + 5);
        while (testNum-- > 0) {
          oos.writeUTF(name);
          oos.writeInt(r.nextInt(101));
        }
      }
    } catch (Exception e) {
      System.out.println("Error creating scores.txt: " + e.getMessage());
    } finally {
      Utils.closeSilently(ofs, oos);
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
