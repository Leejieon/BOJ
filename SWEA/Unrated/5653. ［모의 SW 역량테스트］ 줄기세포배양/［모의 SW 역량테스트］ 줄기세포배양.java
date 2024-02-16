import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static int N, M, K, sizeY, sizeX;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Map<Point, Integer> locationMap;
    static Cell[][] cells;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int sY = K + 1, sX = K + 1; // 시작점
            sizeY = N + 2 * (K + 1);
            sizeX = M + 2 * (K + 1);
            cells = new Cell[sizeY][sizeX];
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < M; x++) {
                    int life = Integer.parseInt(st.nextToken());
                    if(life == 0) continue;
                    cells[sY + y][sX + x] = new Cell(life, 1, 2);
                }
            }

            int curDay = 1;
            while (curDay <= K) {
                proceedOneDay(curDay++);
            }

            long sum = Arrays.stream(cells)
                    .flatMap(Arrays::stream)
                    .filter(Objects::nonNull)
                    .filter(cell -> cell.state != 0)
                    .count();

            sb.append(sum).append("\n");
        }
        System.out.print(sb);
    }

    static void proceedOneDay(int curTime) {
        // 하루동안 번식된 줄기세포의 위치와 생명력
        locationMap = new HashMap<>();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if(cells[y][x] == null) continue;

                Cell cell = cells[y][x];
                switch (cell.state) {
                    case 0: // 죽은 상태
                        break;
                    case 1: // 활성 상태
                        // 첫 1시간 동안 번식
                        if (!cell.isBreeding) {
                            breeding(y, x);
                            cell.isBreeding = true;
                        }
                        if (cell.day == cell.life) {
                            cell.day = 1;
                            cell.state--;
                            break;
                        }
                        cell.day++;
                        break;
                    case 2: // 비활성 상태
                        // 비활성 상태에서 X 시간을 모두 보낸 경우
                        if (cell.day == cell.life) {
                            cell.day = 1;
                            cell.state--;
                        } else {
                            cell.day++;
                        }
                        break;
                }
            }
        }
        updateGraph();
    }

    // 줄기세포 번식
    static void breeding(int y, int x) {
        Cell cell = cells[y][x];
        for (int[] d : dir) {
            int dy = y + d[0];
            int dx = x + d[1];
            // 이동할 위치에 줄기세포가 이미 존재할 때
            if (cells[dy][dx] != null) continue;

            Point point = new Point(dy, dx);
            // 한 점에 동시에 번식하는 경우
            if (locationMap.containsKey(point)) {
                // 기존 세포가 생명력이 높은 경우
                int prevLife = locationMap.get(point);
                if (prevLife > cell.life) continue;
            }
            locationMap.put(point, cell.life);
        }
    }

    static void updateGraph() {
        for (Point point : locationMap.keySet()) {
            cells[point.y][point.x] = new Cell(locationMap.get(point), 1, 2);
        }
    }

    static class Cell {
        int life;
        int day;
        int state; // 0 : 죽은 상태, 1 : 활성 상태, 2 : 비활성 상태
        boolean isBreeding;

        Cell(int life, int day, int state) {
            this.life = life;
            this.day = day;
            this.state = state;
            this.isBreeding = false;
        }
    }

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}