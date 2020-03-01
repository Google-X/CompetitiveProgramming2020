/*
 * 5. Jon wants to watch movies and TV shows, but there are just too many to pick from, what’s more to say is that most of the showtime timeslots are intersecting with one another. The dilemma has been bugging him from time to time. He has a list of TV shows and movies which he favours on his plate, a TV show plays its next episode after a certain interval in a repetitive pattern while a movie can be finished in one go.

You are concerned about this condition, Jon is your best friend and he is getting more and more upset about having the showtimes of his favourite movies and TV show all tangled up. Hence, you have decided to develop a program that receives a series of start times, end times and repetition intervals (for TV shows) by receiving preferred groups of TV shows and movies from Jon and decides whether they intersect with one another. (Note: a TV show with start time 2, end time 8 and repetition interval 10 would be occurring at time intervals [2-8], [12-18], [22-28], ...)

It is assumed that the TV shows are never-ending. Touching is accepted (e.g. [4-7] and [7...10] are not considered as overlapping. The condition for a conflict or intersection to happen is when and only when the time intervals overlap, for example [6-8] and [7-10]).

Input Format
There are several test cases T.
Each test case start with two integers m and n which represent the number of movies and the number of TV shows respectively.
The next  lines contain the start time (S) and end times (E) of the movies.
The next  lines contain the start time (S), end time (E), and repetition interval (R) for a TV show.
The input is terminated with test case the have m and n as zeros "0 0"

Constraints
1 <= T <= 20
0 <= S < E < 1,000,000
0 <= R <= 1,000,000

Output Format
For each test case, if there is at least one overlap between the show times, display INTERSECTION, else, display NO INTERSECTION in a single line.

Sample Input 0
3 0
200 500
500 600
600 800
2 0
200 500
400 600
2 0
500 600
500 600
0 0

Sample Output 0
NO INTERSECTION
INTERSECTION
INTERSECTION

Sample Input 1
1 1
1000 2000
0 10 1000
0 0

Sample Output 1
INTERSECTION
 */
package ProgrammingLeague2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WatchingJon {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        int T = 1;
        List<Boolean> intersect = new ArrayList<>();

        while(true){
            int m = s.nextInt();
            int n = s.nextInt();
            
            if(m == 0 && n == 0){
                intersect.forEach((checked) -> {
                    if(checked) System.out.println("INTERSECTION");
                    else System.out.println("NO INTERSECTION");
                });
                break;
            } 
            
            int[] movStart = new int[m];
            int[] movEnd = new int[m];
            int[] tvStart = new int[n];
            int[] tvEnd = new int[n];
            int[] tvRep = new int[n];
            List<Integer> extraTvStart = new ArrayList<>();
            List<Integer> extraTvEnd = new ArrayList<>();
            
            int max = 0;
            for(int i = 0; i < m; i++){
                movStart[i] = s.nextInt();
                movEnd[i] = s.nextInt();
                if(movEnd[i] > max) max = movEnd[i];
            }
            
            for(int i = 0; i < n; i++){
                tvStart[i] = s.nextInt();
                tvEnd[i] = s.nextInt();
                tvRep[i] = s.nextInt();
                
                int temp = tvEnd[i];
                while (temp < max){
                    extraTvStart.add(tvStart[i] + tvRep[i]);
                    extraTvEnd.add(tvEnd[i] + tvRep[i]);
                    temp += tvRep[i];
                }
            }
            
//double startTime = System.nanoTime();
            
            int[] extraTvStartArr = extraTvStart.stream().mapToInt(Integer::intValue).toArray();
            int[] extraTvEndArr = extraTvEnd.stream().mapToInt(Integer::intValue).toArray();
            
            // Checking intersection
            int checkNum = 0;
            CHECKING:
            while(checkNum <= movStart.length + tvStart.length){
                
                // Checking movie with tv shows and extra tv shows
                for(int i = 0; i < movStart.length; i++){
                    checkNum++;
                    
                    // With movie
                    for(int j = 0; j < movStart.length; j++){
                        
                        if(i == j){
                            // do nothing
                        } else {
                            
                            if(movStart[i] >= movStart[j] && movStart[i] < movEnd[j]){
                                intersect.add(true);
                                break CHECKING;
                            } else if (movEnd[i] > movStart[j] && movEnd[i] <= movEnd[j]){
                                intersect.add(true);
                                break CHECKING;
                            }
                            
                        }
                    }
                    
                    // With tv
                    for(int j = 0; j < tvStart.length; j++){
                        if(movStart[i] >= tvStart[j] && movStart[i] < tvEnd[j]){
                            intersect.add(true);
                            break CHECKING;
                        } else if (movEnd[i] > tvStart[j] && movEnd[i] <= tvEnd[j]){
                            intersect.add(true);
                            break CHECKING;
                        } 
                    }
                    
                    // With extra tv
                    for(int j = 0; j < extraTvStartArr.length; j++){
                        if(movStart[i] >= extraTvStartArr[j] && movStart[i] < extraTvEndArr[j]){
                            intersect.add(true);
                            break CHECKING;
                        } else if (movEnd[i] > extraTvStartArr[j] && movEnd[i] <= extraTvEndArr[j]){
                            intersect.add(true);
                            break CHECKING;
                        } 
                    }
                    
                }
                
                // Checking tv shows with extra tv shows
                for(int i = 0; i < tvStart.length; i++){
                    checkNum++;
                    
                    // With tv shows
                    for(int j = 0; j < tvStart.length; j++){
                        
                        if(i == j){
                            // do nothing
                        } else {
                            
                            if(tvStart[i] >= tvStart[j] && tvStart[i] < tvEnd[j]){
                                intersect.add(true);
                                break CHECKING;
                            } else if (tvEnd[i] > tvStart[j] && tvEnd[i] <= tvEnd[j]){
                                intersect.add(true);
                                break CHECKING;
                            }
                            
                        }
                    }
                    
                    for(int j = 0; j < extraTvStartArr.length; j++){
                        if(tvStart[i] >= extraTvStartArr[j] && tvStart[i] < extraTvEndArr[j]){
                            intersect.add(true);
                            break CHECKING;
                        } else if (tvEnd[i] > extraTvStartArr[j] && tvEnd[i] <= extraTvEndArr[j]){
                            intersect.add(true);
                            break CHECKING;
                        } 
                    }
                    
                }
                
                if(checkNum == movStart.length + tvStart.length){
                    intersect.add(false);
                }
            }
//            System.out.println("Time taken: " + (System.nanoTime() - startTime) * Math.pow(10, -9) + "s");
        }
    }
}
