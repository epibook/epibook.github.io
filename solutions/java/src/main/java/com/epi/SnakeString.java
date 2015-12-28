package com.epi;

import java.util.Random;

public class SnakeString {
   public static void main(String[] args) {
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent mollis, purus id scelerisque " +
                "sollicitudin, nisl urna laoreet mauris, eget vestibulum nibh ligula at augue. Donec et finibus";

        int verticalHt = 20;

        printSin(s,verticalHt);
    }

    private static void printSin(String s, int verticalHt) {
        char[][] grid = new char[verticalHt][s.length()];
        double v = (Math.PI*10)/(s.length());
        double x=0;
        for (int i=0; i<s.length(); i++)
        {
            int h = (int) (verticalHt*Math.sin(x)/2)+verticalHt/2;
            if(h==verticalHt)
             h--;
            /**
             * h would be calculated based on sin function 
             * Sin would return values between -1 and +1 , 
             * which should corrosponds to 0 to vertical height of grid
             * -1 should give 0
             * 0 should give verticalHt/2
             * +1 should give (verticalHt-1)
             * intermediate values are dependent on value of Sine function 
             */
            x+=v;
            System.out.println(h);
            grid[h][i] = s.charAt(i);
        }
        for (int i=0; i< verticalHt; i++)
        {
            for (int j = 0; j < s.length(); j++) {
                if(grid[i][j]!=0)
                    System.out.print(grid[i][j]);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}
