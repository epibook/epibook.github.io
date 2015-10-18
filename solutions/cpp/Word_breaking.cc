// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <unordered_set>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

// @include
vector<string> WordBreaking(const string& s,
                            const unordered_set<string>& dict) {
  // When the algorithm finishes, T[i] != -1 indicates s.substring(0,i+1)
  // has a valid decomposition. Specifically, the length of the last string
  // in the decomposition will be T[i].

  vector<int> T(s.size(), -1);
  for (int i = 0; i < s.size(); ++i) {
    // If s.substr(0, i + 1) is a valid word, set T[i] to the length of that
    // word.
    if (dict.find(s.substr(0, i + 1)) != dict.cend()) {
      T[i] = i + 1;
    }

    // If T[i] = -1 look for j < i such that s.substr(0, j + 1)
    // has a valid decomposition and s.substring(j + 1, i + 1) is a
    // dictionary word. If so, record the length of that word in T[i].
    if (T[i] == -1) {
      for (int j = 0; j < i; ++j) {
        if (T[j] != -1 && dict.find(s.substr(j + 1, i - j)) != dict.cend()) {
          T[i] = i - j;
          break;
        }
      }
    }
  }

  vector<string> ret;
  // s can be assembled by valid words.
  if (T.back() != -1) {
    int idx = s.size() - 1;
    while (idx >= 0) {
      ret.emplace_back(s.substr(idx + 1 - T[idx], T[idx]));
      idx -= T[idx];
    }
    reverse(ret.begin(), ret.end());
  }
  return ret;
}
// @exclude

// Verify the strings in ans can be assembled into s.
void CheckAns(const string& s, vector<string>& ans) {
  string temp;
  cout << s << endl;
  for (int i = 0; i < ans.size(); ++i) {
    cout << ans[i] << ' ';
    temp += ans[i];
  }
  cout << endl;
  assert(!ans.size() || !s.compare(temp));
}

void SmallCase() {
  unordered_set<string> dictionary = {"bed", "bath", "and", "hand", "beyond"};
  auto ans = WordBreaking("bedbathandbeyond", dictionary);
  vector<string> golden_ans = {"bed", "bath", "and", "beyond"};
  CheckAns("bedbathandbeyond", ans);
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  dictionary = {"aa", "b", "ccc"};
  ans = WordBreaking("b", dictionary);
  golden_ans = {"b"};
  CheckAns("b", ans);
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  ans = WordBreaking("ccc", dictionary);
  golden_ans = {"ccc"};
  CheckAns("ccc", ans);
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  ans = WordBreaking("aabccc", dictionary);
  golden_ans = {"aa", "b", "ccc"};
  CheckAns("aabccc", ans);
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  ans = WordBreaking("baabccc", dictionary);
  golden_ans = {"b", "aa", "b", "ccc"};
  CheckAns("baabccc", ans);
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  dictionary.insert("bb");
  ans = WordBreaking("bbb", dictionary);
  // Note: golden_ans relies on how our algorithm is implemented: our
  // algorithm chooses longest word ending at that index, so the answer
  // is "b", "bb", not "b", "b", "b" or "bb", "b".
  golden_ans = {"b", "bb"};
  CheckAns("bbb", ans);
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  ans = WordBreaking("bbcccb", dictionary);
  golden_ans = {"bb", "ccc", "b"};
  CheckAns("bbcccb", ans);
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  ans = WordBreaking("bbcccbabb", dictionary);
  golden_ans = {};
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));

  ans = WordBreaking("d", dictionary);
  golden_ans = {};
  assert(golden_ans.size() == ans.size() &&
         equal(ans.begin(), ans.end(), golden_ans.begin()));
}

int main(int argc, char* argv[]) {
  SmallCase();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    unordered_set<string> dictionary;
    string target;
    if (argc >= 3) {
      target = argv[1];
      for (int i = 3; i < argc; ++i) {
        dictionary.emplace(argv[i]);
      }
    } else if (argc == 2) {
      target = argv[1];
      uniform_int_distribution<int> dis(1, 10000);
      int n = dis(gen);
      while (n--) {
        uniform_int_distribution<int> dis(1, 15);
        dictionary.emplace(RandString(dis(gen)));
      }
    } else {
      uniform_int_distribution<int> dis(1, 50);
      target = RandString(dis(gen));
      uniform_int_distribution<int> n_dis(1, 10000);
      int n = n_dis(gen);
      while (n--) {
        uniform_int_distribution<int> dis(1, 15);
        dictionary.emplace(RandString(dis(gen)));
      }
    }
    vector<string> ans(WordBreaking(target, dictionary));
    CheckAns(target, ans);
    if (argc == 3) {
      break;
    }
  }
  return 0;
}
