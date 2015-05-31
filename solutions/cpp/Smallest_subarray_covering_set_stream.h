// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_STREAM_H_
#define SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_STREAM_H_

#include <list>
#include <string>
#include <sstream>
#include <unordered_map>
#include <utility>
#include <vector>

using std::istringstream;
using std::list;
using std::pair;
using std::unordered_map;

// @include
pair<int, int> FindSmallestSubarrayCoveringSubset(istringstream* sin,
                                                  const vector<string>& Q) {
  list<int> loc;  // Tracks the last occurrence (index) of each string in Q.
  unordered_map<string, list<int>::iterator> dict;
  for (const string& s : Q) {
    dict.emplace(s, loc.end());
  }

  pair<int, int> res(-1, -1);
  int idx = 0;
  string s;
  while (*sin >> s) {
    auto it = dict.find(s);
    if (it != dict.end()) {  // s is in Q.
      if (it->second != loc.end()) {
        loc.erase(it->second);
      }
      loc.emplace_back(idx);
      it->second = --loc.end();
    }

    if (loc.size() == Q.size() &&  // Found |Q| keywords.
        ((res.first == -1 && res.second == -1) ||
         idx - loc.front() < res.second - res.first)) {
      res = {loc.front(), idx};
    }
    ++idx;
  }
  return res;
}
// @exclude
#endif  // SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_STREAM_H_
