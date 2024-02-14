import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static long[] numbers;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new long[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];
        init(1, 0, N - 1);

        M += K;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            switch (type) {
                case 1:
                    int index = Integer.parseInt(st.nextToken());
                    long value = Long.parseLong(st.nextToken());
                    update(1, 0, N - 1, index - 1, value);
                    break;
                case 2:
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());
                    sb.append(query(1, 0, N - 1, left - 1, right - 1)).append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = numbers[start];
            return;
        }

        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static void update(int node, int start, int end, int index, long value) {
        // Base Case
        if (index < start || index > end) {
            return;
        }
        if (start == end) {
            numbers[index] = value;
            tree[node] = value;
            return;
        }

        // Recursive Case
        update(node * 2, start, (start + end) / 2, index, value);
        update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        // Base Case
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && right >= end) {
            return tree[node];
        }

        // Recursive Case
        long lsum = query(node * 2, start, (start + end) / 2, left, right);
        long rsum = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return lsum + rsum;
    }
}
