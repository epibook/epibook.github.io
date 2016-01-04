// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <cassert>
#include <cmath>
#include <deque>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::boolalpha;
using std::cout;
using std::default_random_engine;
using std::deque;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

// @include
bool IsInterleavingString(const string& s1, const string& s2,
                          const string& s3) {
  // Early return if |s1| + |s2| != |s3|.
  if (s1.size() + s2.size() != s3.size()) {
    return false;
  }

  vector<deque<bool>> T(s1.size() + 1, deque<bool>(s2.size() + 1));
  T[0][0] = true;  // Base case.
  // Uses chars from s1 only to match s3.
  for (size_t i = 0; i < s1.size(); ++i) {
    if (s1[i] == s3[i]) {
      T[i + 1][0] = true;
    } else {
      break;
    }
  }
  // Uses chars from s2 only to match s3.
  for (size_t j = 0; j < s2.size(); ++j) {
    if (s2[j] == s3[j]) {
      T[0][j + 1] = true;
    } else {
      break;
    }
  }

  for (size_t i = 0; i < s1.size(); ++i) {
    for (size_t j = 0; j < s2.size(); ++j) {
      T[i + 1][j + 1] = (T[i][j + 1] && s1[i] == s3[i + j + 1]) ||
                        (T[i + 1][j] && s2[j] == s3[i + j + 1]);
    }
  }

  return T[s1.size()][s2.size()];
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<char> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

void SmallTest() {
  assert(IsInterleavingString("aabcc", "dbbca", "aadbbcbcac"));
  assert(!IsInterleavingString("aabcc", "dbbca", "aadbbbaccc"));
  assert(IsInterleavingString("aabaac", "aadaaeaaf", "aadaaeaabaafaac"));
  assert(IsInterleavingString("bbc", "acaab", "abcbcaab"));
}

int main(int argc, char* argv[]) {
  SmallTest();
  if (argc == 4) {
    string s1 = argv[1], s2 = argv[2], s3 = argv[3];
    cout << s1 << " " << s2 << " " << s3 << endl;
    cout << boolalpha << IsInterleavingString(s1, s2, s3) << endl;
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(1, 100);
    string s1 = RandString(dis(gen)), s2 = RandString(dis(gen));
    string s3 = RandString(s1.size() + s2.size());
    cout << s1 << " " << s2 << " " << s3 << endl;
    cout << boolalpha << IsInterleavingString(s1, s2, s3) << endl;
  }
  return 0;
}
