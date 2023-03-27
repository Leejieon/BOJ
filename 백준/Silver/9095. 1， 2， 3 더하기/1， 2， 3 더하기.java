import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int[] dp = new int[15];

            // dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for (int j = 4; j <= 11; j++) {
                dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
            }

            System.out.println(dp[N]);

        }

    }
}
