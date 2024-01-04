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
			int[][] board = new int[9][9];
            for(int row = 0; row < 9; row++) {
            	for(int col = 0; col < 9; col++) {
                	board[row][col] = sc.nextInt();
                }
            }
            
            System.out.println("#" + test_case + " " + check(board));
		}
	}
    
    static int check(int[][] board) {
    	int result = 1;
        
        int[] number = new int[10];
        // check row
        for(int row = 0; row < 9; row++) {
        	number = new int[10];
            for(int col = 0; col < 9; col++) {
            	number[board[row][col]]++;
                if(number[board[row][col]] > 1) {
                	result = 0;
                    return result;
                }
            }
        }
        
        // check col
        for(int col = 0; col < 9; col++) {
        	number = new int[10];
            for(int row = 0; row < 9; row++) {
            	number[board[row][col]]++;
                if(number[board[row][col]] > 1) {
                	result = 0;
                    return result;
                }
            }
        }
        
        //check box
        for(int row = 0; row < 9; row+=3) {
        	for(int col = 0; col < 9; col+=3) {
                if(!checkBox(board, row, col)) {
                	result = 0;
                    return result;
                }
            }
        }
        
        return result;
    }
    
    static boolean checkBox(int[][] board, int r, int c) {
        int[] number = new int[10];
    	for(int row = r; row < r + 3; row++) {
        	for(int col = c; col < c + 3; col++) {
                number[board[row][col]]++;
                if(number[board[row][col]] > 1){
                	return false;
                }
            }
        }
        return true;
    }
    
}