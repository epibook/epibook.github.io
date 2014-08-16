// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

bool IsPalindrome(const string& s);
void PalindromePartitioningHelper(const string& s, size_t begin,
                                  vector<string>* partition,
                                  vector<vector<string>>* result);

// @include
vector<vector<string>> PalindromePartitioning(const string& s) {
  vector<vector<string>> result;
  vector<string> partition;
  PalindromePartitioningHelper(s, 0, &partition, &result);
  return result;
}

void PalindromePartitioningHelper(const string& s, size_t begin,
                                  vector<string>* partition,
                                  vector<vector<string>>* result) {
  if (begin == s.size()) {
    result->emplace_back(*partition);
    return;
  }

  for (size_t i = begin + 1; i <= s.size(); ++i) {
    string prefix = s.substr(begin, i - begin);
    if (IsPalindrome(prefix)) {
      partition->emplace_back(prefix);
      PalindromePartitioningHelper(s, i, partition, result);
      partition->pop_back();
    }
  }
}

bool IsPalindrome(const string& s) {
  for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
    if (s[i] != s[j]) {
      return false;
    }
  }
  return true;
}
// @exclude

void CheckAns(const vector<vector<string>> &vecs, const string& input) {
  for (const auto& vec : vecs) {
    string temp;
    for (const auto& s : vec) {
      assert(IsPalindrome(s));
      temp += s;
    }
    assert(!temp.compare(input));
  }
}

string RandString(size_t len) {
  default_random_engine gen((random_device())());
  string ret;
  uniform_int_distribution<char> dis('a', 'z');
  while (len) {
    --len;
    ret += dis(gen);
  }
  return ret;
}

int main(int argc, char** argv) {
  if (argc == 2) {
    string s = argv[1];
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 1000; ++times) {
      uniform_int_distribution<size_t> dis_len(0, 10);
      string s = RandString(dis_len(gen));
      auto result = PalindromePartitioning(s);
      CheckAns(result, s);
      cout << "string s = " << s << endl;
      for (const auto& vec : result) {
        for (const auto& t : vec) {
          cout << t << " ";
        }
        cout << endl;
      }
    }
  }
  return 0;
}
