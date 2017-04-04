// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <fstream>
#include <iostream>
#include <iterator>
#include <random>
#include <string>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ifstream;
using std::ofstream;
using std::random_device;
using std::string;
using std::uniform_int_distribution;
using std::vector;

// @include
void IndirectSort(const string& file_name) {
  // Stores file records into A.
  ifstream ifs(file_name.c_str());
  vector<int> A;
  int x;
  while (ifs >> x) {
    A.emplace_back(x);
  }

  // Initializes P.
  vector<const int*> P;
  for (int& a : A) {
    P.emplace_back(&a);
  }

  // Indirectly sorts file.
  sort(begin(P), end(P), [](const int* a, const int* b) { return *a < *b; });

  // Outputs file.
  ofstream ofs(file_name.c_str());
  for (const int* p : P) {
    ofs << *p << endl;
  }
}
// @exclude

int main(int argc, char* argv[]) {
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    cout << "times = " << times << endl;
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> A;
    generate_n(back_inserter(A), n, [&] {
      uniform_int_distribution<int> dis(0, 999999);
      return dis(gen);
    });
    ofstream ofs("input.txt");
    for (int a : A) {
      ofs << a << endl;
    }

    IndirectSort("input.txt");
    ifstream ifs("input.txt");
    A.clear();
    int x;
    while (ifs >> x) {
      A.emplace_back(x);
    }
    assert(is_sorted(A.begin(), A.end()));
    remove("input.txt");
  }
  return 0;
}
