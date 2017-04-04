#include <iostream>
#include <string>
#include <unordered_map>

#include "Point.h"

using std::cout;
using std::hash;
using std::string;
using std::endl;
using std::unordered_map;

int main(int argc, char** argv) {
  // @include
  // Attempt 1
  unordered_map<Point, string, HashPoint> table;
  Point p{1, 2};
  table[p] = "Minkowski";
  p.x = 3;
  // @exclude
}
