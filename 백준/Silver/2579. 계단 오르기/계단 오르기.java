import java.util.Scanner;

public class Main {
    static int N;
    static int[] stair;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        stair = new int[N + 1];
        // dp[i][0] : stair[i-2]를 밟고 오는 경우
        // dp[i][1] : stair[i-1]을 밟고 오는 경우
        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            stair[i] = sc.nextInt();
        }
        
        dp[1][0] = 0;
        dp[1][1] = stair[1];

        if (N >= 2) {
            dp[2][0] = stair[2];
            dp[2][1] = stair[1] + stair[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i][0] = Math.max(dp[i - 2][0] + stair[i], dp[i - 2][1] + stair[i]);
            dp[i][1] = dp[i - 1][0] + stair[i];
        }

        System.out.println(Math.max(dp[N][0], dp[N][1]));
        
    }
}
