
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int V, E;
	static int a, b, c;
	static int[][] dp;
	static int min;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dp = new int[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
//			for (int j = 1; j <= V; j++) {
//				dp[i][j] = 5_000_000; 
//			}
			Arrays.fill(dp[i], 5_000_000);
		}
//		for (int i = 1; i <= V; i++) {
//			dp[i][i] = 0;
//		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if (dp[a][b] > c) dp[a][b] = c;
		}
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
//					if (i == k || k == j || i == j) continue;
					if (i == k || k == j) continue;
					if (dp[i][j] > (dp[i][k] + dp[k][j])) dp[i][j] = (dp[i][k] + dp[k][j]);
				}
			}
		}
		min = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			if (min > dp[i][i]) min = dp[i][i];
		}
		System.out.println(min >= 5_000_000 ? -1 : min);
	}
}