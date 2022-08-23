import java.util.*;
import java.io.*;

public class Main {

    public static int N,M;
    public static int[][] arr;

    public static boolean dfs(int x, int y){
        if(x<=-1||x>=N||y<=-1||y>=M)
            return false;

        if(arr[x][y]==1){
            arr[x][y] = 0;
            dfs(x-1,y);
            dfs(x+1,y);
            dfs(x,y-1);
            dfs(x,y+1);
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Testcase = Integer.parseInt(br.readLine());

        for(int a=0;a<Testcase;a++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N][M];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
            }

            int res = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (dfs(i, j)) res++;

            System.out.println(res);
        }

    }


}

