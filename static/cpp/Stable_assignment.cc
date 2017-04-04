// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <deque>
#include <iostream>
#include <iterator>
#include <numeric>
#include <queue>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::queue;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
struct ProfessorStudentPairing {
  int professor, student;
};

vector<ProfessorStudentPairing> find_stable_assignment(
    const vector<vector<int>>& professor_preference,
    const vector<vector<int>>& student_preference) {
  queue<int> free_student;  // stores currently free students.
  for (int i = 0; i < student_preference.size(); ++i) {
    free_student.emplace(i);
  }

  // Records the professors that each student have asked.
  vector<int> student_pref_idx(student_preference.size(), 0);
  // Records the current student choice for each professor.
  vector<int> professor_choice(professor_preference.size(), -1);

  while (!free_student.empty()) {
    int i = free_student.front();  // free student.
    int j = student_preference[i][student_pref_idx[i]];  // target professor.
    if (professor_choice[j] == -1) {  // this professor is free.
      professor_choice[j] = i;
      free_student.pop();
    } else {  // this professor has student now.
      auto original_pref =
          distance(professor_preference[j].cbegin(),
                   find(professor_preference[j].cbegin(),
                        professor_preference[j].cend(), professor_choice[j]));
      auto new_pref = distance(professor_preference[j].cbegin(),
                               find(professor_preference[j].cbegin(),
                                    professor_preference[j].cend(), i));
      if (new_pref < original_pref) {  // this professor prefers the new one.
        free_student.emplace(professor_choice[j]);
        professor_choice[j] = i;
        free_student.pop();
      }
    }
    ++student_pref_idx[i];
  }

  vector<ProfessorStudentPairing> match_result;
  for (int j = 0; j < professor_choice.size(); ++j) {
    match_result.emplace_back(
        ProfessorStudentPairing{professor_choice[j], j});
  }
  return match_result;
}
// @exclude

void check_ans(const vector<vector<int>>& professor_preference,
               const vector<vector<int>>& student_preference,
               const vector<ProfessorStudentPairing>& match_result) {
  assert(match_result.size() == professor_preference.size());
  deque<bool> professor(professor_preference.size(), false),
      student(student_preference.size(), false);
  for (const ProfessorStudentPairing& p : match_result) {
    student[p.professor] = true;
    professor[p.student] = true;
  }
  for (auto p : professor) {
    assert(p);
  }
  for (auto s : student) {
    assert(s);
  }

  for (int i = 0; i < match_result.size(); ++i) {
    for (int j = i + 1; j < match_result.size(); ++j) {
      int s0 = match_result[i].professor, a0 = match_result[i].student;
      int s1 = match_result[j].professor, a1 = match_result[j].student;
      int a0_in_s0_order = distance(student_preference[s0].cbegin(),
                                    find(student_preference[s0].cbegin(),
                                         student_preference[s0].cend(), a0));
      int a1_in_s0_order = distance(student_preference[s0].cbegin(),
                                    find(student_preference[s0].cbegin(),
                                         student_preference[s0].cend(), a1));
      int s0_in_a1_order =
          distance(professor_preference[a1].cbegin(),
                   find(professor_preference[a1].cbegin(),
                        professor_preference[a1].cend(), s0));
      int s1_in_a1_order =
          distance(professor_preference[a1].cbegin(),
                   find(professor_preference[a1].cbegin(),
                        professor_preference[a1].cend(), s1));
      assert(a0_in_s0_order < a1_in_s0_order ||
             s1_in_a1_order < s0_in_a1_order);
    }
  }
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 300);
      n = dis(gen);
    }
    vector<vector<int>> professor_preference(n), student_preference(n);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        professor_preference[i].emplace_back(j);
        student_preference[i].emplace_back(j);
      }
      random_shuffle(professor_preference[i].begin(),
                     professor_preference[i].end());
      random_shuffle(student_preference[i].begin(),
                     student_preference[i].end());
    }

    /*
    for (int i = 0; i < n; ++i) {
      cout << "professor " << i << endl;
      for (int j = 0; j < n; ++j) {
        cout << professor_preference[i][j] << " ";
      }
      cout << endl;
    }
    for (int i = 0; i < n; ++i) {
      cout << "student " << i << endl;
      for (int j = 0; j < n; ++j) {
        cout << student_preference[i][j] << " ";
      }
      cout << endl;
    }
    */

    vector<ProfessorStudentPairing> res =
        find_stable_assignment(professor_preference, student_preference);
    /*
    for (int i = 0; i < res.size(); ++i) {
      cout << res[i].professor << ", " << res[i].student << endl;
    }
    */
    check_ans(professor_preference, student_preference, res);
  }
  return 0;
}
