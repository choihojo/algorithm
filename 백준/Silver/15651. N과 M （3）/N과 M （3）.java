import java.util.*;
import java.io.*;



public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static StringBuilder sb;
	static int x = 1;
	
	static void doublePerm(int[] input, int[] output, int cnt) {
		if (cnt == output.length) {
			for (int o : output) {
				sb.append(o).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			output[cnt] = input[i];
			doublePerm(input, output, cnt + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();

		int[] input = new int[N];
		for (int i = 1; i <= N; i++) {
			input[i - 1] = i;
		}
		int[] output = new int[M];
		int cnt = 0;
		doublePerm(input, output, cnt);
		System.out.print(sb);
	}
}