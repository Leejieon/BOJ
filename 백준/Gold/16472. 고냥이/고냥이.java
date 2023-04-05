import java.util.Scanner;

public class Main {
    static int[] cnt = new int[26];
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        String str = sc.next();

        int ans = 0;
        for (int R = 0, L = 0; R < str.length(); R++) {
            // R번째 문자를 오른쪽에 추가
            cnt[str.charAt(R) - 'a']++;

            while (true) {
                if(checkNum() <= N)
                    break;
                cnt[str.charAt(L) - 'a']--;
                L++;
            }

            ans = Math.max(ans, R - L + 1);
        }

        System.out.println(ans);
    }

    static int checkNum() {
        int num = 0;
        for (int a : cnt) {
            if (a != 0) {
                num++;
            }
        }
        return num;
    }
}
