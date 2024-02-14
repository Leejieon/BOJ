import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, Q, size, sum;
    static int[][] graph, clone;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        sum = 0;
        size = (int) Math.pow(2, N);
        graph = new int[size][size];
        for (int y = 0; y < size; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < size; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
                sum += graph[y][x];
            }
        }

        int[] order = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            int length = (int) Math.pow(2, order[i]);

            if (length == 1) {
                reduceIce();
                continue;
            }

            // 배열 회전 수행
            clone = new int[size][size];
            for (int y = 0; y < size; y += length) {
                for (int x = 0; x < size; x += length) {
                    rotateGraph(y, x, length);
                }
            }
            // 기존 배열에 복사
            for (int j = 0; j < size; j++) {
                graph[j] = clone[j].clone();
            }
            // 얼음 확인
            reduceIce();
        }

        int lump = 0;
        visited = new boolean[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (graph[y][x] != 0 && !visited[y][x]) {
                    lump = Math.max(lump, bfs(y, x));
                }
            }
        }

        sb.append(sum)
                .append("\n")
                .append(lump);
        System.out.println(sb);
    }

    static void rotateGraph(int y, int x, int len) {
        // 사각형의 오른쪽 아래 모서리 위치
        int sY = y;
        int sX = x;
        int eY = y + len - 1;
        int eX = x + len - 1;

        while (len > 1) {
            // Top -> Right
            for (int dx = 0; dx < len; dx++) {
                clone[sY + dx][eX] = graph[sY][sX + dx];
            }

            // Right -> Bottom
            for (int dy = 0; dy < len; dy++) {
                clone[eY][eX - dy] = graph[sY + dy][eX];
            }

            // Bottom -> Left
            for (int dx = 0; dx < len; dx++) {
                clone[sY + dx][sX] = graph[eY][sX + dx];
            }

            // Left -> Top
            for (int dy = 0; dy < len; dy++) {
                clone[sY][eX - dy] = graph[sY + dy][sX];
            }

            len -= 2;
            sY++;
            sX++;
            eY--;
            eX--;
        }
    }

    static void reduceIce() {
        Queue<int[]> queue = new ArrayDeque<>();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if(graph[y][x] == 0) continue;
                if (!check(y, x)) {
                    queue.offer(new int[]{y, x});
                }
            }
        }

        sum -= queue.size();
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            graph[point[0]][point[1]]--;
        }
    }

    static boolean check(int y, int x) {
        int count = 0;
        for (int[] d : dir) {
            int dy = y + d[0];
            int dx = x + d[1];
            if (isOutOfBound(dy, dx) || graph[dy][dx] == 0) continue;
            count++;
        }
        return count >= 3;
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= size || x >= size;
    }

    static int bfs(int y, int x) {
        int scale = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        visited[y][x] = true;
        queue.add(new int[]{y, x});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            scale++;

            for (int[] d : dir) {
                int dy = point[0] + d[0];
                int dx = point[1] + d[1];

                if(isOutOfBound(dy, dx)) continue;
                if(visited[dy][dx]) continue;
                if(graph[dy][dx] == 0) continue;

                visited[dy][dx] = true;
                queue.offer(new int[]{dy, dx});
            }
        }
        return scale;
    }
}