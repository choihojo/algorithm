import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, S;
	static int[] input;
	static int result;
	
	static void dfs(int cnt, int sum, boolean flag) {
		if (sum == S && flag) {
			result++;
		}
		if (cnt == N) {
			return;
		}
		
		dfs(cnt + 1, sum + input[cnt], true);
		dfs(cnt + 1, sum, false);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		input = new int[N];
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, false);
		
		System.out.println(result);
	}
}