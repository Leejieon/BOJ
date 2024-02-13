import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int total = 0;
        while (N > 0) {
            if (N % 5 == 0) {
                N -= 5;
                total++;
            } else if (N % 3 == 0) {
                N -= 3;
                total++;
            } else if (N > 5) {
                N -= 5;
                total++;
            } else {
                total = -1;
                break;
            } 
        }
        System.out.println(total);
    }
}
