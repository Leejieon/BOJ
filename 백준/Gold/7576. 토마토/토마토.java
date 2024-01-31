import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static Queue<Point> que;
    static int[][] arr;
    static int N, M;
    static int startX, startY;
    static int nx, ny;
    static int count;
    static int total;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        count = 0;
        arr = new int[N][M];
        total = 0;
        que = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    Point start = new Point(i, j);
                    que.add(start);
                }
                if (arr[i][j] == 0) {
                    total++;
                }
            }
        }

        bps();

        if (total == 0) {
            bw.write(count + "\n");
        } else {
            bw.write("-1\n");
        }

        bw.close();


    }

    static int bps() {

        while (!que.isEmpty()) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                Point tmp = que.poll();
                int x = tmp.x;
                int y = tmp.y;

                for (int q = 0; q < 4; q++) {
                    nx = x + dx[q];
                    ny = y + dy[q];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                        if (arr[nx][ny] == 0) {
                            arr[nx][ny] = 1;
                            total--;
                            que.add(new Point(nx, ny));
                        }
                    }
                }
                if (que.isEmpty() && total == 0) {
                    return count;
                }
            }
            count++;
        }

        return count;
    }

}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}