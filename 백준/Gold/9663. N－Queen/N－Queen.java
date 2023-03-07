import java.util.Scanner;

public class Main {
    static int N, answer;
    static int[] col;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        col = new int[N + 1];
        rec_func(1);
        System.out.println(answer);
    }

    static void rec_func(int row) {
        if (row == N + 1) { // 성공적으로 퀸들을 배치했을 경우
            answer++;
        } else {
            for (int c = 1; c <= N ; c++) {
                boolean possible = true;
                // validation check
                for (int i = 1; i <= row-1; i++) {
                    // (i, col[i])
                    if (attackable(row, c, i, col[i])) {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    col[row] = c;
                    rec_func(row + 1);
                    col[row] = 0;
                }
            }
        }
    }

    static boolean attackable(int r1, int c1, int r2, int c2) {
        if(c1 == c2) return true;
        if(r1 - c1 == r2 - c2) return true;
        if(r1 + c1 == r2 + c2) return true;
        return false;
    }

}
