// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <iterator>
#include <random>
#include <sstream>
#include <string>
#include <vector>

#include "./execute-shell.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::ostream_iterator;
using std::random_device;
using std::string;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

// @include
vector<int> Multiply(vector<int> num1, vector<int> num2) {
  bool is_negative = (num1.front() < 0 && num2.front() >= 0) ||
                     (num1.front() >= 0 && num2.front() < 0);
  num1.front() = abs(num1.front()), num2.front() = abs(num2.front());

  // Reverses num1 and num2 to make multiplication easier.
  reverse(num1.begin(), num1.end());
  reverse(num2.begin(), num2.end());
  vector<int> result(num1.size() + num2.size(), 0);
  for (int i = 0; i < num1.size(); ++i) {
    for (int j = 0; j < num2.size(); ++j) {
      result[i + j] += num1[i] * num2[j];
      result[i + j + 1] += result[i + j] / 10;
      result[i + j] %= 10;
    }
  }

  // Skips the leading 0s and keeps one 0 if all are 0s.
  while (result.size() != 1 && result.back() == 0) {
    result.pop_back();
  }
  // Reverses result to get the most significant digit as the start of array.
  reverse(result.begin(), result.end());
  if (is_negative) {
    result.front() *= -1;
  }
  return result;
}
// @exclude

vector<int> RandVector(int len) {
  if (!len) return {0};

  default_random_engine gen((random_device())());
  uniform_int_distribution<int> dis(1, 9);
  vector<int> ret;
  ret.emplace_back(dis(gen));
  --len;
  while (len--) {
    uniform_int_distribution<int> dis(0, 9);
    ret.emplace_back(dis(gen));
  }

  uniform_int_distribution<int> positive_or_negative(0, 1);
  if (positive_or_negative(gen)) {
    ret.front() *= -1;
  }
  return ret;
}

bool EqualVector(const vector<int>& A, const vector<int>& B) {
  return A.size() == B.size() && equal(A.begin(), A.end(), B.begin());
}

void SimpleTest() {
  assert(EqualVector(Multiply({0}, {-1, 0, 0, 0}), {0}));
  assert(EqualVector(Multiply({0}, {1, 0, 0, 0}), {0}));
  assert(EqualVector(Multiply({9}, {9}), {8, 1}));
  assert(EqualVector(Multiply({9}, {9, 9, 9, 9}), {8, 9, 9, 9, 1}));
  assert(EqualVector(Multiply({1, 3, 1, 4, 1, 2}, {-1, 3, 1, 3, 3, 3, 2}),
                     {-1, 7, 2, 5, 8, 7, 5, 8, 4, 7, 8, 4}));
  assert(EqualVector(Multiply({7, 3}, {-3}), {-2, 1, 9}));
}

string VectorToString(const vector<int>& A) {
  stringstream converter;
  copy(A.begin(), A.end(), ostream_iterator<int>(converter, ""));
  return converter.str();
}

int main(int argc, char* argv[]) {
  SimpleTest();
  for (int times = 0; times < 1000; ++times) {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(0, 19);
    vector<int> num1 = RandVector(dis(gen)), num2 = RandVector(dis(gen));
    auto res = Multiply(num1, num2);
    cout << VectorToString(num1) << " * " << VectorToString(num2) << " = "
         << VectorToString(res) << endl;
    string command = "bash -c 'bc <<<" + VectorToString(num1) + "*" +
                     VectorToString(num2) + "'";
    string result = execute_shell(command);
    cout << "answer = " << result;
    assert(result.substr(0, result.size() - 1) == VectorToString(res));
  }
  return 0;
}
