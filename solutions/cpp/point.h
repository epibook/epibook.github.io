using std::hash;

class Point {
 public:
  int x;
  int y;
  Point(int a, int b) {
    x = a;
    y = b;
  }

  bool operator==(const Point& that) const {
    return x == that.x && y == that.y;
  }
};

struct HashPoint {
  size_t operator()(const Point& p) const {
    size_t hash_code = 0;
    hash_code ^= hash<int>()(p.x);
    hash_code ^= hash<int>()(p.y);
    return hash_code;
  }
};
