/*
 * 3. The baby is here. Your life is changed forever. But first things first, you need to get diapers. This baby is going through diapers like a legend, so you will need a program to calculate how long the diapers you have will last.

Given the number of diapers you have, and how many diapers per day this legendary baby is using, your task is to write a program to calculate how many days you have until you will have to buy more diapers.

Input Format
The input starts with a number T that represents the number of test cases in the file.
Each test case is represented on a line that contains two integers D, and B, representing the number of diapers you have now, and the number of diapers per day the baby is using, respectively.

Constraints
1 <= T <= 1,000
1 <= D <= 10^9
1 <= B <= 10^9

Output Format
output single integer M that represent the number of days you have left before you will need to buy more diapers.

Sample Input 0
3
20 5
20 9
10 10

Sample Output 0
4
2
1
 */
package ProgrammingLeague2020;

import java.util.Scanner;

public class TheLegendOfDiapers {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        short T = s.nextShort();
        int[] D = new int[T];
        int[] B = new int[T];
        int[] output = new int[T];
        
        for(short i = 0; i < T; i++){
            D[i] = s.nextInt();
            B[i] = s.nextInt();
            output[i] = D[i] / B[i];
        }
        
        for(int out : output) System.out.println(out);
        
    }
}
