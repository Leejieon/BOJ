import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++)
            list.add(Integer.parseInt(st.nextToken()));

        int start = 0;
        int end = (int)1e9;
        int result=0;
        while(start<=end){
            long total = 0;
            int mid = (start+end)/2;
            for(int i=0;i<N;i++){
                if(list.get(i)>mid)
                    total+=list.get(i)-mid;
            }

            if(total<M)
                end = mid-1;
            else{
                result = mid;
                start = mid +1;
            }
        }

        System.out.println(result);

    }
}