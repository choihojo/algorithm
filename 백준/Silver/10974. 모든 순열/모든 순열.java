import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static int N;
	static int[] input;
	static boolean[] visited;
	static int[] output;
	static StringBuilder sb;

	static void dfs(int cnt) {
		if (cnt == N) {
			for (int o : output) {
				sb.append(o).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			if (!visited[i]) {
				output[cnt] = input[i];
				visited[i] = true;
				dfs(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		input = new int[N];
		output = new int[N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		sb = new StringBuilder();
		
		dfs(0);
	
		System.out.println(sb);
	}
}