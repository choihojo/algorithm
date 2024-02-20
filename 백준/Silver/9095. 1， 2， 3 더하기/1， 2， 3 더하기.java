import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static int n;
	static int[] dp;
	static int max = Integer.MIN_VALUE;
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>(); 
		
		int x = 0;
		for (int i = 0; i < n; i++) {
			x = Integer.parseInt(br.readLine());
			list.add(x);
			if (x > max) {
				max = x;
			}
		}
		
		dp = new int[max + 1];
		dp[1] = 1;
		
		if (max > 3) {
			dp[2] = 2;
			dp[3] = 4;
		}
		
		int k = 3;
		while (++k <= max) {
			dp[k] = dp[k - 1] + dp[k - 2] + dp[k - 3];
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(dp[list.get(i)]).append("\n");
		}
		
		System.out.println(sb);
	}
}