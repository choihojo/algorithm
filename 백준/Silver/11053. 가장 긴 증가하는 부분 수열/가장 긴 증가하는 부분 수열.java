import java.io.*;
import java.util.*;

// Longest Increasing Subsequence (LIS) Problem

public class Main {
	static int N;
	static int[] sequence;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sequence = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (sequence[i] > sequence[j] && dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (dp[i] > max) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}
}