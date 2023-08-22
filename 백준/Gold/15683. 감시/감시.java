import java.io.*;
import java.util.*;

// CCTV의 정보가 주어졌을 때, CCTV의 방향을 조절해서 사각지대 최소크기
// CCTV는 벽을 통과할 수 없고 다른 CCTV는 통과할 수 있음
// 사무실의 크기 N*M (1이상 8 이하)
// CCTV의 최대 개수는 8개를 넘지 않음

// 1번은 0, 1, 2, 3 인덱스 중 하나
// 2번은 (0, 2), (1, 3) 인덱스 중 하나
// 3번은 (0, 1), (1, 2), (2, 3), (3, 4) 인덱스 중 하나
// 4번은 (0, 1, 2), (1, 2, 3), (2, 3, 0), (3, 0, 1) 인덱스 중 하나
// 5번은 항상 (0, 1, 2, 3) 인덱스
// 어차피 최대 8개이므로 5번을 제외한 개수만큼 중복순열로 뽑으면 될 듯
// 4^8 = 2^16 = 2^6 * 1024 = 640000 -> 그냥 5번까지 같이 뽑아도 시간 될 듯?

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][] tempMap;
	static List<Camera> cctv;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	
	static int[] input = new int[] {0, 1, 2, 3};
	static int[] output;
	static int cnt;
	static int min = Integer.MAX_VALUE;
	
	static int[][] no1 = new int[][] {{0}, {1}, {2}, {3}};
	static int[][] no2 = new int[][] {{0, 2}, {1, 3}, {0, 2}, {1, 3}};
	static int[][] no3 = new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
	static int[][] no4 = new int[][] {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}};
	static int[][] no5 = new int[][] {{0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}};
	
	static void doublePermutation(int cnt) {
		if (cnt == cctv.size()) {
			calculate();
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			output[cnt] = input[i];
			doublePermutation(cnt + 1);
		}
	}
	
	static void calculate() {
		cnt = 0;
		
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				tempMap[n][m] = map[n][m];
			}
		}
		
//		i번째 cctv의 회전 타입은 output의 i번째 인덱스 성분에 의해 결정됨
		for (int i = 0; i < cctv.size(); i++) {
			Camera camera = cctv.get(i);
			int oRow = camera.row;
			int oCol = camera.col;
			
			if (camera.type == 1) {
				int[] irr = no1[output[i]];
				for (int j = 0; j < irr.length; j++) {
					int row = oRow + dRow[irr[j]];
					int col = oCol + dCol[irr[j]];
					while (row >= 0 && row < N && col >= 0 && col < M) {
						if (tempMap[row][col] == 6) {
							break;
						}
						else if (tempMap[row][col] == 0) {
							tempMap[row][col] = -1;
						}
						row += dRow[irr[j]];
						col += dCol[irr[j]];
					}
				}
			}
			else if (camera.type == 2) {
				int[] irr = no2[output[i]];
				for (int j = 0; j < irr.length; j++) {
					int row = oRow + dRow[irr[j]];
					int col = oCol + dCol[irr[j]];
					while (row >= 0 && row < N && col >= 0 && col < M) {
						if (tempMap[row][col] == 6) {
							break;
						}
						else if (tempMap[row][col] == 0) {
							tempMap[row][col] = -1;
						}
						row += dRow[irr[j]];
						col += dCol[irr[j]];
					}
				}
			}
			else if (camera.type == 3) {
				int[] irr = no3[output[i]];
				for (int j = 0; j < irr.length; j++) {
					int row = oRow + dRow[irr[j]];
					int col = oCol + dCol[irr[j]];
					while (row >= 0 && row < N && col >= 0 && col < M) {
						if (tempMap[row][col] == 6) {
							break;
						}
						else if (tempMap[row][col] == 0) {
							tempMap[row][col] = -1;
						}
						row += dRow[irr[j]];
						col += dCol[irr[j]];
					}
				}
			}
			else if (camera.type == 4) {
				int[] irr = no4[output[i]];
				for (int j = 0; j < irr.length; j++) {
					int row = oRow + dRow[irr[j]];
					int col = oCol + dCol[irr[j]];
					while (row >= 0 && row < N && col >= 0 && col < M) {
						if (tempMap[row][col] == 6) {
							break;
						}
						else if (tempMap[row][col] == 0) {
							tempMap[row][col] = -1;
						}
						row += dRow[irr[j]];
						col += dCol[irr[j]];
					}
				}
			}
			else if (camera.type == 5) {
				int[] irr = no5[0];
				for (int j = 0; j < irr.length; j++) {
					int row = oRow + dRow[irr[j]];
					int col = oCol + dCol[irr[j]];
					while (row >= 0 && row < N && col >= 0 && col < M) {
						if (tempMap[row][col] == 6) {
							break;
						}
						else if (tempMap[row][col] == 0) {
							tempMap[row][col] = -1;
						}
						row += dRow[irr[j]];
						col += dCol[irr[j]];
					}
				}
			}
		}
		
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (tempMap[n][m] == 0) cnt++;
			}
		}
		
		if (cnt < min) {
			min = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		map = new int[N][M];
		tempMap = new int[N][M];
		
		cctv = new ArrayList<>();
		
		for (int n = 0; n < N; n++) {
			srr = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(srr[m]);
				if (map[n][m] >= 1 && map[n][m] <= 5) cctv.add(new Camera(n, m, map[n][m]));
			}
		}
		
		output = new int[cctv.size()];
		
		doublePermutation(0);
		
		System.out.println(min);
	}
}

class Camera {
	int row;
	int col;
	int type;
	
	public Camera (int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}
}