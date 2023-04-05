import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, 1, N + 1);
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if(isPossible(i))
                ans++;
        }

        System.out.println(ans);

    }

    static boolean isPossible(int target_idx) {
        int L = 1, R = N; // 시작 L, 끝 R
        int target = arr[target_idx];

        while (L < R) {
            if(L == target_idx) L++;
            else if(R == target_idx) R--;
            else{
                if(arr[L] + arr[R] == target)
                    return true;
                else if(arr[L] + arr[R] > target)
                    R--;
                else
                    L++;
            }
        }

        return false;
    }
}
