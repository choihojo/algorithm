import java.io.*;
import java.util.*;

// 물건 : 무게 W, 가치 V
// 배낭 : 무게 제한 K
// 물건의 수 : N
// 배낭에 넣을 수 있는 물건들의 가치의 최댓값? -> Knapsack Problem

public class Main {
	static int N, K;
	static int[][] dp;
	static int W, V;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= K; j++) {
				if (j - W >= 0) dp[i][j] = Math.max(dp[i - 1][j - W] + V, dp[i - 1][j]);
				else dp[i][j] = dp[i - 1][j];
			}
		}
		System.out.println(dp[N][K]);
	}
}