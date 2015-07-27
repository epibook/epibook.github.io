// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <iostream>
#include <random>
#include <string>
#include <unordered_map>
#include <cassert>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::unordered_map;

static unordered_map<char, int> charToCount;

// @include
void CountOccurrences(string S) {
  sort(S.begin(), S.end());

  int current_character_count = 1;
  for (int i = 1; i < S.size(); ++i) {
    if (S[i] == S[i - 1]) {
      ++current_character_count;
    } else {
      cout << '(' << S[i - 1] << ',' << current_character_count << "),";
      // @exclude
      charToCount[S[i-1]] = current_character_count;
      // @include
      current_character_count = 1;
    }
  }
  cout << '(' << S.back() << ',' << current_character_count << ')' << endl;
}
// @exclude

string RandString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  while (len--) {
    uniform_int_distribution<int> dis('a', 'z');
    ret += dis(gen);
  }
  return ret;
}

static void SimpleTest() {
  CountOccurrences("foo bar! ABA A");
  assert(charToCount['f'] == 1);
  assert(charToCount.count('F') == 0);
  assert(charToCount['o'] == 2);
  assert(charToCount.count('x') == 0);
  assert(charToCount[' '] == 3);
  assert(charToCount['!'] == 1);
  assert(charToCount['A'] == 3);
  assert(charToCount['B'] == 1);
}

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  string S;
  if (argc == 2) {
    S = argv[1];
  } else {
    uniform_int_distribution<int> dis(1, 1000);
    S = RandString(dis(gen));
  }
  cout << S << endl;
  CountOccurrences(S);
  return 0;
}
