import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        int answer = (1 << 10) - 1;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int visit = 0;
            int N = sc.nextInt();
            String number;
            int index = 1;
            while (true) {
                number = String.valueOf(N * index++);
                for (int i = 0; i < number.length(); i++) {
                    visit |= (1 << (number.charAt(i) - '0'));
                }

                // Base Case
                if (visit == answer) {
                    break;
                }
            }

            System.out.println("#" + test_case + " " + number);
        }
    }
}