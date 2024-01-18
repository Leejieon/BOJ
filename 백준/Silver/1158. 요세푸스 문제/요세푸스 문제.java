import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++)
			queue.add(i);
		
		sb.append("<");
		
		while(queue.size()>1)
		{
			for(int i=0;i<K-1;i++)
				queue.add(queue.poll());
			
			sb.append(queue.poll()+", ");
		}
		
		sb.append(queue.poll()+">");
		
		bw.write(sb.toString()+"\n");
		bw.flush();
		br.close();
		bw.close();	
		
	}
	

}
