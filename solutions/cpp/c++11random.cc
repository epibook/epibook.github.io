// From http://en.cppreference.com/w/cpp/numeric/random
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <random>
#include <string>

int main() {
  // Seed with a real random value, if available
  std::random_device rd;
  std::default_random_engine e1(rd());
  std::uniform_int_distribution<int> uniform_dist_int(1, 10);
  std::uniform_real_distribution<double> uniform_dist_real(10.0, 100.0);
  std::uniform_real_distribution<double> uniform_01(0.0, 1.0);

  std::map<int, int> hist_int;
  std::map<int, int> hist_3;
  std::map<int, int> hist_4;
  std::map<double, int> hist_real;
  int N = 10000;
  int granularity = 1000;
  for (int n = 0; n < N; ++n) {
    int sample_int = uniform_dist_int(e1);
    double sample_real = uniform_dist_real(e1);
    // std::cout << sample_int << "\n";
    // std::cout << sample_real << "\n";
    ++hist_int[sample_int];
    ++hist_real[std::round(sample_real)];
    ++hist_3[std::trunc(3.0 * uniform_01(e1))];
    ++hist_4[std::trunc(4.0 * uniform_01(e1))];
  }
  std::cout << "Uniform int distribution in [1,10) "
            << ":\n";
  for (auto p : hist_int) {
    std::cout << std::fixed << std::setprecision(1) << std::setw(2) << p.first
              << ' ' << std::string(p.second / (N / granularity), '*')
              << '\n';
  }
  std::cout << "Uniform real distribution in [10.0,100.0) "
            << ":\n";
  for (auto p : hist_real) {
    std::cout << std::fixed << std::setprecision(1) << std::setw(2) << p.first
              << ' ' << std::string(p.second / (N / granularity), '*')
              << '\n';
  }
  std::cout << "Uniform int distribution in [0,2] "
            << ":\n";
  for (auto p : hist_3) {
    std::cout << std::fixed << std::setprecision(1) << std::setw(2) << p.first
              << ' ' << std::string(p.second / (N / granularity), '*')
              << '\n';
  }
  std::cout << "Uniform int distribution in [0,3] "
            << ":\n";
  for (auto p : hist_4) {
    std::cout << std::fixed << std::setprecision(1) << std::setw(2) << p.first
              << ' ' << std::string(p.second / (N / granularity), '*')
              << '\n';
  }
}
