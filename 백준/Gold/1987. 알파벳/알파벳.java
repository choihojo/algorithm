import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static char[][] map;
	static int R, C;
	static boolean[] visited;
	static int max;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	
	static void dfs(int oRow, int oCol, int cnt) {
		if (oRow < 0 || oRow >= R || oCol < 0 || oCol >= C || visited[map[oRow][oCol]]) {
			if (max < cnt) {
				max = cnt;
			}
			return;
		}
		visited[map[oRow][oCol]] = true;
		for (int i = 0; i < 4; i++) {
			int row = oRow + dRow[i];
			int col = oCol + dCol[i];
			dfs(row, col, cnt + 1);
		}
		visited[map[oRow][oCol]] = false;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		
		/*
		 * A (65) ~ Z (90)
		 */
		visited = new boolean[91];
		dfs(0, 0, 0);
		System.out.println(max);
	}
}