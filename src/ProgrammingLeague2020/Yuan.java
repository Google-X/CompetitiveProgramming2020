/*
 * 4. Yuan (缘) is a Chinese term that is hard to understand for non-Chinese. Yuan is the fate, chance or binding force that brings this person and other people or objects together. It is a concept in Chinese societies describing good and bad chances and potential relationships. Although it is a blind faith, many people, especially girls like to calculate it.

Unfortunately, my wife is one of them. One day, she asked me, “Darling, shall we find out our yuan?” Luckily, I’m a programmer, so I just need to find a good algorithm and write a yuan calculator. After some searching in the web, I decided to implement this yuan algorithm:

Step 1: Get the name abbreviations of the couple and concatenate them. For example, if their name is Muhammad Ali and Tan Yin Sue, the concatenation is MATYS.

Step 2: Replace each character with an integer string. For some predefined positive integer T, replace A with T, and B with T+1, C with T+2, ..., Z with T+25. For example, if T = 12, A should be replaced with 12, B should be replaced with 13, …, Z will be replaced by 37. In the case above, MATYS will be replaced by 2412313630.

Step 3: Repeat the following: sum up each pair of consecutive digits and write down the last digit of each sum. Each time we perform this action, the string length is decreased by 1. End the process when the number string is exactly 100 or has no more than 2 digits. The last number is the yuan between the couple. In the case above, the process is as follows:

2412313630
653544993
18898382
9677110
534821 
87203 
5923
415
56

So if T = 12, Muhammad Ali and Tan Yue Sue’s yuan is only 56! This is not good! I know my wife very well. She’ll still be unsatisfied even if the result is as high as 99. Could you find the value of T such that the yuan between my wife and I is 100?

Input Format
First line contain N, which is the number of testcases.
For each testcase, there is a line containing the name abbreviations A.

Constraints
1 <= N <= 50
4 <= |A| <= 10

Output Format
For each testcase, print the smallest positive integer T.
If T does not exist or it is larger than 10240, print FOREVER ALONE.

Sample Input 0
9
MALAYSIA
ABCDEF
MAYA
UMISONE
FSKTM
PERL
EXTRA
PYTHON
JAVA

COPY: MALAYSIA ABCDEF MAYA UMISONE FSKTM PERL EXTRA PYTHON JAVA

Sample Output 0
982
634
FOREVER ALONE
473
107
3092
10027
351
143
 */
package ProgrammingLeague2020;

import java.util.Scanner;

public class Yuan {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        byte N = s.nextByte();
        s.nextLine();
        String[] name = new String[N];
        short[] finalT = new short[N];
        
        for(byte i = 0; i < N; i++){
            name[i] = s.next();
        }
        
        for(byte i = 0; i < N; i++){
            for(short T = 0; T <= 10240; T++){
                String yuan = "";
                
                for(byte j = 0; j < name[i].length(); j++){
                    short temp = (short) (T + (byte)name[i].charAt(j) - 'A');
                    yuan += temp;
                }
//System.out.println("Yuan: " + yuan);
                
                String calcYuan = yuan;
                while(calcYuan.length() > 3){
                    String tempStr = "";
                    
                    for(byte k = 0; k < calcYuan.length() - 1; k++){
                        byte a = (byte)(Character.getNumericValue(calcYuan.charAt(k)) + Character.getNumericValue(calcYuan.charAt(k+1)));
                        tempStr += (a % 10);
                    }
                    
                    calcYuan = tempStr;
//System.out.println("Calc Yuan: " + calcYuan);
                }
                
                if(calcYuan.equals("100")){
                    finalT[i] = T;
                    break;
                } else if (T == 10240 && !calcYuan.equals("100")){
                    finalT[i] = -1;
                }
            }
        }
        
        for(byte i = 0; i < N; i++){
            if(finalT[i] == -1) System.out.println("FOREVER ALONE");
            else System.out.println(finalT[i]);
        }
    }
}
