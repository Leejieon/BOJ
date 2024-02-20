import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static ArrayList<Point> homes, chickens;
    static int[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        homes = new ArrayList<>();
        chickens = new ArrayList<>();
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                String number = st.nextToken();
                switch (number) {
                    case "1":
                        homes.add(new Point(y, x));
                        break;
                    case "2":
                        chickens.add(new Point(y, x));
                        break;
                    default:
                        break;
                }
            }
        }

        select = new int[M];
        answer = Integer.MAX_VALUE;
        combination(0, 0);
        System.out.println(answer);
    }

    static void combination(int depth, int prev) {
        // Base Case
        if (depth == M) {
            int distance = getChickenDistance();
            answer = Math.min(answer, distance);
            return;
        }

        for (int i = prev; i < chickens.size(); i++) {
            select[depth] = i;
            combination(depth + 1, i + 1);
        }
    }

    static int getChickenDistance() {
        int result = 0;
        for (Point home : homes) {
            int minDistance = Integer.MAX_VALUE;
            for (int index : select) {
                int distance = home.getDistance(chickens.get(index));
                minDistance = Math.min(minDistance, distance);
            }
            result += minDistance;
        }
        return result;
    }

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        int getDistance(Point other) {
            return Math.abs(y - other.y) + Math.abs(x - other.x);
        }
    }
}
