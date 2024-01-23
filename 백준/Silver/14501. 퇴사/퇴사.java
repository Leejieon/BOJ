import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[][] consulting;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        consulting = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            consulting[i][0] = Integer.parseInt(st.nextToken());
            consulting[i][1] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MIN_VALUE;
        recur(0, 0);
        System.out.println(answer);
    }

    static void recur(int day, int price) {
        // Base Case
        if (day >= N) {
            answer = Math.max(answer, price);
            return;
        }
        
        // Recursive Case
        // 상담일이 넘어 가지 않는 범위에서 동작
        if (day + consulting[day][0] <= N) {
            recur(day + consulting[day][0], price + consulting[day][1]);
        } else {
            recur(day + consulting[day][0], price);
        }

        // 상담하지 않고 그냥 넘어가는 경우
        recur(day + 1, price);
    }
}