
import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] input;
	static int[] output;
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb;

	static void dfs(int cnt) {
		if (cnt == M) {
			for (int o : output) {
				sb.append(o).append(" ");
			}
			sb.append("\n");

			return;
		}

		int temp = 0;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && input[i] != temp) {
				output[cnt] = input[i];
				temp = output[cnt];
				
				visited[i] = true;
				dfs(cnt + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N];
		output = new int[M];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input);
		sb = new StringBuilder();

		dfs(0);

		System.out.println(sb);
	}
}