// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <sstream>
#include <string>
#include <vector>

#include "./execute-shell.h"

using std::cout;
using std::default_random_engine;
using std::endl;
using std::max;
using std::random_device;
using std::string;
using std::stringstream;
using std::uniform_int_distribution;
using std::vector;

class BigInt {
 public:
  explicit BigInt(int capacity) : sign_(1), digits_(capacity) {}

  explicit BigInt(const string &s) : sign_(s[0] == '-' ? -1 : 1),
                                     digits_(s.size() - (s[0] == '-')) {
    for (int i = s.size() - 1, j = 0; i >= (s[0] == '-'); --i, ++j) {
      if (isdigit(s[i])) {
        digits_[j] = s[i] - '0';
      }
    }
  }

  BigInt operator*(const BigInt &n) const {
    BigInt result(digits_.size() + n.digits_.size());
    result.sign_ = sign_ * n.sign_;
    int i, j;
    for (i = 0; i < n.digits_.size(); ++i) {
      if (n.digits_[i]) {
        int carry = 0;
        for (j = 0; j < digits_.size() || carry; ++j) {
          int n_digit = result.digits_[i + j] +
                        (j < digits_.size() ? n.digits_[i] * digits_[j] : 0) +
                        carry;
          result.digits_[i + j] = n_digit % 10;
          carry = n_digit / 10;
        }
      }
    }

    // If one number is 0, the result size should be 0.
    if ((digits_.size() == 1 && digits_.front() == 0) ||
        (n.digits_.size() == 1 && n.digits_.front() == 0)) {
      result.sign_ = 1, result.digits_.resize(1);
    } else {
      result.digits_.resize(i + j - 1);
    }
    return result;
  }

  string toString() const {
    string s = (sign_ > 0 ? "" : "-");
    for (int i = digits_.size() - 1; i >= 0; --i) {
      s += digits_[i] + '0';
    }
    if (digits_.empty() == true) {
      s += '0';
    }
    return s;
  }
 private:
  int sign_;  // -1 or 1;
  vector<char> digits_;
};

// @include
string Multiply(string num1, string num2) {
  bool is_positive = true;
  if (num1.front() == '-') {
    is_positive = !is_positive;
    num1 = num1.substr(1);
  }
  if (num2.front() == '-') {
    is_positive = !is_positive;
    num2 = num2.substr(1);
  }

  // Reverses num1 and num2 to make multiplication easier.
  reverse(num1.begin(), num1.end());
  reverse(num2.begin(), num2.end());
  vector<int> result(num1.size() + num2.size() + 1, 0);
  for (int i = 0; i < num1.size(); ++i) {
    for (int j = 0; j < num2.size(); ++j) {
      result[i + j] += (num1[i] - '0') * (num2[j] - '0');
      result[i + j + 1] += result[i + j] / 10;
      result[i + j] %= 10;
    }
  }

  // Converts result to string in reverse order, and skips the first 0s and
  // keeps one 0 if all are 0s.
  int i = num1.size() + num2.size();
  while (result[i] == 0 && i != 0) {
    --i;
  }
  stringstream ss;
  if (!is_positive && result[i] != 0) {  // It won't print "-0".
    ss << '-';
  }
  while (i >= 0) {
    ss << result[i--];
  }
  return ss.str();
}
// @exclude

string RandString(int len) {
  string ret;
  if (!len) {
    ret += '0';
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> positive_or_negative(0, 1);
    if (positive_or_negative(gen)) {
      ret += '-';
    }
    uniform_int_distribution<int> dis(1, 9);
    ret += dis(gen) + '0';
    --len;
    while (len--) {
      uniform_int_distribution<int> dis(0, 9);
      ret += dis(gen) + '0';
    }
  }
  return ret;
}

void SimpleTest() {
  assert(Multiply("0", "1000") == "0");
  cout << Multiply("131412", "-1313332") << endl;
  assert(Multiply("131412", "-1313332") == "-172587584784");
}

int main(int argc, char *argv[]) {
  SimpleTest();
  for (int times = 0; times < 1000; ++times) {
    string s1, s2;
    if (argc == 3) {
      s1 = argv[1], s2 = argv[2];
    } else {
      default_random_engine gen((random_device())());
      uniform_int_distribution<int> dis(0, 19);
      s1 = RandString(dis(gen)), s2 = RandString(dis(gen));
    }
    string res = Multiply(s1, s2);
    cout << s1 << " * " << s2 << " = " << res << endl;
    string command = "bc <<<" + s1 + "*" + s2;
    string result = execute_shell(command);
    cout << "answer = " << result;
    assert(res.compare(result.substr(0, result.size() - 1)) == 0);
  }
  return 0;
}
