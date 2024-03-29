import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int N, M;
    static int[] parents;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int groupNum = 1;
        visited = new boolean[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(visited[y][x]) continue;
                if(graph[y][x] == 0) continue;

                visited[y][x] = true;
                graph[y][x] = groupNum;
                dfs(y, x, groupNum++);
            }
        }

        // make Set
        parents = new int[groupNum];
        for (int i = 0; i < groupNum; i++) {
            parents[i] = i;
        }

        cost = new int[groupNum][groupNum];

        for (int cur = 0; cur < groupNum; cur++) {
            // 현재 cur 그룹인 노드에서 탐색 시작
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if(graph[y][x] != cur) continue;

                    for (int cand = 0; cand < dir.length; cand++) {
                        int dy = y + dir[cand][0];
                        int dx = x + dir[cand][1];

                        if(isOutOfBound(dy, dx)) continue;
                        if(graph[dy][dx] != 0) continue;

                        findPath(dy, dx, cur, cand);
                    }
                }
            }
        }

        System.out.println(kruskal(groupNum));
    }

    static int kruskal(int groupNum) {
        int len = 0;

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 1; i < groupNum; i++) {
            for (int j = i + 1; j < groupNum; j++) {
                if (cost[i][j] != 0) {
                    list.add(new int[]{i, j, cost[i][j]});
                    list.add(new int[]{j, i, cost[i][j]});
                }
            }
        }
        Collections.sort(list, ((o1, o2) -> o1[2] - o2[2]));

        int bridge = 0;
        for (int i = 0; i < list.size(); i++) {
            int from = list.get(i)[0];
            int to = list.get(i)[1];
            int cost = list.get(i)[2];

            if (find(from) != find(to)) {
                union(from, to);
                bridge++;
                len += cost;
            }
        }

        if (bridge != groupNum - 2) {
            return -1;
        }
        return len;
    }

    static void findPath(int y, int x, int group, int direction) {
        int len = 1;
        boolean isFound = false;
        int connectNode = 0;

        while (true) {
            int dy = y + dir[direction][0];
            int dx = x + dir[direction][1];

            if(isOutOfBound(dy, dx)) break;
            if(graph[dy][dx] == group) break;
            if (graph[dy][dx] != 0 && graph[dy][dx] != group) {
                isFound = true;
                connectNode = graph[dy][dx];
                break;
            }
            y = dy;
            x = dx;
            len++;
        }

        if (isFound) {
            if (len >= 2) {
                if (cost[group][connectNode] != 0) {
                    int newDist = Math.min(cost[group][connectNode], len);
                    cost[group][connectNode] = newDist;
                    cost[connectNode][group] = newDist;
                } else {
                    cost[group][connectNode] = len;
                    cost[connectNode][group] = len;
                }
            }
        }
    }

    static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);

        if (node1 < node2) {
            parents[node2] = node1;
            return;
        }
        parents[node1] = node2;
    }

    static int find(int node) {
        if (parents[node] == node) {
            return node;
        }
        return parents[node] = find(parents[node]);
    }

    static void dfs(int y, int x, int group) {
        for (int[] d : dir) {
            int dy = y + d[0];
            int dx = x + d[1];

            if(isOutOfBound(dy, dx)) continue;
            if(visited[dy][dx]) continue;
            if(graph[dy][dx] == 0) continue;

            visited[dy][dx] = true;
            graph[dy][dx] = group;
            dfs(dy, dx, group);
        }
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }
}