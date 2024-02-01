import java.util.Arrays;
import java.util.Scanner;

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
            int M = sc.nextInt(); // 파리채 크기

            int[][] graph = new int[N][N];
            for(int y = 0; y < N; y++) {
                for(int x = 0; x < N; x++) {
                    graph[y][x] = sc.nextInt();
                }
            }

            int max = 0;
            for (int y = 0; y <= N - M; y++) {
                for (int x = 0; x <= N - M; x++) {
                    int sum = 0;
                    for (int i = y; i < y + M; i++) {
                        for (int j = x; j < x + M; j++) {
                            sum += graph[i][j];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }

            System.out.println("#" + test_case + " " + max);

        }
    }
}