// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <string>
#include <vector>

using std::sort;
using std::string;
using std::vector;

// @include
struct Student {
  bool operator<(const Student& that) const { return name < that.name; }

  string name;
  double grade_point_average;
};

void SortByGPA(vector<Student>* students) {
  sort(students->begin(), students->end(),
       [](const Student& a, const Student& b) {
         return a.grade_point_average >= b.grade_point_average;
       });
}

void SortByName(vector<Student>* students) {
  // Rely on the operator< defined in Student.
  sort(students->begin(), students->end());
}
// @exclude

int main(int argc, char* argv[]) {
  vector<Student> students = {{"A", 4.0}, {"C", 3.0}, {"B", 2.0}, {"D", 3.2}};
  SortByName(&students);
  assert(is_sorted(students.begin(), students.end()));
  SortByGPA(&students);
  assert(is_sorted(students.begin(), students.end(),
                   [](const Student& a, const Student& b) {
                     return a.grade_point_average >= b.grade_point_average;
                   }));
  return 0;
}
