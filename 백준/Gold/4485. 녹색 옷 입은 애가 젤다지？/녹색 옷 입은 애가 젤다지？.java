import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 문제 꼼꼼히 읽기.
public class Main {
    static class data implements Comparable<data>{
        int x;
        int y;
        int w;
        public data(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
        @Override
        public int compareTo(data o) {
            return w-o.w;
        }

        @Override
        public String toString() {
            return "data{" +
                    "x=" + x +
                    ", y=" + y +
                    ", w=" + w +
                    '}';
        }
    }
    static int[][] delta = new int[][] {{0,1},{1,0},{-1,0},{0,-1}};
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = 1;
        while(true){
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            int[][] board = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            PriorityQueue<data> pq = new PriorityQueue<>();
            int[][] make = new int[n][n];
            for (int[] ints : make) {
                Arrays.fill(ints, 999999999);
            }
            pq.add(new data(0,0,board[0][0]));
            make[0][0] = board[0][0];

            while (!pq.isEmpty()){
                data poll = pq.poll();

                for (int d = 0; d < 4; d++) {
                    int dy = poll.y + delta[d][0];
                    int dx = poll.x + delta[d][1];
                    if (check_size(dy, dx)) {
                        if (make[dy][dx] > make[poll.y][poll.x] + board[dy][dx]) {
                            make[dy][dx] = make[poll.y][poll.x] + board[dy][dx];
                            pq.add(new data(dx, dy, make[dy][dx]));
                        }
                    }
                }
            }

            System.out.printf("Problem %d: %d\n",t++,make[n-1][n-1]);
        }
    }

    static boolean check_size(int y , int x){
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}

