import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] dp = new int[N+1][3]; // 0 : R, 1 : G, 2 : B
        dp[1][0] = sc.nextInt();
        dp[1][1] = sc.nextInt();
        dp[1][2] = sc.nextInt();

        for (int i = 2; i <= N ; i++) {
            int R = sc.nextInt(); // R
            int G = sc.nextInt(); // G
            int B = sc.nextInt(); // B

            dp[i][0] = R + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = G + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = B + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        Arrays.sort(dp[N]);

        int ans = dp[N][0];
        System.out.println(ans);
    }
}
