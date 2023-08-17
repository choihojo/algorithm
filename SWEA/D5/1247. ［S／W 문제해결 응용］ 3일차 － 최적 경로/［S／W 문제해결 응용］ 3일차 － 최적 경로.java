import java.io.*;

public class Solution {
	static int[][] irr;
	static int[][] orr;
	static int[] input;
	static int[] output;
	static String[] srr;
	static boolean[] visited;
	static int distance;
	static int min;
	static int N;
	
	static void permutation(int cnt) {
		if (cnt == N) {
			calculate();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[cnt] = input[i];
				permutation(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	static void calculate() {
		distance = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				orr[i + 1][j] = irr[output[i]][j];
			}
		}
		for (int i = 0; i < N + 1; i++) {
			distance += (Math.abs(orr[i][0] - orr[i + 1][0]) + Math.abs(orr[i][1] - orr[i + 1][1]));
		}
		if (distance < min) {
			min = distance;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
//			순열에 쓸 입력 배열 : 고객 배열만 순열 돌림
			irr = new int[N][2];
			input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = i;
			}
			output = new int[N];
//			순열돌릴 때 쓸 방문체크 배열
			visited = new boolean[N];
//			출력 배열 : 회사 -> 고객 -> 집 (회사, 집은 양끝에 고정)
			orr = new int[N + 2][2];
			srr = br.readLine().split(" ");
			orr[0][0] = Integer.parseInt(srr[0]);
			orr[0][1] = Integer.parseInt(srr[1]);
			orr[N + 1][0] = Integer.parseInt(srr[2]);
			orr[N + 1][1] = Integer.parseInt(srr[3]);
			
			for (int i = 4; i < (2 * N + 4); i++) {
				irr[i / 2 - 2][i % 2] = Integer.parseInt(srr[i]);
			}

			permutation(0);
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}