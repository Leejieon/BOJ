import java.util.*;
import java.io.*;

public class Main {

    public static char[][] graph;
    public static char[][] new_graph;
    public static int N;

    public static boolean dfs(int x, int y, char color, char[][] map){
        if(x<=-1||y<=-1||x>=N||y>=N)
            return false;
        if(map[x][y]==color){
            map[x][y] = 'Z';
            dfs(x-1,y,color,map);
            dfs(x+1,y,color,map);
            dfs(x,y-1,color,map);
            dfs(x,y+1,color,map);
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new char[N][N];
        for(int i=0;i<N;i++){
            String temp = br.readLine();
            for(int j=0;j<N;j++){
                graph[i][j] = temp.charAt(j);
            }
        }

        new_graph = new char[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(graph[i][j]=='G')
                    new_graph[i][j] = 'R';
                else
                    new_graph[i][j] = graph[i][j];
            }
        }

        int res=0;
        int res2=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(dfs(i,j,'R',graph)) res++;
                if(dfs(i,j,'G',graph)) res++;
                if(dfs(i,j,'B',graph)) res++;

                if(dfs(i,j,'R',new_graph)) res2++;
                if(dfs(i,j,'G',new_graph)) res2++;
                if(dfs(i,j,'B',new_graph)) res2++;
            }
        }



        System.out.println(res+" "+res2);

    }


}

