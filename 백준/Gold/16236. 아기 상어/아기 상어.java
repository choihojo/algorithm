import java.io.*;
import java.util.*;

// N * N 공간에 물고기 M마리 아기 상어 1마리 존재함
// 아기 상어의 크기는 2 -> 1초에 상하좌우로 한 칸씩 이동함
// 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없음 (같은 물고기는 가능)
// 먹는 건 자신보다 작은 물고기만 가능함 (같은 것도 불가능)

// 먹을 수 있는 물고기가 여러 마리라면 가장 가까운 물고기를 먹음
// 동일한 거리에서는 가장 위에 있는 물고기를 먹음
// 동일한 거리와 동일한 높이에서는 가장 왼쪽에 있는 물고기를 먹음

// 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가함
// 아기 상어가 먹을 수 있는 물고기가 존재하지 않으면 끝남
// 아기 상어는 몇 초 동안 물고기를 잡아먹을 수 있는가?

// 0 : 빈 칸
// 1 ~ 6 : 물고기의 크기
// 9 : 아기 상어의 위치 (처음 아기 상어의 크기는 2임을 명심할 것)

public class Main {
	static int N;
	static int[][] map;
	static Data start;
	static int time;
	static boolean[][] attacked;
	static boolean[][] visited;
//	상(0), 좌(1), 우(2), 하(3) 순서임
	static int[] dRow = new int[] {-1, 0, 0, 1};
	static int[] dCol = new int[] {0, -1, 1, 0};
	static int temp;
	
	static void bfs() {
		Queue<Data> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start.row][start.col] = true;
		map[start.row][start.col] = 0; 
		
		int size = 0;
		Data poll = null;
		int pRow = 0;
		int pCol = 0;
		int pLevel = 0;
		int pExp = 0;
		int row = 0;
		int col = 0;
		boolean flag = false;
		
		while (check(queue.peek().level)) {
			size = queue.size();
			flag = false;
			for (int s = 0; s < size; s++) {
				poll = queue.poll();
				pRow = poll.row;
				pCol = poll.col;
				pLevel = poll.level;
				pExp = poll.exp;
				
				for (int i = 0; i < 4; i++) {
					row = pRow + dRow[i];
					col = pCol + dCol[i];
					if (row >= 0 && row < N && col >= 0 && col < N) {
						if (!visited[row][col]) {
							if (map[row][col] > 0 && map[row][col] < pLevel) {
								attacked[row][col] = true;
								visited[row][col] = true;
								flag = true;
							}
							else if (map[row][col] == 0 || map[row][col] == pLevel) {
								queue.offer(new Data(row, col, pExp, pLevel));
								visited[row][col] = true;
							}
						}
					}
				}
			}
			
			if (flag) {
				outer : for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (attacked[i][j]) {
							row = i;
							col = j;
							break outer;
						}
					}
				}
				map[row][col] = 0;
				queue.clear();
				pExp++;
				if (pExp == pLevel) {
					pExp = 0;
					pLevel++;
				}
				queue.offer(new Data(row, col, pExp, pLevel));
				reset();
				visited[row][col] = true;
				temp++;
				time += temp;
				temp = 0;
			}
			else temp++;
			if (queue.isEmpty()) break;
		}
	}
	
	static void reset() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				attacked[i][j] = false;
				visited[i][j] = false;
			}
		}
	}
	
	static boolean check(int level) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0 && map[i][j] < level) return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] srr;
		map = new int[N][N];
		attacked = new boolean[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(srr[j]);
				if (map[i][j] == 9) {
					start = new Data(i, j, 0, 2);
				}
			}
		}
		bfs();
		System.out.println(time);
	}
}

class Data {
	int row;
	int col;
	int exp;
	int level;
	public Data(int row, int col, int exp, int level) {
		super();
		this.row = row;
		this.col = col;
		this.exp = exp;
		this.level = level;
	}
}