import java.util.*;
import java.io.*;

class Node{
    private int x;
    private int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}

public class Main {
    public static int N;
    public static int[][] map;


    public static boolean dfs(int x, int y, int[][] graph){
        if(x<=-1||x>=N||y<=-1||y>=N)
            return false;

        if(graph[x][y]!=0){
            graph[x][y]=0;
            dfs(x-1,y,graph);
            dfs(x+1,y,graph);
            dfs(x,y-1,graph);
            dfs(x,y+1,graph);
            return true;
        }
        return false;

    }

    public static int[][] make_map(int size){
        int[][] change_map = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                change_map[i][j] = map[i][j];
                if(map[i][j]<=size)
                    change_map[i][j] = 0;
            }
        }
        return change_map;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int max_height=0;
        map = new int[N][N];
        for(int i=0;i<N;i++){
            String temp = br.readLine();
            st = new StringTokenizer(temp);
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(max_height<=map[i][j])
                    max_height = map[i][j];
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int test=0;test<max_height;test++){
            int res=0;
            int[][] temp_map = make_map(test);
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(dfs(i,j,temp_map))
                        res++;
                }
            }
            list.add(res);
        }

        System.out.println(Collections.max(list));

    }


}

