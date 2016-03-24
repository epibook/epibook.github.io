// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSearch {
  // @include
  public static class Student {
    public String name;
    public double gradePointAverage;

    Student(String name, double gradePointAverage) {
      this.name = name;
      this.gradePointAverage = gradePointAverage;
    }
  }

  private static final Comparator<Student> compGPA = new Comparator<Student>() {
    @Override
    public int compare(Student a, Student b) {
      if (a.gradePointAverage != b.gradePointAverage) {
        return Double.compare(a.gradePointAverage, b.gradePointAverage);
      }
      return a.name.compareTo(b.name);
    }
  };

  public static boolean searchStudent(List<Student> students, Student target,
                                      Comparator<Student> compGPA) {
    return Collections.binarySearch(students, target, compGPA) >= 0;
  }
  // @exclude

  public static void main(String[] args) {
    List<Student> students
        = Arrays.asList(new Student("A", 4.0), new Student("C", 3.0),
                        new Student("B", 2.0), new Student("D", 3.2));
    Collections.sort(students, compGPA);
    assert(searchStudent(students, new Student("A", 4.0), compGPA));
    assert(!searchStudent(students, new Student("A", 3.0), compGPA));
    assert(!searchStudent(students, new Student("B", 3.0), compGPA));
    assert(searchStudent(students, new Student("D", 3.2), compGPA));
  }
}
