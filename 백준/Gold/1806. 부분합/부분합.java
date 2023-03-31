import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int R = 0, sum = 0, ans = N+1;
        for (int L = 1; L <= N; L++) {
            // L-1을 구간에서 제외하기
            sum -= numbers[L - 1];

            // R을 옮길 수 있을 때까지 옮기기
            while (R + 1 <= N && sum < S) {
                R++;
                sum += numbers[R];
            }

            // [L...R]의 합이 조건을 만족하면 정답 갱신하기
            if (sum >= S) {
                ans = Math.min(ans, R - L + 1);
            }
        }

        if (ans == N + 1) {
            System.out.println(0);
        }
        else
            System.out.println(ans);

    }
}
