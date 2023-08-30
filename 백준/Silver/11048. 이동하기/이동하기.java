import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		dp[0][0] = map[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dp[i][j] == -1) {
					if (i == 0) {
						dp[i][j] = map[i][j] + dp[i][j - 1];
						continue;
					}
					if (j == 0) {
						dp[i][j] = map[i][j] + dp[i - 1][j];
						continue;
					}
					dp[i][j] = map[i][j] + Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		
		System.out.println(dp[N - 1][M - 1]);
	}
}