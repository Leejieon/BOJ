import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int t = sc.nextInt();
            
            int[] score = new int[1000];
            for(int i = 0; i < 1000; i++) {
            	score[sc.nextInt()]++;
            }
            
            int max = Integer.MIN_VALUE;
            int result = 0;
            for(int i = 0; i < 1000; i++) {
            	if(score[i] >= score[result]) {
                	result = i;
                }
            }
            
            System.out.println("#" + test_case + "  " + result);
		}
	}
}