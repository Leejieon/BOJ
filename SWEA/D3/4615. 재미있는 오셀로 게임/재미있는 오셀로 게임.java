import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

class Solution
{
    static int N;
    static int[][] board;
    static int[][] dir = { {-1, 0} ,{1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            int M = sc.nextInt();

            board = new int[N][N];
            initBoard();

            for(int i=0;i<M;i++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                int player = sc.nextInt();

                board[y][x] = player;

                if(player == 1){
                    checkBlack(y, x);
                }
                else {
                    checkWhite(y, x);
                }
            }

            int black = (int) Arrays.stream(board)
                    .flatMapToInt(Arrays::stream)
                    .filter(i -> i == 1)
                    .count();

            int white = (int) Arrays.stream(board)
                    .flatMapToInt(Arrays::stream)
                    .filter(i -> i == 2)
                    .count();

            System.out.println("#" + test_case + " " + black + " " + white);
        }
    }

    static void initBoard() {
        board[N/2 -1][N/2 -1] = 2;
        board[N/2][N/2] = 2;
        board[N/2][N/2 -1] = 1;
        board[N/2 -1][N/2] = 1;
    }

    static void checkBlack(int row, int col) {
        List<Point> position = new ArrayList<>();

        boolean canTake = false;

        for(int i=0; i < 8; i++) {
            int y = row;
            int x = col;
            int ny = dir[i][0];
            int nx = dir[i][1];

            y += ny;
            x += nx;
            while(!isOutOfBound(y, x)) {
                if(board[y][x] == 0)
                    break;

                if(board[y][x] == 2) {
                    position.add(new Point(y, x));
                }
                if(board[y][x] == 1){
                    if(position.size() > 0) {
                        canTake = true;
                        break;
                    }
                    break;
                }

                y += ny;
                x += nx;
            }

            if(canTake){
                updateBoard(position);
            }
            position.clear();
            canTake = false;
        }
    }

    static void checkWhite(int row, int col) {
        List<Point> position = new ArrayList<>();

        boolean canTake = false;
        for(int i=0; i < 8; i++) {
            int y = row;
            int x = col;
            int ny = dir[i][0];
            int nx = dir[i][1];

            y += ny;
            x += nx;
            while(!isOutOfBound(y, x)) {
                if(board[y][x] == 0)
                    break;

                if(board[y][x] == 1) {
                    position.add(new Point(y, x));
                }
                if(board[y][x] == 2){
                    if(position.size() > 0) {
                        canTake = true;
                        break;
                    }
                    break;
                }

                y += ny;
                x += nx;
            }

            if(canTake){
                updateBoard(position);
            }
            position.clear();
            canTake = false;
        }
    }

    static void updateBoard(List<Point> position) {
        for(Point point : position) {
            int y = point.y;
            int x = point.x;

            board[y][x] = ( board[y][x] == 1) ? 2 : 1;
        }
    }

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isOutOfBound(int y, int x) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}