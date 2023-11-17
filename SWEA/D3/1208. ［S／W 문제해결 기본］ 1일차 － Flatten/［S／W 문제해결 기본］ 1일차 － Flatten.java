import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int dump = sc.nextInt();
            int[] boxes = new int[100];
            for(int i = 0; i < 100; i++) {
            	boxes[i] = sc.nextInt();
            }
            
            for(int i = 0; i < dump; i++) {
            	int[] resultIndex = findMinAndMax(boxes);
                boxes[resultIndex[0]]++;
                boxes[resultIndex[1]]--;
            }
            
			int min = Arrays.stream(boxes).min().orElse(0);
            int max = Arrays.stream(boxes).max().orElse(0);
            System.out.println("#" + test_case + " " + (max - min));
		}
	}
    
    static int[] findMinAndMax(int[] boxes) { 
    	int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        int minIndex = 0;
        int maxIndex = 0;
        for(int i=0; i<100; i++) {
            int box = boxes[i];
            if(min > box) {
                min = box;
                minIndex = i;
            }
            if(max < box) {
           		max = box;
                maxIndex = i;
            }
        }
        return new int[]{minIndex, maxIndex};
    }
}