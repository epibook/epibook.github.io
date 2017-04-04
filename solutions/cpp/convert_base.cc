// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <string>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::string;
using std::uniform_int_distribution;

string ConstructFromBase(int, int);

// @include
string ConvertBase(const string& num_as_string, int b1, int b2) {
  bool is_negative = num_as_string.front() == '-';
  int num_as_int = 0;
  for (int i = (is_negative == true ? 1 : 0); i < num_as_string.size(); ++i) {
    num_as_int *= b1;
    num_as_int += isdigit(num_as_string[i]) ? num_as_string[i] - '0'
                                            : num_as_string[i] - 'A' + 10;
  }
  return (is_negative ? "-" : "") +
         (num_as_int == 0 ? "0" : ConstructFromBase(num_as_int, b2));
}

string ConstructFromBase(int num_as_int, int base) {
  return num_as_int == 0 ? ""
                         : ConstructFromBase(num_as_int / base, base) +
                               (char)(num_as_int % base >= 10
                                          ? 'A' + num_as_int % base - 10
                                          : '0' + num_as_int % base);
}
// @exclude

string RandIntString(int len) {
  default_random_engine gen((random_device())());
  string ret;
  if (len == 0) {
    return {"0"};
  }
  uniform_int_distribution<int> pos_or_neg(0, 1);
  if (pos_or_neg(gen)) {
    ret.push_back('-');
  }
  uniform_int_distribution<int> num_dis('1', '9');
  ret.push_back(num_dis(gen));
  while (--len) {
    uniform_int_distribution<int> dis('0', '9');
    ret.push_back(dis(gen));
  }
  return ret;
}

int main(int argc, char* argv[]) {
  if (argc == 4) {
    string input(argv[1]);
    cout << ConvertBase(input, atoi(argv[2]), atoi(argv[3])) << endl;
    assert(input ==
           ConvertBase(ConvertBase(input, atoi(argv[2]), atoi(argv[3])),
                       atoi(argv[3]), atoi(argv[2])));
  } else {
    default_random_engine gen((random_device())());
    for (int times = 0; times < 100000; ++times) {
      uniform_int_distribution<int> len_dis(1, 9);
      string input = RandIntString(len_dis(gen));
      uniform_int_distribution<int> base_dis(2, 16);
      int base = base_dis(gen);
      cout << "input is " << input << ", base1 = 10, base2 = " << base
           << ", result = " << ConvertBase(input, 10, base) << endl;
      assert(input == ConvertBase(ConvertBase(input, 10, base), base, 10));
    }
  }
  return 0;
}
