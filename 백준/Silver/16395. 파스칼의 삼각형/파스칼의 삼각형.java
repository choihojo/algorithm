import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[n][k];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= Math.min(i, k - 1); j++) {
				if (j == 0 || j == i) {
					dp[i][j] = 1;
				}
				else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}		
		
		System.out.println(dp[n - 1][k - 1]);
	}
}
