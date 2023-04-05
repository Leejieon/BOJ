import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[] cnt = new int[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long ans = 0;
        for (int L = 1, R = 0; L <= N; L++) {
            // R을 옮길 수 있는 만큼 옮기기
            while (R + 1 <= N && cnt[arr[R + 1]] == 0) {
                R++;
                cnt[arr[R]]++;
            }

            // 정답 갱신
            ans += R - L + 1;

            // L을 옮겨주면서 arr[L]의 개수를 감소시키기
            cnt[arr[L]]--;
        }

        System.out.println(ans);
    }
}
