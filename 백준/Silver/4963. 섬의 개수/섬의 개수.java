import java.io.*;
import java.util.*;

public class Main {
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	static int[] dRow = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dCol = new int[] {1, 1, 0, -1, -1, -1, 0, 1};
	static int cnt;
	
	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 0) continue;
				if (visited[i][j]) continue;
				queue.offer(new int[] {i, j});
				int[] poll;
				int pRow = 0;
				int pCol = 0;
				while (!queue.isEmpty()) {
					poll = queue.poll();
					pRow = poll[0];
					pCol = poll[1];
					for (int k = 0; k < 8; k++) {
						int row = pRow + dRow[k];
						int col = pCol + dCol[k];
						if (row >= 0 && row < h && col >= 0 && col < w) {
							if (!visited[row][col] && map[row][col] == 1) {
								visited[row][col] = true;
								queue.offer(new int[] {row, col});
							}
						}
					}
				}
				cnt++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) break;
			map = new int[h][w];
			visited = new boolean[h][w];
			cnt = 0;
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			sb.append(cnt).append("\n");
		}
		
		if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}