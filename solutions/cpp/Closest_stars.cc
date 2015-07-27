// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <algorithm>
#include <array>
#include <cassert>
#include <cmath>
#include <iostream>
#include <queue>
#include <random>
#include <string>
#include <sstream>
#include <vector>

using std::array;
using std::cout;
using std::default_random_engine;
using std::endl;
using std::istringstream;
using std::max;
using std::min;
using std::priority_queue;
using std::random_device;
using std::stoi;
using std::string;
using std::stringstream;
using std::swap;
using std::uniform_int_distribution;
using std::uniform_real_distribution;
using std::vector;

// @include
struct Star {
 public:
  bool operator<(const Star& rhs) const {
    double distance = x * x + y * y + z * z;
    double rhs_distance = rhs.x * rhs.x + rhs.y * rhs.y + rhs.z * rhs.z;
    return distance < rhs_distance;
  }

  // @exclude
  bool operator==(const Star& rhs) const {
    double distance = x * x + y * y + z * z;
    double rhs_distance = rhs.x * rhs.x + rhs.y * rhs.y + rhs.z * rhs.z;
    return fabs(distance - rhs_distance) / rhs_distance < 1.0e-5;
  }
  // @include
  double x, y, z;
};

vector<Star> FindClosestKStars(int k, istringstream* stars) {
  // max_heap to store the closest k stars seen so far.
  priority_queue<Star, vector<Star>> max_heap;

  string line;
  while (getline(*stars, line)) {
    stringstream line_stream(line);
    array<double, 3> data;  // stores x, y, and z.
    for (int i = 0; i < 3; ++i) {
      string buf;
      getline(line_stream, buf, ',');
      data[i] = stod(buf);
    }

    // Add each star to the max-heap. If the max-heap size exceeds k,
    // remove the maximum element from the max-heap.
    max_heap.emplace(Star{data[0], data[1], data[2]});
    if (max_heap.size() == k + 1) {
      max_heap.pop();
    }
  }

  // Iteratively extract from the max-heap, which yields the stars
  // sorted according from furthest to closest.
  vector<Star> closest_stars;
  while (!max_heap.empty()) {
    closest_stars.emplace_back(max_heap.top());
    max_heap.pop();
  }
  reverse(closest_stars.begin(), closest_stars.end());
  return closest_stars;
}
// @exclude

string CreateStreamingString(const vector<Star>& stars) {
  string s;
  for (int i = 0; i < stars.size(); ++i) {
    stringstream ss;
    ss << stars[i].x << ',' << stars[i].y << ',' << stars[i].z << endl;
    s += ss.str();
  }
  return s;
}

void SimpleTest() {
  vector<Star> stars;
  stars.emplace_back((Star{1, 2, 3}));
  stars.emplace_back((Star{5, 5, 5}));
  stars.emplace_back((Star{0, 2, 1}));
  stars.emplace_back((Star{9, 2, 1}));
  stars.emplace_back((Star{1, 2, 1}));
  stars.emplace_back((Star{2, 2, 1}));
  istringstream sin(CreateStreamingString(stars));
  vector<Star> closest_stars = FindClosestKStars(3, &sin);
  assert(3 == closest_stars.size());
  assert(closest_stars[0] == (Star{0, 2, 1}));
  assert(closest_stars[0] == (Star{2, 0, 1}));
  assert(closest_stars[1] == (Star{1, 2, 1}));
  assert(closest_stars[1] == (Star{1, 1, 2}));

  stars.clear();
  stars.emplace_back((Star{1, 2, 3}));
  stars.emplace_back((Star{5, 5, 5}));
  stars.emplace_back((Star{4, 4, 4}));
  stars.emplace_back((Star{3, 2, 1}));
  stars.emplace_back((Star{5, 5, 5}));
  stars.emplace_back((Star{3, 2, 3}));
  stars.emplace_back((Star{3, 2, 3}));
  stars.emplace_back((Star{3, 2, 1}));
  istringstream sin2(CreateStreamingString(stars));
  closest_stars = FindClosestKStars(2, &sin2);
  assert(2 == closest_stars.size());
  assert(closest_stars[0] == (Star{1, 2, 3}));
  assert(closest_stars[1] == (Star{3, 2, 1}));
}

int main(int argc, char* argv[]) {
  SimpleTest();
  default_random_engine gen((random_device())());
  for (int times = 0; times < 1000; ++times) {
    int num, k;
    if (argc == 2) {
      num = stoi(argv[1]);
      uniform_int_distribution<int> dis(1, num);
      k = dis(gen);
    } else if (argc == 3) {
      num = stoi(argv[1]);
      k = stoi(argv[2]);
    } else {
      uniform_int_distribution<int> num_dis(1, 10000);
      num = num_dis(gen);
      uniform_int_distribution<int> k_dis(1, num);
      k = k_dis(gen);
    }
    vector<Star> stars;
    // Randomly generate num of stars.
    uniform_real_distribution<double> dis(0, 10000);
    for (int i = 0; i < num; ++i) {
      stars.emplace_back(Star{dis(gen), dis(gen), dis(gen)});
    }
    istringstream sin(CreateStreamingString(stars));
    vector<Star> closest_stars(FindClosestKStars(k, &sin));
    sort(closest_stars.begin(), closest_stars.end());
    sort(stars.begin(), stars.end());
    cout << "k = " << k << endl;
    cout << stars[k - 1].x << " " << stars[k - 1].y << " " << stars[k - 1].z
         << endl;
    cout << closest_stars.back().x << " " << closest_stars.back().y << " "
         << closest_stars.back().z << endl;
    assert(stars[k - 1] == closest_stars.back());
  }
  return 0;
}
