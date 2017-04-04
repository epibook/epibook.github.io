#include <cstdlib>
#include <ctime>
#include <iostream>
#include <vector>

using namespace std;

// @include
vector<vector<int>> generate_spiral_matrix(int n) {
  vector<vector<int>> A(n, vector<int>(n, 0));
  int shift[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  int dir = 0, x = 0, y = 0;
  for (int i = 1; i <= n * n; ++i) {
    A[x][y] = i;
    int nx = x + shift[dir][0], ny = y + shift[dir][1];
    if (nx < 0 || nx >= n || ny < 0 || ny >= n || A[nx][ny]) {
      dir = (dir + 1) % 4;
      nx = x + shift[dir][0], ny = y + shift[dir][1];
    }
    x = nx, y = ny;
  }
  return A;
}
// @exclude

int main(int argc, char *argv[]) {
  srand(time(NULL));
  int n;
  if (argc == 2) {
    n = atoi(argv[1]);
  } else {
    n = 1 + rand() % 100;
  }
  vector<vector<int>> A(generate_spiral_matrix(n));
  for (int i = 0; i < A.size(); ++i) {
    for (int j = 0; j < A[i].size(); ++j) {
      cout << A[i][j] << ' ';
    }
    cout << endl;
  }
  return 0;
}
