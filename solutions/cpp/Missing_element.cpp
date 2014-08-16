// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <bitset>
#include <cassert>
#include <fstream>
#include <iostream>
#include <limits>
#include <numeric>
#include <random>
#include <stdexcept>
#include <string>
#include <unordered_set>
#include <vector>

using std::bitset;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::ifstream;
using std::invalid_argument;
using std::ios;
using std::numeric_limits;
using std::ofstream;
using std::random_device;
using std::stoi;
using std::uniform_int_distribution;
using std::unordered_set;
using std::vector;

// @include
int FindMissingElement(ifstream* ifs) {
  vector<size_t> counter(1 << 16, 0);
  unsigned int x;
  while (*ifs >> x) {
    ++counter[x >> 16];
  }

  for (int i = 0; i < counter.size(); ++i) {
    // Finds one bucket contains less than (1 << 16) elements.
    if (counter[i] < (1 << 16)) {
      bitset<(1 << 16)> bit_vec;
      ifs->clear();
      ifs->seekg(0, ios::beg);
      while (*ifs >> x) {
        if (i == (x >> 16)) {
          bit_vec.set(((1 << 16) - 1) & x);  // Gets the lower 16 bits of x.
        }
      }
      ifs->close();

      for (int j = 0; j < (1 << 16); ++j) {
        if (bit_vec.test(j) == 0) {
          return (i << 16) | j;
        }
      }
    }
  }
  // @exclude
  throw invalid_argument("no missing element");
  // @include
}
// @exclude

int main(int argc, char* argv[]) {
  int n;
  default_random_engine gen((random_device())());
  if (argc == 2) {
    n = stoi(argv[1]);
  } else {
    n = 300000000;
  }
  vector<int> A(1000000000);
  iota(A.begin(), A.end(), 0);
  unordered_set<int> hash;
  ofstream ofs("missing.txt");
  for (int i = 0; i < n; ++i) {
    hash.emplace(A[i]);
    ofs << A[i] << endl;
  }
  A.clear();
  ofs.close();
  ifstream ifs("missing.txt");
  cout << "Before finding missing" << endl;
  int missing = FindMissingElement(&ifs);
  assert(hash.find(missing) == hash.cend());
  // Remove file after the execution.
  remove("missing.txt");
  cout << missing << endl;
  return 0;
}
