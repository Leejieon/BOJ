import java.util.Scanner;
import java.util.*;
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
            int N = sc.nextInt();
            int L = sc.nextInt();
            
            // 재료 입력 받기
            List<Component> list = new ArrayList<>();
            for(int i = 0; i < N; i++) {
            	list.add(new Component(sc.nextInt(), sc.nextInt()));
            }
            
            // 칼로리 기준으로 정렬
            list.sort(Comparator.comparingInt(comp -> comp.calorie));
            
            int[][] dp = new int[N + 1][L + 1];
            for(int count = 1; count <= N; count++) {
            	for(int cal = 1; cal < L + 1; cal++) {
                    if(list.get(count - 1).calorie > cal) {
                        dp[count][cal] = dp[count - 1][cal];
                    }
                    else {
                    	dp[count][cal] = Math.max( dp[count - 1][cal], dp[count - 1][cal - list.get(count - 1).calorie] + list.get(count - 1).score );
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + dp[N][L]);
		}
	}
    
    static class Component {
    	int score;
        int calorie;
        
        Component(int score, int calorie) {
            this.score = score;
            this.calorie = calorie;
        }
    }
}