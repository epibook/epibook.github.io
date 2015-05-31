package com.epi;

import com.epi.utils.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static com.epi.utils.Utils.find;
import static com.epi.utils.Utils.shuffle;

public class StableAssignment {
  // @include
  public static Pair<Integer, Integer>[] findStableAssignment(
      int[][] professorPreference, int[][] studentPreference) {

    // stores currently free students.
    Queue<Integer> freeStudent = new LinkedList<>();
    for (int i = 0; i < studentPreference.length; ++i) {
      freeStudent.add(i);
    }

    // Records the professors that each student have asked.
    int[] studentPrefIdx = new int[studentPreference.length];
    Arrays.fill(studentPrefIdx, 0);

    // Records the current student choice for each professor.
    int[] professorChoice = new int[professorPreference.length];
    Arrays.fill(professorChoice, -1);

    while (!freeStudent.isEmpty()) {
      int i = freeStudent.element(); // free student.
      int j = studentPreference[i][studentPrefIdx[i]]; // target professor.
      if (professorChoice[j] == -1) { // this professor is free.
        professorChoice[j] = i;
        freeStudent.remove();
      } else { // this professor has student now.
        int originalPref = find(professorPreference[j], professorChoice[j]);
        int newPref = find(professorPreference[j], i);
        if (newPref < originalPref) { // this professor prefers the new one.
          freeStudent.add(professorChoice[j]);
          professorChoice[j] = i;
          freeStudent.remove();
        }
      }
      ++studentPrefIdx[i];
    }

    Pair<Integer, Integer>[] matchResult = new Pair[professorChoice.length];
    for (int j = 0; j < professorChoice.length; ++j) {
      matchResult[j] = new Pair<>(professorChoice[j], j);
    }
    return matchResult;
  }
  // @exclude

  static void checkAns(int[][] professor_preference, int[][] student_preference,
                       Pair<Integer, Integer>[] matchResult) {
    assert matchResult.length == professor_preference.length;

    boolean[] professor = new boolean[professor_preference.length];
    boolean[] student = new boolean[student_preference.length];

    for (Pair<Integer, Integer> p : matchResult) {
      student[p.getFirst()] = true;
      professor[p.getSecond()] = true;
    }
    for (boolean p : professor) {
      assert p;
    }
    for (boolean s : student) {
      assert s;
    }

    for (int i = 0; i < matchResult.length; ++i) {
      for (int j = i + 1; j < matchResult.length; ++j) {
        int s0 = matchResult[i].getFirst(), a0 = matchResult[i].getSecond();
        int s1 = matchResult[j].getFirst(), a1 = matchResult[j].getSecond();
        int a0InS0Order = find(student_preference[s0], a0);
        int a1InS0Order = find(student_preference[s0], a1);
        int s0InA1Order = find(professor_preference[a1], s0);
        int s1InA1Order = find(professor_preference[a1], s1);
        assert a0InS0Order < a1InS0Order || s1InA1Order < s0InA1Order;
      }
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.valueOf(args[0]);
      } else {
        n = gen.nextInt(300) + 1;
      }
      int[][] professorPreference = new int[n][n], studentPreference =
                                                       new int[n][n];
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          professorPreference[i][j] = j;
          studentPreference[i][j] = j;
        }
        shuffle(professorPreference[i]);
        shuffle(studentPreference[i]);
      }

      /*
       * for (int i = 0; i < n; ++i) { System.out.println("professor " + i); for
       * (int j = 0; j < n; ++j) { System.out.println(professorPreference[i][j]
       * + " "); } System.out.println(); } for (int i = 0; i < n; ++i) {
       * System.out.println("student " + i); for (int j = 0; j < n; ++j) {
       * System.out.println(studentPreference[i][j] + " "); }
       * System.out.println(); }
       */
      Pair<Integer, Integer>[] res =
          findStableAssignment(professorPreference, studentPreference);

      /*
       * for (int i = 0; i < res.size(); ++i) {
       * System.out.println(res.get(i).getFirst() + ", " +
       * res.get(i).getSecond()); }
       */
      checkAns(professorPreference, studentPreference, res);
    }
  }
}
