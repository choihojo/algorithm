//	우선 주어지는 형태가 인접배열이 아니라 사실상 그래프임 (노드의 6방향으로 이어져있다고 보면 됨)
//	그냥 bfs 최단경로 알고리즘으로 똑같이 풀릴 듯
//	다만 3차원이 [z][x][y]라는 점(면, 행, 열 순)은 유의해야함

import java.io.*;
import java.util.*;

public class Main {
//	별도로 방문체크 배열을 만들지 않고 바로 토마토 값을 최단경로 계산 알고리즘을 이용해 갱신시킬 예정
	static int[][][] box;
//	면
	static int H;
//	행
	static int N;
//	열
	static int M;
//	입력으로 넣을 3차원 좌표들의 리스트
	static List<int[]> list;
//	6방향 탐색에 쓸 면, 행, 열 방향좌표
//	극좌표 순서로 2차원(우, 상, 좌, 하) 돌리고 위, 아래 순으로 적었음
	static int[] dHgt = new int[] {0, 0, 0, 0, 1, -1};
	static int[] dRow = new int[] {0, -1, 0, 1, 0, 0};
	static int[] dCol = new int[] {1, 0, -1, 0, 0, 0};
//	최댓값(익는 데 걸린 시간) 변수
	static int day = 0;
//	토마토가 다 익었는지 확인하는 변수
	static boolean flag;
	
	
	static void bfsByQueue() {
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int[] e : list) {
			queue.offer(e);
		}
		int[] poll = new int[3];
//		poll한 노드의 면, 행, 열 요소
		int pHgt = 0;
		int pRow = 0;
		int pCol = 0;
		
//		6방향 탐색에 쓸 변화 후의 면, 행, 열 요소
		int Hgt = 0;
		int Row = 0;
		int Col = 0;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pHgt = poll[0];
			pRow = poll[1];
			pCol = poll[2];
			
			for (int i = 0; i < 6; i++) {
				Hgt = pHgt + dHgt[i];
				Row = pRow + dRow[i];
				Col = pCol + dCol[i];
				
//				인덱스 초과 방지
				if (Hgt >= 0 && Hgt < H && Row >= 0 && Row < N && Col >= 0 && Col < M) {
//					만약 익어있지 않았다면 그걸 큐에 넣고(이제 익은 토마토가 되어 다음번에 다른 토마토를 익게 해야함) 뽑은 노드의 숫자에 1을 더함(날짜 더하는 거임)
					if (box[Hgt][Row][Col] == 0) {
						queue.offer(new int[] {Hgt, Row, Col});
						box[Hgt][Row][Col] = box[pHgt][pRow][pCol] + 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		M, N, H 순으로 입력 들어옴 -> [H][N][M]로 배열 만들어야함
		String[] srr = br.readLine().split(" ");
		M = Integer.parseInt(srr[0]);
		N = Integer.parseInt(srr[1]);
		H = Integer.parseInt(srr[2]);
		box = new int[H][N][M];
		
		list = new ArrayList<>();
		
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				srr = br.readLine().split(" ");
				for (int m = 0; m < M; m++) {
					box[h][n][m] = Integer.parseInt(srr[m]);
					if (box[h][n][m] == 1) {
						list.add(new int[] {h, n, m});
					}
				}
			}
		}
		
		bfsByQueue();
		
		flag = true;
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (box[h][n][m] == 0) {
						flag = false;
					}
					if (box[h][n][m] > day) {
						day = box[h][n][m];
					}
				}
			}
		}
		
//		토마토가 하나 이상 있는 경우만 입력으로 주어지므로 -1만 입력으로 들어오는 경우는 고려하지 않아도 됨
//		모두 익어있었음
		if (flag) {
//			시작부터 익은 토마토는 1이었으므로 -1을 해줘야 함
			System.out.println(day - 1);
		}
		else {
			System.out.println(-1);
		}
	}
}