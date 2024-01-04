import java.util.*;
import java.io.*;

/*
 * N : 세로 크기 (row)
 * M : 가로 크기 (col)
 * 
 * 0 : 빈 칸
 * 1 : 벽
 * 2 : 바이러스
 * 
 * -> 벽을 3개 세우고 안전지역의 최대 크기?
*/

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	
	static void dfs(int count) {
		if (count == 3) {
			int temp = calculate(bfs());
			if (max < temp) {
				max = temp;
			}
			return;
		}
		
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] == 0) {
					map[n][m] = 1;
					dfs(count + 1);
					map[n][m] = 0;
				}
			}
		}
	}
	
	static int[][] bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		
		int[][] clone = new int[N][M];
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				clone[n][m] = map[n][m];
				if (clone[n][m] == 2) {
					queue.offer(new int[] {n, m});
				}
			}
		}
		
		int[] poll = null;
		int pRow = 0;
		int pCol = 0;
		int row = 0;
		int col = 0;
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pRow = poll[0];
			pCol = poll[1];
			
			for (int i = 0; i < 4; i++) {
				row = pRow + dRow[i];
				col = pCol + dCol[i];
				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (clone[row][col] == 0) {
						clone[row][col] = 2;
						queue.offer(new int[] {row, col});
					}
				}
			}
		}
		
		return clone;
	}
	
	static int calculate(int[][] clone) {
		int size = 0;
		
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (clone[n][m] == 0) {
					size++;
				}
			}
		}
		
		return size;
	}
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.println(max);
	}

}
