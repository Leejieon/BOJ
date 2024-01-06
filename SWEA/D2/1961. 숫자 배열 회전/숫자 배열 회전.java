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
			int n = sc.nextInt();
            int[][] board = new int[n][n];
            
            for(int row = 0; row < n; row++) {
                for(int col = 0; col < n; col++) {
                    board[row][col] = sc.nextInt();
                }
            }
            
            int[][] arr1 = new int[n][n]; //90
            int[][] arr2 = new int[n][n]; //180
            int[][] arr3 = new int[n][n]; //270
            
              for(int row = 0; row < n; row++) {
                for(int col = 0; col < n; col++) {
                    arr1[row][col] = board[n - 1 - col][row];
                    arr2[row][col] = board[n - 1 - row][n - 1 - col];
                    arr3[row][col] = board[col][n - 1 - row];
                }
            }
            
            System.out.println("#" + test_case);
            for(int row = 0; row < n; row++) {
            	// arr1
                for(int col = 0; col < n; col++) {
                	System.out.print(arr1[row][col]);
                }
                System.out.print(" ");
                // arr2
                for(int col = 0; col < n; col++) {
                	System.out.print(arr2[row][col]);
                }
                System.out.print(" ");
                // arr3
                for(int col = 0; col < n; col++) {
                	System.out.print(arr3[row][col]);
                }
                System.out.println();
            }
		}
	}
}