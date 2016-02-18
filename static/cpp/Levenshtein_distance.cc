// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <numeric>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::min;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

int ComputeDistanceBetweenPrefixes(const string&, int, const string&, int,
                                   vector<vector<int>>*);

// @include
int LevenshteinDistance(const string& A, const string& B) {
  vector<vector<int>> distance_between_prefixes(A.size(),
                                                vector<int>(B.size(), -1));
  return ComputeDistanceBetweenPrefixes(A, A.size() - 1, B, B.size() - 1,
                                        &distance_between_prefixes);
}

int ComputeDistanceBetweenPrefixes(
    const string& A, int A_idx, const string& B, int B_idx,
    vector<vector<int>>* distance_between_prefixes_ptr) {
  vector<vector<int>>& distance_between_prefixes =
      *distance_between_prefixes_ptr;
  if (A_idx < 0) {
    // A is empty so add all of B's characters.
    return B_idx + 1;
  } else if (B_idx < 0) {
    // B is empty so delete all of A's characters.
    return A_idx + 1;
  }
  if (distance_between_prefixes[A_idx][B_idx] == -1) {
    if (A[A_idx] == B[B_idx]) {
      distance_between_prefixes[A_idx][B_idx] = ComputeDistanceBetweenPrefixes(
          A, A_idx - 1, B, B_idx - 1, distance_between_prefixes_ptr);
    } else {
      int substitute_last = ComputeDistanceBetweenPrefixes(
          A, A_idx - 1, B, B_idx - 1, distance_between_prefixes_ptr);
      int add_last = ComputeDistanceBetweenPrefixes(
          A, A_idx - 1, B, B_idx, distance_between_prefixes_ptr);
      int delete_last = ComputeDistanceBetweenPrefixes(
          A, A_idx, B, B_idx - 1, distance_between_prefixes_ptr);
      distance_between_prefixes[A_idx][B_idx] =
          1 + min({substitute_last, add_last, delete_last});
    }
  }
  return distance_between_prefixes[A_idx][B_idx];
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis('a', 'z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  string A, B;
  // Wiki example (http://en.wikipedia.org/wiki/Levenshtein_distance)
  A = "k", B = "sitt";
  assert(4 == LevenshteinDistance(A, B));
  A = "Saturday", B = "Sunday";
  assert(3 == LevenshteinDistance(A, B));
  A = "kitten", B = "sitting";
  assert(3 == LevenshteinDistance(A, B));

  if (argc == 3) {
    A = argv[1], B = argv[2];
  } else {
    uniform_int_distribution<int> dis(1, 100);
    A = RandString(dis(gen));
    B = RandString(dis(gen));
  }
  cout << A << endl << B << endl;
  cout << LevenshteinDistance(A, B) << endl;
  return 0;
}
