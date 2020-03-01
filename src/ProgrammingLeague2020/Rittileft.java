/*
 * Open Category
Your little brother is sad, because he can not play Rittileft. So you want to help him.

In Rittileft, there is a stright path made of N steps. Each step have two numbers, one on the left and one on the right.

You can collect the points by stepping on the number, but you cannot step on a number that is divisible by 10.

Your goal is to obtain the maximum sum of the numbers you walk on them.

Input Format
First line contain single integer N
Each of the following lines contain two integers R and L

Constraints
1 <= N <= 10,000
1 <= R, L <= 1,000

Output Format
If you brother cannot complete the game, print -1
On other cases, print the maximum sum

Sample Input 0
5
8 10
3 23
10 9
56 4
4 100

Sample Output 0
100

Explanation 0
8	10
3	23
10	9
56	4
4	100
from the top, selecting 8 + 23 + 9 + 56 + 4 which sums up to 100

Sample Input 1
3
9 3
200 10
88 3

Sample Output 1
-1

Explanation 1
two numbers that are divisible by 10 are blocking the way
 */
package ProgrammingLeague2020;

import java.util.Scanner;

public class Rittileft{
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        short N = s.nextShort();
        short[] L = new short[N];
        short[] R = new short[N];
        int sum = 0;
        
        for(short i = 0; i < N; i++){
            L[i] = s.nextShort();
            R[i] = s.nextShort();
        }
        
        for(short i = 0; i < N; i++){
            
            if(L[i] % 10 == 0 && R[i] % 10 == 0){
                sum = -1;
                break;
            } else if (L[i] % 10 == 0){
                sum += R[i];
            } else if (R[i] % 10 == 0){
                sum += L[i];
            } else {
                if(L[i] > R[i]) sum += L[i];
                else sum += R[i];
            }
        }
        System.out.println(sum);
    }
}
