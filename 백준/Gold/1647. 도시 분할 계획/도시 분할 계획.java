import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visited;
	static ArrayList<Edge>[] graph;
	static ArrayList<Integer> path = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}

		int totalWeight = prim(1);
		Collections.sort(path);
		System.out.println(totalWeight - path.get(path.size() - 1));
	}

	static int prim(int start) {
		int total = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if(visited[edge.to]) continue;

			visited[edge.to] = true;
			path.add(edge.weight);
			total += edge.weight;

			for (Edge e : graph[edge.to]) {
				if(!visited[e.to]) {
					pq.offer(e);
				}
			}
		}
		return total;
	}

	static class Edge {
		int to;
		int weight;

		Edge (int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
