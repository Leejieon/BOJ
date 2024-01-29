import java.util.Arrays;
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
            int N = sc.nextInt();
            int[] numbers = new int[N];
            int[] dp = new int[N];

            for (int i = 0; i < N; i++) {
                numbers[i] = sc.nextInt();
            }
            
            for(int i = 0; i < N; i++) {
                dp[i] = 1;
                for(int j = 0; j < i; j++) {
                    if(numbers[i] > numbers[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            System.out.println("#" + test_case + " " + Arrays.stream(dp).max().getAsInt());
        }
    }
}