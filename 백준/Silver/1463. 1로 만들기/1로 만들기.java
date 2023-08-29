import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dp;
	
	static int calculate(int n) {
		if (dp[n] != -1) return dp[n];
		int min = calculate(n - 1) + 1;
		if (n % 2 == 0) min = Math.min(min, calculate(n / 2) + 1);
		if (n % 3 == 0) min = Math.min(min, calculate(n / 3) + 1);
		return dp[n] = min;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		dp[1] = 0;
		for (int i = 2; i <= N; i++) {
//			if (3 * i <= N && dp[3 * i] == -1) dp[3 * i] = dp[i] + 1;
//			if (2 * i <= N && dp[2 * i] == -1) dp[2 * i] = dp[i] + 1;
//			if (i + 1 <= N && dp[i + 1] == -1) dp[i + 1] = dp[i] + 1;
			dp[i] = dp[i - 1] + 1;
			if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			
		}
		System.out.println(dp[N]);
	}
}
