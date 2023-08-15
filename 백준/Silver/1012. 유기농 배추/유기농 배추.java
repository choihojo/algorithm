import java.io.*;
import java.util.*;

// 시간 1초인 게 싸해서 bfs로 풀어봄
// 한마디로 영역 찾는 문제인데 방문체크 이용하지 않고 true를 false로 바꾸는 걸로 대체해볼 예정
// 이 문제도 연결리스트로 푸는 건 힘들어보임 (주어지는 게 연결 정보가 아니라 좌표기 때문)
// (1, 2), (2, 3) 이렇게 주어진다고 해서 1 -> 2 -> 3이 가능한 게 아니기 때문임

public class Main {
	static BufferedReader br;
	static int T;
	static int M;
	static int N;
	static int K;
	static String[] srr;
	static boolean[][] map;
	static int X;
	static int Y;
	static StringBuilder sb;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static int cnt;
	
	public static void bfsByQueue(int[] start) {
		map[start[0]][start[1]] = false;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(start);
		int[] poll;
		
//		4방위 중 하나로 한 칸 이동한 변수
		int row;
		int col;
//		poll한 요소의 row와 col
		int pRow;
		int pCol;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pRow = poll[0];
			pCol = poll[1];
			
			for (int i = 0; i < 4; i++) {
				row = pRow + dRow[i];
				col = pCol + dCol[i];
				if (row >= 0 && row < M && col >= 0 && col < N) {
					if (map[row][col]) {
//						한번 방문한 곳은 false로 변경
						map[row][col] = false;
						queue.offer(new int[] {row, col});
					}
				}
			}
		}	
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
//			매 테스트케이스마다 cnt 초기화
			cnt = 0;
			srr = br.readLine().split(" ");
			M = Integer.parseInt(srr[0]);
			N = Integer.parseInt(srr[1]);
			K = Integer.parseInt(srr[2]);
			map = new boolean[M][N];
			for (int k = 0; k < K; k++) {
				srr = br.readLine().split(" ");
				X = Integer.parseInt(srr[0]);
				Y = Integer.parseInt(srr[1]);
				map[X][Y] = true;
			}
			for (int m = 0; m < M; m++) {
				for (int n = 0; n < N; n++) {
					if (map[m][n]) {
						cnt++;
						bfsByQueue(new int[] {m, n});
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}