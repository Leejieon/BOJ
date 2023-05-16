import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N; // 노드의 개수
    static int ans = Integer.MIN_VALUE;
    static ArrayList<ArrayList<Node>> node = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            node.get(p).add(new Node(c, w));
            node.get(c).add(new Node(p, w));
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            visited[i] = true;
            DFS(i, 0);
        }
        System.out.println(ans);
    }

    static void DFS(int v, int d) {
        for (int i = 0; i < node.get(v).size(); i++) {
            int next = node.get(v).get(i).node;
            int dist = node.get(v).get(i).weight;

            if (!visited[next]) {
                visited[next] = true;
                DFS(next, d + dist);
            }
        }
        ans = Math.max(ans, d);
    }



    static class Node {
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

}
