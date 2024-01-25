import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] numbers, order;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        order = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        permutation(0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void permutation(int depth, int prev) {
        // Base Case
        if (depth == M) {
            for (int number : order) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        // Recursive Case
        int prevNum = 0;
        for (int cand = prev; cand < numbers.length; cand++) {
            if (!visited[cand] && prevNum != numbers[cand]) {
                visited[cand] = true;
                order[depth] = numbers[cand];
                prevNum = numbers[cand];
                permutation(depth + 1, cand);
                visited[cand] = false;
            }

        }
    }
}