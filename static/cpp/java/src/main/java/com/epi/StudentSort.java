// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSort {
  // @include
  public static class Student implements Comparable<Student> {
    public String name;
    public double gradePointAverage;

    public int compareTo(Student that) { return name.compareTo(that.name); }

    Student(String name, double gradePointAverage) {
      this.name = name;
      this.gradePointAverage = gradePointAverage;
    }
  }

  public static void sortByGPA(List<Student> students) {
    Collections.sort(
        students, Collections.reverseOrder(new Comparator<Student>() {
          @Override
          public int compare(Student a, Student b) {
            return Double.compare(a.gradePointAverage, b.gradePointAverage);
          }
        }));
  }

  public static void sortByName(List<Student> students) {
    Collections.sort(students);
  }
  // @exclude

  public static void main(String[] args) {
    List<Student> students
        = Arrays.asList(new Student("A", 4.0), new Student("C", 3.0),
                        new Student("B", 2.0), new Student("D", 3.2));
    sortByName(students);
    for (int i = 1; i < students.size(); ++i) {
      assert(students.get(i - 1).name.compareTo(students.get(i).name) <= 0);
    }
    sortByGPA(students);
    for (int i = 1; i < students.size(); ++i) {
      assert(students.get(i - 1).gradePointAverage
             >= students.get(i).gradePointAverage);
    }
  }
}
