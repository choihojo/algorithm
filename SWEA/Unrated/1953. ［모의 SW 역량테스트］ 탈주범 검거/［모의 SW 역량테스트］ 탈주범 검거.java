import java.io.*;
import java.util.*;

// 탈주범은 탈출한 시 1시간 뒤에 들어감
// 시간당 1의 거리 움직일 수 있음
// 3시간이 경과했을 경우 t = 1일 때 어딘가에 위치, t = 2일 때 1칸 이동, t = 3일 때 한 칸 이동 이렇게 함
// 위와 같은 경우 최대 처음 위치로부터 2만큼 떨어질 수 있음
// 터널 인덱스는 0부터 시작함

// 가려고 하는 방향과 연결될 수 있는 터널 관계
// 우 : 1, 3, 6, 7
// 상 : 1, 2, 5, 6
// 좌 : 1, 3, 4, 5
// 하 : 1, 2, 4, 7

// 탈주범의 현재 위치
// 터널 1 : 우(0)상(1)좌(2)하(3) 이동 가능
// 터널 2 : 상(1)하(3) 이동 가능
// 터널 3 : 우(0)좌(2) 이동 가능
// 터널 4 : 우(0)상(1) 이동 가능
// 터널 5 : 우(0)하(3) 이동 가능
// 터널 6 : 좌(2)하(3) 이동 가능
// 터널 7 : 상(1)좌(2) 이동 가능

public class Solution {
	static int[] dRow = {0, -1, 0, 1};
	static int[] dCol = {1, 0, -1, 0};
	static int T;
//	전체 맵 크기 (N, M)
//	탈주범 처음 위치(R, C)
//	경과 시간 (L)
	static int N, M, R, C, L;
	static String[][] map;
	static boolean[][] visited;
	static int cnt;
//	각 터널 타입이 가질 수 있는 탐색 방향 (우상좌하 인덱스)
	static int[][] direction = new int[][]
			{{0}, // 인덱싱용
			{0, 1, 2, 3}, // 터널 1
			{1, 3}, // 터널 2
			{0, 2}, // 터널 3
			{0, 1}, // 터널 4
			{0, 3}, // 터널 5
			{2, 3}, // 터널 6
			{1, 2}}; // 터널 7
//	각각 우, 상, 좌, 하로 연결된다고 했을 때 올 수 있는 터널의 타입
	static String[] check = new String[] {"1367", "1256", "1345", "1247"};
	
	static void bfs() {
		cnt = 0;
		Queue<Data> queue = new ArrayDeque<>();
		queue.offer(new Data(R, C, Integer.parseInt(map[R][C])));
		visited[R][C] = true;
		cnt++;
		
//		depth 측정용 변수
		int size;
//		방향 탐색용 변수
		int row;
		int col;
		Data poll;
//		poll한 Data의 프로퍼티를 저장할 변수
		int pRow;
		int pCol;
		int pType;
//		아마 1시간 후에 탈주범이 시작하기 때문에 L만큼이 아니라 (L - 1)만큼 반복해야 함
		
		for (int l = 1; l < L; l++) {
			size = queue.size();
			for (int s = 0; s < size; s++) {
				poll = queue.poll();
				pRow = poll.row;
				pCol = poll.col;
				pType = poll.type;
				
//				poll한 터널의 타입에 따라 탐색해야될 방향은 정해져 있음
//				따라서 direction 배열을 poll한 것의 타입(pType)으로 인덱싱하고 그만큼 반복문을 수행함
//				예를 들어 터널 3의 경우 우(0), 좌(2)를 탐색해야 하고 이게 direction[3]에 저장되어있는 것을 볼 수 있음
				for (int i = 0; i < direction[pType].length; i++) {
					row = pRow + dRow[direction[pType][i]];
					col = pCol + dCol[direction[pType][i]];
					if (row >= 0 && row < N && col >= 0 && col < M) {
//						만약 방문한 적이 없고 map[row][col]이 해당 방향에 올 수 있는 터널 중 하나라면 방문체크 + 큐에 삽입 + 카운팅
//						예를 들어 터널 3의 경우로 우(0)에 대한 탐색을 한다 했을 때 check[0]인 "1367"에 터널의 타입(map[row][col])이 포함된다면 가야되는 곳이라고 판단하는 것임
						if (!visited[row][col] && check[direction[pType][i]].contains(map[row][col])) {
							visited[row][col] = true;
							queue.offer(new Data(row, col, Integer.parseInt(map[row][col])));
							cnt++;
						}
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			String[] srr = br.readLine().split(" ");
			N = Integer.parseInt(srr[0]);
			M = Integer.parseInt(srr[1]);
			R = Integer.parseInt(srr[2]);
			C = Integer.parseInt(srr[3]);
			L = Integer.parseInt(srr[4]);
			
			map = new String[N][M];
			visited = new boolean[N][M];
			for (int n = 0; n < N; n++) {
				map[n] = br.readLine().split(" ");
			}
			
			bfs();
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}

// 터널 클래스 Data
class Data {
	int row;
	int col;
	int type;
	public Data(int row, int col, int type) {
		super();
		this.row = row;
		this.col = col;
		this.type = type;
	}
}