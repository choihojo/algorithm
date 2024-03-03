import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static int[] input;
	static boolean[] visited;
	static int sum;
	static int goal;
	static StringBuilder sb;
	static boolean flag;
	
	static void dfs(int cnt, int current) {
		if (cnt == 2) {
			if (current == goal && !flag) {
				for (int i = 0; i < 9; i++) {
					if (!visited[i]) {
						sb.append(input[i]).append("\n");
					}
				}
				flag = true;
			}
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(cnt + 1, current + input[i]);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		input = new int[9];
		visited = new boolean[9];
		for (int i = 0; i < 9; i++) {
			input[i] = Integer.parseInt(br.readLine());
			sum += input[i];
		}
		Arrays.sort(input);
		sb = new StringBuilder();
		
		goal = sum - 100;
		/*
		 * 2명 더해서 goal인 경우를 찾아야 함
		 */
		dfs(0, 0);
		
		System.out.println(sb.deleteCharAt(sb.length() - 1));
	}
}