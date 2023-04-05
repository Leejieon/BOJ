import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] Limit;
    static boolean[] possible;
    static boolean[][][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Limit = new int[3]; // 각각 a, b, c의 최대 용량
        for (int i = 0; i < 3; i++) {
            Limit[i] = sc.nextInt();
        }

        visited = new boolean[205][205][205]; // 상태마다 탐색 여부를 체크
        possible = new boolean[205]; // 만약 결과로 3이 나왔다면 possible[3] = true

        BFS(0, 0, Limit[2]);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= Limit[2]; i++) {
            if(possible[i])
                ans.add(i);
        }

        for (Integer an : ans) {
            System.out.print(an + " ");
        }
    }

    static void BFS(int a, int b, int c) {
        Queue<State> queue = new LinkedList<>();

        visited[a][b][c] = true;
        queue.add(new State(new int[]{a, b, c}));

        while (!queue.isEmpty()) {
            State s = queue.poll();

            // 만약 A 물통이 비어있다면 원하는 정답
            if (s.bottles[0] == 0) {
                possible[s.bottles[2]] = true;
            }

            // 모두 방문하기
            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    // 같은 물통끼리는 수행할 수 없다.
                    if (from == to) continue;

                    State next = s.move(from, to, Limit);
                    // next 상태를 방문한 적이 없다면
                    if (!visited[next.bottles[0]][next.bottles[1]][next.bottles[2]]) {
                        queue.add(next);
                        visited[next.bottles[0]][next.bottles[1]][next.bottles[2]] = true;
                    }
                }
            }
        }
    }

    // 물통의 현재 상태(정점)와 물을 붓는 행위(간선)를 관리하는 구조체
    static class State{
        int[] bottles;

        State(int[] _bottles){
            bottles = new int[3];
            System.arraycopy(_bottles, 0, bottles, 0, 3);
        }

        /**
         * from 물통에서 to 물통으로 물을 옮기기
         * @param from : from 물통
         * @param to : to 물통
         * @param Limit : 각 물통의 최대 용량
         * @return 옮기고 난 뒤, 새로운 물통의 상태
         */
        State move(int from, int to, int[] Limit) {
            int[] nbottles = new int[]{bottles[0], bottles[1], bottles[2]};

            // 1. to 물통이 먼저 차는 경우
            if (bottles[from] + bottles[to] >= Limit[to]) {
                nbottles[from] -= Limit[to] - bottles[to];
                nbottles[to] = Limit[to];
            }
            // 2. from 물통이 바닥나는 경우
            else{
                nbottles[to] += nbottles[from];
                nbottles[from] = 0;
            }

            return new State(nbottles);
        }
    }
}
