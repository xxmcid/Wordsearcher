// Humza Rahman
// NID: hu688213
// Dr. Tanvir Ahmed | COP3503 | Spring 2022
// Assignment 02: Backtracking

import java.io.*;
import java.util.*;

public class Main
{
  public static String solution [] [];
  public static boolean visited [] [];

  public static boolean isSafe(int row, int col)
  {
    if (row < 0 || row >= solution.length)
      return false;
    if (col < 0 || col >= solution[0].length)
      return false;
    if (visited[row][col])
      return false;

    return true;
  }

  // Finds which letter to find first and begin searching for word.
  public static boolean wrapper(String wordtoFind, String matrix [] [])
  {
    for (int i = 0; i < matrix.length; i++)
    {
      for (int j = 0; j < matrix[0].length; j++)
      {
        if (wordSearch(wordtoFind, matrix, i, j, 0))
          return true;
      }
    }

    return false;
  }

  public static boolean wordSearch(String wordtoFind, String matrix [] [], int row, int col, int curLetter)
  {
    // Base case for success.
    // If we're able to even reach the end of the word.
    if (curLetter == wordtoFind.length() - 1 && matrix[row][col].charAt(0) == wordtoFind.charAt(wordtoFind.length() - 1))
    {
      solution[row][col] = matrix[row][col];
      return true;
    }

    // Base case for out of bounds and non-matching letters.
    // Implicit conversion of string whitespace to char whitespace
    if (solution[row][col].charAt(0) != ' ' || wordtoFind.charAt(curLetter) != matrix[row][col].charAt(0))
    {
      return false;
    }

    // UPDATE STATE IN SOLUTION MATRIX
    solution[row][col] = matrix[row][col];
    visited[row][col] = true;

    // Checking right
    if (isSafe(row, col + 1))
      if (wordSearch(wordtoFind, matrix, row, col + 1, curLetter + 1))
        return true;

    // Checking down
    if (isSafe(row + 1, col))
      if (wordSearch(wordtoFind, matrix, row + 1, col, curLetter + 1))
        return true;

    // Checking up
    if (isSafe(row - 1, col))
      if (wordSearch(wordtoFind, matrix, row - 1, col, curLetter + 1))
        return true;

    // Checking left
    if (isSafe(row, col - 1))
      if (wordSearch(wordtoFind, matrix, row, col - 1, curLetter + 1))
        return true;

    // Checking down right diagonal
    if (isSafe(row + 1, col + 1))
      if (wordSearch(wordtoFind, matrix, row + 1, col + 1, curLetter + 1))
        return true;

    // Checking down left diagonal
    if (isSafe(row + 1, col - 1))
      if (wordSearch(wordtoFind, matrix, row + 1, col - 1, curLetter + 1))
        return true;

    // Checking upper left diagonal
    if (isSafe(row - 1, col - 1))
      if (wordSearch(wordtoFind, matrix, row - 1, col - 1, curLetter + 1))
        return true;

    // Checking upper right diagonal
    if (isSafe(row - 1, col + 1))
      if (wordSearch(wordtoFind, matrix, row - 1, col + 1, curLetter + 1))
        return true;

    // UNDO STATE CHANGE
    solution[row][col] = Character.toString(' ');
    visited[row][col] = false;
    return false;
  }

  public static void main(String[] args) throws IOException
  {
    String matrix [] [];
    String wordList [];
    BufferedReader stdin = new BufferedReader(new FileReader("in.txt"));
    StringTokenizer tok = new StringTokenizer(stdin.readLine());
    int k = Integer.parseInt(tok.nextToken()); // Number of testcases.

    for (int i = 0; i < k; i++)
    {
      tok = new StringTokenizer(stdin.readLine());
      int m = Integer.parseInt(tok.nextToken()); // Num rows
      int n = Integer.parseInt(tok.nextToken()); // Num cols
      int s = Integer.parseInt(tok.nextToken()); // Num words to search.

      // Create array to store words to search.
      wordList = new String [s];
      // Make our m x n matrix
      matrix = new String [m][n];

      // Populate newly created matrix with letters
      for (int row = 0; row < m; row++)
      {
        tok = new StringTokenizer(stdin.readLine());
        for (int col = 0; col < n; col++)
        {
          matrix[row][col] = tok.nextToken();
        }
      }

      // Populate wordList
      for (int w = 0; w < s; w++)
      {
        tok = new StringTokenizer(stdin.readLine());
        wordList[w] = tok.nextToken();
      }

      System.out.println("Test#" + (i + 1));
      for (int word = 0; word < s; word++)
      {
        System.out.println("Looking for " + wordList[word]);

        // Initialize solution matrix.
        solution = new String [m][n];
        for (String [] row : solution)
          Arrays.fill(row, Character.toString(' '));

        // Initialize visited matrix
        visited = new boolean [m][n];
        for (boolean [] bools : visited)
          Arrays.fill(bools, false);

        // Call backtracking utility and print results.
        if (wrapper(wordList[word], matrix))
        {
          for (String [] solRow : solution)
            System.out.println(Arrays.toString(solRow));
          System.out.println();
        }
        else
        {
          System.out.println(wordList[word] + " not found!");
        }
      }

      System.out.println();
    }


  } // end main
}
