package com.epi;

import java.io.*;

import static com.epi.utils.Utils.close;

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

    close(filePtr);

    lastNLines.reverse();
    return lastNLines.toString();
  }
  // @exclude

  public static void main(String[] args) throws IOException {
    System.out.println("Usage: file name and tail count");

    int tailCount = 10;
    String fileName;
    if (args.length == 1) {
      fileName = args[0];
    } else if (args.length == 2) {
      fileName = args[0];
      tailCount = Integer.valueOf(args[1]);
    } else {
      return;
    }

    String output = tail(fileName, tailCount);
    System.out.println(output);

    System.out.println();
    System.out.println(
        String.format("Show last %d lines from file %s", tailCount, fileName));
    System.out.println();

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

    close(br);
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

    close(is);
    return (count == 0 && !empty) ? 1 : count;
  }
}
