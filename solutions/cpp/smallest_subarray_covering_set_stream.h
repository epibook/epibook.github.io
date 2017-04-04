// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_CPP_SMALLEST_SUBARRAY_COVERING_SET_STREAM_H_
#define SOLUTIONS_CPP_SMALLEST_SUBARRAY_COVERING_SET_STREAM_H_

#include <list>
#include <sstream>
#include <string>
#include <unordered_map>
#include <utility>
#include <vector>

using std::istringstream;
using std::list;
using std::pair;
using std::unordered_map;

// @include
Subarray FindSmallestSubarrayCoveringSubset(
    istringstream* stream, const vector<string>& query_strings) {
  // Tracks the last occurrence (index) of each string in query_strings.
  list<int> loc;
  unordered_map<string, list<int>::iterator> dict;
  for (const string& s : query_strings) {
    dict.emplace(s, loc.end());
  }

  Subarray res = Subarray{-1, -1};
  int idx = 0;
  string s;
  while (*stream >> s) {
    auto it = dict.find(s);
    if (it != dict.end()) {  // s is in query_strings.
      if (it->second != loc.end()) {
        // Explicitly remove s so that when we add it, it's the string most
        // recently added to loc.
        loc.erase(it->second);
      }
      loc.emplace_back(idx);
      it->second = --loc.end();
    }

    if (loc.size() == query_strings.size()) {
      // We have seen all strings in query_strings, let's get to work.
      if ((res.start == -1 && res.end == -1) ||
          idx - loc.front() < res.end - res.start) {
        res = {loc.front(), idx};
      }
    }
    ++idx;
  }
  return res;
}
// @exclude
#endif  // SOLUTIONS_CPP_SMALLEST_SUBARRAY_COVERING_SET_STREAM_H_
