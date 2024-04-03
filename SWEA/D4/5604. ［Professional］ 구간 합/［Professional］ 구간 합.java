import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static HashMap<Long, Long> f;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int Tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        f = new HashMap<Long, Long>();

        long sum = 0;
        for (long i = 0; i < 10; i++) {
            sum += i;
            f.put(i, sum);
        }

        for (int tc = 1; tc <= Tc; tc++) {

            st = new StringTokenizer(br.readLine());

            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            long ans;
            if (A > 0)
                ans = F(B) - F(A - 1);
            else
                ans = F(B) - F(A);
            sb.append("#" + tc + " " + ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static long F(long n) {

        if (f.containsKey(n))
            return f.get(n);
        if (n < 10)
            return f.get(n);

//        long v = (long) Math.pow(10, (int) Math.log10(n));
        long v=getV(n);
        long num = F(n - 1 - n % v) + G(n, v);
        f.put(n, num);
        return num;
    }

    static long G(long n, long v) {
        return (n / v) * (n % v + 1) + F(n % v);
    }

    static long getV(long i) {
        long v = 1;
        while (i >= 10) {
            v = v * 10;
            i = i / 10;
        }
        return v;
    }
}