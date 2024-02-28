import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br;
	static String S;
	static int[] input;
	static int maxLength;
	static int strLength;
	static int result;
	
	static void dfs(int cnt, int before) {
		if (cnt == strLength) {
			result++;
			return;
		}
		
		for (int i = 0; i < maxLength; i++) {
			if (i == before) {
				continue;
			}
			
			if (input[i] > 0) {
				input[i]--;
				dfs(cnt + 1, i);
				input[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		maxLength = 'z' - 'a' + 1;
		strLength = S.length();
		
		input = new int[maxLength];
		for (int i = 0; i < strLength; i++) {
			input[S.charAt(i) - 'a']++;
		}
		
		dfs(0, -1);
		
		System.out.println(result);
	}
}