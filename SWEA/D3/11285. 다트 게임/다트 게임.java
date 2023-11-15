import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        int[] scoreBoard = initScore();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int count = sc.nextInt();
            int score = 0;
            for(int i=0; i<count; i++) {
                int x = Math.abs(sc.nextInt());
                int y = Math.abs(sc.nextInt());

                double r = Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2) );
                score += calculateScore(scoreBoard, r);
            }

            System.out.println("#" + test_case + " " + score);
        }
    }

    static int[] initScore() {
        int[] init = new int[11];
        for(int i=1; i<=10; i++) {
            init[i] = 20 * (11 - i);
        }
        return init;
    }

    static int calculateScore(int[] scoreBoard, double r) {
        int result = 0;
        for(int i=10; i>0; i--) {
            if(r <= scoreBoard[i]) {
                result = i;
                break;
            }
        }
        return result;
    }
}