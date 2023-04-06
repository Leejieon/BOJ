import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] distance = new int[100001];
        boolean[] visited = new boolean[100001];
        Queue<Integer> queue = new LinkedList<>();
        distance[N] = 0;
        visited[N] = true;
        queue.offer(N);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            // Case 1
            int next = cur - 1;
            if (next >= 0 && next <= 100000 && !visited[next]) {
                visited[next] = true;
                distance[next] = distance[cur] + 1;
                queue.add(next);
            }
            // Case 2
            next = cur + 1;
            if (next >= 0 && next <= 100000 && !visited[next]) {
                visited[next] = true;
                distance[next] = distance[cur] + 1;
                queue.add(next);
            }
            // Case 3
            next = cur * 2;
            if (next >= 0 && next <= 100000 && !visited[next]) {
                visited[next] = true;
                distance[next] = distance[cur] + 1;
                queue.add(next);
            }
        }

        System.out.println(distance[K]);
    }
}
