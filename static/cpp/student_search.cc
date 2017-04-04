// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <functional>
#include <string>
#include <vector>

using std::binary_search;
using std::function;
using std::sort;
using std::string;
using std::vector;

// @include
struct Student {
  string name;
  double grade_point_average;
};

const static function<bool(const Student&, const Student&)> CompGPA = [](
    const Student& a, const Student& b) {
  if (a.grade_point_average != b.grade_point_average) {
    return a.grade_point_average > b.grade_point_average;
  }
  return a.name < b.name;
};

bool SearchStudent(
    const vector<Student>& students, const Student& target,
    const function<bool(const Student&, const Student&)>& comp_GPA) {
  return binary_search(students.begin(), students.end(), target, comp_GPA);
}
// @exclude

int main(int argc, char* argv[]) {
  vector<Student> students = {{"A", 4.0}, {"C", 3.0}, {"B", 2.0}, {"D", 3.2}};
  sort(students.begin(), students.end(), CompGPA);
  assert(SearchStudent(students, {"A", 4.0}, CompGPA));
  assert(!SearchStudent(students, {"A", 3.0}, CompGPA));
  assert(!SearchStudent(students, {"B", 3.0}, CompGPA));
  assert(SearchStudent(students, {"D", 3.2}, CompGPA));
  return 0;
}
