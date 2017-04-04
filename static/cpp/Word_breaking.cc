// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

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
vector<string> DecomposeIntoDictionaryWords(
    const string& domain, const unordered_set<string>& dictionary) {
  // When the algorithm finishes, last_length[i] != -1 indicates
  // domain.substr(0, i + 1) has a valid decomposition, and the length of the
  // last string in the decomposition is last_length[i].
  vector<int> last_length(domain.size(), -1);
  for (int i = 0; i < domain.size(); ++i) {
    // If domain.substr(0, i + 1) is a dictionary word, set last_length[i] to
    // the length of that word.
    if (dictionary.find(domain.substr(0, i + 1)) != dictionary.cend()) {
      last_length[i] = i + 1;
    }

    // If last_length[i] = -1 look for j < i such that domain.substr(0, j + 1)
    // has a valid decomposition and domain.substring(j + 1, i + 1) is a
    // dictionary word. If so, record the length of that word in
    // last_length[i].
    if (last_length[i] == -1) {
      for (int j = 0; j < i; ++j) {
        if (last_length[j] != -1 &&
            dictionary.find(domain.substr(j + 1, i - j)) !=
                dictionary.cend()) {
          last_length[i] = i - j;
          break;
        }
      }
    }
  }

  vector<string> decompositions;
  if (last_length.back() != -1) {
    // domain can be assembled by dictionary words.
    int idx = domain.size() - 1;
    while (idx >= 0) {
      decompositions.emplace_back(
          domain.substr(idx + 1 - last_length[idx], last_length[idx]));
      idx -= last_length[idx];
    }
    reverse(decompositions.begin(), decompositions.end());
  }
  return decompositions;
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
  auto ans = DecomposeIntoDictionaryWords("bedbathandbeyond", dictionary);
  vector<string> golden_ans = {"bed", "bath", "and", "beyond"};
  CheckAns("bedbathandbeyond", ans);
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  dictionary = {"aa", "b", "ccc"};
  ans = DecomposeIntoDictionaryWords("b", dictionary);
  golden_ans = {"b"};
  CheckAns("b", ans);
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  ans = DecomposeIntoDictionaryWords("ccc", dictionary);
  golden_ans = {"ccc"};
  CheckAns("ccc", ans);
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  ans = DecomposeIntoDictionaryWords("aabccc", dictionary);
  golden_ans = {"aa", "b", "ccc"};
  CheckAns("aabccc", ans);
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  ans = DecomposeIntoDictionaryWords("baabccc", dictionary);
  golden_ans = {"b", "aa", "b", "ccc"};
  CheckAns("baabccc", ans);
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  dictionary.insert("bb");
  ans = DecomposeIntoDictionaryWords("bbb", dictionary);
  // Note: golden_ans relies on how our algorithm is implemented: our
  // algorithm chooses longest word ending at that index, so the answer
  // is "b", "bb", not "b", "b", "b" or "bb", "b".
  golden_ans = {"b", "bb"};
  CheckAns("bbb", ans);
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  ans = DecomposeIntoDictionaryWords("bbcccb", dictionary);
  golden_ans = {"bb", "ccc", "b"};
  CheckAns("bbcccb", ans);
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  ans = DecomposeIntoDictionaryWords("bbcccbabb", dictionary);
  golden_ans = {};
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));

  ans = DecomposeIntoDictionaryWords("d", dictionary);
  golden_ans = {};
  assert(equal(ans.begin(), ans.end(), golden_ans.begin(), golden_ans.end()));
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
    vector<string> ans(DecomposeIntoDictionaryWords(target, dictionary));
    CheckAns(target, ans);
    if (argc == 3) {
      break;
    }
  }
  return 0;
}
