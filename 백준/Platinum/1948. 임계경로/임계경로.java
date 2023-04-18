// BOJ : 1948 임계경로(P5)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, start, end, ans;
    static ArrayList<ArrayList<Point>> child = new ArrayList<>();
    static ArrayList<ArrayList<Point>> parent = new ArrayList<>();
    static int[] indeg, time;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        indeg = new int[N + 1];
        time = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            child.add(new ArrayList<>());
            parent.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            child.get(x).add(new Point(y, cost));
            parent.get(y).add(new Point(x, cost)); // 그래프를 역추적하기 위한 용도
            indeg[y]++;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        ans = 0;
        tSort();

        System.out.println(time[end] + "\n" + ans);
    }

    static void tSort() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = 0; i < child.get(x).size(); ++i) {
                int y = child.get(x).get(i).to;
                indeg[y]--;

                if(indeg[y] == 0)
                    queue.add(y);

                time[y] = Math.max(time[y], time[x] + child.get(x).get(i).cost);
            }
        }

        queue.add(end);
        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int i = 0; i < parent.get(y).size(); i++) {
                int x = parent.get(y).get(i).to;
                int cost = parent.get(y).get(i).cost;

                // 최장 경로에 포함되는 간선인지 확인
                if (time[y] - time[x] == cost) {
                    ans++;

                    // 정점을 한 번씩만 방문하도록 설정
                    if (!visited[x]) {
                        visited[x] = true;
                        queue.add(x);
                    }
                }
            }
        }

    }

    static class Point{
        int to;
        int cost;

        public Point(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
