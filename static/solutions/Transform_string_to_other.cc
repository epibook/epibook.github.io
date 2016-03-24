// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <iostream>
#include <queue>
#include <random>
#include <string>
#include <unordered_set>
#include <utility>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::pair;
using std::queue;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

string RandString(int len) {
  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis('a', 'z');
  string ret;
  while (len--) {
    ret += dis(gen);
  }
  return ret;
}

// @include
// Uses BFS to find the least steps of transformation.
int TransformString(unordered_set<string> D, const string& s,
                    const string& t) {
  struct StringWithDistance {
    string candidate_string;
    int distance;
  };
  queue<StringWithDistance> q;
  D.erase(s);  // Marks s as visited by erasing it in D.
  q.emplace(StringWithDistance{s, 0});

  while (!q.empty()) {
    StringWithDistance f(q.front());
    // Returns if we find a match.
    if (f.candidate_string == t) {
      return f.distance;  // Number of steps reaches t.
    }

    // Tries all possible transformations of f.candidate_string.
    string str = f.candidate_string;
    for (int i = 0; i < str.size(); ++i) {
      for (int j = 0; j < 26; ++j) {  // Iterates through 'a' ~ 'z'.
        str[i] = 'a' + j;
        auto it(D.find(str));
        if (it != D.end()) {
          D.erase(it);
          q.emplace(StringWithDistance{str, f.distance + 1});
        }
      }
      str[i] = f.candidate_string[i];  // Reverts the change of str.
    }
    q.pop();
  }

  return -1;  // Cannot find a possible transformations.
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  int len;
  if (argc == 2) {
    len = atoi(argv[1]);
  } else {
    uniform_int_distribution<int> dis(1, 10);
    len = dis(gen);
  }
  string s(RandString(len)), t(RandString(len));
  unordered_set<string> D;
  D.emplace(s), D.emplace(t);
  uniform_int_distribution<int> dis(1, 1000000);
  int n = dis(gen);
  for (size_t i = 0; i < n; ++i) {
    D.emplace(RandString(len));
  }
  /*
  for (const string &s : D) {
    cout << s << endl;
  }
  */
  cout << s << ' ' << t << ' ' << D.size() << endl;
  cout << TransformString(D, s, t) << endl;
  return 0;
}
