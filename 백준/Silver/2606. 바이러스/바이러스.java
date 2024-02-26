import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 사람들의 수
		N = Integer.parseInt(br.readLine());

		// parents 배열 초기화
		parents = new int[N + 1];
		makeSet();
	
		// 연결되어 있는 사람 쌍의 수
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			union(x, y);
		}
		
		// 1번과 연결된 사람 수 찾기
		int result = 0;
		for(int i = 2; i < N + 1; i++) {
			if(find(i) == 1) {
				result++;
			}
		}
		
		System.out.println(result);
	}

	static void makeSet() {
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
	}
	
	static int find(int x) {
		if(parents[x] == x)
			return x;
		return parents[x] = find(parents[x]);
	}

}
