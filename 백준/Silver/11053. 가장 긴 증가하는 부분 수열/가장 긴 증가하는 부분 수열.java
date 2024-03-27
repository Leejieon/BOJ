import java.util.*;
import java.io.*;

class Main
{
    static int[] arr;
    static int[] dp;
    
    static int binary(int left, int right, int value) {
    	int mid = 0;
        while(left < right) {
        	mid = (left+right)/2;
            if(dp[mid] < value) {
            	left = mid+1;
            }
            else {
            	right = mid;
            }
        }
        return right;
    }
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      

			int N = Integer.parseInt(br.readLine());
            arr = new int[N];
            dp = new int[N+1];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
            	arr[i] =Integer.parseInt(st.nextToken());
            }
			
            dp[0] = arr[0];
            int idx =0 ;
            int num = 0;
            
            for(int i=1; i<N; i++) {
            	if(arr[i] >dp[num]) {
                	num += 1;
                    dp[num] = arr[i];
                }
                else {
                	idx = binary(0, num, arr[i]);
                    dp[idx] = arr[i];
                }
            }
            System.out.println(num+1);
		
	}
}