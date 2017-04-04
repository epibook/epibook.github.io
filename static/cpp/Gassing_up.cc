// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <cassert>
#include <iostream>
#include <random>
#include <utility>
#include <vector>

using std::cout;
using std::default_random_engine;
using std::endl;
using std::random_device;
using std::uniform_int_distribution;
using std::vector;

// @include
const int kMPG = 20;

// gallons[i] is the amount of gas in city i, and distances[i] is the distance
// city i to the next city.
size_t FindAmpleCity(const vector<int>& gallons,
                     const vector<int>& distances) {
  int remaining_gallons = 0;
  struct CityAndRemainingGas {
    int city = 0, remaining_gallons = 0;
  };
  CityAndRemainingGas city_remaining_gallons_pair;
  const int num_cities = gallons.size();
  for (int i = 1; i < num_cities; ++i) {
    remaining_gallons += gallons[i - 1] - distances[i - 1] / kMPG;
    if (remaining_gallons < city_remaining_gallons_pair.remaining_gallons) {
      city_remaining_gallons_pair = {i, remaining_gallons};
    }
  }
  return city_remaining_gallons_pair.city;
}
// @exclude

void CheckAns(const vector<int>& gallons, const vector<int>& distances,
              size_t c) {
  size_t s = c;
  int gas = 0;
  do {
    gas += gallons[s] - distances[s] / kMPG;
    assert(gas >= 0);
    s = (s + 1) % gallons.size();
  } while (s != c);
}

void SmallTest() {
  // Example in the book.
  vector<int> gallons = {20, 15, 15, 15, 35, 25, 30, 15, 65, 45, 10, 45, 25};
  vector<int> distances = {15 * kMPG, 20 * kMPG, 50 * kMPG, 15 * kMPG,
                           15 * kMPG, 30 * kMPG, 20 * kMPG, 55 * kMPG,
                           20 * kMPG, 50 * kMPG, 10 * kMPG, 15 * kMPG,
                           15 * kMPG};
  size_t ans = FindAmpleCity(gallons, distances);
  assert(ans == 8);
  CheckAns(gallons, distances, ans);
}

int main(int argc, char* argv[]) {
  SmallTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int n;
    if (argc == 2) {
      n = atoi(argv[1]);
    } else {
      uniform_int_distribution<int> dis(1, 10000);
      n = dis(gen);
    }
    vector<int> gallons, distances;
    int sum = 0;
    for (int i = 0; i < n; ++i) {
      uniform_int_distribution<int> dis(1, 200);
      int x = dis(gen);
      sum += x;
      gallons.emplace_back(x);
    }
    sum -= n;
    for (int i = 0; i < n; ++i) {
      int x = 0;
      if (sum) {
        uniform_int_distribution<int> dis(1, sum);
        x = dis(gen);
      }
      distances.emplace_back(x + 1);
      sum -= x;
    }
    distances.back() += sum;
    size_t c = FindAmpleCity(gallons, distances);
    cout << "start city = " << c << endl;
    CheckAns(gallons, distances, c);
  }
  return 0;
}
