import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<String> in_list = new ArrayList<>();
        ArrayList<String> out_list = new ArrayList<>();

        for(int i=0;i<N;++i)
            in_list.add(br.readLine());
        for(int i=0;i<N;++i)
            out_list.add(br.readLine());

        int answer=0;
        while(!in_list.isEmpty()){
            if(!in_list.get(0).equals(out_list.get(0)))
                answer++;
            in_list.remove(out_list.get(0));
            out_list.remove(0);
        }

        System.out.println(answer);
    }
}
