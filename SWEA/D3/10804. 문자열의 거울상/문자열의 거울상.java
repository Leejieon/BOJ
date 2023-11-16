import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		Map<Character, Character> map = new HashMap(){{
            put('b', 'd');
            put('d', 'b');
            put('p', 'q');
            put('q', 'p');
        }};
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String words = sc.next();
           	char[] mirrors = new char[words.length()];
            
            for(int i = 0; i < words.length(); i++) {
                mirrors[i] = map.get(words.charAt(words.length() - 1 - i));
            }
            
            System.out.println("#" + test_case + " " + String.valueOf(mirrors));
		}
	}
}