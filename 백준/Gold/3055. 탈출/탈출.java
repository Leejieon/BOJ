import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] dist_s, dist_water;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        /*
        . = 비어있는 곳
        * = 물이 찬 곳
        X = 돌
        D = 비버의 굴
        S = 고슴도치의 위치
         */
        map = new char[R][C];
        dist_s = new int[R][C];
        dist_water = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = sc.next().toCharArray();
        }

        BFS_water();
        BFS_S();

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (map[y][x] == 'D') {
                    // 탐색이 모두 끝났는데 D가 -1이라면, 고슴도치가 도달하지 못한 경우
                    if(dist_s[y][x] == -1)
                        System.out.println("KAKTUS");
                    else
                        System.out.println(dist_s[y][x]);
                    return;
                }
            }
        }

    }

    static void BFS_water() {
        // y, x 순서로 넣고, poll() 할 때도 y, x 순서로 꺼내준다.
        Queue<Integer> queue = new LinkedList<>();
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                dist_water[y][x] = -1;
                // 물일 경우, 큐에 넣고 방문 체크
                if(map[y][x] == '*'){
                    queue.add(y);
                    queue.add(x);
                    visited[y][x] = true;
                    dist_water[y][x] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            int y = queue.poll();
            int x = queue.poll();

            for (int cand = 0; cand < 4; cand++) {
                int ny = y + dir[cand][0];
                int nx = x + dir[cand][1];

                // 범위를 벗어나는 경우
                if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                // .이 아닌 경우
                if (map[ny][nx] != '.') continue;
                // 이미 방문한 경우
                if(visited[ny][nx]) continue;

                dist_water[ny][nx] = dist_water[y][x] + 1;
                queue.add(ny);
                queue.add(nx);
                visited[ny][nx] = true;
            }
        }
    }

    static void BFS_S() {
        Queue<Integer> queue = new LinkedList<>();
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                // 고슴도치 그래프와 방문 여부 그래프 초기화
                dist_s[y][x] = -1;
                visited[y][x] = false;
                if (map[y][x] == 'S') {
                    queue.add(y);
                    queue.add(x);
                    visited[y][x] = true;
                    dist_s[y][x] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            int y = queue.poll();
            int x = queue.poll();

            for (int cand = 0; cand < 4; cand++) {
                int ny = y + dir[cand][0];
                int nx = x + dir[cand][1];

                // 범위를 벗어나는 경우
                if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                // 갈 수 없는 경우
                if (map[ny][nx] != '.' && map[ny][nx] != 'D') continue;
                // T_s + 1 >= T_w 인 경우
                if(dist_water[ny][nx] != -1 && dist_s[y][x] + 1 >= dist_water[ny][nx]) continue;
                // 이미 방문한 경우
                if(visited[ny][nx]) continue;

                dist_s[ny][nx] = dist_s[y][x] + 1;
                queue.add(ny);
                queue.add(nx);
                visited[ny][nx] = true;
            }
        }
    }

}
