import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int R, C, answer;
    static boolean flag;
    static int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};
    static char[][] graph;
    static boolean[][] pipe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pipe = new boolean[R][C];
        graph = new char[R][C];
        for (int y = 0; y < R; y++) {
            graph[y] = br.readLine().toCharArray();
        }

        answer = 0;
        for (int y = 0; y < R; y++) {
            flag = false;
            proceed(y, 0);
        }
        System.out.println(answer);
    }

    static void proceed(int y, int x) {
        // Base Case : 끝에 도달하는 경우
        if (x == C - 1) {
            answer++;
            flag = true;
            return;
        }

        // Recursive Case
        for (int[] d : dir) {
            int dy = y + d[0];
            int dx = x + d[1];

            if(isOutOfBound(dy, dx)) continue;
            if(graph[dy][dx] == 'x') continue;
            if(pipe[dy][dx]) continue;

            pipe[dy][dx] = true;
            proceed(dy, dx);
            if(flag) return;
        }
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= R || x >= C;
    }
}