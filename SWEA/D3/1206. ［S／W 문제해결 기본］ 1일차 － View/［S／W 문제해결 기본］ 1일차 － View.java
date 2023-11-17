import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T= 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int length = sc.nextInt();
            int[] buildings = new int[length];
            for(int i=0; i<length; i++) {
            	buildings[i] = sc.nextInt();
            }
            
            int answer = 0;
            for(int i = 0; i < length; i++) {
                int max = getMaxBuilding(buildings, i);
                
                int possible = buildings[i] - max;
                if(possible > 0) {
                	answer += possible;
                }
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
    static int getMaxBuilding(int[] bulidings, int index) {
   		List<Integer> beside = new ArrayList<>();
        
        if(index - 2 >= 0) {
            beside.add(bulidings[index - 2]);
        }
        if(index - 1 >= 0) {
            beside.add(bulidings[index - 1]);
        }
        if(index + 1 < bulidings.length) {
            beside.add(bulidings[index + 1]);
        }
        if(index + 2 < bulidings.length) {
            beside.add(bulidings[index + 2]);
        }
        
        return beside.stream()
            .mapToInt(Integer::intValue)
            .max()
            .orElse(0);
    }
    
}