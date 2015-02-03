// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <limits>
#include <random>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::min;
using std::numeric_limits;
using std::pair;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;
using std::unordered_set;
using std::vector;

string RandString(int len) {
  string ret;
  default_random_engine gen((random_device())());
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// @include
pair<int, int> FindSmallestSequentiallyCoveringSubset(
    const vector<string>& paragraph, const vector<string>& keywords) {
  // Maps each keyword to its index in the keywords array.
  unordered_map<string, int> keyword_to_idx;
  // Initializes keyword_to_idx.
  for (int i = 0; i < keywords.size(); ++i) {
    keyword_to_idx.emplace(keywords[i], i);
  }

  // Since keywords are uniquely identified by their indices in keywords
  // array, we can use those indices as keys to lookup in a vector.
  vector<int> latest_occurrence(keywords.size(), -1);
  // For each keyword (identified by its index in keywords array), stores the
  // length of the shortest subarray ending at the most recent occurrence of
  // that keyword that sequentially cover all keywords up to that keyword.
  vector<int> shortest_subarray_length(keywords.size(),
                                       numeric_limits<int>::max());

  pair<int, int> result(-1, -1);
  for (int i = 0; i < paragraph.size(); ++i) {
    if (keyword_to_idx.find(paragraph[i]) != keyword_to_idx.cend()) {
      int keyword_idx = keyword_to_idx.find(paragraph[i])->second;
      if (keyword_idx == 0) {  // First keyword.
        shortest_subarray_length[keyword_idx] = 1;
      } else if (shortest_subarray_length[keyword_idx - 1] !=
                 numeric_limits<int>::max()) {
        int distance_to_previous_keyword =
            i - latest_occurrence[keyword_idx - 1];
        shortest_subarray_length[keyword_idx] =
            distance_to_previous_keyword +
            shortest_subarray_length[keyword_idx - 1];
      }
      latest_occurrence[keyword_idx] = i;

      // Last keyword, look for improved subarray.
      if (keyword_idx == keywords.size() - 1 &&
          shortest_subarray_length.back() <
              result.second - result.first + 1) {
        result = {i - shortest_subarray_length.back() + 1, i};
      }
    }
  }
  return result;
}
// @exclude

void small_test() {
  vector<string> A3 = {"0", "1", "2", "3",  "4",  "5",  "6", "7", "8", "9",
                       "2", "4", "6", "10", "10", "10", "3", "2", "1", "0"};
  vector<string> subseq4 = {"0", "2", "9", "4", "6"};
  pair<int, int> rr = FindSmallestSequentiallyCoveringSubset(A3, subseq4);
  assert(rr.first == 0 && rr.second == 12);
}

int main(int argc, char* argv[]) {
  small_test();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    vector<string> A;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    uniform_int_distribution<int> dis(1, 5);
    generate_n(back_inserter(A), n, [&] { return RandString(dis(gen)); });
    unordered_set<string> dict;
    copy(A.begin(), A.end(), inserter(dict, dict.end()));
    cout << "A = ";
    copy(A.begin(), A.end(), ostream_iterator<string>(cout, ","));
    cout << endl;
    uniform_int_distribution<int> m_dis(1,
                                        min(static_cast<int>(dict.size()), 10));
    int m = m_dis(gen);
    vector<string> Q;
    auto it = dict.begin();
    generate_n(back_inserter(Q), m, [&] { return *it++; });
    cout << "Q = ";
    copy(Q.begin(), Q.end(), ostream_iterator<string>(cout, ","));
    cout << endl;

    pair<int, int> res(FindSmallestSequentiallyCoveringSubset(A, Q));
    cout << res.first << ", " << res.second << endl;
    if (res.first != -1 && res.second != Q.size()) {
      if (res.first != res.second)
        cout << res.first << ", " << res.second << endl;
      dict.clear();
      copy(Q.begin(), Q.end(), inserter(dict, dict.end()));
      for (int i = res.first; i <= res.second; ++i) {
        if (dict.find(A[i]) != dict.end()) {
          dict.erase(A[i]);
        }
      }
      assert(dict.empty());
    }
  }
  return 0;
}
