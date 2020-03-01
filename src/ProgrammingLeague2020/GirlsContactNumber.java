/*
 * 2. There was this girl everyone wanted to get her number, but she wouldn’t give her number that easily. If she didn’t want to give her number to someone, she would answer “If it’s meant to be, you will guess it”. In some cases, she would give only the first few digits of her number and say “If it’s meant to be, you will guess the rest of it”.

In this problem, your task is to find out the chances someone can guess her number correctly.

Given the number of digits in her phone number N and the number of known digits as described above X. Knowing that each digit has an equal chance of being between 0 and 9 inclusive, and given Y number of unique trials to guess the phone number, what is the probability that you get the number right?

Input Format
The input starts with a number T that represents the number of test cases.
Each test case consists of one line that contains three integers N, X, and Y, respectively.

Constraints
1 <= T <= 1,000
1 <= N <= 20
0 <= X <= N
0 <= Y <= 10^9

Output Format
The output for each test case is in this form:
k. ans%

where k represents the test case number (starting at 1), and "ans" is the probability the phone number is guessed correctly in percentage printed with accuracy of 2 decimal points.

Sample Input 0
3
3 0 10
7 4 2
7 0 2000

(1/10) ^ 3 -> *10 *100 == 1.00
(1/10) ^ (7-4) *2 *100 == 0.20
(1/10) ^ 7 -> *2000 *100 == 0.02

Sample Output 0
1. 1.00%
2. 0.20%
3. 0.02%
 */
package ProgrammingLeague2020;

import java.util.Scanner;

public class GirlsContactNumber {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        short T = s.nextShort();
        byte[] N = new byte[T];
        byte[] X = new byte[T];
        int[] Y = new int[T];
        double[] answer = new double[T];
        
        for(short t = 0; t < T; t++){
            N[t] = s.nextByte();
            X[t] = s.nextByte();
            Y[t] = s.nextInt();
            answer[t] = Math.pow((1/10.0), (N[t] - X[t])) * Y[t] * 100;
        }
        
        for(int i = 0; i < answer.length; i++){
            
            if(answer[i] > 100){
                System.out.println((i+1) + ". 100.00%");
            } else {
                // Math Floor to avoid rounding off
                System.out.printf("%d. %.2f%%\n", (i+1), Math.floor(answer[i] * 100) / 100.0);
            }
        }
        
    }
}
