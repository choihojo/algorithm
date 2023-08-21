import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	static int cnt;
	
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	
	static void bfs() {
		cnt = 0;
		Queue<Cell> queue = new ArrayDeque<>();
		List<Cell> input = new ArrayList<>();
		
		for (int i = K; i < K + N; i++) {
			for (int j = K; j < K + M; j++) {
				if (map[i][j] > 0) {
					visited[i][j] = true;
					input.add(new Cell(i, j, map[i][j], map[i][j], map[i][j]));
				}
			}
		}
		
		Collections.sort(input);
		
		for (int i = 0; i < input.size(); i++) {
			queue.offer(input.get(i));
		}
		
//		배양 시간동안 큐 반복
		for (int time = 0; time < K; time++) {
			int size = queue.size();
			
			for (int s = 0; s < size; s++) {
				Cell poll = queue.poll();
				int pRow = poll.row;
				int pCol = poll.col;
				
				if (poll.active == 0) {
					if (poll.life > 0) {
						for (int i = 0; i < 4; i++) {
							int row = pRow + dRow[i];
							int col = pCol + dCol[i];
							
							if (row >= 0 && row < (N + 2 * K) && col >= 0 && col < (M + 2 * K)) {
								if (!visited[row][col]) {
									visited[row][col] = true;
									queue.offer(new Cell(row, col, poll.power, poll.power, poll.power));
									map[row][col] = 1;
								}
							}
							else {
								System.out.println("Debug");
							}
						}
						if (poll.life == 1) {
							map[pRow][pCol] = -1;
						}
						else {
							queue.offer(new Cell(pRow, pCol, poll.power, poll.active, poll.life - 1));	
						}
					}
				}
				else {
					queue.offer(new Cell(pRow, pCol, poll.power, poll.active - 1, poll.life));
				}
			}
		}
		
		for (int i = 0; i < N + 2 * K; i++) {
			for (int j = 0; j < M + 2 * K; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) cnt++;
			}
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		String[] srr;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			srr = br.readLine().split(" ");
			N = Integer.parseInt(srr[0]);
			M = Integer.parseInt(srr[1]);
			K = Integer.parseInt(srr[2]);
			map = new int[N + 2 * K][M + 2 * K];
			visited = new boolean[N + 2 * K][M + 2 * K];
			
			for (int i = K; i < K + N; i++) {
				srr = br.readLine().split(" ");
				for (int j = K; j < K + M; j++) {
					map[i][j] = Integer.parseInt(srr[j - K]);
				}
			}
			bfs();
//			for (int[] m : map) {
//				System.out.println(Arrays.toString(m));
//			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);

	}
}

class Cell implements Comparable<Cell> {
	public int row;
	public int col;
	public int power;
	public int active;
	public int life;
	
	public Cell (int row, int col, int power, int active, int life) {
		this.row = row;
		this.col = col;
		this.power = power;
		this.active = active;
		this.life = life;
	}

	@Override
	public int compareTo(Cell o) {
//		같은 활성화 기간 중에서는 생명력이 더 높은 세포가 앞에 오도록 내림차순으로 정렬
		return o.power - this.power;
	}
}
