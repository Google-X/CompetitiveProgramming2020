/*
 * 1. Ahmad is a system administrator. He handles Linux server. He uses a variant of ps utility to monitor which process it taking up most memory.

Ahmad needs to sort the ps output by the ppid which is the parents process id and then by its mem which is memory. He is more interested about process that use more memory, so mem should be sorted in decreasing order.

Given a sample ps output as input, generate another ps output in increasing ppid, then decreasing mem. If two processshare the same ppid and mem, keep the original relative ordering of the two process.

Input Format

The input first start with a header row, which have three constant word "pid", "ppid" and "mem" separated by a space.
The several lines each consist of three integer , where a is the pid or process id, b is the ppid or parent process id, and c is the mem or memory.

Constraints
0 <= a, b, c <= 10^5

Output Format

Output the sorted ps output in the same format as the input.

Sample Input 0
pid ppid mem
1 0 12
2 1 22
4 1 92
100 4 98
101 4 92

Sample Output 0
pid ppid rss
1 0 12
4 1 92
2 1 22
100 4 98
101 4 92

Sample Input 1
pid ppid mem
1 0 12
2 1 22
4 1 92
103 1 92
102 4 98
101 4 94
100 4 98

Sample Output 1
pid ppid rss
1 0 12
4 1 92
103 1 92
2 1 22
102 4 98
100 4 98
101 4 94

my example
after sort memory
102 8 98
101 4 98

Explanation 1
Notice that process 102 appears before 100 in the input, therefore in the output, 102 appears before 100, no reordering between these two process as they have the same parent and memory usage.
 */
package ProgrammingLeague2020;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExpensiveProcess {
    public static void main(String[] args) {
        
        List<Integer> pidVal = new ArrayList<>();
        List<Integer> ppidVal = new ArrayList<>();
        List<Integer> memVal = new ArrayList<>();
        
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Linux.dat"));
            
            String pid = in.readUTF();
            String ppid = in.readUTF();
            String mem = in.readUTF();
            
            try{
                while(true){
                    
                    pidVal.add(in.readInt());
                    ppidVal.add(in.readInt());
                    memVal.add(in.readInt());
                    
                }
            } catch(EOFException eof){
                
            }
            
            in.close();
        } catch (FileNotFoundException fnf){
            System.err.println("File not found!");
        } catch (IOException ioe){
            System.err.println("Problem with file!");
        }
        
        int[] pidArr = pidVal.stream().mapToInt(i -> i).toArray();
        int[] ppidArr = ppidVal.stream().mapToInt(i -> i).toArray();
        int[] memArr = memVal.stream().mapToInt(Integer::intValue).toArray();
        int memTemp, ppidTemp, pidTemp;
        
        // Sort in increasing ppid
        for(int i = 1; i < ppidArr.length; i++){
            for(int j = 0; j < ppidArr.length - 1; j++){
                if(ppidArr[j] > ppidArr[j+1]){
                    memTemp = memArr[j];
                    memArr[j] = memArr[j+1];
                    memArr[j+1] = memTemp;

                    ppidTemp = ppidArr[j];
                    ppidArr[j] = ppidArr[j+1];
                    ppidArr[j+1] = ppidTemp;

                    pidTemp = pidArr[j];
                    pidArr[j] = pidArr[j+1];
                    pidArr[j+1] = pidTemp;
                }
            }
        }
        
        // Sort in decreasing memory
        for(int i = 1; i < memArr.length; i++){
            for(int j = 0; j < memArr.length - 1; j++){
                if(memArr[j] < memArr[j+1]){
                    if(ppidArr[j] == ppidArr[j+1]){
                        memTemp = memArr[j];
                        memArr[j] = memArr[j+1];
                        memArr[j+1] = memTemp;

                        ppidTemp = ppidArr[j];
                        ppidArr[j] = ppidArr[j+1];
                        ppidArr[j+1] = ppidTemp;

                        pidTemp = pidArr[j];
                        pidArr[j] = pidArr[j+1];
                        pidArr[j+1] = pidTemp;
                    }
                }
            }
        }
        
        
        System.out.println("pid ppid res");
        for(int i = 0; i < pidArr.length; i++){
            System.out.printf("%d %d %d\n", pidArr[i], ppidArr[i], memArr[i]);
        }
        
    }
}
