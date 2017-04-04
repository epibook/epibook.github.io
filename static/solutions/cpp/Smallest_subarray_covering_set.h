// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#ifndef SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_H_
#define SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_H_

#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using std::pair;
using std::string;
using std::unordered_map;
using std::unordered_set;
using std::vector;

// @include
struct Subarray {
  int start, end;
};

Subarray FindSmallestSubarrayCoveringSet(
    const vector<string> &paragraph, const unordered_set<string> &keywords) {
  unordered_map<string, int> keywords_to_cover;
  for (const string &keyword : keywords) {
    ++keywords_to_cover[keyword];
  }

  Subarray result = Subarray{-1, -1};
  int remaining_to_cover = keywords.size();
  for (int left = 0, right = 0; right < paragraph.size(); ++right) {
    if (keywords.count(paragraph[right]) &&
        --keywords_to_cover[paragraph[right]] >= 0) {
      --remaining_to_cover;
    }

    // Keeps advancing left until keywords_to_cover does not contain all
    // keywords.
    while (remaining_to_cover == 0) {
      if ((result.start == -1 && result.end == -1) ||
          right - left < result.end - result.start) {
        result = {left, right};
      }
      if (keywords.count(paragraph[left]) &&
          ++keywords_to_cover[paragraph[left]] > 0) {
        ++remaining_to_cover;
      }
      ++left;
    }
  }
  return result;
}
// @exclude
#endif  // SOLUTIONS_SMALLEST_SUBARRAY_COVERING_SET_H_
