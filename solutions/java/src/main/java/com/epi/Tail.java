package com.epi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Tail {
  // @include
  public static String tail(String fileName, int N) throws IOException {
    RandomAccessFile filePtr = new RandomAccessFile(fileName, "r");

    filePtr.seek(filePtr.length() - 1);
    long fileSize = filePtr.length(), newLineCount = 0;
    StringBuilder lastNLines = new StringBuilder();
    // Reads file in reverse looking for '\n'.
    for (long i = fileSize - 1; i != -1; i--) {
      filePtr.seek(i);
      int readByte = filePtr.readByte();
      char c = (char)readByte;
      if (c == '\n') {
        ++newLineCount;
        if (newLineCount > N) {
          break;
        }
      }
      lastNLines.append(c);
    }

    filePtr.close();

    lastNLines.reverse();
    return lastNLines.toString();
  }
  // @exclude

  private static void simpleTest() {
    try {
      File testFile = File.createTempFile("TailTest", ".txt");
      String L1 = "The first line";
      String L2 = "The second line";
      String L3 = "The third line";
      String L4 = "The fourth line";
      PrintWriter writer = new PrintWriter(testFile);
      writer.println(L1);
      writer.println(L2);
      writer.println(L3);
      writer.println(L4);
      writer.close();
      String fileName = testFile.getAbsolutePath();
      String output = tail(fileName, 1);
      assert(output.equals(L4 + "\n"));
      output = tail(fileName, 2);
      assert(output.equals(L3 + "\n" + L4 + "\n"));
      output = tail(fileName, 3);
      assert(output.equals(L2 + "\n" + L3 + "\n" + L4 + "\n"));
      output = tail(fileName, 4);
      assert(output.equals(L1 + "\n" + L2 + "\n" + L3 + "\n" + L4 + "\n"));
    } catch (IOException e) {
      e.printStackTrace();
      assert(false);
    }
  }

  public static void main(String[] args) throws IOException {
    simpleTest();
    System.out.println("Usage: file name and tail count");

    int tailCount = 10;
    String fileName;
    if (args.length == 1) {
      fileName = args[0];
    } else if (args.length == 2) {
      fileName = args[0];
      tailCount = Integer.parseInt(args[1]);
    } else {
      return;
    }

    String output = tail(fileName, tailCount);
    System.out.println(output);

    System.out.println();
    System.out.println(
        String.format("Last %d lines from file %s", tailCount, fileName));
    System.out.println();

    System.out.println("Brute force solution:");
    show(fileName, tailCount);
  }

  /*
   * Show a number of last lines from a file.
   *
   * This is a naive implementation. It first counts the total number of
   * new lines, then reads the file again, this time reading last X lines.
   */
  private static void show(String fileName, int lines) throws IOException {
    BufferedReader br = null;
    String currentLine;

    int totalLines = countNewLines(fileName);
    br = new BufferedReader(new FileReader(fileName));

    int lineCounter = 0;
    while ((currentLine = br.readLine()) != null) {
      lineCounter++;
      if (lineCounter > totalLines - lines) {
        System.out.println(currentLine);
      }
    }

    br.close();
  }

  // A slightly modified version of answer given here:
  // http://stackoverflow.com/a/453067/1059744
  private static int countNewLines(String filename) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(filename));
    byte[] c = new byte[1024];
    int count = 0;
    int readChars = 0;
    boolean empty = true;

    while ((readChars = is.read(c)) != -1) {
      empty = false;
      for (int i = 0; i < readChars; ++i) {
        if (c[i] == '\n') {
          ++count;
        }
      }
    }

    is.close();
    return (count == 0 && !empty) ? 1 : count;
  }
}
