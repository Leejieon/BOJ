import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int V, maxDist, maxNode;
	static boolean[] visited;
	static ArrayList<Edge>[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());

		tree = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());

			int node = Integer.parseInt(st.nextToken());
			while(true) {
				int next = Integer.parseInt(st.nextToken());
				if(next == -1) break;

				int dist = Integer.parseInt(st.nextToken());

				tree[node].add(new Edge(next, dist));
				tree[next].add(new Edge(node, dist));
			}
		}

		visited = new boolean[V + 1];

		maxDist = -1;
		bfs(1);

		visited = new boolean[V + 1];

		bfs(maxNode);

		System.out.println(maxDist);
	}

	static void bfs(int source) {
		// [0] : 현재 노드, [1] : 시작 노드로부터의 거리
		Queue<int[]> queue = new LinkedList<>();

		visited[source] = true;
		queue.add(new int[] {source, 0});
		while (!queue.isEmpty()) {
			int[] element = queue.poll();

			for (Edge edge : tree[element[0]]) {
				int next = edge.node;
				int dist = edge.dist;

				if (!visited[next]) {
					visited[next] = true;
					if (maxDist < element[1] + dist) {
						maxDist = element[1] + dist;
						maxNode = next;
					}
					queue.add(new int[] {next, element[1] + dist});
				}
			}
		}
	}

	static class Edge {
		int node, dist;

		Edge(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
}