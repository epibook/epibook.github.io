package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class GaussianPrimes {
  // @include
  public static class Complex implements Comparable<Complex> {
    private int real;
    private int imag;

    public Complex(int real, int imag) {
      this.real = real;
      this.imag = imag;
    }

    public int getReal() { return real; }

    public int getImag() { return imag; }

    public int getNorm() { return real * real + imag * imag; }

    public Complex multiply(Complex p) {
      return new Complex(real * p.getReal() - imag * p.getImag(),
                         real * p.getImag() + imag * p.getReal());
    }

    @Override
    public int compareTo(Complex o) {
      int result = Integer.compare(getNorm(), o.getNorm());
      if (result == 0) {
        result = Integer.compare(getReal(), o.getReal());
      }
      if (result == 0) {
        result = Integer.compare(getImag(), o.getImag());
      }
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Complex)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      Complex that = (Complex)obj;
      return getReal() == that.getReal() && getImag() == that.getImag();
    }

    // clang-format off
    @Override
    public int hashCode() { return Objects.hash(getReal(), getImag()); }
    // clang-format on

    // clang-format off
    @Override
    public String toString() { return "(" + real + "," + imag + ")"; }
    // clang-format on
  }

  public static List<Complex> generateGaussianPrimes(int n) {
    SortedSet<Complex> candidates = new TreeSet<>();
    List<Complex> primes = new ArrayList<>();

    // Generate all possible Gaussian prime candidates.
    for (int i = -n; i <= n; ++i) {
      for (int j = -n; j <= n; ++j) {
        if (!isUnit(new Complex(i, j)) && i != 0 && j != 0) {
          candidates.add(new Complex(i, j));
        }
      }
    }

    while (!candidates.isEmpty()) {
      Complex p = candidates.first();
      primes.add(p);
      candidates.remove(p);
      int maxMultiplier = (int)Math.ceil(Math.sqrt(2.0) * n
                                         / Math.floor(Math.sqrt(p.getNorm())));

      // Any Gaussian integer outside the range we're iterating
      // over below has a modulus greater than maxMultiplier.
      for (int i = maxMultiplier; i >= -maxMultiplier; --i) {
        for (int j = maxMultiplier; j >= -maxMultiplier; --j) {
          Complex x = new Complex(i, j);
          if (Math.floor(Math.sqrt(x.getNorm())) > maxMultiplier) {
            // Skip multipliers whose modulus exceeds maxMultiplier.
            continue;
          }
          if (!isUnit(x)) {
            candidates.remove(x.multiply(p));
          }
        }
      }
    }
    return primes;
  }

  private static boolean isUnit(Complex z) {
    return (z.getReal() == 1 && z.getImag() == 0)
        || (z.getReal() == -1 && z.getImag() == 0)
        || (z.getReal() == 0 && z.getImag() == 1)
        || (z.getReal() == 0 && z.getImag() == -1);
  }
  // @exclude

  private static List<Complex> generateGaussianPrimesCanary(int n) {
    SortedSet<Complex> candidates = new TreeSet<>();
    List<Complex> primes = new ArrayList<>();

    // Generate all possible Gaussian prime candidates.
    for (int i = -n; i <= n; ++i) {
      for (int j = -n; j <= n; ++j) {
        if (!isUnit(new Complex(i, j)) && i != 0 && j != 0) {
          candidates.add(new Complex(i, j));
        }
      }
    }

    while (!candidates.isEmpty()) {
      Complex p = candidates.first();
      primes.add(p);
      candidates.remove(p);
      int maxMultiplier = n;

      for (int i = maxMultiplier; i >= -maxMultiplier; --i) {
        for (int j = maxMultiplier; j >= -maxMultiplier; --j) {
          Complex x = new Complex(i, j);
          if (!isUnit(x)) {
            candidates.remove(x.multiply(p));
          }
        }
      }
    }
    return primes;
  }

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(100) + 1;
    }

    for (int i = 1; i <= n; ++i) {
      System.out.println("n = " + i);
      List<Complex> first = generateGaussianPrimesCanary(i);
      List<Complex> gPrimes = generateGaussianPrimes(i);
      System.out.println(first.size() + " " + gPrimes.size());
      for (int j = 0; j < first.size(); ++j) {
        if (first.get(j).getReal() != gPrimes.get(j).getReal()
            || first.get(j).getImag() != gPrimes.get(j).getImag()) {
          System.out.print(first.get(j) + " ");
          System.out.print(gPrimes.get(j) + " ");
        }
      }
      for (int j = first.size(); j < gPrimes.size(); ++j) {
        System.out.print(gPrimes.get(i) + " ");
      }
      assert(first.size() == gPrimes.size());
    }
  }
}
