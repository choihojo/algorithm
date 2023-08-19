import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D;
	static int[][] map;
	static int[][] tempMap;
	static int[] input;
	static int[][] output;
	static int[] dRow = new int[] {0, -1, 0};
	static int[] dCol = new int[] {-1, 0, 1};
	static boolean[][] visited;
	static boolean[][] attack;
	static int max;
	
	static void combination(int cnt, int start) {
//		궁수 3명을 뽑았으면 defenceByBfs 메서드 호출
		if (cnt == 3) {
			defenceByBfs();
			return;
		}
//		output(궁수의 좌표) 중 1번째(행) 요소를 뽑음
		for (int i = start; i < input.length; i++) {
			output[cnt][1] = input[i];
			combination(cnt + 1, i + 1);
		}
	}
	
	static void defenceByBfs() {
//		조합 경우의 수에 따라 tempMap, attack 초기화
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				tempMap[n][m] = map[n][m];
				attack[n][m] = false;
			}
		}
		
//		궁수의 위치를 중심으로 bfs
		Queue<int[]> queue = new ArrayDeque<>();

		int[] poll;
		int pRow = 0;
		int pCol = 0;
		int row = 0;
		int col = 0;
//		적이 내려온다고 생각하지 않고 궁수가 올라간다고 생각하기로 함
//		적이 내려온다고 생각하려면 배열을 매번 수정해줘야 함
//		근데 반대로 생각하면 t를 이용하여 궁수 좌표만 올리면 됨
		int t = 0;
		int d = 0;
		int cnt = 0;
		int size = 0;
		
		for (t = 0; t < N; t++) {
//			조합에 나올 궁수의 위치 중 행의 좌표는 미리 N으로 초기화
			for (int j = 0; j < 3; j++) {
				output[j][0] = N - t;
			}
			
			for (int i = 0; i < 3; i++) {
				queue.offer(output[i]);
				d = 1;
				A : while (d <= D) {
					size = queue.size();
					for (int s = 0; s < size; s++) {
						poll = queue.poll();
						pRow = poll[0];
						pCol = poll[1];
						for (int j = 0; j < 3; j++) {
							row = pRow + dRow[j];
							col = pCol + dCol[j];
							if (row >= 0 && row < (N - t) && col >= 0 && col < M) {
								if (!visited[row][col]) {
									visited[row][col] = true;
									queue.offer(new int[] {row, col});
									if (tempMap[row][col] == 1) {
										attack[row][col] = true;
										break A;
									}
								}
							}
						}
					}
					d++;
				}
				queue.clear();
//				visited 초기화
				for (int n = 0; n < N; n++) {
					for (int m = 0; m < M; m++) {
						visited[n][m] = false;
					}
				}
			}
			
			for (int n = 0; n < (N - t) ; n++) {
				for (int m = 0; m < M; m++) {
					if (attack[n][m] && tempMap[n][m] == 1) {
						tempMap[n][m] = 0;
						cnt++;
					}
				}
			}
		}
		
		if (cnt > max) {
			max = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		D = Integer.parseInt(srr[2]);
		map = new int[N][M];
		tempMap = new int[N][M];
		visited = new boolean[N][M];
		attack = new boolean[N][M];
		
		for (int n = 0; n < N; n++) {
			srr = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(srr[m]);
			}
		}
		
		input = new int[M];
		output = new int[3][2];
		for (int m = 0; m < M; m++) {
			input[m] = m;
		}
		
		combination(0, 0);
		System.out.println(max);
	}
}