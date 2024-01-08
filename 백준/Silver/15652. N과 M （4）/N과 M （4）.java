import java.util.*;
import java.io.*;



public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static StringBuilder sb;
	static int x = 1;
	static boolean[] visited;
	
	private static void doubleComb(int[] input, int[] output, int cnt, int start) {
		if (cnt == output.length) {
			for (int o : output) {
				System.out.print(o + " ");
			}
			System.out.println("");
			return;
		}
		
		for (int i = start; i < input.length; i++) {
			output[cnt] = input[i];
			doubleComb(input, output, cnt + 1, i);
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
		visited = new boolean[N];
		int[] output = new int[M];
		int cnt = 0;
		doubleComb(input, output, cnt, 0);
		System.out.print(sb);
	}
}