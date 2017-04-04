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
  auto it = table.find(p);
  p.x = 4;
  std::swap(table[p], it->second);
  table.erase(it);
  // @exclude
}
