import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] a = new int[n];
            for(int i=0; i<n; i++) {
                a[i] = sc.nextInt();
            }

            int[] b = new int[m];
            for(int i=0; i<m; i++) {
                b[i] = sc.nextInt();
            }

            int diff = m - n;
            if(n > m){
                diff = n - m;
            }

            int max = Integer.MIN_VALUE;
            int sum = 0;
            for(int i = 0; i <= diff; i++) {
                if (n > m) {
                    sum = calculate(b, a, i);
                } else {
                    sum = calculate(a, b, i);
                }
                max = Math.max(max, sum);
            }

            System.out.println("#" + test_case + " " + max);
        }
    }

    static int calculate(int[] arr1, int[] arr2, int index) {
        int result = 0;
        for(int i=0; i < arr1.length; i++) {
            result += arr1[i] * arr2[i+index];
        }
        return result;
    }
}