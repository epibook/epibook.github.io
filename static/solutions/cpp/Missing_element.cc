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
  const int kNumBucket = 1 << 16;
  vector<size_t> counter(kNumBucket, 0);
  unsigned int x;
  while (*ifs >> x) {
    int upper_part_x = x >> 16;
    ++counter[upper_part_x];
  }

  // Look for a bucket that contains less than (1 << 16) elements.
  const int kBucketCapacity = 1 << 16;
  int candidate_bucket;
  for (int i = 0; i < kNumBucket; ++i) {
    if (counter[i] < kBucketCapacity) {
      candidate_bucket = i;
      break;
    }
  }

  // Finds all IP addresses in the stream whose first 16 bits
  // are equal to candidate_bucket.
  ifs->clear();
  ifs->seekg(0, ios::beg);
  bitset<kBucketCapacity> bit_vec;
  while (*ifs >> x) {
    int upper_part_x = x >> 16;
    if (candidate_bucket == upper_part_x) {
      // Records the presence of 16 LSB of x.
      int lower_part_x = ((1 << 16) - 1) & x;
      bit_vec.set(lower_part_x);
    }
  }
  ifs->close();

  // At least one of the LSB combinations is absent, find it.
  for (int i = 0; i < kBucketCapacity; ++i) {
    if (bit_vec[i] == 0) {
      return (candidate_bucket << 16) | i;
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
