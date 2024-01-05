import java.util.*;
import java.io.*;



public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] map;
	static int N;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static boolean[][] visitedA;
	static boolean[][] visitedB;
	static int A, B;
	static int countA, countB;
	
	static void bfsA() {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visitedA[i][j]) continue;
				
				queue.offer(new int[] {i, j});
				visitedA[i][j] = true;
				
				int[] poll = null;
				int pRow = 0;
				int pCol = 0;
				int row = 0;
				int col = 0;
				
				while (!queue.isEmpty()) {
					poll = queue.poll();
					pRow = poll[0];
					pCol = poll[1];
					
					for (int k = 0; k < 4; k++) {
						row = pRow + dRow[k];
						col = pCol + dCol[k];
						if (row >= 0 && row < N && col >= 0 && col < N) {
							if (visitedA[row][col]) continue;
							if (map[pRow][pCol] == map[row][col]) {
								queue.offer(new int[] {row, col});
								visitedA[row][col] = true;
							}
						}
					}
				}
				countA++;
			}
		}
	}
	
	static void bfsB() {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visitedB[i][j]) continue;
				
				queue.offer(new int[] {i, j});
				visitedB[i][j] = true;
				
				int[] poll = null;
				int pRow = 0;
				int pCol = 0;
				int row = 0;
				int col = 0;
				
				while (!queue.isEmpty()) {
					poll = queue.poll();
					pRow = poll[0];
					pCol = poll[1];
					boolean flag = false;
					
					if (map[pRow][pCol] == 2) {
						flag = true;
					}
					
					if (flag) {
						for (int k = 0; k < 4; k++) {
							row = pRow + dRow[k];
							col = pCol + dCol[k];
							if (row >= 0 && row < N && col >= 0 && col < N) {
								if (visitedB[row][col]) continue;
								if (map[row][col] == 2) {
									queue.offer(new int[] {row, col});
									visitedB[row][col] = true;
								}
							}
						}
					}
					else {
						for (int k = 0; k < 4; k++) {
							row = pRow + dRow[k];
							col = pCol + dCol[k];
							if (row >= 0 && row < N && col >= 0 && col < N) {
								if (visitedB[row][col]) continue;
								if (map[row][col] == 1 || map[row][col] == 0) {
									queue.offer(new int[] {row, col});
									visitedB[row][col] = true;
								}
							}
						}
					}
				}
				countB++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visitedA = new boolean[N][N];
		visitedB = new boolean[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int i = 0; i < N; i++) {
				if ("R".equals(String.valueOf(str.charAt(i)))) {
					map[n][i] = 0;
				}
				else if ("G".equals(String.valueOf(str.charAt(i)))) {
					map[n][i] = 1;
				}
				else {
					map[n][i] = 2;
				}
			}
		}
		bfsA();
		bfsB();
		System.out.printf("%d %d", countA, countB);
	}
}