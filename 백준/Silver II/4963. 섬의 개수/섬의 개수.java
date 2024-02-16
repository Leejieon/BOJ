import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int w, h;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            visited = new boolean[h][w];
            graph = new int[h][w];
            for (int y = 0; y < h; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < w; x++) {
                    graph[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (!visited[y][x] && graph[y][x] == 1) {
                        bfs(y, x);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[y][x] = true;
        queue.offer(new int[]{y, x});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] d : dir) {
                int dy = point[0] + d[0];
                int dx = point[1] + d[1];

                if(isOutOfBound(dy, dx)) continue;
                if(graph[dy][dx] == 0) continue;
                if(visited[dy][dx]) continue;

                visited[dy][dx] = true;
                queue.offer(new int[]{dy, dx});
            }
        }
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= h || x >= w;
    }
}