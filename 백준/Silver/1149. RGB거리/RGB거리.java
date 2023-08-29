import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] temp;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[3];
		temp = new int[3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dp[0] = r + Math.min(temp[1], temp[2]);
			dp[1] = g + Math.min(temp[0], temp[2]);
			dp[2] = b + Math.min(temp[0], temp[1]);
			for (int j = 0; j < 3; j++) {
				temp[j] = dp[j];
			}
		}
		Arrays.sort(dp);
		System.out.println(dp[0]);
	}
}