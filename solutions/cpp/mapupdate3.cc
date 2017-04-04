#include <iostream>
#include <string>
#include <unordered_map>

using std::cout;
using std::hash;
using std::string;
using std::endl;
using std::unordered_map;

#include "Point.h"

using std::unordered_map;

int main(int argc, char** argv) {
  // @include
  Point p(1, 2);
  unordered_map<Point, string, HashPoint> table;
  table[p] = "Minkowski";
  auto val = table[p];
  table.erase(p);
  p.x = 4;
  table[p] = val;
  // @exclude
}
