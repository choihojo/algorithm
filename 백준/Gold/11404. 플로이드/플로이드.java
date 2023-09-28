
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int[][] dp;
	static StringBuilder sb;
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) continue;
				dp[i][j] = 10_000_000;
			}
		}
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (dp[start][end] < weight) continue;
			dp[start][end] = weight;
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == k || k == j || i == j) continue;
					dp[i][j] = dp[i][j] < (dp[i][k] + dp[k][j]) ? dp[i][j] : (dp[i][k] + dp[k][j]);
				}
			}
		}
		sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (dp[i][j] == 10_000_000) sb.append(0).append(" ");
				else sb.append(dp[i][j]).append(" ");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}