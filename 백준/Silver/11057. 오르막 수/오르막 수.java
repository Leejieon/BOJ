import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] dp = new int[N + 1][10];
        Arrays.fill(dp[1], 1);

        for (int len = 2; len <= N; len++) {
            for (int last = 0; last <= 9; last++) {
                if (last == 0) {
                    dp[len][last] = dp[len - 1][last] % 10007;
                } else {
                    dp[len][last] = (dp[len - 1][last] + dp[len][last - 1]) % 10007;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= 9; i++) {
            ans += dp[N][i];
            ans %= 10007;
        }
        
        System.out.println(ans);
    }
}
