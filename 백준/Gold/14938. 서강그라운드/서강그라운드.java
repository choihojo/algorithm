
import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int n, m, r;
	static int t;
	static int a, b, l;
	static int[][] dp;
	static int[] item;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		item = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) continue;
				dp[i][j] = 10_000;
			}
		}
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			if (dp[a][b] > l) dp[a][b] = l;
			if (dp[b][a] > l) dp[b][a] = l;
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == k || k == j || i == j) continue;
					if (dp[i][j] > (dp[i][k] + dp[k][j])) dp[i][j] = (dp[i][k] + dp[k][j]);
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			int cnt = 0;
			for (int j = 1; j <= n; j++) {
				if (dp[i][j] <= m) cnt += item[j];
			}
			if (cnt > max) max = cnt;
		}
		System.out.println(max);
	}
}