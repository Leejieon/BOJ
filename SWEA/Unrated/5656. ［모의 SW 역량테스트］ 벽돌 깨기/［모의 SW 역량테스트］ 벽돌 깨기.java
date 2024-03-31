import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int tCase, N, W, H, ans;
    static int[][] map;
    static boolean[] visited;
    static int[] dirX = new int[] { 0, 0, 1, -1 };
    static int[] dirY = new int[] { 1, -1, 0, 0 };
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        tCase = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= tCase; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
 
            map = new int[H][W];
            ans = Integer.MAX_VALUE;
 
            for (int i = 0; i < H; i++) {
 
                st = new StringTokenizer(br.readLine());
 
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            dfs(0, map);
            System.out.println("#" + t + " " + ans);
        }
    }
 
    public static void dfs(int depth, int[][] prev) {
        // Base Case
        if (ans == 0)
            return;
 
        if (depth == N) {
            int cnt = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (prev[i][j] != 0)
                        cnt += 1;
                }
            }
            ans = Math.min(ans, cnt);
            return;
        }
 
        // Recursive Case
        boolean already = true;
        for (int i = 0; i < W; i++) {
            int[][] temp = new int[H][W];
            for (int j = 0; j < H; j++)
                temp[j] = Arrays.copyOf(prev[j], prev[j].length);
 
            if (dropBall(temp, i)) {
                dropBlock(temp);
                dfs(depth + 1, temp);
                already = false;
            }
        }
        if (already)
            ans = 0;
    }
 
    public static void dropBlock(int[][] prev) {
        for (int i = 0; i < W; i++) {
            for (int j = H - 1; j > 0; j--) {
                if (prev[j][i] == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (prev[k][i] != 0) {
                            prev[j][i] = prev[k][i];
                            prev[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }
 
    public static boolean dropBall(int[][] prev, int curCol) {
        int curRow = -1;
        for (int i = 0; i < H; i++) {
            if (prev[i][curCol] != 0) {
                curRow = i;
                break;
            }
        }
        if (curRow == -1)
            return false;
 
        boolean[][] visited = new boolean[H][W];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(curRow, curCol, prev[curRow][curCol]));
        prev[curRow][curCol] = 0;
 
        while (!q.isEmpty()) {
 
            Node node = q.poll();
            int cnt = node.cnt;
 
            for (int i = 0; i < 4; i++) {
 
                int nr = node.row;
                int nc = node.col;
 
                for (int j = 0; j < cnt - 1; j++) {
 
                    nr += dirX[i];
                    nc += dirY[i];
 
                    if (!isBoundary(nr, nc))
                        break;
 
                    if (prev[nr][nc] != 0) {
                        q.offer(new Node(nr, nc, prev[nr][nc]));
                        prev[nr][nc] = 0;
                    }
                }
            }
        }
        return true;
    }
 
    public static boolean isBoundary(int row, int col) {
        return (row >= 0 && row < H) && (col >= 0 && col < W);
    }
 
    public static void showMap(int[][] prev) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
 
class Node {
 
    int row;
    int col;
    int cnt;
 
    public Node(int row, int col, int num) {
        this.row = row;
        this.col = col;
        this.cnt = num;
    }
}