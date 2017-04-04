package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import static com.epi.utils.Utils.shuffle;

public class StableAssignment {
  // @include
  private static class ProfessorStudentPairing {
    public Integer professor;
    public Integer student;

    public ProfessorStudentPairing(Integer professor, Integer student) {
      this.professor = professor;
      this.student = student;
    }
  }

  public static ProfessorStudentPairing[] findStableAssignment(
      int[][] professorPreference, int[][] studentPreference) {
    // stores currently free students.
    Queue<Integer> freeStudent = new LinkedList<>();
    for (int i = 0; i < studentPreference.length; ++i) {
      freeStudent.add(i);
    }

    // Records the professors that each student have asked.
    List<Integer> studentPrefIdx
        = new ArrayList<>(Collections.nCopies(studentPreference.length, 0));

    // Records the current student choice for each professor.
    List<Integer> professorChoice
        = new ArrayList<>(Collections.nCopies(professorPreference.length, -1));

    while (!freeStudent.isEmpty()) {
      int i = freeStudent.element(); // free student.
      int j = studentPreference[i][studentPrefIdx.get(i)]; // target professor.
      if (professorChoice.get(j) == -1) { // this professor is free.
        professorChoice.set(j, i);
        freeStudent.remove();
      } else { // this professor has student now.
        int originalPref = find(professorPreference[j], professorChoice.get(j));
        int newPref = find(professorPreference[j], i);
        if (newPref < originalPref) { // this professor prefers the new one.
          freeStudent.add(professorChoice.get(j));
          professorChoice.set(j, i);
          freeStudent.remove();
        }
      }
      studentPrefIdx.set(i, studentPrefIdx.get(i) + 1);
    }

    ProfessorStudentPairing[] matchResult
        = new ProfessorStudentPairing[professorChoice.size()];
    for (int j = 0; j < professorChoice.size(); ++j) {
      matchResult[j] = new ProfessorStudentPairing(professorChoice.get(j), j);
    }
    return matchResult;
  }

  public static int find(int[] array, int x) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == x) {
        return i;
      }
    }

    return -1;
  }

  // @exclude

  private static void checkAns(int[][] professorPreference,
                               int[][] studentPreference,
                               ProfessorStudentPairing[] matchResult) {
    assert(matchResult.length == professorPreference.length);

    boolean[] professor = new boolean[professorPreference.length];
    boolean[] student = new boolean[studentPreference.length];

    for (ProfessorStudentPairing p : matchResult) {
      student[p.professor] = true;
      professor[p.student] = true;
    }
    for (boolean p : professor) {
      assert p;
    }
    for (boolean s : student) {
      assert s;
    }

    for (int i = 0; i < matchResult.length; ++i) {
      for (int j = i + 1; j < matchResult.length; ++j) {
        int s0 = matchResult[i].professor, a0 = matchResult[i].student;
        int s1 = matchResult[j].professor, a1 = matchResult[j].student;
        int a0InS0Order = find(studentPreference[s0], a0);
        int a1InS0Order = find(studentPreference[s0], a1);
        int s0InA1Order = find(professorPreference[a1], s0);
        int s1InA1Order = find(professorPreference[a1], s1);
        assert a0InS0Order < a1InS0Order || s1InA1Order < s0InA1Order;
      }
    }
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(300) + 1;
      }
      int[][] professorPreference = new int[n][n], studentPreference
                                                   = new int[n][n];
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
      ProfessorStudentPairing[] res
          = findStableAssignment(professorPreference, studentPreference);

      /*
       * for (int i = 0; i < res.size(); ++i) {
       * System.out.println(res.get(i).getFirst() + ", " +
       * res.get(i).getSecond()); }
       */
      checkAns(professorPreference, studentPreference, res);
    }
  }
}
