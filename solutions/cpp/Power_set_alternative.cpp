// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cmath>
#include <iostream>
#include <random>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::ostream_iterator;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

void GeneratePowerSetHelper(const vector<int>& S, int m, vector<int>* subset);

// @include
void GeneratePowerSet(const vector<int>& S) {
  vector<int> subset;
  GeneratePowerSetHelper(S, 0, &subset);
}

void GeneratePowerSetHelper(const vector<int>& S, int m,
                            vector<int>* subset) {
  if (!subset->empty()) {
    // Print the subset.
    cout << subset->front();
    for (int i = 1; i < subset->size(); ++i) {
      cout << "," << (*subset)[i];
    }
  }
  cout << endl;

  for (int i = m; i < S.size(); ++i) {
    subset->emplace_back(S[i]);
    GeneratePowerSetHelper(S, i + 1, subset);
    subset->pop_back();
  }
}
// @exclude

int main(int argc, char* argv[]) {
  vector<int> S;
  if (argc >= 2) {
    for (int i = 1; i < argc; ++i) {
      S.emplace_back(atoi(argv[i]));
    }
  } else {
    default_random_engine gen((random_device())());
    uniform_int_distribution<int> dis(1, 10);
    S.resize(dis(gen));
    for (int i = 0; i < S.size(); ++i) {
      S[i] = i;
    }
  }
  GeneratePowerSet(S);
  return 0;
}
