// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <memory>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::make_unique;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

bool IsPalindrome(const string&);
void DirectedPalindromePartitioning(const string&, int, vector<string>*,
                                    vector<vector<string>>*);

// @include
vector<vector<string>> PalindromePartitioning(const string& input) {
  vector<vector<string>> result;
  DirectedPalindromePartitioning(
      input, 0, make_unique<vector<string>>().get(), &result);
  return result;
}

void DirectedPalindromePartitioning(const string& input, int offset,
                                    vector<string>* partial_partition,
                                    vector<vector<string>>* result) {
  if (offset == input.size()) {
    result->emplace_back(*partial_partition);
    return;
  }

  for (int i = offset + 1; i <= input.size(); ++i) {
    string prefix = input.substr(offset, i - offset);
    if (IsPalindrome(prefix)) {
      partial_partition->emplace_back(prefix);
      DirectedPalindromePartitioning(input, i, partial_partition, result);
      partial_partition->pop_back();
    }
  }
}

bool IsPalindrome(const string& prefix) {
  for (int i = 0, j = prefix.size() - 1; i < j; ++i, --j) {
    if (prefix[i] != prefix[j]) {
      return false;
    }
  }
  return true;
}
// @exclude

void CheckAns(const vector<vector<string>>& vecs, const string& input) {
  for (const vector<string>& vec : vecs) {
    string temp;
    for (const string& s : vec) {
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

static void SimpleTest() {
  auto result = PalindromePartitioning("abbbac");
  vector<string> v0 = {"a", "b", "b", "b", "a", "c"};
  vector<string> v1 = {"a", "b", "bb", "a", "c"};
  vector<string> v2 = {"a", "bb", "b", "a", "c"};
  vector<string> v3 = {"a", "bbb", "a", "c"};
  vector<string> v4 = {"abbba", "c"};
  vector<vector<string>> golden = {v0, v1, v2, v3, v4};
  assert(result == golden);
}

int main(int argc, char** argv) {
  SimpleTest();
  if (argc == 2) {
    string s = argv[1];
    auto result = PalindromePartitioning(s);
    CheckAns(result, s);
    cout << "string s = " << s << endl;
    for (const vector<string>& vec : result) {
      for (const string& t : vec) {
        cout << t << " ";
      }
      cout << endl;
    }
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 1000; ++times) {
      uniform_int_distribution<size_t> dis_len(0, 10);
      string s = RandString(dis_len(gen));
      auto result = PalindromePartitioning(s);
      CheckAns(result, s);
      cout << "string s = " << s << endl;
      for (const vector<string>& vec : result) {
        for (const string& t : vec) {
          cout << t << " ";
        }
        cout << endl;
      }
    }
  }
  return 0;
}
