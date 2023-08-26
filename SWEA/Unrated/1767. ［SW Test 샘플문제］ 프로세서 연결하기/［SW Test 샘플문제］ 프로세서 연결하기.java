import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N;
	static int[][] map;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static List<int[]> list;
	static int core;
	static int maxCore;
	static int minLength;
	static int length;
	
	static void dfs(int cnt) {
		if (cnt == list.size()) {
			if (core > maxCore) {
				maxCore = core;
				minLength = length;
			}
			else if (core == maxCore) {
				if (minLength > length) minLength = length;
			}
			return;
		}
		
//		가지치기
		if (maxCore > (core + list.size() - cnt)) return;
		
		int oRow = list.get(cnt)[0];
		int oCol = list.get(cnt)[1];
		
		for (int i = 0; i < 4; i++) {
			int row = oRow + dRow[i];
			int col = oCol + dCol[i];
			boolean flag = true;
//			만약 인덱스를 벗어나기 전에 0이 아닌 점을 만났다면 전선을 깔 수 없다는 것임
			while (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] != 0) {
					flag = false;
					break;
				}
				row += dRow[i];
				col += dCol[i];
			}
//			전선을 깔 수 있다면 설치
			if (flag) {
				core++;
				row = oRow + dRow[i];
				col = oCol + dCol[i];
				while (row >= 0 && row < N && col >= 0 && col < N) {
					map[row][col] = -1;
					length++;
 					row += dRow[i];
					col += dCol[i];
				}
			}
//			다음 코어 설치하러 넘어감
			dfs(cnt + 1);
//			원상복구
			if (flag) {
				core--;
				row = oRow + dRow[i];
				col = oCol + dCol[i];
				while (row >= 0 && row < N && col >= 0 && col < N) {
					map[row][col] = 0;
					length--;
					row += dRow[i];
					col += dCol[i];
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		String[] srr;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			core = 0;
			maxCore = Integer.MIN_VALUE;
			length = 0;
			minLength = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				srr = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(srr[j]);
					if (i == 0 || i == (N - 1) || j == 0 || j == (N - 1)) continue;
					if (map[i][j] == 1) list.add(new int[] {i, j});
				}
			}
			dfs(0);
			sb.append("#").append(t).append(" ").append(minLength).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}