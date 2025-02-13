import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] tasks;
	static boolean[] visited;
	static ArrayList<Integer>[] works;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tasks = new int[M + 1];
		visited = new boolean[M + 1];
		works = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			works[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int count = Integer.parseInt(st.nextToken());
			for (int j = 0; j < count; j++) {
				works[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		int count = 0;
		for (int i = 1; i <= N; i++) {
			visited = new boolean[M + 1];
			if (dfs(i)) {
				count++;
			}
		}
		System.out.println(count);
	}

	static boolean dfs(int cur) {
		for (int next : works[cur]) {
			if (!visited[next]) {
				visited[next] = true;

				if (tasks[next] == 0 || dfs(tasks[next])) {
					tasks[next] = cur;
					return true;
				}
			}
		}
		return false;
	}


}