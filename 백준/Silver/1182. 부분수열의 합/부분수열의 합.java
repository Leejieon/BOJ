import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S, answer;
    static int[] numbers;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
        for (int i = 1; i <= N; i++) {
            combination(0, i, 0, 0);
        }
        System.out.println(answer);
    }

    static void combination(int depth, int k, int count, int sum) {
        // Base Case
        if (count == k) {
            if (sum == S) {
                answer++;
            }
            return;
        }

        if (depth == N) {
            return;
        }

        // Recursive Case
        combination(depth + 1, k, count, sum);
        combination(depth + 1, k, count + 1, sum + numbers[depth]);
    }
}