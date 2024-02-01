import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M;
    static int size;
    static int[][][] graph;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Queue<Point> roads = new LinkedList<>(); // 0의 위치를 저장하고 담는 Queue
    static Queue<Point> walls = new LinkedList<>();
    static Map<Integer, Integer> group = new HashMap<Integer, Integer>();
    static int groupNum = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        graph = new int[2][N][M];
        for (int y = 0; y < N; y++) {
            String row = br.readLine();
            for (int x = 0; x < M; x++) {
                graph[0][y][x] = row.charAt(x) - '0';
                if (graph[0][y][x] == 0) {
                    roads.offer(new Point(y, x));
                } else {
                    graph[0][y][x] = -1; // 벽을 -1로 선언
                    walls.offer(new Point(y, x));
                }
            }
        }

        while (!roads.isEmpty()) {
            Point point = roads.poll();
            int y = point.y;
            int x = point.x;
            if (!visited[y][x]) {
                size = 1;
                visited[y][x] = true;
                graph[1][y][x] = groupNum;
                dfs(y, x);
                group.put(groupNum, size);
                groupNum++;
            }
        }

        int[][] result = new int[N][M];
        boolean[] check;
        while (!walls.isEmpty()) {
            check = new boolean[groupNum];
            Point point = walls.poll();
            int y = point.y;
            int x = point.x;

            int count = 1;
            for (int cand = 0; cand < 4; cand++) {
                int dy = y + dir[cand][0];
                int dx = x + dir[cand][1];

                if (isOutOfBound(dy, dx)) continue;
                if (graph[0][dy][dx] == -1) continue;
                if (check[graph[1][dy][dx]]) continue; // 이미 확인한 그룹일 경우

                check[graph[1][dy][dx]] = true;
                count += group.get(graph[1][dy][dx]);
            }

            result[y][x] = count % 10;
        }


        for (int[] rows : result) {
            for (int number : rows) {
                sb.append(number);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int y, int x) {
        for (int cand = 0; cand < 4; cand++) {
            int dy = y + dir[cand][0];
            int dx = x + dir[cand][1];

            if (isOutOfBound(dy, dx)) continue;
            if (visited[dy][dx]) continue;

            if (graph[0][dy][dx] == 0) {
                visited[dy][dx] = true;
                graph[1][dy][dx] = groupNum;
                size++;
                dfs(dy, dx);
            }
        }
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}