import java.io.*;
public class Solution {

	public static void snail(int[][] map, int N, int flag, int start, int num) {
		if (num == (N * N)) {
			return;
		}
		if (flag % 4 == 0) {
			for (int i = 0; i < N; i++) {
				if (map[start / 4][i] == 0) {
					num++;
					map[start / 4][i] = num;
				}
			}
			snail(map, N, flag + 1, start + 1, num);
		}
		else if (flag % 4 == 1) {
			for (int i = 0; i < N; i++) {
				if (map[i][N - start / 4 - 1] == 0) {
					num++;
					map[i][N - start / 4 - 1] = num;
				}
			}
			snail(map, N, flag + 1, start + 1, num);
			
		}
		else if (flag % 4 == 2) {
			for (int i = 0; i < N; i++) {
				if (map[N - start / 4 - 1][N - i - 1] == 0) {
					num++;
					map[N - start / 4 - 1][N - i - 1] = num;
				}
			}
			snail(map, N, flag + 1, start + 1, num);
		}
		else if (flag % 4 == 3) {
			for (int i = 0; i < N; i++) {
				if (map[N - i - 1][start / 4] == 0) {
					num++;
					map[N - i - 1][start / 4] = num;
				}
			}
			snail(map, N, flag + 1, start + 1, num);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		int start = 0;
		int num = 0;
		int flag = 0;
		for (int t = 0; t < T; t++) {
			start = 0;
			num = 0;
			flag = 0;
			N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			snail(map, N, flag, start, num);
			System.out.println("#" + (t + 1));
			for (int[] a : map) {
				for (int b : a) {
					System.out.print(b + " ");
				}
				System.out.println("");
			}
		}
	}
}