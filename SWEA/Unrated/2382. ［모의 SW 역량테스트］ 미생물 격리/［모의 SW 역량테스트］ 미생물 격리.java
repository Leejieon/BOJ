import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static int N, M, K;
    static int[][] move = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Bacteria[][] graph;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new Bacteria[N][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph[y][x] = new Bacteria(c, d);
            }

            while (M-- > 0) {
                // 미생물 이동 시키기
                moveAllBacteria();
            }

            int sum = Arrays.stream(graph)
                    .flatMap(Arrays::stream)
                    .filter(Objects::nonNull)
                    .mapToInt(bacteria -> bacteria.count)
                    .sum();
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    static void moveAllBacteria() {
        ArrayList<Bacteria>[][] afterMove = new ArrayList[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(graph[y][x] == null) continue;
                // 다음 이동 위치
                int dy = y + move[graph[y][x].dir][0];
                int dx = x + move[graph[y][x].dir][1];

                // 약품에 위치할 경우 절반 줄이기
                if (dy == 0 || dx == 0 || dy == N - 1 || dx == N - 1) {
                    killBacteria(y, x);
                }

                if (afterMove[dy][dx] == null) {
                    afterMove[dy][dx] = new ArrayList<>();
                }
                afterMove[dy][dx].add(graph[y][x]);
            }
        }
        copyToOriginal(afterMove);
    }

    static void copyToOriginal(ArrayList<Bacteria>[][] afterMove) {
        Bacteria[][] result = new Bacteria[N][N];
        
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (afterMove[y][x] == null) continue;
                ArrayList<Bacteria> list = afterMove[y][x];
                if (list.size() == 1) {
                    result[y][x] = list.get(0);
                } else {
                    list.sort((o1, o2) -> o2.count - o1.count);
                    result[y][x] = new Bacteria(
                            list.stream()
                                    .mapToInt(bacteria -> bacteria.count)
                                    .sum(),
                            list.get(0).dir
                    );
                }
            }
        }
        graph = result;
    }

    static void killBacteria(int y, int x) {
        Bacteria bacteria = graph[y][x];
        bacteria.count /= 2; // 미생물 절반 줄이기
        if (bacteria.count == 0) { // 미생물이 없다면, 군집은 사라진다.
            graph[y][x] = null;
            return;
        }
        bacteria.dir = bacteria.dir % 2 == 1 ? bacteria.dir + 1 : bacteria.dir - 1; // 방향 바꾸기
    }

    static class Bacteria {
        int count;
        int dir; // 1 : Up, 2 : Down, 3 : Left, 4 : Right

        Bacteria(int count, int dir) {
            this.count = count;
            this.dir = dir;
        }
    }
}